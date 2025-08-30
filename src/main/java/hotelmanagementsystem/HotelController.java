package hotelmanagementsystem;

import hotelmanagementsystem.*;
import hotelmanagementsystem.enums.RoomStyle;
import hotelmanagementsystem.enums.RoomType;
import hotelmanagementsystem.factory.RoomFactory;
import hotelmanagementsystem.model.Booking;
import hotelmanagementsystem.model.Guest;
import hotelmanagementsystem.model.Room;
import hotelmanagementsystem.observer.EmailNotifier;
import hotelmanagementsystem.observer.SmsNotifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final PaymentService paymentService;
    private final HotelManagerFacade hotelManager;

    public HotelController() {
        // Setup Services
        this.roomService = new RoomService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();

        // Register observers
        bookingService.addObserver(new EmailNotifier());
        bookingService.addObserver(new SmsNotifier());

        // Facade
        this.hotelManager = new HotelManagerFacade(roomService, bookingService, paymentService);

        // Populate hotel with demo rooms (could be moved to DB later)
        roomService.addRoom(RoomFactory.createRoom("101", "SINGLE", "STANDARD", 100));
        roomService.addRoom(RoomFactory.createRoom("102", "SINGLE", "DELUXE", 120));
        roomService.addRoom(RoomFactory.createRoom("201", "DOUBLE", "STANDARD", 150));
        roomService.addRoom(RoomFactory.createRoom("202", "DOUBLE", "DELUXE", 180));
        roomService.addRoom(RoomFactory.createRoom("301", "SUITE", "OCEAN_VIEW", 300));
    }

    // ----------------- ROOMS -----------------

    @GetMapping("/rooms/{roomNumber}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomNumber) {
        Room room = roomService.findRoomByNumber(roomNumber);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ----------------- BOOKINGS -----------------

    @PostMapping("/bookings")
    public ResponseEntity<Booking> bookRoom(@RequestParam String guestId,
                                            @RequestParam String guestName,
                                            @RequestParam String guestEmail,
                                            @RequestParam RoomType type,
                                            @RequestParam RoomStyle style,
                                            @RequestParam String startDate,
                                            @RequestParam String endDate,
                                            @RequestParam(required = false) List<String> amenities) {
        Guest guest = new Guest(guestId, guestName, guestEmail);
        Booking booking = hotelManager.bookRoom(
                guest,
                type,
                style,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                amenities != null ? amenities : List.of()
        );

        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/checkin/{bookingId}")
    public ResponseEntity<String> checkIn(@PathVariable String bookingId) {
        hotelManager.checkIn(bookingId);
        return ResponseEntity.ok("Check-in process triggered for booking: " + bookingId);
    }

    @PostMapping("/checkout/{roomNumber}")
    public ResponseEntity<String> checkOut(@PathVariable String roomNumber) {
        hotelManager.checkOut(roomNumber);
        return ResponseEntity.ok("Checked out from room: " + roomNumber);
    }

    // ----------------- MAINTENANCE -----------------

    @PostMapping("/rooms/{roomNumber}/maintenance")
    public ResponseEntity<String> markRoomForMaintenance(@PathVariable String roomNumber) {
        Room room = roomService.findRoomByNumber(roomNumber);
        if (room != null) {
            room.markForMaintenance();
            return ResponseEntity.ok("Room " + roomNumber + " marked for maintenance.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

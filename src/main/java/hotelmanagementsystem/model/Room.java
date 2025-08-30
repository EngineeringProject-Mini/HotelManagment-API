package hotelmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hotelmanagementsystem.enums.RoomStyle;
import hotelmanagementsystem.enums.RoomType;
import hotelmanagementsystem.state.AvailableState;
import hotelmanagementsystem.state.RoomState;

public class Room {
    public String roomNumber;
    public RoomType type;
    public RoomStyle style;
    public double price;

    @JsonIgnore
    private RoomState state;

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setStyle(RoomStyle style) {
        this.style = style;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room(String roomNumber, RoomType type, RoomStyle style, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.style = style;
        this.price = price;
        this.state = new AvailableState(); // Initial state
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public void book() {
        state.book(this);
    }

    public void checkIn() {
        state.checkIn(this);
    }

    public void checkOut() {
        state.checkOut(this);
    }

    public void markForMaintenance() {
        state.markForMaintenance(this);
    }

    public String getRoomNumber() { return roomNumber; }
    public RoomType getType() { return type; }
    public RoomStyle getStyle() { return style; }
    public double getPrice() { return price; }
    public RoomState getState() { return state; }

    @Override
    public String toString() {
        return "Room [Number=" + roomNumber + ", Type=" + type + ", Style=" + style + ", Price=$" + price + ", State=" + state.getClass().getSimpleName() + "]";
    }
}

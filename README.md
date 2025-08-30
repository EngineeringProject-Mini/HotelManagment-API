# HotelManagment-API

This is a simple ATM backend application built using Spring Boot.  
It provides basic REST APIs for ATM operations like deposit, withdrawal, and balance check.

---

## 🚀 Getting Started

### 1. Generate Project with Spring Initializr
1. Go to [Spring Initializr](https://start.spring.io/).
2. Fill in the details:
   - Project: Maven
   - Language: Java
   - Spring Boot Version: (latest stable, e.g., 3.x.x)
   - Group: hotelmanagmentsystem
   - Artifact: 
   - Name: hotelmanagmentsystem
   - Packaging: Jar
   - Java: 17 (or your installed version)
3. Add dependencies:
   - Spring Web
   - - Lombok
4. Click Generate, and extract the downloaded project.

---

### 2. Open in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Import the project by selecting the extracted folder.
3. Wait until Maven builds the project and dependencies are downloaded.

---

### 3. Run the Application
1. Locate the main class:  
   src/main/java/hotelmanagmentsystem.java
2. Right-click and select Run 'HotelmanagmentsystemApplication'.
3. The application will start on http://localhost:8080 by default.

---

## 🛠️ Tech Stack
- Java 17+
- Spring Boot
- Maven
- REST API

Got it 👍 Here are the **REST API endpoints** for your `HotelController` with clear descriptions:

---

## 🏨 Hotel Management System – API Endpoints

### 🛏 Rooms

#### 1. **Get Room Details**

**GET** `/hotel/rooms/{roomNumber}`

* **Path Variable**:

  * `roomNumber` (String) → Unique room number (e.g., `101`)
* **Description**: Fetches details of a specific room (type, style, price, availability).
* **Response**: Room details (JSON).
<img width="1366" height="768" alt="Screenshot (391)" src="https://github.com/user-attachments/assets/9b34e42c-1a6d-40e1-b27c-800a9cbbd0e7" />

---

### 📖 Bookings

#### 2. **Book a Room**

**POST** `/hotel/bookings`

* **Params**:

  * `guestId` (String) → Guest’s unique ID
  * `guestName` (String) → Guest’s full name
  * `guestEmail` (String) → Guest’s email address
  * `type` (Enum: SINGLE, DOUBLE, SUITE, etc.) → Room type
  * `style` (Enum: STANDARD, DELUXE, OCEAN\_VIEW, etc.) → Room style
  * `startDate` (String, ISO date) → e.g., `2025-09-01`
  * `endDate` (String, ISO date) → e.g., `2025-09-05`
  * `amenities` (List<String>, optional) → Extra amenities (e.g., WiFi, Breakfast)
* **Description**: Books a room for a guest within a date range.
* **Response**: Booking details (JSON).
<img width="1366" height="768" alt="Screenshot (392)" src="https://github.com/user-attachments/assets/669cd199-087c-4b81-97fb-8d839a60615e" />

#### 3. **Check-In Guest**

**POST** `/hotel/checkin/{bookingId}`

* **Path Variable**:

  * `bookingId` (String) → Unique booking identifier
* **Description**: Marks a guest as checked-in for their booking.
* **Response**: Confirmation message.
<img width="1366" height="768" alt="Screenshot (393)" src="https://github.com/user-attachments/assets/5135d864-c024-4594-bb82-27ad17b3b187" />

#### 4. **Check-Out Guest**

**POST** `/hotel/checkout/{roomNumber}`

* **Path Variable**:

  * `roomNumber` (String) → Room number assigned to booking
* **Description**: Marks the guest as checked out and frees the room.
* **Response**: Confirmation message.
<img width="1366" height="768" alt="Screenshot (394)" src="https://github.com/user-attachments/assets/e2009765-9a80-4ad5-ac6d-886d416c4770" />

---

### 🛠 Maintenance

#### 5. **Mark Room for Maintenance**

**POST** `/hotel/rooms/{roomNumber}/maintenance`

* **Path Variable**:

  * `roomNumber` (String) → Room number
* **Description**: Flags a room as under maintenance (unavailable for booking).
* **Response**: Confirmation message.
<img width="1366" height="768" alt="Screenshot (395)" src="https://github.com/user-attachments/assets/b9024cca-ae36-4fda-abe9-852f4e5b4f3f" />

---

package hotel.management;

public class InstanceClassBooking {
    private int bookingID;
    private int guestID;
    private int roomID;
    private String checkInDate;
    private String checkOutDate;

    public InstanceClassBooking (int bookingID, int guestID, int roomID, String checkInDate, String checkOutDate) {
        this.bookingID = bookingID;
        this.guestID = guestID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getGuestID() {
        return guestID;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", guestID=" + guestID +
                ", roomID=" + roomID +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

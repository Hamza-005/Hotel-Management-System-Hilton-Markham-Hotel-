package hotel.management;

public class InstanceClassRoomService {
    private int roomServiceID;
    private int bookingID;
    private int serviceID;
    private int staffID;
    private int roomID;
    private int quantity;
    private String status;
    private int guestId;

    public InstanceClassRoomService(int roomServiceID, int bookingID, int serviceID, int quantity, String status) {
        this.roomServiceID = roomServiceID;
        this.bookingID = bookingID;
        this.serviceID = serviceID;
        this.roomID = Resources.dataBase.fetchRoomIDFromBooking(bookingID);
        this.quantity = quantity;
        this.status = status;
        this.guestId = Resources.loggedInGuest.getId();
    }

    public InstanceClassRoomService(int roomServiceID, int bookingID, int serviceID, int roomID, int quantity, String status, int staffID) {
        this.roomServiceID = roomServiceID;
        this.bookingID = bookingID;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.quantity = quantity;
        this.status = status;
        this.staffID = staffID;
    }
    
    public InstanceClassRoomService(int roomServiceID, int bookingID, int serviceID, int roomID, int quantity, int guestid, String status) {
        this.roomServiceID = roomServiceID;
        this.bookingID = bookingID;
        this.serviceID = serviceID;
        this.roomID = roomID;
        this.quantity = quantity;
        this.status = status;
        this.guestId = guestid;
    }

    public void setRoomServiceID(int roomServiceID) {
        this.roomServiceID = roomServiceID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomServiceID() {
        return roomServiceID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public int getGuestId(){
        return guestId;
    }

    @Override
    public String toString() {
        return "RoomService{" +
                "roomServiceID=" + roomServiceID +
                ", bookingID=" + bookingID +
                ", serviceID=" + serviceID +
                ", staffID=" + staffID +
                ", roomID=" + roomID +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }
}

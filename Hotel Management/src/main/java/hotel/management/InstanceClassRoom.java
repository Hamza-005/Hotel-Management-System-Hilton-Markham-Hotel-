package hotel.management;
public class InstanceClassRoom {
    private int roomID;
    private String type;
    private double rate;
    private boolean availability;

    public InstanceClassRoom(int roomID, String type, double rate, boolean availability) {
        this.roomID = roomID;
        this.type = type;
        this.rate = rate;
        this.availability = availability;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getType() {
        return type;
    }

    public double getRate() {
        return rate;
    }

    public boolean isAvailable() {
        return availability;
    }

    // Setters
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "RoomID: " + roomID +
                ", Type: " + type +
                ", Rate: " + rate +
                ", Availability: " + (availability ? "Available" : "Not Available");
    }

    public static void main(String[] args) {
        InstanceClassRoom room1 = new InstanceClassRoom(101, "Single", 100.0, true);
        System.out.println(room1.toString());
    }
}

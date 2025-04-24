package hotel.management;

public class InstanceClassGuest {
    private int id;
    private String password;
    private String name;
    private String nationality;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor
    public InstanceClassGuest(int id, String password, String name, String nationality, String address, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nationality = nationality;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // toString method
    @Override
    public String toString() {
        return "InstanceClassGuest{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

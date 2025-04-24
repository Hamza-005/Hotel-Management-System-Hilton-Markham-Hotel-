package hotel.management;

public class InstanceClassStaff {
    private int id;
    private String password;
    private String fullName;
    private String position;
    private int salary;
    private String address;
    private String phoneNumber;
    private String email;

    public InstanceClassStaff(int id, String password, String fullName, String position, int salary, String address, String phoneNumber, String email) {
        this.id = id;
        this.password = password;
        this.fullName = fullName;
        this.position = position;
        this.salary = salary;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
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
        return "InstanceClassStaff{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


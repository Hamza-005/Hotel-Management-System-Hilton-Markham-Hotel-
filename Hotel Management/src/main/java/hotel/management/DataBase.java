package hotel.management;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    private Connection connection;
    private Statement statement;

    public DataBase() {
        if (connectToDatabase()) {
            System.out.println("Connection Established");
            createTable();
        } else {
            System.out.println("Connection Failed");
        }
    }

    private boolean connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "dbtest", "postgres", "admin");
            if (connection != null) {
                statement = connection.createStatement();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createTable() {
        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Room (" +
                    "RoomID INTEGER PRIMARY KEY, " +
                    "Type VARCHAR(50), " +
                    "Rate DECIMAL, " +
                    "Availability BOOLEAN)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Guest (" +
                    "GuestID INTEGER PRIMARY KEY, " +
                    "GuestPassword VARCHAR(50), " +
                    "Name VARCHAR(100), " +
                    "Nationality VARCHAR(100), " +
                    "Address VARCHAR(200), " +
                    "PhoneNumber VARCHAR(20), " +
                    "Email VARCHAR(100))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Booking (" +
                    "BookingID INTEGER PRIMARY KEY, " +
                    "GuestID INTEGER REFERENCES Guest(GuestID), " +
                    "RoomID INTEGER REFERENCES Room(RoomID), " +
                    "CheckInDate DATE, " +
                    "CheckOutDate DATE)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Staff (" +
                    "StaffID INTEGER PRIMARY KEY, " +
                    "StaffPassword VARCHAR(50), " +
                    "Name VARCHAR(100), " +
                    "Position VARCHAR(100), " +
                    "Salary DECIMAL, " +
                    "Address VARCHAR(200), " +
                    "PhoneNumber VARCHAR(20), " +
                    "Email VARCHAR(100))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Service (" +
                    "ServiceID INTEGER PRIMARY KEY, " +
                    "Name VARCHAR(100), " +
                    "Description TEXT, " +
                    "Price DECIMAL)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS RoomService (" +
                    "RoomServiceID INTEGER PRIMARY KEY, " +
                    "BookingID INTEGER REFERENCES Booking(BookingID), " +
                    "ServiceID INTEGER REFERENCES Service(ServiceID), " +
                    "StaffID INTEGER REFERENCES Staff(StaffID), " +
                    "RoomID INTEGER REFERENCES Room(RoomID), " +
                    "Quantity INTEGER, " +
                    "Status VARCHAR(50), " +
                    "GuestID INTEGER REFERENCES Guest(GuestID))" );

            //Create one staff member as admin at very start
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Staff");
            if (!resultSet.next()) {
                statement.executeUpdate("INSERT INTO Staff (StaffID, StaffPassword, Name, Position, Salary, Address, PhoneNumber, Email) VALUES " +
                        "(1000, 'admin', 'Admin Name', 'Admin', 900000, 'Admin Adress', '123456', 'admin@gmail.com')");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean createGuest(int id, String password, String name, String nationality, String address, String phoneNumber, String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Guest (GuestID, GuestPassword, Name, Nationality, Address, PhoneNumber, Email) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, nationality);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setString(7, email);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean validateGuest(int id, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Guest WHERE GuestID = ? AND GuestPassword = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int guestId = resultSet.getInt("GuestID");
                String guestPassword = resultSet.getString("GuestPassword");
                String name = resultSet.getString("Name");
                String nationality = resultSet.getString("Nationality");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");

                Resources.loggedInGuest = new InstanceClassGuest(guestId, guestPassword, name, nationality, address, phoneNumber, email);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<String[]> readAllGuest() {
        ArrayList<String[]> guestList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Guest");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int guestID = resultSet.getInt("GuestID");
                String name = resultSet.getString("name");
                String country = resultSet.getString("Nationality");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");

                String[] guest = {String.valueOf(guestID), name, country, address, phoneNumber, email};
                guestList.add(guest);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guestList;
    }

    public InstanceClassGuest findGuestByID(int guestID) {
        InstanceClassGuest guestObj = null;

        try {
            String sql = "SELECT * FROM Guest WHERE GuestID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, guestID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String guestPassword = resultSet.getString("GuestPassword");
                String name = resultSet.getString("Name");
                String nationality = resultSet.getString("Nationality");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");

                guestObj = new InstanceClassGuest(guestID, guestPassword, name, nationality, address, phoneNumber, email);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guestObj;
    }

    public boolean deleteGuestByID(int guestID) {
        try {
            String sql = "DELETE FROM Guest WHERE GuestID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, guestID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateGuest(InstanceClassGuest guest) {
        try {

            String sql = "UPDATE Guest SET GuestPassword=?, Name=?, Nationality=?, Address=?, PhoneNumber=?, Email=? WHERE GuestID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, guest.getPassword());
            preparedStatement.setString(2, guest.getName());
            preparedStatement.setString(3, guest.getNationality());
            preparedStatement.setString(4, guest.getAddress());
            preparedStatement.setString(5, guest.getPhoneNumber());
            preparedStatement.setString(6, guest.getEmail());
            preparedStatement.setInt(7, guest.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Guest information updated successfully.");
                return true;
            } else {
                System.out.println("No rows were updated.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean createStaff(InstanceClassStaff staff) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Staff (StaffID, StaffPassword, Name, Position, Salary, Address, PhoneNumber, Email) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getPassword());
            preparedStatement.setString(3, staff.getFullName());
            preparedStatement.setString(4, staff.getPosition());
            preparedStatement.setInt(5, staff.getSalary());
            preparedStatement.setString(6, staff.getAddress());
            preparedStatement.setString(7, staff.getPhoneNumber());
            preparedStatement.setString(8, staff.getEmail());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> readAllStaff() {
        ArrayList<String[]> staffList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Staff");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int staffID = resultSet.getInt("StaffID");
                String name = resultSet.getString("Name");
                String position = resultSet.getString("Position");
                int salary = resultSet.getInt("Salary");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");

                String[] staff = {String.valueOf(staffID), name, position, String.valueOf(salary), address, phoneNumber, email};
                staffList.add(staff);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public boolean validateStaff(int id, String password, String type) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Staff WHERE StaffID = ? AND StaffPassword = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int staffID = resultSet.getInt("StaffID");
                String name = resultSet.getString("Name");
                String position = resultSet.getString("Position");
                int salary = resultSet.getInt("Salary");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                if (position.equals(type)) {
                    Resources.loggedInStaff = new InstanceClassStaff(staffID, password, name, position, salary, address, phoneNumber, email);
                    return true;
                } else {
                    String message = "Incorrect Position you must be logged in as a " + position;
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public InstanceClassStaff findStaffByID(int staffID) {
        InstanceClassStaff staffObj = null;

        try {
            String sql = "SELECT * FROM Staff WHERE StaffID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staffID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String staffPassword = resultSet.getString("StaffPassword");
                String name = resultSet.getString("Name");
                String position = resultSet.getString("Position");
                int salary = resultSet.getInt("Salary");
                String address = resultSet.getString("Address");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");

                staffObj = new InstanceClassStaff(staffID, staffPassword, name, position, salary, address, phoneNumber, email);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffObj;
    }

    public boolean deleteStaffByID(int staffID) {
        try {
            String sql = "DELETE FROM Staff WHERE StaffID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staffID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStaff(InstanceClassStaff staff) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Staff SET StaffPassword=?, Name=?, Position=?, Salary=?, Address=?, PhoneNumber=?, Email=? WHERE StaffID=?"
            );
            preparedStatement.setString(1, staff.getPassword());
            preparedStatement.setString(2, staff.getFullName());
            preparedStatement.setString(3, staff.getPosition());
            preparedStatement.setInt(4, staff.getSalary());
            preparedStatement.setString(5, staff.getAddress());
            preparedStatement.setString(6, staff.getPhoneNumber());
            preparedStatement.setString(7, staff.getEmail());
            preparedStatement.setInt(8, staff.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean createRoom(InstanceClassRoom room) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Room (RoomID, Type, Rate, Availability) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, room.getRoomID());
            preparedStatement.setString(2, room.getType());
            preparedStatement.setDouble(3, room.getRate());
            preparedStatement.setBoolean(4, room.isAvailable());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> readAllRoom() {
        ArrayList<String[]> roomList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Room");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomID = resultSet.getInt("RoomID");
                String type = resultSet.getString("Type");
                int rate = resultSet.getInt("Rate");
                boolean availability = resultSet.getBoolean("Availability");

                String[] room = {String.valueOf(roomID), type, String.valueOf(rate), String.valueOf(availability)};
                roomList.add(room);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    public InstanceClassRoom findRoomByID(int roomID) {
        InstanceClassRoom roomObj = null;

        try {
            String sql = "SELECT * FROM Room WHERE RoomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String type = resultSet.getString("Type");
                int rate = resultSet.getInt("Rate");
                boolean availability = resultSet.getBoolean("Availability");


                roomObj = new InstanceClassRoom(roomID, type, rate, availability);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomObj;
    }

    public boolean deleteRoomByID(int roomID) {
        try {
            String sql = "DELETE FROM Room WHERE RoomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoom(InstanceClassRoom room) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Room SET Type=?, Rate=?, Availability=? WHERE RoomID=?"
            );

            preparedStatement.setString(1, room.getType());
            preparedStatement.setDouble(2, room.getRate());
            preparedStatement.setBoolean(3, room.isAvailable());
            preparedStatement.setInt(4, room.getRoomID());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public InstanceClassRoom findRoomByTypeAndAvailability(String type, boolean availability) {
        InstanceClassRoom roomObj = null;

        try {
            String sql = "SELECT * FROM Room WHERE Type = ? AND Availability = ? LIMIT 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);
            preparedStatement.setBoolean(2, availability);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int roomID = resultSet.getInt("RoomID");
                int rate = resultSet.getInt("Rate");

                roomObj = new InstanceClassRoom(roomID, type, rate, availability);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomObj;
    }

    public boolean createBooking(InstanceClassBooking booking) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Booking (BookingID, GuestID, RoomID, CheckInDate, CheckOutDate) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, booking.getBookingID());
            preparedStatement.setInt(2, booking.getGuestID());
            preparedStatement.setInt(3, booking.getRoomID());
            preparedStatement.setDate(4, Date.valueOf(booking.getCheckInDate()));
            preparedStatement.setDate(5, Date.valueOf(booking.getCheckOutDate()));

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public int getLastBookingID() {
        int lastRoomID = 1000;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT BookingID FROM Booking ORDER BY BookingID DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastRoomID = resultSet.getInt("BookingID");
            }

            return lastRoomID;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lastRoomID;
    }

    public ArrayList<String[]> readAllBookingsPerPerson() {
        ArrayList<String[]> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking WHERE GuestID = ?");
            preparedStatement.setInt(1, Resources.loggedInGuest.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int roomID = resultSet.getInt("RoomID");
                String checkInDate = resultSet.getString("CheckInDate");
                String checkOutDate = resultSet.getString("CheckOutDate");

                String[] booking = {String.valueOf(bookingID), String.valueOf(roomID),checkInDate,checkOutDate};
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public ArrayList<String[]> readAllBookings() {
        ArrayList<String[]> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int roomID = resultSet.getInt("RoomID");
                String checkInDate = resultSet.getString("CheckInDate");
                String checkOutDate = resultSet.getString("CheckOutDate");

                String[] booking = {String.valueOf(bookingID), String.valueOf(roomID),checkInDate,checkOutDate};
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }

    public InstanceClassBooking findBookingByID(int bookingID) {
        InstanceClassBooking bookingObj = null;

        try {
            String sql = "SELECT * FROM Booking WHERE BookingID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookingID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String guestId = resultSet.getString("GuestID");
                String roomID = resultSet.getString("RoomID");
                String checkInDate = resultSet.getString("CheckInDate");
                String checkOutDate = resultSet.getString("CheckOutDate");

                bookingObj = new InstanceClassBooking(bookingID, Integer.parseInt(guestId), Integer.parseInt(roomID),checkInDate,checkOutDate);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingObj;
    }

    public boolean deleteBookingByID(int bookingID) {
        try {
            String sql = "DELETE FROM Booking WHERE BookingID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookingID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createService(InstanceClassServices service) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Service (ServiceID, Name, Description, Price) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, service.getId());
            preparedStatement.setString(2, service.getName());
            preparedStatement.setString(3, service.getDescription());
            preparedStatement.setDouble(4, service.getPrice());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> readAllServices() {
        ArrayList<String[]> serviceList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Service");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int serviceID = resultSet.getInt("ServiceID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");

                String[] service = {String.valueOf(serviceID), name, description, String.valueOf(price)};
                serviceList.add(service);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList;
    }

    public InstanceClassServices findServiceByID(int serviceId){
        InstanceClassServices serviceObj = null;

        try {
            String sql = "SELECT * FROM Service WHERE ServiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, serviceId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");

                serviceObj = new InstanceClassServices(serviceId, price, name, description);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceObj;
    }

    public boolean deleteServiceByID(int serviceID) {
        try {
            String sql = "DELETE FROM Service WHERE ServiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, serviceID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateService(InstanceClassServices service) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Service SET Name=?, Description=?, Price=? WHERE ServiceID=?"
            );

            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setDouble(3, service.getPrice());
            preparedStatement.setInt(4, service.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String[] fetchBookingsByGuestID() {
        ArrayList<String> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking WHERE GuestID = ?");
            preparedStatement.setInt(1, Resources.loggedInGuest.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                bookingList.add(String.valueOf(bookingID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList.toArray(new String[bookingList.size()]);
    }
    
    public String[] fetchAllBookings(){
         ArrayList<String> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Booking");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                bookingList.add(String.valueOf(bookingID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList.toArray(new String[bookingList.size()]);
    }

    public String[] fetchServicesNames() {
        ArrayList<String> serviceList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name FROM Service");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String serviceName = resultSet.getString("Name");
                serviceList.add(serviceName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceList.toArray(new String[serviceList.size()]);
    }

    public int fetchRoomIDFromBooking(int bookingID) {
        int roomID = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RoomID FROM Booking WHERE BookingID = ? LIMIT 1");
            preparedStatement.setInt(1, bookingID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roomID = resultSet.getInt("RoomID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomID;
    }
    
    public int fetchGuestIDFromBooking(int bookingID) {
        int GuestID = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT GuestID FROM Booking WHERE BookingID = ? LIMIT 1");
            preparedStatement.setInt(1, bookingID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                GuestID = resultSet.getInt("GuestID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GuestID;
    }

    public int getLastServiceBookingID() {
        int lastServiceBookedID = 1000;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RoomServiceID FROM RoomService ORDER BY RoomServiceID DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastServiceBookedID = resultSet.getInt("RoomServiceID");
            }

            return lastServiceBookedID;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lastServiceBookedID;
    }

    public int fetchServiceIDByName(String serviceName) {
        int serviceID = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ServiceID FROM Service WHERE Name = ? LIMIT 1");
            preparedStatement.setString(1, serviceName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                serviceID = resultSet.getInt("ServiceID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceID;
    }

    public boolean createRoomService(InstanceClassRoomService roomService) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RoomService (RoomServiceID, BookingID, ServiceID, RoomID, Quantity, Status, GuestID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, roomService.getRoomServiceID());
            preparedStatement.setInt(2, roomService.getBookingID());
            preparedStatement.setInt(3, roomService.getServiceID());
            preparedStatement.setInt(4, roomService.getRoomID());
            preparedStatement.setInt(5, roomService.getQuantity());
            preparedStatement.setString(6, roomService.getStatus());
            preparedStatement.setInt(7, roomService.getGuestId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> readAllRoomServiceByLoggedInUser() {
        ArrayList<String[]> roomServiceList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RoomService WHERE GuestID = ?");
            preparedStatement.setInt(1, Resources.loggedInGuest.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomServiceID = resultSet.getInt("RoomServiceID");
                int bookingID = resultSet.getInt("BookingID");
                int roomNo = fetchRoomIDFromBooking(bookingID);
                int serviceID = resultSet.getInt("ServiceID");
                String serviceDescription = fetchServiceDescription(serviceID);
                int quantity = resultSet.getInt("Quantity");
                String status = resultSet.getString("Status");

                String[] roomService = {String.valueOf(roomServiceID), String.valueOf(bookingID), String.valueOf(roomNo), serviceDescription, String.valueOf(quantity), status};
                roomServiceList.add(roomService);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomServiceList;
    }

    public String fetchServiceName(int serviceID) {
        String serviceName = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Name FROM Service WHERE ServiceID = ?");
            preparedStatement.setInt(1, serviceID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                serviceName = resultSet.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serviceName;
    }

    public InstanceClassRoomService findRoomServiceByID(int roomServiceID) {
        InstanceClassRoomService roomServiceObj = null;

        try {
            String sql = "SELECT * FROM RoomService WHERE RoomServiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomServiceID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int bookingID = resultSet.getInt("BookingID");
                int roomID = resultSet.getInt("RoomID");
                int serviceID = resultSet.getInt("ServiceID");
                int quantity = resultSet.getInt("Quantity");
                String status = resultSet.getString("Status");
                int guestID = resultSet.getInt("GuestID");
                roomServiceObj = new InstanceClassRoomService(roomServiceID, bookingID, serviceID,roomID, quantity, status, guestID);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomServiceObj;
    }

    public boolean deleteRoomServiceByID(int roomServiceID) {
        try {
            String sql = "DELETE FROM RoomService WHERE RoomServiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, roomServiceID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRoomServiceByBookingID(int bookingID) {
        try {
            String sql = "DELETE FROM RoomService WHERE BookingID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookingID);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> readAllRoomService() {
        ArrayList<String[]> roomServiceList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RoomService");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomServiceID = resultSet.getInt("RoomServiceID");
                int bookingID = resultSet.getInt("BookingID");
                int roomNo = fetchRoomIDFromBooking(bookingID);
                int serviceID = resultSet.getInt("ServiceID");
                String serviceDescription = fetchServiceDescription(serviceID);
                int quantity = resultSet.getInt("Quantity");
                String staffId = " ";
                try{
                    staffId = String.valueOf(resultSet.getInt("StaffID"));
                } catch (Exception e){
                    staffId = " ";
                }
                String status = resultSet.getString("Status");

                String[] roomService = {String.valueOf(roomServiceID), String.valueOf(bookingID), String.valueOf(roomNo), serviceDescription, String.valueOf(quantity), staffId, status};
                roomServiceList.add(roomService);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomServiceList;
    }

    public String fetchServiceDescription(int serviceID) {
        String description = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Description FROM Service WHERE ServiceID = ?");
            preparedStatement.setInt(1, serviceID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                description = resultSet.getString("Description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    public String[] fetchStaffIDs() {
        ArrayList<String> staffList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT StaffID FROM Staff");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String staffID = resultSet.getString("StaffID");
                staffList.add(staffID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList.toArray(new String[staffList.size()]);
    }


    public boolean assignStaffToRoomService(int roomServiceID, int staffID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RoomService SET StaffID = ? WHERE RoomServiceID = ?");
            preparedStatement.setInt(1, staffID);
            preparedStatement.setInt(2, roomServiceID);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<String[]> fetchRoomServicesByStaffID(int staffID) {
        ArrayList<String[]> roomServiceList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RoomService WHERE StaffID = ?");
            preparedStatement.setInt(1, staffID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int roomServiceID = resultSet.getInt("RoomServiceID");
                int bookingID = resultSet.getInt("BookingID");
                int roomNo = fetchRoomIDFromBooking(bookingID);
                int serviceID = resultSet.getInt("ServiceID");
                String serviceDescription = fetchServiceDescription(serviceID);
                int quantity = resultSet.getInt("Quantity");
                String staffId = " ";
                try{
                    staffId = String.valueOf(resultSet.getInt("StaffID"));
                } catch (Exception e){
                    staffId = " ";
                }
                String status = resultSet.getString("Status");

                String[] roomService = {String.valueOf(roomServiceID), String.valueOf(bookingID), String.valueOf(roomNo),serviceDescription, String.valueOf(quantity),staffId ,status};
                roomServiceList.add(roomService);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomServiceList;
    }

    public boolean updateRoomServiceStatus(int roomServiceID) {
        if (!(findRoomServiceAndStaffID(roomServiceID, Resources.loggedInStaff.getId()))){
            JOptionPane.showMessageDialog(null, "Room Service not found", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (findRoomServiceStatus(roomServiceID)){
            JOptionPane.showMessageDialog(null, "Room Service has already been delivered", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE RoomService SET Status = ? WHERE RoomServiceID = ?");
                preparedStatement.setString(1, "delivered");
                preparedStatement.setInt(2, roomServiceID);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0){
                    JOptionPane.showMessageDialog(null, "Room Service Status Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null, "Room Service ID not found");
        return false;
    }

    public boolean findRoomServiceStatus(int roomServiceID) {
        String status = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Status FROM RoomService WHERE RoomServiceID = ?");
            preparedStatement.setInt(1, roomServiceID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                status = resultSet.getString("Status");
                if (status.equals("delivered")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findRoomServiceAndStaffID(int roomServiceID, int staffID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM RoomService WHERE RoomServiceID = ? AND StaffID = ?");
            preparedStatement.setInt(1, roomServiceID);
            preparedStatement.setInt(2, staffID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoomService(InstanceClassRoomService roomService) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE RoomService SET BookingID=?, ServiceID=?, RoomID=?, Quantity=?, Status=? WHERE RoomServiceID=?"
            );

            preparedStatement.setInt(1, roomService.getBookingID());
            preparedStatement.setInt(2, roomService.getServiceID());
            preparedStatement.setInt(3, roomService.getRoomID());
            preparedStatement.setInt(4, roomService.getQuantity());
            preparedStatement.setString(5, roomService.getStatus());
            preparedStatement.setInt(6, roomService.getRoomServiceID());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public String fetchRoomType(int roomID) {
        String roomType = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Type FROM Room WHERE RoomID = ?");
            preparedStatement.setInt(1, roomID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roomType = resultSet.getString("Type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomType;
    }

    public boolean updateBooking(InstanceClassBooking booking) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Booking SET GuestID=?, RoomID=?, CheckInDate=?, CheckOutDate=? WHERE BookingID=?"
            );

            preparedStatement.setInt(1, booking.getGuestID());
            preparedStatement.setInt(2, booking.getRoomID());
            preparedStatement.setDate(3, Date.valueOf(booking.getCheckInDate()));
            preparedStatement.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            preparedStatement.setInt(5, booking.getBookingID());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateRoomStatus(int roomID, boolean status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Room SET Availability=? WHERE RoomID=?"
            );

            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, roomID);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

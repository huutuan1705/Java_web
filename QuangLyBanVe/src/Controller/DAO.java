/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Model.Employee;
import Model.Station;
import Model.Ticket;
import Model.TrainRide;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class DAO {

    protected Connection getConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName= QLBV";
            String username = "sa";
            String password = "12345";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<TrainRide> getAllTrainRides() {
        List<TrainRide> trainRides = new ArrayList<>();
        String sql = "Select * from TrainRide";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String depatureTime = rs.getString("DepatureTime");
                String depatureStation = rs.getString("DepartureStation");
                String destination = rs.getString("Destination");
                String idTrain = rs.getString("idTrain");
                trainRides.add(new TrainRide(id, depatureTime, depatureStation, destination, idTrain));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return trainRides;
    }

    public Customer getCustomer(String username, String password) {
        Customer c = new Customer();
        String sql = "SELECT [id]\n"
                + "      ,[Name]\n"
                + "      ,[phoneNumber]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE username = ? and password=?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Customer(rs.getString("id"), rs.getString("name"),
                        username, password, rs.getString("phoneNumber"), rs.getString("Address"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Ticket> getAllTicketInBranchOfCus(String idCustomer, String branch) {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[ticketPrice]\n"
                + "      ,[discount]\n"
                + "      ,[IdTrainRide]\n"
                + "      ,[IdEmployee]\n"
                + "      ,[SeatType]\n"
                + "  FROM [dbo].[Ticket]\n"
                + "  Where IdCustomer = ? and IdEmployee like '%" + branch + "%'";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idCustomer);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                double ticketPrice = rs.getDouble("ticketPrice");
                double discount = rs.getDouble("discount");
                String idTrainRide = rs.getString("IdTrainRide");
                String idEmployee = rs.getString("IdEmployee");
                String seatType = rs.getString("SeatType");
                list.add(new Ticket(id, ticketPrice, discount, idTrainRide, idCustomer, idEmployee, seatType));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Station> getAllStations() {
        List<Station> list = new ArrayList<>();
        String sql = "SELECT [Name]\n"
                + "      ,[StationClass]\n"
                + "      ,[Address]\n"
                + "  FROM [dbo].[Station]";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                String stationClass = rs.getString("StationClass");
                String address = rs.getString("Address");
                list.add(new Station(name, stationClass, address));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<TrainRide> getListTrainRides(String depatureStation, String destination) {
        List<TrainRide> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[DepatureTime]\n"
                + "      ,[idTrain]\n"
                + "  FROM [dbo].[TrainRide]\n"
                + "  WHERE DepartureStation = ? AND Destination = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, depatureStation);
            ps.setString(2, destination);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String departureTime = rs.getString("DepatureTime");
                String idTrain = rs.getString("idTrain");
                list.add(new TrainRide(id, departureTime, depatureStation, destination, idTrain));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<TrainRide> getListTrainRidesDepatureStation(String depatureStation) {
        List<TrainRide> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[DepatureTime]\n"
                + "      ,[Destination]\n"
                + "      ,[idTrain]\n"
                + "  FROM [dbo].[TrainRide]\n"
                + "  WHERE DepartureStation = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, depatureStation);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String departureTime = rs.getString("DepatureTime");
                String idTrain = rs.getString("idTrain");
                String destination = rs.getString("Destination");
                list.add(new TrainRide(id, departureTime, depatureStation, destination, idTrain));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<TrainRide> getListTrainRidesDestination(String destination) {
        List<TrainRide> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[DepatureTime]\n"
                + "      ,[DepartureStation]\n"
                + "      ,[idTrain]\n"
                + "  FROM [dbo].[TrainRide]\n"
                + "  WHERE Destination = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, destination);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String departureTime = rs.getString("DepatureTime");
                String depatureStation = rs.getString("DepartureStation");
                String idTrain = rs.getString("idTrain");
                list.add(new TrainRide(id, departureTime, depatureStation, destination, idTrain));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Employee> getEmployeesByBranch(String idBranch) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[fullname]\n"
                + "      ,[salary]\n"
                + "      ,[role]\n"
                + "      ,[phoneNumber]\n"
                + "  FROM [dbo].[Employee]\n"
                + "  where idBranch = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idBranch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String fullname = rs.getString("fullname");
                double salary = rs.getDouble("salary");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");
                list.add(new Employee(id, fullname, salary, role, phoneNumber));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Employee getEmployeeById(String id) {
        String sql = "SELECT [fullname]\n"
                + "      ,[salary]\n"
                + "      ,[role]\n"
                + "      ,[phoneNumber]\n"
                + "      ,[idBranch]\n"
                + "  FROM [dbo].[Employee]\n"
                + "  where id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                double salary = rs.getDouble("salary");
                String role = rs.getString("role");
                String phoneNumber = rs.getString("phoneNumber");
                String idBranch = rs.getString("idBranch");
                Employee e = new Employee(id, fullname, salary, role, phoneNumber);
                return e;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Ticket> getAllTicketOfBranch(String branch) {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[ticketPrice]\n"
                + "      ,[discount]\n"
                + "      ,[IdTrainRide]\n"
                + "      ,[IdCustomer]\n"
                + "      ,[IdEmployee]\n"
                + "      ,[SeatType]\n"
                + "  FROM [dbo].[Ticket]\n"
                + "  where id like '%" + branch + "%'";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                double ticketPrice = rs.getDouble("ticketPrice");
                double discount = rs.getDouble("discount");
                String idTrainRide = rs.getString("IdTrainRide");
                String idEmployee = rs.getString("IdEmployee");
                String idCustomer = rs.getString("IdCustomer");
                String seatType = rs.getString("SeatType");
                list.add(new Ticket(id, ticketPrice, discount, idTrainRide, idCustomer, idEmployee, seatType));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertTicket(Ticket t) {
        String sql = "INSERT INTO [dbo].[Ticket]\n"
                + "           ([id]\n"
                + "           ,[ticketPrice]\n"
                + "           ,[discount]\n"
                + "           ,[IdTrainRide]\n"
                + "           ,[IdCustomer]\n"
                + "           ,[IdEmployee]\n"
                + "           ,[SeatType])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, t.getId());
            ps.setDouble(2, t.getTicketPrice());
            ps.setDouble(3, t.getDiscount());
            ps.setString(4, t.getIdTrainRide());
            ps.setString(5, t.getIdCustomer());
            ps.setString(6, t.getIdEmployee());
            ps.setString(7, t.getSeatType());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void CancelTicket(String id) {
        String sql = "DELETE FROM [dbo].[Ticket]\n"
                + "      WHERE id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public TrainRide getTrainRideById(String id) {
        String sql = "SELECT [DepatureTime]\n"
                + "      ,[DepartureStation]\n"
                + "      ,[Destination]\n"
                + "      ,[idTrain]\n"
                + "  FROM [dbo].[TrainRide]\n"
                + "  where id = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String depatureTime = rs.getString("DepatureTime");
                String departureStation = rs.getString("DepartureStation");
                String destination = rs.getString("Destination");
                String idTrain = rs.getString("idTrain");
                TrainRide t = new TrainRide(id, depatureTime, departureStation, destination, idTrain);
                return t;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        
    }
}

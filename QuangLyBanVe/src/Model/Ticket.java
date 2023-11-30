/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author huutuan
 */
public class Ticket {
    private String id;
    private double ticketPrice, discount;
    private String idTrainRide, idCustomer, idEmployee, seatType;

    public Ticket() {
    }

    public Ticket(String id, double ticketPrice, double discount, String idTrainRide, String idCustomer, String idEmployee, String seatType) {
        this.id = id;
        this.ticketPrice = ticketPrice;
        this.discount = discount;
        this.idTrainRide = idTrainRide;
        this.idCustomer = idCustomer;
        this.idEmployee = idEmployee;
        this.seatType = seatType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getIdTrainRide() {
        return idTrainRide;
    }

    public void setIdTrainRide(String idTrainRide) {
        this.idTrainRide = idTrainRide;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
    
    
    public Object[] toObjects(){
        return new Object[]{
            id, ticketPrice, discount, idTrainRide, seatType
        };
    }
}

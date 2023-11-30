/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author huutuan
 */
public class TrainRide {
    private String id, departureTime, depatureStation, destination, idTrain;

    public TrainRide() {
    }

    public TrainRide(String id, String departureTime, String depatureStation, String destination, String idTrain) {
        this.id = id;
        this.departureTime = departureTime;
        this.depatureStation = depatureStation;
        this.destination = destination;
        this.idTrain = idTrain;
    }

    public String getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(String idTrain) {
        this.idTrain = idTrain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepatureStation() {
        return depatureStation;
    }

    public void setDepatureStation(String depatureStation) {
        this.depatureStation = depatureStation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    
    public Object[] toObjects(){
        return new Object[]{
            id, departureTime, depatureStation, destination, idTrain
        };
    }
}

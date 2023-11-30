/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author huutuan
 */
public class Station {
    private String name, stationClass, address;

    public Station() {
    }

    public Station(String name, String stationClass, String address) {
        this.name = name;
        this.stationClass = stationClass;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationClass() {
        return stationClass;
    }

    public void setStationClass(String stationClass) {
        this.stationClass = stationClass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}

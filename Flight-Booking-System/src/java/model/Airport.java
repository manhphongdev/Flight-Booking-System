/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class Airport {

    private String airportCode;
    private String airportName;
    private String city;
    private String country;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Airport() {
    }

    public Airport(String airportCode, String airportName, String city, String country, LocalDateTime createAt, LocalDateTime updateAt) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Airport(String airportCode, String airportName, String city, String country, LocalDateTime createAt) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
        this.createAt = createAt;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "AirportEntity{" + "airportCode=" + airportCode + ", airportName=" + airportName + ", city=" + city + ", country=" + country + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }
}

package model;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class Airline {

    private Long id;
    private String airlineName;

    private String airlineCode;
    private LocalDateTime createAt;

    public Airline() {
    }

    public Airline(String airlineName, String airlineCode, LocalDateTime createAt) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.createAt = createAt;
    }

    public Airline(Long id, String airlineName, String airlineCode, LocalDateTime createAt) {
        this.id = id;
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Airline{" + "id=" + id + ", airlineCode=" + airlineCode + ", airlineName=" + airlineName + ", createAt=" + createAt + ", updateAt=" + '}';
    }

}

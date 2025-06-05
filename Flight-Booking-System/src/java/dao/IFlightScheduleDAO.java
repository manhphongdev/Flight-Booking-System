package dao;

import java.time.LocalDateTime;
import java.util.List;
import model.FlightSchedule;

public interface IFlightScheduleDAO extends IBaseDAO<FlightSchedule> {
    List<FlightSchedule> findByFlightId(Long flightId);
    List<FlightSchedule> findByStatus(String status);
    List<FlightSchedule> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    boolean updateStatus(Long scheduleId, String status);
} 
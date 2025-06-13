package service.interfaces;

import dto.request.FlightScheduleRequest;
import dto.response.FlightScheduleResponse;
import java.util.List;
import model.FlightSchedule;

/**
 *
 * @author manhphong
 */
public interface IFlightScheduleService {
    boolean save(FlightScheduleRequest req);
    FlightSchedule getById(Long id);
    List<FlightScheduleResponse> getAll();
    void updateById(FlightScheduleRequest req);
    void deleteById(Long id);
}

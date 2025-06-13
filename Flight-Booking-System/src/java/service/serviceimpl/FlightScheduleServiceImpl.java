package service.serviceimpl;

import dao.interfaces.IFlightDAO;
import dao.interfaces.IFlightScheduleDAO;
import dao.interfaces.IFlightSegmentDAO;
import dao.daoimpl.FlightDAOImpl;
import dao.daoimpl.FlightScheduleDAOImpl;
import dao.daoimpl.FlightSegmentDAOImpl;
import dto.request.FlightScheduleRequest;
import dto.response.FlightScheduleResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import model.FlightSchedule;
import service.interfaces.IFlightScheduleService;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class FlightScheduleServiceImpl implements IFlightScheduleService {

    private final IFlightScheduleDAO fsDao = new FlightScheduleDAOImpl();
    private final IFlightDAO flightDao = new FlightDAOImpl();
    private final IFlightSegmentDAO fSegmentDao = new FlightSegmentDAOImpl();

    @Override
    public boolean save(FlightScheduleRequest req) {
        return false;
    }

    @Override
    public FlightSchedule getById(Long id) {
        return fsDao.findByID(id).orElse(null);
    }

    @Override
    public List<FlightScheduleResponse> getAll() {
        List<FlightSchedule> flightSchedules = fsDao.findAll();

//        return flightSchedules.stream()
//                .map(flightSchedule -> {
//                    Long flightId = flightSchedule.getFlightId();
//                    String airlineName = fsDao.getAirlineNameByFlightId(flightId);
//                    String flightNumber = fsDao.getAirlineCodeByFlightId(flightId) + String.format("%03d", flightId);
//                    String departureAirport = fSegmentDao.f
//                    return new FlightScheduleResponse(
//                            flightSchedule.getScheduleId(),
//                            airlineName,
//                            flightNumber,
//                            flightSchedule.get,
//                            toAirport,
//                            LocalDateTime.MIN,
//                            LocalDateTime.MIN, status,
//                            Integer.SIZE);
//                }
//                ).collect(Collectors.toList());
        return null;
    }

    @Override
    public void updateById(FlightScheduleRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Long id) {
        fsDao.deleteByID(id);
    }

}

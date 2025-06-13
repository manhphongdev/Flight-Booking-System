package service.serviceimpl;

import dto.response.FlightBookingResponse;
import dao.interfaces.IAirlineDAO;
import dao.interfaces.IFlightDAO;
import dao.daoimpl.AirlineDAOImpl;
import dao.daoimpl.FlightDAOImpl;
import dao.daoimpl.SeatDAOImpl;
import dao.interfaces.ISeatDAO;
import dto.FlightSearchDTO;
import dto.response.FlightResponse;
import dto.request.FlightRequest;
import dto.request.FlightSearchRequest;
import dto.response.FlightSearchResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import model.Flight;
import model.Seat;
import service.interfaces.IFlightService;
import utils.DateUtils;
import utils.FormatMoney;

/**
 *
 * @author manhphong
 */
public class FlightServiceImpl implements IFlightService {

    private final IFlightDAO fDao;
    private static final Logger LOG = Logger.getLogger(FlightServiceImpl.class.getName());
    private final IAirlineDAO aDao = new AirlineDAOImpl();
    private final ISeatDAO seatDao = new SeatDAOImpl();
    private final FeeServiceImpl feeService = new FeeServiceImpl();

    public FlightServiceImpl() {
        this.fDao = new FlightDAOImpl();
    }

    @Override
    public boolean addFlight(FlightRequest req) {

        Long airlineId = aDao.getIdByAirlineName(req.getAirlineName());
        if (airlineId == null) {
            throw new IllegalArgumentException("Airname not exists!");
        }
        Flight flight = new Flight(airlineId,
                req.getEconomyPrice(),
                req.getBusinessPrice(),
                LocalDateTime.now(),
                LocalDateTime.now());

        fDao.insert(flight);
        LOG.info("Flight inserted!");
        return true;
    }

    @Override
    public Flight getFlightById(Long id) {
        return fDao.findByID(id).orElse(null);
    }

    @Override
    public List<Flight> getAll() {
        return fDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        fDao.deleteByID(id);
    }

    @Override
    public void updateById(Long id, FlightRequest req) {
        Flight flight = getFlightById(id);
        if (flight == null) {
            throw new RuntimeException("Flight id not exist!");
        }
        flight.setAirlineId(aDao.getIdByAirlineName(req.getAirlineName()));
        flight.setEconomyPrice(req.getEconomyPrice());
        flight.setBusinessPrice(req.getBusinessPrice());

        fDao.updateByID(id, flight);

    }

    @Override
    public List<FlightResponse> getAllFlight() {
        List<Flight> flights = fDao.findAll();

        return flights.stream()
                .map(flight -> {
                    String airlineName = aDao.getAirlineNameByAirlineId(flight.getAirlineId());
                    return new FlightResponse(
                            flight.getFlightId(),
                            airlineName,
                            flight.getEconomyPrice(),
                            flight.getBusinessPrice());
                }).collect(Collectors.toList());
    }

    @Override
    public List<FlightSearchResponse> searchFlightForBooking(FlightSearchRequest req) {
        List<FlightSearchDTO> flights = fDao.searchFlightForBookingOneWay(req.getDepartureFrom(), req.getArrivalTo(), req.getDateFrom());

        return flights.stream()
                .map(flight -> {
                    String seatClass = req.getTicketClass();
                    int availableSeats = seatDao.countUnBookedSeat(flight.getFlightId(), seatClass);

                    // check seat available
                    if (availableSeats < totalPassenger(req)) {
                        return null;
                    }

                    String price = "businessClass".equals(seatClass)
                            ? FormatMoney.formatNumber(feeService.totalPriceForTicket(flight.getBusinessPrice()))
                            : FormatMoney.formatNumber(feeService.totalPriceForTicket(flight.getEconomyPrice()));

                    return new FlightSearchResponse(
                            flight.getFlightId(),
                            price,
                            flight.getAirlineName(),
                            flight.getAirlineCode(),
                            flight.getDepartureAirportCode(),
                            flight.getDepartureAirportName(),
                            flight.getDepartureAirportCity(),
                            flight.getArrivalAirportCode(),
                            flight.getArrivalAirportName(),
                            flight.getArrivalAirportCity(),
                            flight.getFlightNumber(),
                            DateUtils.formatDate(flight.getDepartureTime(), "dd-MM-yyyy HH:mm"),
                            DateUtils.formatDate(flight.getArrivalTime(), "dd-MM-yyyy HH:mm")
                    );
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private int totalPassenger(FlightSearchRequest req) {
        return req.getAdultNumber() + req.getChildNumber();
    }

    public List<FlightSearchResponse> searchFlightWithFilter(FlightSearchRequest req) {
//        List<FlightSearchDTO> flights = fDao.searchFlightOneWayAndFilterWithCondition(filter);
//        return flights.stream()
//                .map(flight -> {
//                    return new FlightSearchResponse(
//                            flight.getFlightId(),
//                            FormatMoney.formatNumber(flight.getEconomyPrice()),
//                            FormatMoney.formatNumber(flight.getBusinessPrice()),
//                            flight.getAirlineName(),
//                            flight.getAirlineCode(),
//                            flight.getDepartureAirportCode(),
//                            flight.getDepartureAirportName(),
//                            flight.getDepartureAirportCity(),
//                            flight.getArrivalAirportCode(),
//                            flight.getArrivalAirportName(),
//                            flight.getArrivalAirportCity(),
//                            flight.getFlightNumber(),
//                            DateUtils.formatDate(flight.getDepartureTime(), "dd-MM-yyyy HH:mm"),
//                            DateUtils.formatDate(flight.getArrivalTime(), "dd-MM-yyyy HH:mm"));
//                }).collect(Collectors.toList());
        return null;
    }

    public FlightBookingResponse getFlightAndSeatsToBooking(Long id) {
        FlightSearchDTO dto = fDao.searchByFlightId(id);
        if (dto == null) {
            return null;
        }
        List<Seat> seats = seatDao.findByFlightId(id);

        return new FlightBookingResponse(
                dto.getFlightId(),
                dto.getEconomyPrice(),
                dto.getBusinessPrice(),
                dto.getAirlineName(),
                dto.getAirlineCode(),
                dto.getDepartureAirportName(),
                dto.getDepartureAirportCode(),
                dto.getAirlineName(),
                dto.getAirlineCode(),
                dto.getDepartureAirportCity(),
                dto.getArrivalAirportCity(),
                dto.getFlightNumber(),
                dto.getDepartureTime(),
                dto.getArrivalTime(),
                seats);
    }

    public static void main(String[] args) {
        FlightServiceImpl service = new FlightServiceImpl();

        FlightSearchRequest req = new FlightSearchRequest("Hồ Chí Minh", "Hà Nội", "2025-06-11", "", "businessclass");
        req.setAdultNumber(1);
        List<FlightSearchResponse> flights = service.searchFlightForBooking(req);
        for (FlightSearchResponse flight : flights) {
            System.out.println(flight);
        }
    }
}

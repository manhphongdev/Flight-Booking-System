package dao.interfaces;

import java.util.List;
import model.Passenger;

public interface IPassengerDAO extends IBaseDAO<Passenger> {

    List<Passenger> findByUserId(Long userId);

    List<Passenger> findByIdentityNumber(String identityNumber);

    List<Passenger> findByNationality(String nationality);
}

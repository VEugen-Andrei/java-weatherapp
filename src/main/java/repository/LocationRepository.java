package repository;

import tup.Location;
import tup.Parameter;

import java.util.List;

public interface LocationRepository {
    void addNewLocation(Location location);
    List<Location> searchLocations();

    List<Location> searchLocationByCity(String city);

    List<Parameter> searchParametersByCity(String city);

    double calculateAverageTemperature(String location);

    double calculateAveragePressure(String location);

    double calculateAverageHumidity(String location);
}

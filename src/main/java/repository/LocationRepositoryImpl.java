package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import tup.Location;
import tup.Parameter;

import java.util.ArrayList;
import java.util.List;

public class LocationRepositoryImpl implements LocationRepository {

    private final EntityManager entityManager;

    public LocationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addNewLocation(Location location) {
        entityManager.getTransaction().begin();
        entityManager.persist(location);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Location> searchLocations() {
        entityManager.getTransaction().begin();
        List<Location> locationList = entityManager.createQuery("Select l FROM Location l", Location.class).getResultList();
        entityManager.getTransaction().commit();
        return locationList;
    }

    @Override
    public List<Location> searchLocationByCity(String city) {
        entityManager.getTransaction().begin();
        List<Location> locationList = entityManager.createQuery("SELECT l FROM Location l WHERE city = :city", Location.class)
                .setParameter("city", city)
                .getResultList();
        entityManager.getTransaction().commit();
        return locationList;
    }

    @Override
    public List<Parameter> searchParametersByCity(String city) {
        List<Parameter> parameterList = new ArrayList<>();
        entityManager.getTransaction().begin();
        List<Location> locationList = entityManager.createQuery("SELECT l FROM Location l WHERE city = :city", Location.class)
                .setParameter("city", city)
                .getResultList();
        locationList.get(0).getParameterList().forEach(parameter -> parameterList.add(parameter));
        entityManager.getTransaction().commit();
        return parameterList;
    }

    @Override
    public double calculateAverageTemperature(String location) {
        Query query = entityManager.createQuery("SELECT AVG(p.temperature) FROM Parameter p LEFT JOIN p.location l WHERE l.city = :location")
                .setParameter("location", location);
        return (Double) query.getSingleResult();
    }

    @Override
    public double calculateAveragePressure(String location) {
        Query query = entityManager.createQuery("SELECT AVG(p.pressure) FROM Parameter p LEFT JOIN p.location l WHERE l.city = :location")
                .setParameter("location", location);
        return (Double) query.getSingleResult();
    }

    @Override
    public double calculateAverageHumidity(String location) {
        Query query = entityManager.createQuery("SELECT AVG(p.humidity) FROM Parameter p LEFT JOIN p.location l WHERE l.city = :location")
                .setParameter("location", location);
        return (Double) query.getSingleResult();
    }
}

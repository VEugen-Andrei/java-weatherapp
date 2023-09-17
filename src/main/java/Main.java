import repository.LocationRepositoryImpl;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import input.Input;
import tup.Location;
import tup.Parameter;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Parameter.class)
                .addAnnotatedClass(Location.class)
                .buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();
        LocationRepositoryImpl locationRepository = new LocationRepositoryImpl(entityManager);

        List<Location> locationList;

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("Select an option:");
            System.out.println("1 - Add a new city and parameters");
            System.out.println("2 - Show all cities");
            System.out.println("3 - Show parameters of a city");
            System.out.println("4 - The average of the parameters by city");
            System.out.println("5 - Add parameters for an existing city");
            System.out.println("0 - Exit");
            System.out.println();
            System.out.print("Enter an option: ");
            int userOption = scanner.nextInt();
            if (userOption == 0) {
                System.out.println("Goodbye!");
                break;
            }
            if (userOption == 1) {
                Location location = Input.addNewLocationByInput();
                Parameter parameter = Input.addNewParameterByInput();
                location.setParameterList(List.of(parameter));
                parameter.setLocation(location);
                locationRepository.addNewLocation(location);
            }
            if (userOption == 2) {
                System.out.print("All available cities: ");
                locationList = locationRepository.searchLocations();
                locationList.forEach(location -> System.out.println(location.getCity()));
            }
            if (userOption == 3) {
                System.out.println("Enter the desired city: ");
                Scanner scannerCity = new Scanner(System.in);
                String city = scannerCity.nextLine();
                List<Parameter> parameterList = locationRepository.searchParametersByCity(city);
                parameterList.forEach(location -> System.out.println("Temperature: " + location.getTemperature() + " \u00B0C," +
                        " Pressure: " + location.getPressure() + " hPa," +
                        " Humidity: " + location.getHumidity() + " \u0025"));
            }
            if (userOption == 4) {
                System.out.println("Enter the desired city: ");
                String city = scanner.next();
                List<Location> locationList2 = locationRepository.searchLocationByCity(city);
                for (Location location : locationList2) {
                    double averageTemperature = locationRepository.calculateAverageTemperature(String.valueOf(location.getCity()));
                    double averagePressure = locationRepository.calculateAveragePressure(String.valueOf(location.getCity()));
                    double averageHumidity = locationRepository.calculateAverageHumidity(String.valueOf(location.getCity()));

                    System.out.println("Location: " + location.getCity());
                    System.out.println("Average temperature: " + averageTemperature + " \u00B0C");
                    System.out.println("Average pressure: " + averagePressure + " hPa");
                    System.out.println("Average humidity: " + averageHumidity + " \u0025");
                    System.out.println();
                }
            }
            if (userOption == 5) {
                System.out.println("Enter the desired city: ");
                String city = scanner.next();
                Parameter parameter = Input.addNewParameterByInput();
                List<Location> locatieList = locationRepository.searchLocationByCity(city);
                Location savedLocation = locatieList.get(0);
                parameter.setLocation(savedLocation);
                entityManager.getTransaction().begin();
                entityManager.persist(parameter);
                entityManager.getTransaction().commit();
            }
        }
        while (true);
    }
}

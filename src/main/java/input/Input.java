package input;

import exception.LocationOutOfBound;
import exception.ParameterOutOfBound;
import tup.Continent;
import tup.Location;
import tup.Parameter;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Input {

    public static Location addNewLocationByInput() throws Exception {
        System.out.println("Type location data: (longitude;latitude;continent;country;city)");
        String input = new Scanner(System.in).nextLine();
        String[] fieldsOfLocation = input.split(";");
        if ((Arrays.stream(fieldsOfLocation).count() != 5)) {
            throw new Exception("Country and City are mandatory fields!");
        }
        float longitude = Float.parseFloat(fieldsOfLocation[0].trim());
        float latitude = Float.parseFloat(fieldsOfLocation[1].trim());
        Continent continent = Continent.valueOf(fieldsOfLocation[2].trim());
        String country = fieldsOfLocation[3].trim();
        if (country.isBlank()) {
            throw new LocationOutOfBound(country, "Country doesn't exist!");
        }
        String city = fieldsOfLocation[4].trim();
        if (city.isBlank()) {
            throw new LocationOutOfBound(city, "City doesn't exist!");
        }
        Location location = new Location(longitude, latitude, continent, country, city, null);
        return location;
    }

    public static Parameter addNewParameterByInput() throws ParameterOutOfBound {
        System.out.println("Type parameter data: (temperature;pressure;humidity");
        String input = new Scanner(System.in).nextLine();
        String[] fieldsOfParameter = input.split(";");
        int temperature = Integer.parseInt(fieldsOfParameter[0].trim());
        int pressure = Integer.parseInt(fieldsOfParameter[1].trim());
        int humidity = Integer.parseInt(fieldsOfParameter[2].trim());
        Date data = new Date();
        Parameter parameter = new Parameter(temperature, pressure, humidity, data, null);
        return parameter;
    }
}
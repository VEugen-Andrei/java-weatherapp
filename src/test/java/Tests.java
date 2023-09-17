import exception.ParameterOutOfBound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import tup.Location;
import tup.Parameter;

import java.util.Date;

import static tup.Continent.EUROPE;

public class Tests {

    public Tests() {
    }

    @Test
    public void TaraIsNotNull() throws Exception {
        Location location = new Location(45, 45, EUROPE, null, "Oras", null);
        Executable executable = () -> {
            location.getCountry().isBlank();
        };
        Assertions.assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void OrasIsNotNull() throws Exception {
        Location location = new Location(45, 45, EUROPE, "Romania", null, null);
        Executable executable = () -> {
            location.getCity().isBlank();
        };
        Assertions.assertThrows(NullPointerException.class, executable);
    }

    @Test
    public void ValidateLongitudine() throws Exception {
        Location location = new Location(45, 45, EUROPE, "Romania", "Brasov", null);
        Assertions.assertEquals(45, location.getLongitude());
    }

    @Test
    public void ValidateLatitudine() throws Exception {
        Location location = new Location(45, 50, EUROPE, "Romania", "Brasov", null);
        Assertions.assertEquals(50, location.getLatitude());
    }

    @Test
    public void ValidateContinent() throws Exception {
        Location location = new Location(45, 45, EUROPE, "Romania", "Brasov", null);
        Assertions.assertEquals(EUROPE, location.getContinent());
    }

    @Test
    public void ValidateTara() throws Exception {
        Location location = new Location(45, 45, EUROPE, "Romania", "Brasov", null);
        Assertions.assertEquals("Romania", location.getCountry());
    }

    @Test
    public void ValidateOras() throws Exception {
        Location location = new Location(45, 45, EUROPE, "Romania", "Brasov", null);
        Assertions.assertEquals("Brasov", location.getCity());
    }

    @Test
    public void ParameterIsOutOfBoundTemperature() {
        Executable executable = () -> {
            Parameter parameter = new Parameter(-500, 900, 50, new Date(), null);
        };
        Assertions.assertThrows(ParameterOutOfBound.class, executable);
    }

    @Test
    public void ParameterIsOutOfBoundPressure() {
        Executable executable = () -> {
            Parameter parameter = new Parameter(10, 1100, 50, new Date(), null);
        };
        Assertions.assertThrows(ParameterOutOfBound.class, executable);
    }

    @Test
    public void ParameterIsOutOfBoundHumidity() {
        Executable executable = () -> {
            Parameter parameter = new Parameter(10, 900, 101, new Date(), null);
        };
        Assertions.assertThrows(ParameterOutOfBound.class, executable);
    }
}
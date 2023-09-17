package tup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import exception.ParameterOutOfBound;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParameter;
    private int temperature;
    private int pressure;
    private int humidity;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "location_id")
    Location location;

    public Parameter(int temperature, int pressure, int humidity, Date date, Location location) throws ParameterOutOfBound {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.date = date;
        this.location = location;

        if (temperature < -89.2 || temperature > 56.7) {
            throw new ParameterOutOfBound(temperature, "Temperature has never existed on Earth! The value must be between -89.2 and 56.7");
        }
        if (pressure < 870 || pressure > 1083) {
            throw new ParameterOutOfBound(pressure, "Pressure has never existed on Earth! The value must be between 870 and 1083");
        }
        if (humidity < 5 || humidity > 100) {
            throw new ParameterOutOfBound(humidity, "Humidity has never existed on Earth! The value must be between 5 and 100");
        }
    }
}

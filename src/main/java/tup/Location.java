package tup;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import exception.CoordinateOutOfBound;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float longitude;
    private float latitude;
    @Enumerated(EnumType.STRING)
    private Continent continent;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "location")
    @Cascade(CascadeType.ALL)
    List<Parameter> parameterList;

    public Location(float longitude, float latitude, Continent continent, String country, String city, List<Parameter> parameterList) throws CoordinateOutOfBound {
        this.longitude = longitude;
        this.latitude = latitude;
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.parameterList = parameterList;
        if (longitude < -180 || longitude > 180) {
            throw new CoordinateOutOfBound(longitude, "The value for longitude must be between -180 and 180.");
        }
        if (latitude < -90 || latitude > 90) {
            throw new CoordinateOutOfBound(latitude, "The value for latitude must be between -90 and 90.");
        }
    }
}

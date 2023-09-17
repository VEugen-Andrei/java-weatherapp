package exception;

public class LocationOutOfBound extends Exception {
    private String location;

    public LocationOutOfBound(String location, String message) {
        super(message);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}

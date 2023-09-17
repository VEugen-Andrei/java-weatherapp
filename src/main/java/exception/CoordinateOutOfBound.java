package exception;

public class CoordinateOutOfBound extends Exception {
    private double coordinate;

    public CoordinateOutOfBound(double coordinate, String message) {
        super(message);
        this.coordinate = coordinate;
    }

    public double getCoordinate() {
        return coordinate;
    }

}

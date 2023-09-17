package exception;

public class ParameterOutOfBound extends Exception {
    private double parameter;

    public ParameterOutOfBound(double parameter, String message) {
        super(message);
        this.parameter = parameter;
    }

    public double getParameter() {
        return parameter;
    }
}
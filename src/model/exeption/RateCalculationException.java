package model.exeption;

public class RateCalculationException extends RuntimeException{

    public RateCalculationException() {
        super("Rate case calculation not supported");
    }

    public RateCalculationException(String message) {
        super(message);
    }
}

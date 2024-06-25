package YahiaLakrikba.Esercitazione25._6.exeptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super(id + " not found!");
    }
}
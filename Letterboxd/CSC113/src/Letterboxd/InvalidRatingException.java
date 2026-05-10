package Letterboxd;

public class InvalidRatingException extends Exception {

    public InvalidRatingException(String msg){
        super(msg);
    }

}
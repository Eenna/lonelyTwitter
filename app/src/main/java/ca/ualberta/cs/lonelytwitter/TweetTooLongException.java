package ca.ualberta.cs.lonelytwitter;

public class TweetTooLongException extends Exception {

    TweetTooLongException() {
        super("The message is too long! Please keep yout tweets within 140 characters");
        //super calls the methods in the base class
    }
}

package ca.ualberta.cs.lonelytwitter;

public class ImportantTweet extends Tweet {

    ImportantTweet() {
        super(); //gonna go to the Tweet class and do Tweet
    }

    ImportantTweet(String message) {
        super(message);
    }

    @Override
    public Boolean isImportant() {
        return true;
    }
}

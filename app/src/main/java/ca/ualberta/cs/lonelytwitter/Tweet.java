package ca.ualberta.cs.lonelytwitter; //groupings of related classes

import java.util.ArrayList;
import java.util.Date; //Date is an object - everything is an object

public abstract class Tweet implements Tweetable {// AFTER THE CLASS WAS WRITTEN WE MADE IT ABSTRACT
    //about state, and behaviour (does stuff) - every object will have this

    private Date date; //private - only this class has access to this datatype.
    //the access modifier (private) dictates who can access. If you leave it out, it
    //belongs to the package.
    //information hiding in terms of encapsulation - you don't want people to go in
    //and change the data
    //general rule of thumb, start with the most restrictive access

    private String message;
    private static final Integer MAX_CHARS = 140;
    ArrayList<Mood> moodList = new ArrayList<Mood>();
    //instead of having a hardcoded constant

    //ALL THESE VARIABLES are defined within their instance
    //static means that it's operating within the class, and it's not defined within the instance
    //final means that nothing else in the application can change it

    //in order to make new tweets, you need a CONSTRUCTOR
    //name of the class - ALWAYS
    //two - overloading - same name, different signatures

    Tweet() {
        this.date = new Date();
        //using the new keyword and call a constructor - it's getting memory allocated to it
        //without "this", you don't know what date it's calling
        //it's what makes it define/refer to the variables in this class

        this.message = "I am a default message";


    }

    Tweet(String message) {
        this.date = new Date();
        this.message = message;
        // ** Tweet myTweet = new Tweet("Blaerggh"); that's how you'd call it

    }

    //You don't want to be able to go in and modify the data
    //but you still want people to be able to look at it
    //Getters and Setters


    public Date getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() <= this.MAX_CHARS) {
            this.message = message;
        } else {
            //errors and exceptions differ in severity
            //throw an exception and then catch it
            throw new TweetTooLongException();
            // make this a subclass of the Exception Superclass
            //when you're making the new class
        }
    }

    public ArrayList listOfMoods(Mood mood){
        this.moodList.add(mood);
        return moodList;
    }

    public abstract Boolean isImportant();

    //abstract classes give coherence and structure, but aren't implemented themselves
}

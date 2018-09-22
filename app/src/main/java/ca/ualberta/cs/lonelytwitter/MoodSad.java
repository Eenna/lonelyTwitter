package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class MoodSad extends Mood {

    private String moodSad;

    public MoodSad(){super();}
    public MoodSad(Date date){super(date);}

    @Override
    public String theMoodIs() {
        return moodSad;
    }
}

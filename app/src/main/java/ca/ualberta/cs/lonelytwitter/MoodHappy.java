package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public class MoodHappy extends Mood {

    private String moodHappy;

    public MoodHappy(){ super();}
    public MoodHappy(Date date){super(date);}

    @Override
    public String theMoodIs() {
        return moodHappy;
    }
}

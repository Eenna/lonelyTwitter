package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	/** lab 3 new*/
	ArrayList<Tweet> tweetList;
	ArrayAdapter<Tweet> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { //on create shows layout of the main file
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//loadFromFile();
		//adapter.notifyDataSetChanged;

		bodyText = findViewById(R.id.body);
		Button saveButton = findViewById(R.id.save);
		Button clearButton = findViewById(R.id.clear); /**new*/
		oldTweetsList = findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { //will get triggered when you press the button
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); //string gets created

				Tweet tweet = new NormalTweet(text);	/**new*/
				tweetList.add(tweet); 					/**new*/

				saveInFile(); 							/**new*/
				adapter.notifyDataSetChanged(); 		/**new*/
				//saveInFile(text, new Date(System.currentTimeMillis())); //saves into the file //old
				//finish(); //closes the app once you press save LOL

			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				tweetList.clear();

				saveInFile();
				adapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	protected void onStart() { //like on create - called by itself

		super.onStart();
		loadFromFile(); /**new*/

		//String[] tweets = loadFromFile();
		/**creating an adapter - an interface that connect listView to your data
		//data can be coming from database or file, doesn't matter,list of strings, list of tweets,
		//or database object */
		/**ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);*/ //this is the string way - not the OO way

		adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
		//takes list view and binds to data

		oldTweetsList.setAdapter(adapter);//setting an adapter - bind the listView with the string //old
	}

	//private String[] loadFromFile() { //loads all the saved string tweets
	private void loadFromFile() { //loads all the saved Tweet class tweets
		// for strings: ArrayList<String> tweets = new ArrayList<String>();

		/** / you want to save the object not just the text
		//which is what this is doing:
		//ArrayList<Tweet> tweetlist = new ArrayList<Tweet>();
		//NormalTweet myTweet = new NormalTweet();
		//tweetlist.add(myTweet);
		//instance of normal tweet added to the array */

		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson(); //library to save objects
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();

			tweetList = gson.fromJson(in, listType); //in gets the file, and what is the type of the object


			/**String line = in.readLine(); //the old string way, no use anymore cause saving classes
			//reading each line is a loop and it's adding it into the tweets
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			} //done loading all the tweets into the array*/

		} catch (FileNotFoundException e) {
			//tweetList = new ArrayList<Tweet>();
			tweetList = new ArrayList<Tweet>(); //if file doesn't exist, create one

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return tweets.toArray(new String[tweets.size()]);
	}

	//private void saveInFile(String text, Date date) {//going to save text and date into the file
		//ArrayList<String> tweets = new ArrayList<>(); //from Shawna
		//ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
		//ArrayList<Mood> moodList = new ArrayList<Mood>();
		//NormalTweet myTweet = new NormalTweet();
		//tweetList.add(myTweet);
		//Happy myMood = new Happy();
		//moodList.add(myMood);
	private void saveInFile() {
		try {
			NormalTweet myTweet = new NormalTweet("");
			myTweet.setMessage("Yikes");

			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			//MODE_PRIVATE = can only be used by this application - MODE_APPEND was the old way

			/**fos.write(new String(date.toString() + " | " + text) //what it's saving
					.getBytes());*/

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

			Gson gson = new Gson();
			gson.toJson(tweetList, out);
			out.flush();

			fos.close(); //how the file saves the tweet
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TweetTooLongException e){
			e.printStackTrace();
		}

	}
}

/**instead of passing a string, we should be passing an object of tweet/mood and the object will be
saved - this is the object oriented way

*/
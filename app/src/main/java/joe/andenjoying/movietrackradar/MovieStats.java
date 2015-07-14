package joe.andenjoying.movietrackradar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MovieStats extends ActionBarActivity {

    TextView movieTitle;
    TextView mlDirectors;
    TextView mlReleaseDate;
    TextView mlRuntime;
    TextView mlRating;
    TextView mlGenre;
    TextView mlActors;
    TextView mlPlot;


    Button btnATL;
    String id;
    String title;
    String year;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_stats);



        movieTitle = (TextView) findViewById(R.id.mlTitle);
        mlDirectors = (TextView) findViewById(R.id.mlDirectors);
        mlReleaseDate= (TextView) findViewById(R.id.mlReleaseDate);
        mlRuntime = (TextView) findViewById(R.id.mlRuntime);
        mlRating = (TextView) findViewById(R.id.mlRating);
        mlGenre = (TextView) findViewById(R.id.mlGenre);
        mlActors= (TextView) findViewById(R.id.mlActors);
        mlPlot = (TextView) findViewById(R.id.mlPlot);

        btnATL = (Button) findViewById(R.id.atl);













        //Scrolling text view
        mlPlot.setMovementMethod(new ScrollingMovementMethod());

        String url = "http://www.omdbapi.com/?plot=full&r=json&t=";

        Intent i = getIntent();
        String passed = i.getStringExtra("UserTitleInput");


        passed = passed.replace(" ", "+");
        url = url + passed;

        Toast.makeText(getBaseContext(), "Querying: " + url, Toast.LENGTH_LONG).show();


        final String earl = url;
        new HttpHandler() {
            @Override
            public HttpUriRequest getHttpRequestMethod() {

                return new HttpGet(earl);
                // return new HttpPost(url)
            }

            @Override
            public void onResponse(String result) {


                ///Movie was found returned JSON data
                String chi = result.replace("{", "");
                chi = chi.replace("}", "");
                String[] chis = chi.split("\",\"");

                for (int i = 0; i < chis.length; i++) {
                    String[] cutter = chis[i].split(":\"");
                    chis[i] = cutter[1];
                }


                //Get Data into strings
                String mTitle = chis[0]; title = mTitle;
                String mYear = chis[1]; year = mYear;
                String mRating = chis[2];
                String mReleaseDate = chis[3];
                String mRuntime = chis[4];
                String mGenre = chis[5];
                String mDirectors = chis[6];
                String mWriter = chis[7];
                String mActors = chis[8];
                String mPlot = chis[9];
                String mLanguage = chis[10];
                String mCountry = chis[11];
                String mAwards = chis[12];
                String jPoster = chis[13];
                String mID = chis[17];  id = mID;
                String mType = chis[18];




                ///Presentation formatting
                mTitle = mTitle + " (" + mYear + ") ";

                ///Setting Data
                movieTitle.setText(mTitle);

                mlDirectors.setText("Directed by: " + mDirectors);
                mlReleaseDate.setText(mReleaseDate);
                mlRuntime.setText(mRuntime);
                mlRating.setText("Rated: " + mRating);
                mlGenre.setText("Genre: " + mGenre);
                mlActors.setText("Cast: " + mActors);
                mlPlot.setText(mPlot);



                new DownloadImageTask((ImageView) findViewById(R.id.mlPoster)).execute(jPoster);


            }

        }.execute();


        // Binding Click event to Button
        btnATL.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity

                //Toast.makeText(getBaseContext(), "Are you ready for this?", Toast.LENGTH_LONG).show();


                // Read data from SD card
                try {
                    File myFile = new File("/sdcard/mysd.txt");
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    String theString = aBuffer + title + " (" + year +") ";

                    //txtData.setText(theString);

                    ///File writing part
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(theString);
                    myOutWriter.close();
                    fOut.close();
                    myReader.close();


                    //Toast.makeText(getBaseContext(), "read SD  " + theString, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    ///No Such file or directory - add one
                    Toast.makeText(getBaseContext(), "Creating file and adding first id", Toast.LENGTH_SHORT).show();

                    ///Write the id to the file
                    try {
                        File myFile = new File("/sdcard/mysd.txt");
                        myFile.createNewFile();
                        FileOutputStream fOut = new FileOutputStream(myFile);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                        myOutWriter.append(id);
                        myOutWriter.close();
                        fOut.close();
                        Toast.makeText(getBaseContext(), "Done writing SD 'mysd.txt'", Toast.LENGTH_SHORT).show();
                    } catch (Exception e2) {
                        Toast.makeText(getBaseContext(), e2.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}


/*



File folder = new File(Environment.getExternalStorageDirectory() + "/TollCulator");
boolean success = true;
if (!folder.exists()) {
    success = folder.mkdir();
}
if (success) {
    // Do something on success
} else {
    // Do something else on failure
}

 */
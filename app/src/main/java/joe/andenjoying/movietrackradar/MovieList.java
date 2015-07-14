package joe.andenjoying.movietrackradar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Created by jelder on 6/15/15.
 */
public class MovieList extends Activity
{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);
        //String[] eatables = new String[]{, "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion","Pizza", "Olives", "Burger", "Sauce", "Cheeze", "Onion"};

        Vector<String> myVector=new Vector<String>(10,2);

        try {
            File myFile = new File("/sdcard/mysd.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String aDataRow = "";
            int i = 1;
            //String aBuffer = "";
            while ((aDataRow = myReader.readLine()) != null)
            {
                String blank = ". ";
                blank = i + blank;

                myVector.add(blank + aDataRow);
                i++;
            }
            myReader.close();
            Toast.makeText(getBaseContext(), "Done reading SD 'mysd.txt'", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }







        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.single_item ,myVector);
        lv.setAdapter(arrayAdapter);
    }





}
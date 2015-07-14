package joe.andenjoying.movietrackradar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MyTest extends ActionBarActivity {

    // GUI controls
    EditText txtData;
    Button btnWriteSDFile;
    Button btnReadSDFile;
    Button btnClearScreen;
    Button btnClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        // bind GUI elements with local controls
        txtData = (EditText) findViewById(R.id.txtData);
        txtData.setHint("Enter some lines of data here...");

        btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
        btnWriteSDFile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // write on SD card file data in the text box
                try {
                    File myFile = new File("/sdcard/mysd.txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fOut);
                    myOutWriter.append(txtData.getText());
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(),
                            "Done writing SD 'mysd.txt'",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }// onClick
        }); // btnWriteSDFile

        btnReadSDFile = (Button) findViewById(R.id.btnReadSDFile);
        btnReadSDFile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // write on SD card file data in the text box
                try {
                    File myFile = new File("/sdcard/mysd.txt");
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    txtData.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(getBaseContext(),
                            "Done reading SD 'mysd.txt'",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }// onClick
        }); // btnReadSDFile

        btnClearScreen = (Button) findViewById(R.id.btnClearScreen);
        btnClearScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // clear text box
                txtData.setText("");
            }
        }); // btnClearScreen



    }// onCreate




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_list, menu);
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
}

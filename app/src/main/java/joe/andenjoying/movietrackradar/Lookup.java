package joe.andenjoying.movietrackradar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lookup extends Activity {
    /** Called when the activity is first created. */
    EditText inputName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup);

        inputName = (EditText) findViewById(R.id.userinputTitle);

        Button go = (Button) findViewById(R.id.goButton);

        // Binding Click event to Button
        go.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity

                //Toast.makeText(getBaseContext(), "Paramter 1:" , Toast.LENGTH_LONG).show();
                Intent nextScreen = new Intent(getApplicationContext(), MovieStats.class);
                nextScreen.putExtra("UserTitleInput", inputName.getText().toString());
                startActivity(nextScreen);
                
            }
        });


        Button btnClose = (Button) findViewById(R.id.btnClose);

        // Binding Click event to Button
        btnClose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Closing SecondScreen Activity
                finish();
            }
        });

    }
}
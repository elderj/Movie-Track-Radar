package joe.andenjoying.movietrackradar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends Activity {
    // Initializing variables
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);







        findViewById(R.id.movieLookupButton).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Inform the user the button has been clicked
                Toast.makeText(getBaseContext(), "Movie Lookup Button", Toast.LENGTH_LONG).show();
                Intent movieLookupButton = new Intent(getApplicationContext(), Lookup.class);
                startActivity(movieLookupButton);
            }
        });


        findViewById(R.id.myTestButton).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Toast.makeText(getBaseContext(), "Testing and enjoying" , Toast.LENGTH_LONG).show();
                Intent myListButton = new Intent(getApplicationContext(), MyTest.class);
                startActivity(myListButton);
            }
        });



        findViewById(R.id.movieListButton).setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Inform the user the button has been clicked
                Toast.makeText(getBaseContext(), "My List" , Toast.LENGTH_LONG).show();
                Intent movieListButton = new Intent(getApplicationContext(), MovieList.class);
                startActivity(movieListButton);
            }
        });




    }
}
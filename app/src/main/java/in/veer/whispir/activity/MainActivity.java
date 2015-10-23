package in.veer.whispir.activity;



import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.aabit.design.R;

import in.veer.whispir.fragment.Drawerfragment;

public class MainActivity extends ActionBarActivity {

   Toolbar toolbar;
    ListView listView;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating The Toolbar and setting it as the Toolbar for the activity


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Drawerfragment drawerFragment = (Drawerfragment) getSupportFragmentManager().findFragmentById(R.id.sliderFragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

    }

}
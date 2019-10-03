package com.ceng319.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Secondary extends AppCompatActivity {

    private TextView mTextMessage;

    // Generated
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish(); // finish the current activity.
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_datasection);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_alarms);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); // Make sure this method is before super.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // TODO: Step 7. Display the up to Home button on left top corner See Gist on GitHub. Finihed
        // Display the home icon on the activity ActionBar; needs the appcompat support library
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // TODO: Step 8. Get and display the message from MainActivity. Finished
        // Get the message from main.
        processData();
    }

    public void processData()
    {
        // TODO: Step 9. Get the data and display here. Finished
        Bundle extras = getIntent().getExtras();
        if (extras == null) { return; } // get data via the key
        String value1 = extras.getString("EXTRA_MESSAGE");
        if (value1 != null) { // do something with the data
            TextView mTextMessage = (TextView) findViewById(R.id.message1);
            mTextMessage.setText(value1);

        }
    }

}

package com.ceng319.intents;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Third extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme); // Make sure this method is before super.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        // TODO: Step 11. Again, display the up home button here. Finished
        // Display the up to Home button.
        // And Change the icon to a check mark
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_check_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Remove the focus from EditText
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // TODO: Step 12: Deal with the DatePicker Object Finished
        // Deal with the setting of datepicker
        DatePicker datePicker = findViewById(R.id.datePicker);
        datePicker.setSpinnersShown(false);   // Remove the spinner if this is "false"

        // TODO: Step 13: Add  OnClickListener here for the submit button. to be continued.....
        // Program the submit button tap.
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // TODO step 14: Return the text back to the calling activity. Finished
        EditText editText = findViewById(R.id.editText3);
        String str = String.valueOf(editText.getText());
        Intent message = new Intent();
        message.putExtra("returnVal_from_Third", str);

        // TODO Step 15: Return the datepicker result back to the calling activity.
        // get the values for day of month , month and year from a date picker
        DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker); // initiate a date picker
        String day = Integer.toString(datePicker.getDayOfMonth());
        String month = Integer.toString((datePicker.getMonth() + 1));
        String year = Integer.toString(datePicker.getYear());
        message.putExtra("Day", day);
        message.putExtra("Month", month);
        message.putExtra("Year", year);
        // Activity finished ok, return the data
        setResult(RESULT_OK, message);
        // display the values by using a toast
       //  Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
        finish();  // finish this current activity and return to the former activity.
    }


}

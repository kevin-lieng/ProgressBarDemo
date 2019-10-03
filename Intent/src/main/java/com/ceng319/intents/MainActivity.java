package com.ceng319.intents;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Month;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 2000;
    private Menu menu;
    private boolean mclicked = false;
    Toolbar mToolbar;
    FloatingActionButton mFab;
    FloatingActionButton mFab1;
    EditText mediText2;
    TextView mtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       setTheme(R.style.AppTheme_NoActionBar); // Make sure this method is before super.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        setSupportActionBar(mToolbar);


        // TODO: 1. Assign a dialing intent here. Finished
        // How to use the FloatingActionButton.
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do you want to call someone?", Snackbar.LENGTH_LONG)
                        .setAction("DIAL", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                call();
                            }
                        }).show();
            }
        });

        // TODO: 2. Add another FAB and assign the email intent. Finished
        mFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do you want to send message?", Snackbar.LENGTH_LONG)
                        .setAction("TEXT", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                email();
                            }
                        }).show();
            }
        });
    }

    private void findAllViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mediText2 = (EditText) findViewById(R.id.editText2);
        mtextView = (TextView) findViewById(R.id.returnedDate);
        mFab1 = (FloatingActionButton) findViewById(R.id.fab1);
    }

    /**
     * Prepare the Screen's standard options menu to be displayed.  This is
     * called right before the menu is shown, every time it is shown.  You can
     * use this method to efficiently enable/disable items or otherwise
     * dynamically modify the contents.
     * <p>
     * <p>The default implementation updates the system menu items based on the
     * activity's state.  Deriving classes should always call through to the
     * base class implementation.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO: 3. Add a favorite icon in the menu. Finished
        this.menu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    // To make a phone call, you need to have the permission in manifest first, and
    // use explicit intent to call.
    private void call(){
        // TODO: 4. Finish this method Finished
        try{
            String phoneNumber = "+14166753111";
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }catch (SecurityException error)
        {
            Log.e("MapleLeaf", "Call Failed");
        }

    }

    // To make a phone call, you need to have the permission in manifest first, and
    // use explicit intent to call.
    private void email(){
        // TODO: 5. Finish this method - Finished
        try{
            String message = String.valueOf(mediText2.getText());
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }catch (SecurityException error)
        {
            Log.e("MapleLeaf", "Call Failed");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        // TODO: 6. Finish all the options for the menu selections. Finished
        if (id == R.id.action_settings) {
            Intent intent0 = new Intent(this, SettingScreen.class);
            startActivity(intent0);
            return true;
        }
        else if (id == R.id.action_secondary)
        {
            Intent intent1 = new Intent(this, Secondary.class);
            intent1.putExtra("EXTRA_MESSAGE", String.valueOf(mediText2.getText()));
            startActivity(intent1);
        }
        else if (id == R.id.action_third)
        {
            Intent intent2 = new Intent(this, Third.class);
            startActivityForResult(intent2, REQUEST_CODE);
        }
        else if (id == R.id.action_quit) {
                finishAndRemoveTask();
        }
        else if (id == R.id.action_favorite)
        {
            // MenuItem v = findViewById(R.id.action_favorite);
            MenuItem menuItem = menu.getItem(4);
            if (menuItem.getItemId() == R.id.action_favorite)
            {
                if (mclicked)
                    menuItem.setIcon(R.drawable.ic_favorite_border_black_24dp);
                else
                    menuItem.setIcon(R.drawable.ic_favorite_black_24dp);

                mclicked = !mclicked;

            }// v.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_shopping_cart_black_24dp));
            Toast.makeText(this, "You clicked the heart", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_CODE) &&
                (resultCode == RESULT_OK))  // this must be the result from the Third activity.
        {
            if (data.hasExtra("returnVal_from_Third")) {
                Toast.makeText(this, "Your message from the third activity is:\n" + data.getExtras().getString("returnVal_from_Third"),
                        Toast.LENGTH_LONG).show();
            }
            if (data.hasExtra("Day") && data.hasExtra("Month") && data.hasExtra("Year")){
                String str = "Returned Date: " + data.getExtras().getString("Day");
                str = str+ "/" + data.getExtras().getString("Month");
                str = str+ "/" + data.getExtras().getString("Year");


                mtextView.setText(str);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

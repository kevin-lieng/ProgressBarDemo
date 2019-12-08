package com.ceng319.progressbardemo;

// TODO: Make sure the import the progress bar library
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mSub1Button;
    private Button mSub5Button;
    private Button mAdd1Button;
    private Button mAdd5Button;
    private Button mResetButton;
    private ImageView mProgressImage;
    private TextView mProgressValue;
    private int progress;
    boolean animate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Retrieve all the views including the progress bar
        findAllViews();

        // TODO: Setup the maximum value of the progress bar
        setmProgressBar();

        // TODO: Retrieve the beginning progress level of progressBar
        progress = mProgressBar.getProgress();

        // TODO: Setup the button event listeners
        mSub1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSub1Button();
                checkProgress();
            }
        });

        mSub5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSub5Button();
                checkProgress();
            }
        });

        mAdd1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdd1Button();
                checkProgress();
            }
        });

        mAdd5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdd5Button();
                checkProgress();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmResetButton();
                checkProgress();
            }
        });
    }

    public void findAllViews() {
        // TODO: Retrieve all the views on the layout
        mSub1Button = (Button) findViewById(R.id.sub1Button);
        mSub5Button = (Button) findViewById(R.id.sub5Button);
        mAdd1Button = (Button) findViewById(R.id.add1Button);
        mAdd5Button = (Button) findViewById(R.id.add5Button);
        mProgressImage = (ImageView) findViewById(R.id.progressImage);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mResetButton = (Button) findViewById(R.id.resetBtn);
        mProgressValue = (TextView) findViewById(R.id.progressValue);
    }

    // TODO: Function to create a maximum value for progress bar
    // Default is 100 but can be set to other values.
    public void setmProgressBar() {
        mProgressBar.setMax(100);
    }

    /* Please Note: That mProgressBar.setProgress does not have to be used,
    another function exists to increment such as mProgressBar.incrementProgressBy(int); however,
    method to decrement progress bar does not exist and setProgress(int) should be used.
    The follow functions below uses, setProgress(int, boolean), where the boolean is if the progress
    bar should animate upon change.

    Animation setting of progress bar is only available for Android devices that run Android SDK 24.
     */
    // TODO: Function to decrease progress bar by 1
    public void setSub1Button() {
        if(progress > 0) {
            progress = progress - 1;
            mProgressBar.setProgress(progress, animate);
        }
        else {
            Toast.makeText(this, getText(R.string.minWarning), Toast.LENGTH_LONG)
                    .show();
        }
    }

    // TODO: Function to decrease progress bar by 5
    public void setSub5Button() {
        if(progress > 4) {
            progress = progress - 5;
            mProgressBar.setProgress(progress, animate);
        }
        else {
            Toast.makeText(this, getText(R.string.minWarning), Toast.LENGTH_LONG)
                    .show();
        }
    }

    // TODO: Function to increase progress bar by 1
    public void setAdd1Button() {
        if(progress < 100) {
            progress = progress + 1;
            mProgressBar.setProgress(progress, animate);
        }
        else {
            Toast.makeText(this, getText(R.string.maxWarning), Toast.LENGTH_LONG)
                    .show();
        }
    }

    // TODO: Function to increase progress bar by 5
    public void setAdd5Button() {
        if(progress < 96) {
            progress = progress + 5;
            mProgressBar.setProgress(progress, animate);
        }
        else if(progress > 95 && progress < mProgressBar.getMax()) {
            progress = mProgressBar.getMax();
            mProgressBar.setProgress(progress, animate);
        }
        else {
            Toast.makeText(this, getText(R.string.maxWarning), Toast.LENGTH_LONG)
                    .show();
        }
    }

    // TODO: Function to reset progress bar to 0
    public void setmResetButton(){
        progress = 0;
        mProgressBar.setProgress(progress, animate);
    }

    /*
    Please note: Within the following function, checkProgress(), there exists a usage of a method called
    setProgressTintList that changes the color of the progress bar itself. This color change during
    changes is only available for android devices running Android SDK 23.
     */
    // TODO: Function that checks the progress and updates progress bar and other things on the layout
    public void checkProgress() {
        mProgressValue.setText(progress + "%");
        if(progress < 50)
        {
            mProgressImage.setImageDrawable(getDrawable(R.drawable.sadpikachu));
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressLow)));
        }
        else if(progress > 50 && progress < mProgressBar.getMax()) {
            mProgressImage.setImageDrawable(getDrawable(R.drawable.neutralpikachu));
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressMid)));
        }
        else if (progress == mProgressBar.getMax()){
            mProgressImage.setImageDrawable(getDrawable(R.drawable.happypikachu));
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressMax)));
        }
    }

}

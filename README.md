# **Progress Bar (Android Component)**

## **Introduction**

-------------------------------------------------------------------------

The Progress Bar is a widget in a shape of a bar that fills upon the impending completion of an action. The major function of this component is to give a visual representation of an action completing or moving forward. In this document we will be discussing the different methods and attributes that are available to use for the progress bar from SDK 26 and below. There are two main progress bars that are used inside this component, the horizontal and spinning progress bar. The horizontal progress bar gives a better visual representation of showing progress; whereas, the spinning progress bar gives a representation of loading data. 

The progress bar that will be mostly discussed in this blog will be the usage of the horizontal bar. This progress bar can be seen used during installation of new applications and updates of the Android Operating System. It can also be used in a variety of other ways; however, this blog will explain the basic usages of this component.

## **History** 
-------------------------------------------------------------------------
The Progress Bar component was introduced to the Android Operating System as early as the first version of Android or SDK 1. It could be found in the **android.widget** library. Although an old component or widget, the usefulness of its functionality remains to this date. Although this component is old, it is still be constantly updated as Android improves their operating system. Many other methods are being added with each update.

## **Major Methods**
-------------------------
The major methods in using the progress bar are the following:
    
**ProgressBar(Context context, AttributeSet barAttributes)**

    1. In which the context is how and where you want the progress bar to be used. 
    2. AttributeSet is the different dimensions on how you want to set up the progress bar. 

    Example: ProgressBar(this, setMinWidth(value), setMaxWidth(value));


It can be used normally without these context and attributes set through the usage of:
    
**ProgresBar(Context context)**

    However, this creates the a progress bar with the default minimum of 0 and maximum of 100. 

Other methods that may be used with the progress bar include:

**ProgressBar.setProgress(int value)**
        
    This method when used sets the progress bar to a certain value and displays that value onto the progress bar in terms of fullness. 

    Example: ProgressBar.setProgress(50) 
    This would set the bar to halfway full.

**ProgressBar.setProgress(int value, boolean toAnimate)**
    
    This method was introduced in SDK 24 for the usage of showing an animated progress as it decreases or increases. 
    Without this animation, the bar fills up staticly; however, with the animation as the bar changes the 
    movement of the bar appears to be more fluid. 

    toAnimate is a boolean that can be set to true or false.

    Example: ProgressBar.setProgress(50, true)
    This would animate the bar increasing or decreasing to the halfway point. 

**ProgressBar.getProgress()**

    This method retrieves the current progress value and returns that value as an integer. This value can be saved 
    in a variable and displayed as text if needed. 

**ProgressBar.incrementProgressBy(int value)**
    
    This method increments / increases the Progress Bar by the value that is given to it. Not to be confused 
    with setProgress(int value), this method increases the bar by a value rather than setting the bar to a value. 

    Currently there is no decrement method that is similar to incrementProgressBy(int value).

**ProgressBar.setMin(int value)**

    This method is introduced in SDK 26 in which you are able to set the lower limit of the progress bar to a certain integer value.

**ProgressBar.setMax(int value)** 

    Like the above method, this method allows you to change the upper limit of the progress bar to a positive integer. 

## **Major Attributes**
----------------------------------------------------------------
Attributes for this component can be changed either in code or through the activity XML file. 

Some of the major attributes are: 

**minWidth / maxWidth (ProgressBar.setMinWidth(value) / ProgressBar.setMaxWidth(value)**

    By editing these values inside the XML file or through the code, one can change the width dimensions of the progress bar. 

    Code usage: ProgressBar.setMinWidth(300); 
    This would set the minimum width of the Progress Bar to an integer value of DP (Density-Independent Pixels).

**minHeight / maxHeight (ProgressBar.setMinHeight(value) / ProgressBar.setMaxHeight(value)**

    By editing these values inside the XML file or through the code, one can change the height dimensions of the progress bar. 

    Code usage: ProgressBar.setMinHeight(300); 
    This would set the minimum height of the Progress Bar to an integer value of DP (Density-Independent Pixels).

**ProgressTintList (ProgressBar.setProgressTintList(ColorTintList))**

    The changing of the attributes here, allows for the changing of the color of the bar within the progress bar. This color can be changed multiple times if need be.  

    Code usage: 
    ProgressBar.setProgressTintList(ColorTintList.valueOf(getColor(R.color.colorString)));

    This would change the color of the bar to the color within the string inside the colors.XML

## **Sample Functions Using Methods**
---------------------------------------------------------------
**checkProgress()**
    
    void checkProgress() {
        mProgressValue.setText(progress + "%");
        if(progress < 50)
        {
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressLow)));
        }
        else if(progress > 50 && progress < mProgressBar.getMax()) {
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressMid)));
        }
        else if (progress == mProgressBar.getMax()){
            mProgressBar.setProgressTintList(ColorStateList.valueOf(getColor(R.color.progressMax)));
        }
    }

    /* This function checks the progress of the progress bar and changes the color of the progress bar at certain intervals */

**setAdd5ButtonEvent()**

    void setAdd5ButtonEvent() {
        if(progress < mProgressBar.getMax()) {
            progress = progress + 5;
            mProgressBar.setProgress(progress, animate);
            // mProgressBar.incrementProgressBy(5);
        }
        else {
            Toast.makeText(this, getText(R.string.maxWarning), Toast.LENGTH_LONG)
                    .show();
        }
    }

    /* This event would be implemented could be implemented to increment the progress bar by 5. If the progress bar is already full, it would send out a toast message. 
    Please Note: That the incrementProgressBy(5) can replace the first two lines after the if statement. */ 

## **Demo App With ProgressBar**
----------------------------------------------------------------------------
A full demo is made available when the code within this repository is cloned or downloaded. The demo can run in either emulator or actual android device. 

Please refer to the clone or download button at the top of the page. 

[Click Here To Go To The Top!](#top)

## **References**
----------------------------------------------------------------------------
ProgressBar. (n.d.). Retrieved from https://developer.android.com/reference/android/widget/ProgressBar.
package com.example.myapplication24_04;

import android.widget.CompoundButton;
import android.widget.Toast;

public class MyListener implements CompoundButton.OnCheckedChangeListener {

    MainActivity activity;

    public MyListener(MainActivity activity)
    {
        this.activity = activity;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked)
//        {
//            activity.agreeResult = 100;
//            Toast.makeText(activity,"check box is checked", Toast.LENGTH_LONG).show();
//        }
//
//        else
//        {
//            activity.agreeResult = 0;
//            Toast.makeText(activity,"check box is unchecked :(", Toast.LENGTH_LONG).show();
//        }
    }
}

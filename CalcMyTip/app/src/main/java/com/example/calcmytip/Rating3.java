package com.example.calcmytip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

public class Rating3 extends RatingBase {

    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating3);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this,  Bill.class );
        intent.putExtra(RatingBase.KEY_TIP,calculateRating(baseTip));
        intent.putExtra(MainActivity.KEY_RESTAURANTNAME, restName);
        startActivity(intent);
    }

    @Override
    protected String calculateRating(String base){
        int iBase = Integer.parseInt(base);
        int rank = 0;
        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButton1:
                rank = 6;
                break;
            case R.id.radioButton2:
                rank = 4;
                break;
            case R.id.radioButton3:
                rank = 2;
                break;
        }
        return (String.valueOf(iBase + rank));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent nextActivity;

        int id = item.getItemId();
        if (id == R.id.home) {
            nextActivity = new Intent(this,MainActivity.class);
        }
        else if (id == R.id.rating1) {
            nextActivity = new Intent(this,Rating1.class);
        }
        else if (id == R.id.rating2) {
            nextActivity = new Intent(this,Rating2.class);
        }
        else
        {
            nextActivity = new Intent(this,Rating3.class);
        }

        startActivity(nextActivity);

        return super.onOptionsItemSelected(item);

    }

}

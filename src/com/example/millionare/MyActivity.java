package com.example.millionare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import static com.example.millionare.R.drawable.calive;

public class MyActivity extends Activity  implements View.OnClickListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


    }


    @Override
    public void onClick(View view)
    {
       // mpaudio.pause();
        finish();
        moveTaskToBack(true);
    }

    public void onNew(View view)
    {
        setContentView(R.layout.second);
        Intent intent=new Intent(MyActivity.this,SecondActivity.class);
        startActivity(intent);
        MyActivity.this.finish();

    }



}

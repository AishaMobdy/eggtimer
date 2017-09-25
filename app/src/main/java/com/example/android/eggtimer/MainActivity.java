package com.example.android.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Boolean counterIsActive=false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        seekBar.setProgress(0);
        textView.setText("0.00");
        countDownTimer.cancel();
        button.setText("go");
        seekBar.setEnabled(true);
        counterIsActive=false;
    }

    public void updateTimer(int secondsleft)
    {
        int minutes=(int)secondsleft/60;
        int seconds=secondsleft-minutes*60;
        String seconedString=Integer.toString(seconds);
         if (seconds <=9)
        {
            seconedString="0"+seconedString;
        }
        textView.setText(Integer.toString(minutes)+":"+seconedString);
    }

    public void tapped(View view)
    {
        if(counterIsActive==false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            button.setText("stop");
           countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pew);
                    mediaPlayer.start();
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }
            }.start();
        }
        else
        {
          resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=(SeekBar)findViewById(R.id.TimerseekBar);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

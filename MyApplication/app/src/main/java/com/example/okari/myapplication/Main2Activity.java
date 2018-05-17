package com.example.okari.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Main2Activity extends AppCompatActivity {

    private Button bt;
    private TextView time_count;
    private Bundle bundle;
    private String picture;
    private  String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //選告一個bundle來接這個activity的intent的bundle
        bundle = this.getIntent().getExtras();
        //透過bundle的key值 取出先前頁面所選的選項
        picture = bundle.getString("picture");
        switch (picture){
            case "1元小薯":
                getWindow().setBackgroundDrawableResource(R.drawable.french_fries);
                break;
            case "10元麥香魚":
                getWindow().setBackgroundDrawableResource(R.drawable.fish);
                break;
            case "1元冰淇淋":
                getWindow().setBackgroundDrawableResource(R.drawable.ice_cream);
                break;
        }
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(buttonlistener);
    }
    private Button.OnClickListener buttonlistener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            time_count = (TextView) findViewById(R.id.time_count);
            time_count.setVisibility(View.VISIBLE);
            bt.setVisibility(View.INVISIBLE);
            new CountDownTimer(120000, 1000){
                @Override
                public void onFinish() {
                    Intent intent = new Intent();
                    intent.setClass(Main2Activity.this, MainActivity.class);
                    bundle.putString("picture",picture);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    int time = (int)millisUntilFinished/1000;
                    int minute = time/60;
                    int second = time%60;
                    String content = "<img src='" + R.drawable.time_icon + "'>" + " 優惠倒數: " + minute + ":" + second;
                    time_count.setText(Html.fromHtml(content, new Html.ImageGetter() {
                        @Override
                        public Drawable getDrawable(String source) {
                            int id = Integer.parseInt(source);
                            Drawable drawable = getResources().getDrawable(id);
                            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()-30);
                            Log.d(TAG, "get Height: " + drawable.getIntrinsicHeight());

                            return drawable;
                        }
                    },null));
                }
            }.start();
        }

    };
}

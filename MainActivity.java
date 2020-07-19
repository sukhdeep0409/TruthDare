package com.example.truthdare;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView imgv;
    private Random random = new Random();
    private int lastDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        imgv = findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin(view);
            }
        });

    }

    private void spin(View v) {

        int newDirection = random.nextInt(2400);
        float pivotX = imgv.getWidth() >> 1;        //imgv.getWidth()/2;
        float pivotY = imgv.getHeight() >> 1;       //imgv.getHeight()/2;

        Animation rotate = new RotateAnimation(lastDirection, newDirection, pivotX, pivotY);
        rotate.setDuration(2000);
        rotate.setFillAfter(true);       //keeps the animation there
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                btn.setEnabled(false);
                btn.setBackground(Drawable.createFromPath("@drawable/button_disabled"));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btn.setEnabled(true);
                btn.setBackground(Drawable.createFromPath("@drawable/custom_button"));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        lastDirection = newDirection;

        imgv.startAnimation(rotate);
    }
}
package com.barkerjeb.liftmeup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button main, stat;
    boolean isMain = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main_pager);
        stat = findViewById(R.id.statter);
    }

    public void toggles(View view) {
        Button b = (Button)(view);
        if (isMain && b.getText().equals("Stats"))
        {
//            stat.setBackgroundTintList(ColorStateList.valueOf(R.color.colorAccent));
//            main.setBackgroundTintList(ColorStateList.valueOf(R.color.colorPrimary));

            Drawable buttonDrawable = stat.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(stat.getContext(), R.color.colorAccent));
            stat.setBackground(buttonDrawable);

            Drawable button2Drawable = main.getBackground();
            button2Drawable = DrawableCompat.wrap(button2Drawable);
            DrawableCompat.setTint(button2Drawable, ContextCompat.getColor(main.getContext(), R.color.colorPrimary));
            main.setBackground(button2Drawable);
            isMain = false;
        }
        else if (!isMain && b.getText().equals("Main Page"))
        {
//            main.setBackgroundTintList(ColorStateList.valueOf(R.color.colorAccent));
//            stat.setBackgroundTintList(ColorStateList.valueOf(R.color.colorPrimary));
            Drawable button2Drawable = main.getBackground();
            button2Drawable = DrawableCompat.wrap(button2Drawable);
            DrawableCompat.setTint(button2Drawable, ContextCompat.getColor(main.getContext(), R.color.colorAccent));
            main.setBackground(button2Drawable);

            Drawable buttonDrawable = stat.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(stat.getContext(), R.color.colorPrimary));
            stat.setBackground(buttonDrawable);
            isMain = true;
        }
    }
}
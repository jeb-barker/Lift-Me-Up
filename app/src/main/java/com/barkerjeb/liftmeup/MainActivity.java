package com.barkerjeb.liftmeup;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Button main, stat;
    boolean isMain = true;
    LiftManager lm;
    FrameLayout fragment;
    StatManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main_pager);
        stat = findViewById(R.id.statter);
        lm = new LiftManager();
        fragment = findViewById(R.id.fragment);
        sm = new StatManager();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, lm).commit();
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



            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment, sm);
            transaction.addToBackStack(null);
            transaction.commit();
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



            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment, lm);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
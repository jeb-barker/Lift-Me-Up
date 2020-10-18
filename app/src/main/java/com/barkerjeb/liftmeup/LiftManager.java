package com.barkerjeb.liftmeup;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LiftManager extends Fragment {
    private static final String TAG = "MAIN_FRAGMENT";
    public LiftManager() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weight_layout, container, false);
    }

    EditText date, amount, rns, type;
    Gson gson = new GsonBuilder().create();
    static LinearLayout liftMaker;
    static LinearLayout liftContainer;
    static Button buttonMaker;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        date = view.findViewById(R.id.date);
        amount = view.findViewById(R.id.amount);
        rns = view.findViewById(R.id.repsnsets);
        type = view.findViewById(R.id.lift);

        liftMaker = view.findViewById(R.id.liftMaker);
        liftContainer = view.findViewById(R.id.lift_container);
        System.out.println(getString(R.string.lifts));
        Lifts data = gson.fromJson(getString(R.string.lifts), Lifts.class);
        for(Lifts.Lift l: data.list_data){
            createLiftView(l);
        }
        buttonMaker = view.findViewById(R.id.button_maker);
    }

    @SuppressLint("SetTextI18n")
    public void createLiftView(Lifts.Lift l){
        TextView lif = new TextView(getActivity());
        lif.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lif.setText(" Workout on, " + l.date + ":\n" + l.amount + " " + l.type + ", " + l.repsndsets + ".");
        lif.setGravity(Gravity.CENTER);

        lif.setBackgroundColor(Color.parseColor(String.valueOf(R.color.colorToggled)));

        ((LinearLayout)liftContainer).addView(lif);
    }

    public static void submitLift(View view){
        Lifts data = new Lifts();

        liftMaker.setVisibility(View.INVISIBLE);
        liftContainer.setVisibility(View.VISIBLE);
        buttonMaker.setVisibility(View.VISIBLE);
    }

    public static void newLift(View view){
        liftMaker.setVisibility(View.VISIBLE);
        liftContainer.setVisibility(View.INVISIBLE);
        buttonMaker.setVisibility(View.INVISIBLE);
    }
}

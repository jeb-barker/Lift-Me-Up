package com.barkerjeb.liftmeup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    LinearLayout liftMaker;
    LinearLayout liftContainer;
    Button buttonMaker;
    ScrollView lmmm;
    Lifts data;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.date);
        amount = view.findViewById(R.id.amount);
        rns = view.findViewById(R.id.repsnsets);
        type = view.findViewById(R.id.lift);

        liftMaker = view.findViewById(R.id.liftMaker);
        Button sub = view.findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLift(v);
            }
        });
        liftContainer = view.findViewById(R.id.lift_container);
        buttonMaker = view.findViewById(R.id.button_maker);
        buttonMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newLift(v);
            }
        });
        //System.out.println(getString(R.string.lifts));
        lmmm = view.findViewById(R.id.lmmm);

        sharedPreferences = view.getContext().getSharedPreferences("Values", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        data = gson.fromJson(sharedPreferences.getString("lifts", "{list_data:[]}"), Lifts.class);

        for(Lifts.Lift l: data.list_data){
            createLiftView(l);
        }

    }

    @SuppressLint("SetTextI18n")
    public void createLiftView(Lifts.Lift l){
        TextView lif = new TextView(getActivity());
        lif.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        lif.setText(" Workout on " + l.date + ":\n" + l.amount + " " + l.type + ", " + l.repsndsets + ".");
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)lif.getLayoutParams();
        params.setMargins(0, 0, 0, 30);
        lif.setLayoutParams(params);
        lif.setTextSize(35);
        lif.setGravity(Gravity.CENTER);

        lif.setBackgroundResource(R.drawable.faded_text);

        ((LinearLayout)liftContainer).addView(lif);
    }

    public void submitLift(View view){
        Lifts.Lift data2enter = new Lifts.Lift(""+date.getText(), ""+amount.getText(), ""+rns.getText(), ""+type.getText());
        date.setText("");
        amount.setText("");
        rns.setText("");
        type.setText("");
        data.list_data.add(data2enter);
        createLiftView(data2enter);
        System.out.println(gson.toJson(data.list_data));
        editor.putString("lifts", "{\"list_data\":" + gson.toJson(data.list_data) + "}");
        editor.apply();
        updateVis();
    }
    public void updateVis(){
        lmmm.setVisibility(View.INVISIBLE);
        liftContainer.setVisibility(View.VISIBLE);
        buttonMaker.setVisibility(View.VISIBLE);
    }

    public void newLift(View view){//deprecated
        lmmm.setVisibility(View.VISIBLE);

        liftContainer.setVisibility(View.INVISIBLE);
        buttonMaker.setVisibility(View.INVISIBLE);
    }


}

package com.barkerjeb.liftmeup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatManager extends Fragment {
    private static final String TAG = "MAIN_FRAGMENT";

    Lifts data;
    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView avgR, avgW, sumR, sumW;
    Button resetter;

    public StatManager() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stat_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gson = new GsonBuilder().create();

        sharedPreferences = view.getContext().getSharedPreferences("Values", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        data = gson.fromJson(sharedPreferences.getString("lifts", "{list_data:[]}"), Lifts.class);

        avgR = view.findViewById(R.id.avgReps);
        avgR.setText("Average Reps Per Lift: " + data.avgR());
        avgW = view.findViewById(R.id.avgWeight);
        avgW.setText("Average Weight Per Lift: " + data.avgW());

        sumR = view.findViewById(R.id.totalReps);
        sumR.setText("Total Reps Lifted: " + data.sumR());
        sumW = view.findViewById(R.id.totalWeight);
        sumW.setText("Total Weight Lifted: " + data.sumW());

        avgR.setTextSize(25);
        avgW.setTextSize(25);
        sumR.setTextSize(25);
        sumW.setTextSize(25);

        resetter = view.findViewById(R.id.resetter);
        resetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("lifts", "{list_data:[]}");
                editor.apply();
            }
        });
    }
}
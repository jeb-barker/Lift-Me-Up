package com.barkerjeb.liftmeup;

import java.util.ArrayList;

public class Lifts {
    public ArrayList<Lift> list_data;
    private int i = -1;
    public Lift getNextSample() {
        return list_data.get((++i)%list_data.size());
    }
    public int sumW(){
        int s=0;
        for(Lift l : list_data){
            String str = "" + l.amount;
            str = str.replaceAll("[^0-9]", "");
            s += Integer.parseInt(str);
        }
        return s;
    }
    public int sumR(){
        int s=0;

        for(Lift l : list_data){
            if(l.repsndsets.trim().length() == 3){
                String[] rs = l.repsndsets.trim().split("x");
                s += Integer.parseInt(rs[0]) * Integer.parseInt(rs[1]);
            }
        }
        return s;
    }
    public int avgW(){
        if(list_data.size() == 0){
            return 0;
        }
        int a=0;
        a = sumW()/list_data.size();
        return a;
    }
    public int avgR(){
        if(list_data.size() == 0){
            return 0;
        }
        int a=0;
        a = sumW()/list_data.size();
        return a;
    }
    public static class Lift {
        public String date;
        public String amount;
        public String repsndsets;
        public String type;
        public Lift(String d, String am, String rs, String ty){
            date = d;
            amount = am;
            repsndsets = rs;
            type = ty;
        }
    }
}

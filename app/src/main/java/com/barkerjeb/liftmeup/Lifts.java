package com.barkerjeb.liftmeup;

public class Lifts {
    public Lift[] list_data;
    private int i = -1;
    public Lift getNextSample() {
        return list_data[(++i)%list_data.length];
    }
    public class Lift {
        public String date;
        public String amount;
        public String repsndsets;
        public String type;

    }
}

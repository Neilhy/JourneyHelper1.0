package com.android.bigexercise.BasicClass.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NeilHY on 2016/6/2.
 */
public class yearAndmonth {
    private ArrayList<Integer> yearList;
    private HashMap<Integer,ArrayList<Integer>> monthList;

    public yearAndmonth() {
    }

    public yearAndmonth(ArrayList<Integer> yearList, HashMap<Integer, ArrayList<Integer>> monthList) {
        this.yearList = yearList;
        this.monthList = monthList;
    }

    public ArrayList<Integer> getYearList() {
        return yearList;
    }

    public void setYearList(ArrayList<Integer> yearList) {
        this.yearList = yearList;
    }

    public HashMap<Integer, ArrayList<Integer>> getMonthList() {
        return monthList;
    }

    public void setMonthList(HashMap<Integer, ArrayList<Integer>> monthList) {
        this.monthList = monthList;
    }
}

package vladimir.enough;

import java.util.ArrayList;

import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 05.05.2017.
 */

public class CheckInputedValues {
    DB dbHelper;
    private ArrayList<KindsOfActivity> acts;

    public CheckInputedValues(DB dbHelper, ArrayList<KindsOfActivity> acts) {
        this.dbHelper = dbHelper;
        this.acts = acts;
    }

    public boolean CheckTime() {
        final int MINS_IN_DAY = 1440;
        int sum = 0;
        //acts = dbHelper.getAllActivities();
        ArrayList<String> times = dbHelper.getTime();
        for (int i = 0; i < times.size(); i++) {
            sum += Integer.parseInt(times.get(i));
        }
        if (sum == MINS_IN_DAY) {
            return true;
        } else {
            return false;
        }


    }


}

package vladimir.enough.models;

/**
 * Created by 32669 on 03.05.2017.
 */

public class KindsOfActivity {

    private long id;
    private String activityName;
    private double consumptionVal;
    private int time;
    private String type;

    public KindsOfActivity() {

    }

    public String getActivityName() {
        return activityName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public double getConsumptionVal() {
        return consumptionVal;
    }

    public void setConsumptionVal(double consumptionVal) {
        this.consumptionVal = consumptionVal;
    }

}


package vladimir.enough;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vladimir.enough.models.KindsOfActivity;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.PersonalInfo;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 05.05.2017.
 */

public class Calculations {
    private ArrayList<KindsOfActivity> acts;
    private ArrayList<PersonalInfo> personalInfo;

    private DB dbHelper;

    public Calculations(DB dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void calculateDailyConsumption(PersonalConsumtion personalConsumtion) {
        String currentDate = currentDate();


        acts = dbHelper.getAllActivities();
        personalInfo = dbHelper.getPersonalInfo();
        double dailyCallories = 0;
        double dailyProteins = 0;
        double dailyLipids = 0;
        double dailyCarbonides = 0;

        for (int i = 0; i < acts.size(); i++) {
            dailyCallories += acts.get(i).getConsumptionVal() * acts.get(i).getTime();
        }

        dailyCallories *= personalInfo.get(0).getWeight() + dailyCallories * 0.1;
        dailyProteins = dailyCallories * 0.14;
        dailyLipids = dailyCallories * 0.3;
        dailyCarbonides = dailyCallories * 0.56;
        personalConsumtion.setDailyCallories(round(dailyCallories));
        personalConsumtion.setDailyLipids(round(dailyLipids) );
        personalConsumtion.setDailyCarbonides(round(dailyCarbonides));
        personalConsumtion.setDailyProteins(round(dailyProteins));


    }



    public void countCurrentConsumption(PersonalConsumtion personalConsumtion, Product product) {


        double currentCallories = personalConsumtion.getCurrentCallories();
        double currentProteins = personalConsumtion.getCurrentProteins();
        double currentLipids = personalConsumtion.getCurrentLipids();
        double currentCarbonides = personalConsumtion.getCurrentCarbonides();

        currentCallories += (product.getCallories() / 100) * product.getWeight();
        currentProteins += (product.getProteins() / 100) * product.getWeight();
        currentLipids += (product.getLipids() / 100) * product.getWeight();
        currentCarbonides += (product.getCarbonides() / 100) * product.getWeight();

        personalConsumtion.setCurrentCallories(round(currentCallories));
        personalConsumtion.setCurrentProteins(round(currentProteins));
        personalConsumtion.setCurrentLipids(round(currentLipids));
        personalConsumtion.setCurrentCarbonides(round(currentCarbonides));



    }


    public String currentDate() {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        return date;
    }
    public double round(double number) {
        int pow = 10;
        for (int i = 1; i < 1; i++)
            pow *= 10;
        double tmp = number * pow;
        return (double) (int) ((tmp - (int) tmp) >= 0.5 ? tmp + 1 : tmp) / pow;
    }

}

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

        //formula maffina-geora
        if (personalInfo.get(0).getSex()=="W"){
            double bx= 9.99*personalInfo.get(0).getWeight()+6.25*personalInfo.get(0).getHeight()-
                    4.92*personalInfo.get(0).getAge()-161;
            round(bx);
            personalConsumtion.setBasicExchenge(bx);
        }else {
            double bx= 9.99*personalInfo.get(0).getWeight()+6.25*personalInfo.get(0).getHeight()-
                    4.92*personalInfo.get(0).getAge()+5;
            round(bx);
            personalConsumtion.setBasicExchenge(bx);

        }

        double dailyCallories = 0;
        double dailyProteins = 0;
        double dailyLipids = 0;
        double dailyCarbonides = 0;


        for (int i = 0; i < acts.size(); i++) {
            dailyCallories += acts.get(i).getConsumptionVal() * acts.get(i).getTime();
        }
        dailyCallories*=personalInfo.get(0).getWeight();

        if (personalInfo.get(0).getSex()=="M"){
            dailyCallories+=dailyCallories*0.1;
        }

        dailyProteins = dailyCallories * 0.14 /4;
        dailyLipids = dailyCallories * 0.3/9;
        dailyCarbonides = dailyCallories * 0.56/4;
        personalConsumtion.setDailyCallories(round(dailyCallories));
        personalConsumtion.setDailyLipids(round(dailyLipids) );
        personalConsumtion.setDailyCarbohydrates(round(dailyCarbonides));
        personalConsumtion.setDailyProteins(round(dailyProteins));

        double a =personalInfo.get(0).getWeight();
        double b =(double)personalInfo.get(0).getHeight()/100;
        personalConsumtion.setWeightIndex(round(a/(b*b)));

    }



    public void countCurrentConsumption(PersonalConsumtion personalConsumtion, Product product) {


        double currentCallories = personalConsumtion.getCurrentCallories();
        double currentProteins = personalConsumtion.getCurrentProteins();
        double currentLipids = personalConsumtion.getCurrentLipids();
        double currentCarbonides = personalConsumtion.getCurrentCarbohydrates();

        currentCallories += (product.getCallories() / 100) * product.getWeight();
        currentProteins += (product.getProteins() / 100) * product.getWeight();
        currentLipids += (product.getLipids() / 100) * product.getWeight();
        currentCarbonides += (product.getCarbohydrates() / 100) * product.getWeight();

        personalConsumtion.setCurrentCallories(round(currentCallories));
        personalConsumtion.setCurrentProteins(round(currentProteins));
        personalConsumtion.setCurrentLipids(round(currentLipids));
        personalConsumtion.setCurrentCarbohydrates(round(currentCarbonides));



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

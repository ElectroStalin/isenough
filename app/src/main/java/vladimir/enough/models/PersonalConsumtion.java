package vladimir.enough.models;

/**
 * Created by 32669 on 05.05.2017.
 */

public class PersonalConsumtion {
    String date;
    double dailyCallories;
    double dailyProteins;
    double dailyLipids;
    double dailyCarbonides;
    double currentCallories;
    double currentProteins;
    double currentLipids;
    double currentCarbonides;

    public PersonalConsumtion() {
    }

    public double getCurrentCallories() {
        return currentCallories;
    }

    public double getCurrentCarbonides() {
        return currentCarbonides;
    }

    public double getCurrentLipids() {
        return currentLipids;
    }

    public double getCurrentProteins() {
        return currentProteins;
    }

    public double getDailyCallories() {
        return dailyCallories;
    }

    public double getDailyCarbonides() {
        return dailyCarbonides;
    }

    public double getDailyLipids() {
        return dailyLipids;
    }

    public double getDailyProteins() {
        return dailyProteins;
    }

    public String getDate() {
        return date;
    }

    public void setCurrentCallories(double currentCallories) {
        this.currentCallories = currentCallories;
    }

    public void setCurrentCarbonides(double currentCarbonides) {
        this.currentCarbonides = currentCarbonides;
    }

    public void setCurrentLipids(double currentLipids) {
        this.currentLipids = currentLipids;
    }

    public void setCurrentProteins(double currentProteins) {
        this.currentProteins = currentProteins;
    }

    public void setDailyCallories(double dailyCallories) {
        this.dailyCallories = dailyCallories;
    }

    public void setDailyCarbonides(double dailyCarbonides) {
        this.dailyCarbonides = dailyCarbonides;
    }

    public void setDailyLipids(double dailyLipids) {
        this.dailyLipids = dailyLipids;
    }

    public void setDailyProteins(double dailyProteins) {
        this.dailyProteins = dailyProteins;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

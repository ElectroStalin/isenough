package vladimir.enough.models;

/**
 * Created by 32669 on 05.05.2017.
 */

public class PersonalConsumtion {
    String date;
    double dailyCallories;
    double dailyProteins;
    double dailyLipids;
    double dailyCarbohydrates;
    double currentCallories;
    double currentProteins;
    double currentLipids;
    double currentCarbohydrates;
    double basicExchenge;
    double weightIndex;

    public PersonalConsumtion() {
    }

    public double getCurrentCallories() {
        return currentCallories;
    }

    public double getCurrentCarbohydrates() {
        return currentCarbohydrates;
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

    public double getDailyCarbohydrates() {
        return dailyCarbohydrates;
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

    public void setCurrentCarbohydrates(double currentCarbohydrates) {
        this.currentCarbohydrates = currentCarbohydrates;
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

    public void setDailyCarbohydrates(double dailyCarbohydrates) {
        this.dailyCarbohydrates = dailyCarbohydrates;
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

    public double getBasicExchenge() {
        return basicExchenge;
    }

    public void setBasicExchenge(double basicExchenge) {
        this.basicExchenge = basicExchenge;
    }

    public double getWeightIndex() {
        return weightIndex;
    }

    public void setWeightIndex(double weightIndex) {
        this.weightIndex = weightIndex;
    }
}

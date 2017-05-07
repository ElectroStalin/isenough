package vladimir.enough.models;

/**
 * Created by 32669 on 06.05.2017.
 */

public class Product {
    int id;
    String name;
    double callories;
    double proteins;
    double lipids;
    double carbonides;
    String type;
    int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCallories() {
        return callories;
    }

    public void setCallories(double callories) {
        this.callories = callories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getLipids() {
        return lipids;
    }

    public void setLipids(double lipids) {
        this.lipids = lipids;
    }

    public double getCarbonides() {
        return carbonides;
    }

    public void setCarbonides(double carbonides) {
        this.carbonides = carbonides;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

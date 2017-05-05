package vladimir.enough.models;

/**
 * Created by 32669 on 05.05.2017.
 */

public class PersonalInfo {


    int height;
    int weight;
    int age;
    String sex;
    public  PersonalInfo(){}


    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getHeight(){ return height;}

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}

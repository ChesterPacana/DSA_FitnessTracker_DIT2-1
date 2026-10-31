package fitnesstracker;

public class Meal {
    private int mealId;
    private String name;
    private double calories;
    private String nutritionalInfo;   

    public Meal(int mealId, String name, double calories, String nutritionalInfo) {
        this.mealId = mealId;
        this.name = name;
        this.calories = calories;
        this.nutritionalInfo = nutritionalInfo;
    }

    public int getMealId() { return mealId; }
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public String getNutritionalInfo() { return nutritionalInfo; }

    @Override
    public String toString() {
        return "Meal #" + mealId + " | " + name + " | " + calories + " cal | " + nutritionalInfo;
    }
}
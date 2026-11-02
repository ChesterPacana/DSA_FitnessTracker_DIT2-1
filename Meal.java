public class Meal {
    private final int mealId;
    private final String name;
    private final int calories;

    public Meal(int mealId, String name, int calories) {
        this.mealId = mealId;
        this.name = name;
        this.calories = calories;
    }

    public int getMealId()   { return mealId; }
    public String getName()  { return name; }
    public int getCalories() { return calories; }
}
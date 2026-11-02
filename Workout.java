public class Workout {
    private final int workoutId;
    private final String name;
    private final int caloriesBurned;

    public Workout(int workoutId, String name, int caloriesBurned) {
        this.workoutId = workoutId;
        this.name = name;
        this.caloriesBurned = caloriesBurned;
    }

    public int getWorkoutId()      { return workoutId; }
    public String getName()        { return name; }
    public int getCaloriesBurned() { return caloriesBurned; }
}
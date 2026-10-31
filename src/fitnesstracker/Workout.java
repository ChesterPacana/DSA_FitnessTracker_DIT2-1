package fitnesstracker;

public class Workout {
    private int workoutId;
    private String name;
    private int duration;         
    private double caloriesBurned;

    public Workout(int workoutId, String name, int duration, double caloriesBurned) {
        this.workoutId = workoutId;
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public int getWorkoutId() { return workoutId; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public double getCaloriesBurned() { return caloriesBurned; }

    @Override
    public String toString() {
        return "Workout #" + workoutId + " | " + name + " | " + duration + " min | " + caloriesBurned + " cal burned";
    }
}
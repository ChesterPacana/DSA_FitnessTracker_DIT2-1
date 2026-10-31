package fitnesstracker;

public class User {
    private int id;
    private String name;
    private Workout[] workouts = new Workout[100];   
    private Meal[] meals = new Meal[100];
    private int workoutCount = 0;
    private int mealCount = 0;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addWorkout(Workout w) {
        if (workoutCount < workouts.length) {
            workouts[workoutCount++] = w;
        } else {
            System.out.println("Workout list is full!");
        }
    }

    public void addMeal(Meal m) {
        if (mealCount < meals.length) {
            meals[mealCount++] = m;
        } else {
            System.out.println("Meal list is full!");
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Workout[] getWorkouts() { return workouts; }
    public Meal[] getMeals() { return meals; }
    public int getWorkoutCount() { return workoutCount; }
    public int getMealCount() { return mealCount; }
    
        
    public Workout findWorkoutById(int targetId) {
        int low = 0;
        int high = workoutCount - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midId = workouts[mid].getWorkoutId();
            if (midId == targetId) {
                return workouts[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  
    }

    
    public Meal findMealById(int targetId) {
        int low = 0;
        int high = mealCount - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midId = meals[mid].getMealId();
            if (midId == targetId) {
                return meals[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;   
    }

    
    public Meal[] getMealsSortedByCalories() {
        Meal[] sorted = new Meal[mealCount];
        for (int i = 0; i < mealCount; i++) {
            sorted[i] = meals[i];   
        }

        for (int i = 0; i < sorted.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[j].getCalories() < sorted[minIndex].getCalories()) {
                    minIndex = j;
                }
            }
            Meal temp = sorted[i];
            sorted[i] = sorted[minIndex];
            sorted[minIndex] = temp;
        }
        return sorted;
    }
    
        
    private double calorieBurnGoal = 0;

    public void setCalorieBurnGoal(double goal) {
        this.calorieBurnGoal = goal;
        System.out.println("Goal set: burn " + goal + " calories.");
    }

    public double getTotalCaloriesBurned() {
        double total = 0;
        for (int i = 0; i < workoutCount; i++) {
            total += workouts[i].getCaloriesBurned();
        }
        return total;
    }

    public double getTotalCaloriesEaten() {
        double total = 0;
        for (int i = 0; i < mealCount; i++) {
            total += meals[i].getCalories();
        }
        return total;
    }

    
    public void checkGoal() {
        if (calorieBurnGoal <= 0) {
            System.out.println("NOTIFICATION: You haven't set a goal yet.");
            return;
        }
        double burned = getTotalCaloriesBurned();
        if (burned >= calorieBurnGoal) {
            System.out.println("NOTIFICATION: Congratulations! You reached your goal of "
                    + calorieBurnGoal + " calories!");
        } else {
            System.out.println("NOTIFICATION: Keep going! You need "
                    + (calorieBurnGoal - burned) + " more calories to reach your goal.");
        }
    }

   
    public void generateReport() {
        System.out.println("======= PROGRESS REPORT: " + name + " =======");
        System.out.println("Workouts logged : " + workoutCount);
        System.out.println("Meals logged    : " + mealCount);
        System.out.println("Calories burned : " + getTotalCaloriesBurned());
        System.out.println("Calories eaten  : " + getTotalCaloriesEaten());
        System.out.println("Net calories    : "
                + (getTotalCaloriesEaten() - getTotalCaloriesBurned()));
        if (calorieBurnGoal > 0) {
            double percent = (getTotalCaloriesBurned() / calorieBurnGoal) * 100;
            System.out.println("Goal progress   : " + Math.round(percent) + "% of "
                    + calorieBurnGoal + " calories");
        }
        System.out.println("=========================================");
    }
}


package fitnesstracker;

import java.util.Scanner;

public class FitnessTracker {

    static Scanner input = new Scanner(System.in);

    
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    
    static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    static String readText(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    public static void main(String[] args) {
        System.out.println("===== FITNESS TRACKER =====");
        String userName = readText("Enter your name: ");
        User user = new User(1, userName);

       
        int nextWorkoutId = 101;
        int nextMealId = 201;

        boolean running = true;
        while (running) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Log a workout");
            System.out.println("2. Log a meal");
            System.out.println("3. View all workouts");
            System.out.println("4. View all meals");
            System.out.println("5. Find a workout by ID (binary search)");
            System.out.println("6. Find a meal by ID (binary search)");
            System.out.println("7. View meals sorted by calories");
            System.out.println("8. Set calorie-burn goal");
            System.out.println("9. Check goal progress");
            System.out.println("10. Generate progress report");
            System.out.println("0. Exit");

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    String wName = readText("Workout name: ");
                    int duration = readInt("Duration (minutes): ");
                    double burned = readDouble("Calories burned: ");
                    user.addWorkout(new Workout(nextWorkoutId, wName, duration, burned));
                    System.out.println("Workout saved with ID " + nextWorkoutId);
                    nextWorkoutId++;
                    break;

                case 2:
                    String mName = readText("Meal name: ");
                    double cals = readDouble("Calories: ");
                    String info = readText("Nutritional info (e.g. Protein 20g, Carbs 30g): ");
                    user.addMeal(new Meal(nextMealId, mName, cals, info));
                    System.out.println("Meal saved with ID " + nextMealId);
                    nextMealId++;
                    break;

                case 3:
                    if (user.getWorkoutCount() == 0) {
                        System.out.println("No workouts logged yet.");
                    }
                    for (int i = 0; i < user.getWorkoutCount(); i++) {
                        System.out.println(user.getWorkouts()[i]);
                    }
                    break;

                case 4:
                    if (user.getMealCount() == 0) {
                        System.out.println("No meals logged yet.");
                    }
                    for (int i = 0; i < user.getMealCount(); i++) {
                        System.out.println(user.getMeals()[i]);
                    }
                    break;

                case 5:
                    Workout w = user.findWorkoutById(readInt("Workout ID to find: "));
                    System.out.println(w != null ? "Found: " + w : "Workout not found.");
                    break;

                case 6:
                    Meal m = user.findMealById(readInt("Meal ID to find: "));
                    System.out.println(m != null ? "Found: " + m : "Meal not found.");
                    break;

                case 7:
                    if (user.getMealCount() == 0) {
                        System.out.println("No meals logged yet.");
                    }
                    for (Meal meal : user.getMealsSortedByCalories()) {
                        System.out.println(meal);
                    }
                    break;

                case 8:
                    user.setCalorieBurnGoal(readDouble("Calories you want to burn: "));
                    break;

                case 9:
                    user.checkGoal();
                    break;

                case 10:
                    user.generateReport();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye, " + user.getName() + "!");
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
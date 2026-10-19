import javax.swing.*;

public class FitnessTrackerApp extends JFrame{

    private JLabel lblWorkoutSection, lblWorkoutName, lblWorkoutSearch, lblMealSection, lblMealName, lblMealCalories, lblNutrition, lblMealSearch;
    private JTextField txtWorkoutName, txtWorkoutSearch, txtMealName, txtNutrition, txtMealCalories, txtMealSearch;
    private JButton btnAddWorkout, btnSearchWorkout, btnAddMeal, btnSearchMeal;

    FitnessTrackerApp() {
        setTitle("Fitness Tracker Application");
        setSize(950, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Workout Section
        JLabel lblWorkoutSection = new JLabel("WORKOUT RECORD");
        lblWorkoutSection.setBounds(30, 50, 200, 25);
        add(lblWorkoutSection);
        
        lblWorkoutName = new JLabel("Workout Name:");
        lblWorkoutName.setBounds(30, 85, 110, 25);
        add(lblWorkoutName);

        txtWorkoutName = new JTextField();
        txtWorkoutName.setBounds(140, 85, 180, 25);
        add(txtWorkoutName);

        lblWorkoutSearch = new JLabel("Search Workout ID:");
        lblWorkoutSearch.setBounds(30, 120, 130, 25);
        add(lblWorkoutSearch);

        txtWorkoutSearch = new JTextField();
        txtWorkoutSearch.setBounds(160, 120, 80, 25);
        add(txtWorkoutSearch);

        btnSearchWorkout = new JButton("Search");
        btnSearchWorkout.setBounds(250, 120, 100, 25);
        add(btnSearchWorkout);
    
    //Meal Section
        lblMealSection = new JLabel("MEAL RECORD");
        lblMealSection.setBounds(30, 165, 200, 25);
        add(lblMealSection);

        lblMealName = new JLabel("Meal Name:");
        lblMealName.setBounds(30, 200, 100, 25);
        add(lblMealName);

        txtMealName = new JTextField();
        txtMealName.setBounds(130, 200, 150, 25);
        add(txtMealName);
        
        lblMealCalories = new JLabel("Calories:");
        lblMealCalories.setBounds(290, 200, 70, 25);
        add(lblMealCalories);

        txtMealCalories = new JTextField();
        txtMealCalories.setBounds(360, 200, 80, 25);
        add(txtMealCalories);

        lblNutrition = new JLabel("Nutrition Info:");
        lblNutrition.setBounds(450, 200, 100, 25);
        add(lblNutrition);

        txtNutrition = new JTextField();
        txtNutrition.setBounds(550, 200, 170, 25);
        add(txtNutrition);

        btnAddMeal = new JButton("Add Meal");
        btnAddMeal.setBounds(740, 200, 150, 25);
        add(btnAddMeal);

        lblMealSearch = new JLabel("Search Meal ID:");
        lblMealSearch.setBounds(30, 235, 110, 25);
        add(lblMealSearch);

        txtMealSearch = new JTextField();
        txtMealSearch.setBounds(140, 235, 80, 25);
        add(txtMealSearch);

        btnSearchMeal = new JButton("Search");
        btnSearchMeal.setBounds(230, 235, 100, 25);
        add(btnSearchMeal);
          
    }
    public static void main(String[] args) {
        FitnessTrackerApp app = new FitnessTrackerApp();
        app.setVisible(true);
    }
}

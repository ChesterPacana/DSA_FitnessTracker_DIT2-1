import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FitnessTrackerApp extends JFrame implements ActionListener {

    private JLabel lblHeader, lblSubtitle, lblWorkoutSection, lblWorkoutId, lblWorkoutName, lblWorkoutDuration, lblCaloriesBurned, lblWorkoutSearch, lblMealSection, lblMealId, lblMealName, lblMealCalories, lblNutrition, lblMealSearch, lblGoalSection, lblGoalName, lblGoalTarget, lblGoalType, lblProgressSection, lblReportType;
    private JTextField txtWorkoutId, txtWorkoutName, txtWorkoutDuration, txtCaloriesBurned, txtWorkoutSearch, txtMealId, txtMealName, txtMealCalories, txtNutrition, txtMealSearch, txtGoalName, txtGoalTarget;
    private JComboBox<String> cmbGoalType, cmbReportType;
    private JButton btnAddWorkout, btnSearchWorkout, btnClearWorkout, btnAddMeal, btnSearchMeal, btnClearMeal, btnSetGoal, btnGenerateReport, btnReset;
    private JTextArea txaProgressReport;
    private static final String[] goalTypes = {
        "Workout Duration", "Calories Burned", "Daily Calories"
    };
    private static final String[] reportTypes = {
        "Workout Report", "Meal Report", "Overall Progress"
    };

    FitnessTrackerApp() {
        setTitle("Fitness Tracker Application");
        setSize(1000, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblHeader = new JLabel("FITNESS TRACKER", SwingConstants.CENTER);
        lblHeader.setBounds(0, 10, 1000, 35);
        add(lblHeader);

        lblSubtitle = new JLabel("Workout  |  Nutrition  |  Fitness Progress", SwingConstants.CENTER);
        lblSubtitle.setBounds(0, 42, 1000, 25);
        add(lblSubtitle);

        // Workout Section
        lblWorkoutSection = new JLabel("WORKOUT RECORD");
        lblWorkoutSection.setBounds(30, 85, 250, 25);
        add(lblWorkoutSection);
        
        lblWorkoutId = new JLabel("Workout ID:");
        lblWorkoutId.setBounds(30, 120, 100, 25);
        add(lblWorkoutId);

        txtWorkoutId = new JTextField();
        txtWorkoutId.setBounds(130, 120, 100, 25);
        add(txtWorkoutId);

        lblWorkoutName = new JLabel("Workout Name:");
        lblWorkoutName.setBounds(260, 120, 110, 25);
        add(lblWorkoutName);

        txtWorkoutName = new JTextField();
        txtWorkoutName.setBounds(370, 120, 180, 25);
        add(txtWorkoutName);

        lblWorkoutDuration = new JLabel("Duration (min):");
        lblWorkoutDuration.setBounds(570, 120, 110, 25);
        add(lblWorkoutDuration);

        txtWorkoutDuration = new JTextField();
        txtWorkoutDuration.setBounds(680, 120, 100, 25);
        add(txtWorkoutDuration);

        lblCaloriesBurned = new JLabel("Calories Burned:");
        lblCaloriesBurned.setBounds(30, 155, 130, 25);
        add(lblCaloriesBurned);

        txtCaloriesBurned = new JTextField();
        txtCaloriesBurned.setBounds(165, 155, 90, 25);
        add(txtCaloriesBurned);

        btnAddWorkout = new JButton("Add Workout");
        btnAddWorkout.setBounds(280, 155, 140, 28);
        add(btnAddWorkout);

        btnClearWorkout = new JButton("Clear");
        btnClearWorkout.setBounds(435, 155, 120, 28);
        add(btnClearWorkout);

        lblWorkoutSearch = new JLabel("Search ID:");
        lblWorkoutSearch.setBounds(590, 155, 110, 25);
        add(lblWorkoutSearch);

        txtWorkoutSearch = new JTextField();
        txtWorkoutSearch.setBounds(690, 155, 100, 25);
        add(txtWorkoutSearch);

        btnSearchWorkout = new JButton("Search");
        btnSearchWorkout.setBounds(820, 155, 110, 28);
        add(btnSearchWorkout);

        // Meal Section
        lblMealSection = new JLabel("MEAL RECORD");
        lblMealSection.setBounds(30, 215, 250, 25);
        add(lblMealSection);

        lblMealId = new JLabel("Meal ID:");
        lblMealId.setBounds(30, 250, 80, 25);
        add(lblMealId);

        txtMealId = new JTextField();
        txtMealId.setBounds(110, 250, 100, 25);
        add(txtMealId);

        lblMealName = new JLabel("Meal Name:");
        lblMealName.setBounds(240, 250, 100, 25);
        add(lblMealName);

        txtMealName = new JTextField();
        txtMealName.setBounds(340, 250, 180, 25);
        add(txtMealName);

        lblMealCalories = new JLabel("Calories:");
        lblMealCalories.setBounds(545, 250, 70, 25);
        add(lblMealCalories);

        txtMealCalories = new JTextField();
        txtMealCalories.setBounds(615, 250, 100, 25);
        add(txtMealCalories);

        lblNutrition = new JLabel("Nutrition Info:");
        lblNutrition.setBounds(30, 285, 100, 25);
        add(lblNutrition);

        txtNutrition = new JTextField();
        txtNutrition.setBounds(130, 285, 390, 25);
        add(txtNutrition);

        btnAddMeal = new JButton("Add Meal");
        btnAddMeal.setBounds(545, 285, 130, 28);
        add(btnAddMeal);

        btnClearMeal = new JButton("Clear");
        btnClearMeal.setBounds(690, 285, 120, 28);
        add(btnClearMeal);

        lblMealSearch = new JLabel("Search ID:");
        lblMealSearch.setBounds(30, 320, 110, 25);
        add(lblMealSearch);

        txtMealSearch = new JTextField();
        txtMealSearch.setBounds(120, 320, 100, 25);
        add(txtMealSearch);

        btnSearchMeal = new JButton("Search");
        btnSearchMeal.setBounds(250, 320, 110, 28);
        add(btnSearchMeal);

        // Fitness Goals Section
        lblGoalSection = new JLabel("FITNESS GOALS");
        lblGoalSection.setBounds(30, 380, 250, 25);
        add(lblGoalSection);

        lblGoalName = new JLabel("Goal Name:");
        lblGoalName.setBounds(30, 415, 100, 25);
        add(lblGoalName);

        txtGoalName = new JTextField();
        txtGoalName.setBounds(130, 415, 180, 25);
        add(txtGoalName);

        lblGoalType = new JLabel("Goal Type:");
        lblGoalType.setBounds(335, 415, 90, 25);
        add(lblGoalType);

        cmbGoalType = new JComboBox<>(goalTypes);
        cmbGoalType.setBounds(425, 415, 170, 25);
        add(cmbGoalType);

        lblGoalTarget = new JLabel("Target Value:");
        lblGoalTarget.setBounds(620, 415, 100, 25);
        add(lblGoalTarget);

        txtGoalTarget = new JTextField();
        txtGoalTarget.setBounds(720, 415, 100, 25);
        add(txtGoalTarget);

        btnSetGoal = new JButton("Set Goal");
        btnSetGoal.setBounds(835, 415, 110, 28);
        add(btnSetGoal);

        // Progress Report Section
        lblProgressSection = new JLabel("PROGRESS REPORT");
        lblProgressSection.setBounds(30, 475, 250, 25);
        add(lblProgressSection);

        lblReportType = new JLabel("Report Type:");
        lblReportType.setBounds(30, 510, 100, 25);
        add(lblReportType);

        cmbReportType = new JComboBox<>(reportTypes);
        cmbReportType.setBounds(130, 510, 190, 25);
        add(cmbReportType);

        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.setBounds(345, 510, 160, 28);
        add(btnGenerateReport);
        
        txaProgressReport = new JTextArea();
        txaProgressReport.setBounds(30, 555, 900, 145);
        txaProgressReport.setEditable(false);
        txaProgressReport.setLineWrap(true);
        txaProgressReport.setWrapStyleWord(true);
        add(txaProgressReport);

        // Reset Button
        btnReset = new JButton("Reset");
        btnReset.setBounds(30, 720, 140, 28);
        add(btnReset);

        btnAddWorkout.addActionListener(this);
        btnSearchWorkout.addActionListener(this);
        btnClearWorkout.addActionListener(this);
        btnAddMeal.addActionListener(this);
        btnSearchMeal.addActionListener(this);
        btnClearMeal.addActionListener(this);
        btnSetGoal.addActionListener(this);
        btnGenerateReport.addActionListener(this);
        btnReset.addActionListener(this);
    }
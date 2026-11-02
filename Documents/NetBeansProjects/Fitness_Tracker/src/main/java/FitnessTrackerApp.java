import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class FitnessTrackerApp extends JFrame implements ActionListener {

    private JLabel lblHeader, lblDate, lblDateHint, lblGoalSection, lblGoalName, lblGoalType, lblGoalTarget, lblWorkoutSection, lblWorkoutName, lblCaloriesBurned, lblMealSection, lblMealName, lblMealCalories;
    private JTextField txtDate, txtGoalName, txtGoalTarget, txtWorkoutName, txtCaloriesBurned, txtMealName, txtMealCalories;
    private JComboBox<String> cmbGoalType, cmbView;
    private JButton btnClear, btnConfirmGoal, btnConfirmWorkout, btnConfirmMeal, btnSearch;
    private JTextArea txaProgressReport;
    private JScrollPane scrollReport;
    private static final String[] goalTypes = {
        "Calories Burned", "Calorie Limit"
    };
    private static final String[] viewTypes = {
        "All Workouts", "All Meals", "All Goals", "Overall Progress"
    };
    private int totalWorkouts, totalBurned, totalMeals, totalEaten, dayBurned, dayEaten;

    FitnessTrackerApp() {
        setTitle("Fitness Tracker Application");
        setSize(1000, 590);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblHeader = new JLabel("FITNESS TRACKER", SwingConstants.CENTER);
        lblHeader.setBounds(0, 10, 1000, 35);
        add(lblHeader);

        // Date
        lblDate = new JLabel("Date:");
        lblDate.setBounds(30, 95, 120, 25);
        add(lblDate);

        txtDate = new JTextField();
        txtDate.setBounds(150, 95, 190, 25);
        add(txtDate);

        lblDateHint = new JLabel("format: yyyy-mm-dd");
        lblDateHint.setBounds(150, 119, 190, 18);
        add(lblDateHint);

        // Pitness gowl
        lblGoalSection = new JLabel("FITNESS GOALS");
        lblGoalSection.setBounds(30, 140, 250, 25);
        add(lblGoalSection);

        lblGoalName = new JLabel("Goal Name:");
        lblGoalName.setBounds(30, 175, 120, 25);
        add(lblGoalName);

        txtGoalName = new JTextField();
        txtGoalName.setBounds(150, 175, 190, 25);
        add(txtGoalName);

        lblGoalType = new JLabel("Goal Type:");
        lblGoalType.setBounds(30, 210, 120, 25);
        add(lblGoalType);

        cmbGoalType = new JComboBox<>(goalTypes);
        cmbGoalType.setBounds(150, 210, 190, 25);
        add(cmbGoalType);

        lblGoalTarget = new JLabel("Target (per day):");
        lblGoalTarget.setBounds(30, 245, 120, 25);
        add(lblGoalTarget);

        txtGoalTarget = new JTextField();
        txtGoalTarget.setBounds(150, 245, 190, 25);
        add(txtGoalTarget);

        // Working-working portion
        lblWorkoutSection = new JLabel("WORKOUT RECORD");
        lblWorkoutSection.setBounds(30, 295, 250, 25);
        add(lblWorkoutSection);

        lblWorkoutName = new JLabel("Workout Name:");
        lblWorkoutName.setBounds(30, 330, 120, 25);
        add(lblWorkoutName);

        txtWorkoutName = new JTextField();
        txtWorkoutName.setBounds(150, 330, 190, 25);
        add(txtWorkoutName);

        lblCaloriesBurned = new JLabel("Calories Burned:");
        lblCaloriesBurned.setBounds(30, 365, 120, 25);
        add(lblCaloriesBurned);

        txtCaloriesBurned = new JTextField();
        txtCaloriesBurned.setBounds(150, 365, 190, 25);
        add(txtCaloriesBurned);

        // Eto meal
        lblMealSection = new JLabel("MEAL RECORD");
        lblMealSection.setBounds(30, 415, 250, 25);
        add(lblMealSection);

        lblMealName = new JLabel("Meal Name:");
        lblMealName.setBounds(30, 450, 120, 25);
        add(lblMealName);

        txtMealName = new JTextField();
        txtMealName.setBounds(150, 450, 190, 25);
        add(txtMealName);

        lblMealCalories = new JLabel("Calories:");
        lblMealCalories.setBounds(30, 485, 120, 25);
        add(lblMealCalories);

        txtMealCalories = new JTextField();
        txtMealCalories.setBounds(150, 485, 190, 25);
        add(txtMealCalories);

        // Butones
        btnClear = new JButton("Clear");
        btnClear.setBounds(360, 95, 150, 30);
        add(btnClear);

        btnConfirmGoal = new JButton("Confirm Goal");
        btnConfirmGoal.setBounds(360, 210, 150, 30);
        add(btnConfirmGoal);

        btnConfirmWorkout = new JButton("Confirm Workout");
        btnConfirmWorkout.setBounds(360, 347, 150, 30);
        add(btnConfirmWorkout);

        btnConfirmMeal = new JButton("Confirm Meal");
        btnConfirmMeal.setBounds(360, 467, 150, 30);
        add(btnConfirmMeal);

        // Pang Search
        cmbView = new JComboBox<>(viewTypes);
        cmbView.setBounds(530, 95, 260, 25);
        add(cmbView);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(805, 93, 120, 30);
        add(btnSearch);

        txaProgressReport = new JTextArea();
        txaProgressReport.setEditable(false);
        txaProgressReport.setLineWrap(true);
        txaProgressReport.setWrapStyleWord(true);

        scrollReport = new JScrollPane(txaProgressReport);
        scrollReport.setBounds(530, 140, 410, 370);
        add(scrollReport);

        btnClear.addActionListener(this);
        btnConfirmGoal.addActionListener(this);
        btnConfirmWorkout.addActionListener(this);
        btnConfirmMeal.addActionListener(this);
        btnSearch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClear) {
            clearFields();
            txtDate.setText("");
        } else if (e.getSource() == btnConfirmGoal) {
            confirmGoal();
        } else if (e.getSource() == btnConfirmWorkout) {
            confirmWorkout();
        } else if (e.getSource() == btnConfirmMeal) {
            confirmMeal();
        } else if (e.getSource() == btnSearch) {
            search();
        }
    }

    private void clearFields() {
        txtGoalName.setText("");
        txtGoalTarget.setText("");
        cmbGoalType.setSelectedIndex(0);
        txtWorkoutName.setText("");
        txtCaloriesBurned.setText("");
        txtMealName.setText("");
        txtMealCalories.setText("");
    }

    private Date readDate() {
        String dateText = txtDate.getText().trim();
        if (dateText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please type the date (yyyy-mm-dd).");
            return null;
        }
        try {
            return Date.valueOf(dateText);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Date must be in the format yyyy-mm-dd (example: 2026-11-02).");
            return null;
        }
    }
    private void confirmGoal() {
        String goalName = txtGoalName.getText().trim();
        String targetText = txtGoalTarget.getText().trim();
        String goalType = (String) cmbGoalType.getSelectedItem();

        if (goalName.isEmpty() || targetText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both Goal Name and Target.");
            return;
        }

        double target;
        try {
            target = Double.parseDouble(targetText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Target must be a number.");
            return;
        }

        Date entryDate = readDate();
        if (entryDate == null) {
            return;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO goals (goalName, goalType, targetValue) VALUES (?, ?, ?)");
            statement.setString(1, goalName);
            statement.setString(2, goalType);
            statement.setDouble(3, target);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Goal Saved: " + goalName);
        txtGoalName.setText("");
        txtGoalTarget.setText("");
        cmbGoalType.setSelectedIndex(0);
        checkGoals(entryDate);
    }
    private void confirmWorkout() {
        String workoutName = txtWorkoutName.getText().trim();
        String burnedText = txtCaloriesBurned.getText().trim();

        if (workoutName.isEmpty() || burnedText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both Workout Name and Calories Burned.");
            return;
        }

        int caloriesBurned;
        try {
            caloriesBurned = Integer.parseInt(burnedText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Calories Burned must be a whole number.");
            return;
        }

        Date entryDate = readDate();
        if (entryDate == null) {
            return;
        }

        int newId;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO workouts (name, caloriesBurned, entryDate) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, workoutName);
            statement.setInt(2, caloriesBurned);
            statement.setDate(3, entryDate);
            statement.executeUpdate();
            newId = getNewId(statement);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Workout Saved. Workout ID: " + newId);
        txtWorkoutName.setText("");
        txtCaloriesBurned.setText("");
        checkGoals(entryDate);
    }
    private void confirmMeal() {
        String mealName = txtMealName.getText().trim();
        String caloriesText = txtMealCalories.getText().trim();

        if (mealName.isEmpty() || caloriesText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both Meal Name and Calories.");
            return;
        }

        int mealCalories;
        try {
            mealCalories = Integer.parseInt(caloriesText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Calories must be a whole number.");
            return;
        }

        Date entryDate = readDate();
        if (entryDate == null) {
            return;
        }

        int newId;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO meals (name, calories, entryDate) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, mealName);
            statement.setInt(2, mealCalories);
            statement.setDate(3, entryDate);
            statement.executeUpdate();
            newId = getNewId(statement);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Meal Saved. Meal ID: " + newId);
        txtMealName.setText("");
        txtMealCalories.setText("");
        checkGoals(entryDate);
    }
    private int getNewId(PreparedStatement statement) throws SQLException {
        ResultSet keys = statement.getGeneratedKeys();
        int newId = 0;
        if (keys.next()) {
            newId = keys.getInt(1);
        }
        keys.close();
        return newId;
    }
    private void loadTotals(Connection connection, Date day) throws SQLException {
        Statement statement = connection.createStatement();

        ResultSet RS = statement.executeQuery("SELECT COUNT(*), COALESCE(SUM(caloriesBurned), 0) FROM workouts");
        RS.next();
        totalWorkouts = RS.getInt(1);
        totalBurned = RS.getInt(2);
        RS.close();

        RS = statement.executeQuery("SELECT COUNT(*), COALESCE(SUM(calories), 0) FROM meals");
        RS.next();
        totalMeals = RS.getInt(1);
        totalEaten = RS.getInt(2);
        RS.close();
        statement.close();

        PreparedStatement dayStatement = connection.prepareStatement("SELECT COALESCE(SUM(caloriesBurned), 0) FROM workouts WHERE entryDate = ?");
        dayStatement.setDate(1, day);
        RS = dayStatement.executeQuery();
        RS.next();
        dayBurned = RS.getInt(1);
        RS.close();
        dayStatement.close();

        dayStatement = connection.prepareStatement("SELECT COALESCE(SUM(calories), 0) FROM meals WHERE entryDate = ?");
        dayStatement.setDate(1, day);
        RS = dayStatement.executeQuery();
        RS.next();
        dayEaten = RS.getInt(1);
        RS.close();
        dayStatement.close();
    }
    private int currentValueFor(String goalType) {
        if (goalType.equals("Calories Burned")) {
            return dayBurned;
        } else {
            return dayEaten;   // Calorie Limit
        }
    }
    private void checkGoals(Date day) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            loadTotals(connection, day);

            Statement statement = connection.createStatement();
            ResultSet RS = statement.executeQuery("SELECT goalName, goalType, targetValue FROM goals");

            String notifications = "";
            while (RS.next()) {
                String goalName = RS.getString("goalName");
                String goalType = RS.getString("goalType");
                double target = RS.getDouble("targetValue");
                int current = currentValueFor(goalType);

                if (goalType.equals("Calorie Limit")) {
                    if (current > target) {
                        notifications += "Over your limit: " + goalName + " (" + current + " / " + (int) target + " kcal)\n";
                    } else if (current >= target * 0.9) {
                        notifications += "Close to your limit: " + goalName + " (" + current + " / " + (int) target + " kcal)\n";
                    }
                } else {
                    if (current >= target) {
                        notifications += "Goal reached: " + goalName + " (" + current + " / " + (int) target + " kcal)\n";
                    } else if (current >= target * 0.8) {
                        notifications += "Almost there: " + goalName + " (" + (int) (target - current) + " kcal to go)\n";
                    }
                }
            }
            RS.close();
            statement.close();
            connection.close();

            if (!notifications.isEmpty()) {
                JOptionPane.showMessageDialog(this, notifications, "Goal Notification", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
        }
    }
    private void search() {
        int choice = cmbView.getSelectedIndex();

        Date day = null;
        if (choice >= 3) {
            day = readDate();
            if (day == null) {
                return;
            }
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            String text = "";

            if (choice == 0) {
                text = listWorkouts(connection);
            } else if (choice == 1) {
                text = listMeals(connection);
            } else if (choice == 2) {
                text = listGoals(connection);
            } else {
                text = buildReport(connection, day);
            }

            connection.close();
            txaProgressReport.setText(text);
            txaProgressReport.setCaretPosition(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
        }
    }
    private String listWorkouts(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet RS = statement.executeQuery("SELECT workoutId, name, caloriesBurned, entryDate FROM workouts");
        String text = "WORKOUTS\n";
        boolean found = false;
        while (RS.next()) {
            found = true;
            text += "#" + RS.getInt("workoutId") + "  " + RS.getDate("entryDate") + "  "
                    + RS.getString("name") + "  -  " + RS.getInt("caloriesBurned") + " kcal burned\n";
        }
        if (!found) {
            text += "No workouts logged yet.\n";
        }
        RS.close();
        statement.close();
        return text;
    }

    private String listMeals(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet RS = statement.executeQuery("SELECT mealId, name, calories, entryDate FROM meals");
        String text = "MEALS\n";
        boolean found = false;
        while (RS.next()) {
            found = true;
            text += "#" + RS.getInt("mealId") + "  " + RS.getDate("entryDate") + "  "
                    + RS.getString("name") + "  -  " + RS.getInt("calories") + " kcal\n";
        }
        if (!found) {
            text += "No meals logged yet.\n";
        }
        RS.close();
        statement.close();
        return text;
    }

    private String listGoals(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet RS = statement.executeQuery("SELECT id, goalName, goalType, targetValue FROM goals");
        String text = "GOALS\n";
        boolean found = false;
        while (RS.next()) {
            found = true;
            text += "#" + RS.getInt("id") + "  " + RS.getString("goalName") + "  ("
                    + RS.getString("goalType") + ")  target: " + (int) RS.getDouble("targetValue") + " kcal per day\n";
        }
        if (!found) {
            text += "No goals set yet.\n";
        }
        RS.close();
        statement.close();
        return text;
    }
    private String buildReport(Connection connection, Date day) throws SQLException {
        loadTotals(connection, day);
        String report = "";

        report += "WORKOUT REPORT\n";
        report += "Total workouts: " + totalWorkouts + "\n";
        report += "Total calories burned: " + totalBurned + " kcal\n";
        if (totalWorkouts > 0) {
            report += "Average burn per workout: " + (totalBurned / totalWorkouts) + " kcal\n";
        }
        report += "Calories burned on " + day + ": " + dayBurned + " kcal\n";

        report += "\n";
        report += "MEAL REPORT\n";
        report += "Total meals: " + totalMeals + "\n";
        report += "Total calories consumed: " + totalEaten + " kcal\n";
        if (totalMeals > 0) {
            report += "Average calories per meal: " + (totalEaten / totalMeals) + " kcal\n";
        }
        report += "Calories consumed on " + day + ": " + dayEaten + " kcal\n";

        report += "\nNet calories on " + day + " (eaten - burned): " + (dayEaten - dayBurned) + " kcal\n";
        report += "\nFITNESS GOALS (" + day + ")\n";

        Statement statement = connection.createStatement();
        ResultSet RS = statement.executeQuery("SELECT goalName, goalType, targetValue FROM goals");
        boolean hasGoals = false;
        while (RS.next()) {
            hasGoals = true;
            String goalType = RS.getString("goalType");
            double target = RS.getDouble("targetValue");
            int current = currentValueFor(goalType);
            int percent = 0;
            if (target > 0) {
                percent = (int) (current * 100 / target);
            }
            report += RS.getString("goalName") + " (" + goalType + "): "
                    + current + " / " + (int) target + " kcal  [" + percent + "%]\n";
        }
        if (!hasGoals) {
            report += "No goals set.\n";
        }
        RS.close();
        statement.close();
        return report;
    }
}

import javax.swing.*;

public class FitnessTrackerApp extends JFrame{

    private JLabel lblHeader, lblWorkoutName, lblWorkoutSearch;
    private JTextField txtWorkoutName, txtWorkoutSearch;
    private JButton btnAddWorkout, btnSearchWorkout;

    fitnessTrackerApp() {
        setTitle("Fitness Tracker Application");
        setSize(950, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblHeader = new JLabel("FITNESS TRACKER APPLICATION",
                SwingConstants.CENTER);
        lblHeader.setBounds(200, 10, 550, 30);
        add(lblHeader);

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
    }
    
    public static void main(String[] args) {
        FitnessTrackerApp app = new FitnessTrackerApp();
        app.setVisible(true);
    }
}

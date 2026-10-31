import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {

    private static final int MAX_ATTEMPTS = 3;

    private JButton LOGIN_btn, SignUp_btn, Password_btn;
    private JLabel Labelusername, Labelpassword;
    private JTextField usertxt;
    private JPasswordField password;
    private char defaultEchoChar;
    private int attempts = 0;

    Login() {
        setTitle("Log-in Page");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Labelusername = new JLabel("Username");
        Labelusername.setBounds(160, 50, 100, 30);
        add(Labelusername);

        usertxt = new JTextField();
        usertxt.setBounds(115, 80, 160, 30);
        add(usertxt);

        Labelpassword = new JLabel("Password");
        Labelpassword.setBounds(160, 120, 100, 30);
        add(Labelpassword);

        password = new JPasswordField();
        password.setBounds(115, 150, 160, 30);
        add(password);
        defaultEchoChar = password.getEchoChar();

        Password_btn = new JButton("Show Password");
        Password_btn.setBounds(115, 190, 160, 25);
        add(Password_btn);

        LOGIN_btn = new JButton("Log-in");
        LOGIN_btn.setBounds(130, 235, 130, 30);
        add(LOGIN_btn);

        SignUp_btn = new JButton("Sign-Up");
        SignUp_btn.setBounds(130, 280, 130, 30);
        add(SignUp_btn);

        // Register listeners (without these, nothing happens on click)
        Password_btn.addActionListener(this);
        LOGIN_btn.addActionListener(this);
        SignUp_btn.addActionListener(this);
        usertxt.addActionListener(this);
        password.addActionListener(this);   // Enter key submits
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Password_btn) {
            togglePasswordVisibility();
        } else if (source == LOGIN_btn || source == usertxt || source == password) {
            handleLogin();
        } else if (source == SignUp_btn) {
            new Register().setVisible(true);
            dispose();
        }
    }

    private void togglePasswordVisibility() {
        if (password.getEchoChar() == (char) 0) {
            password.setEchoChar(defaultEchoChar);
            Password_btn.setText("Show Password");
        } else {
            password.setEchoChar((char) 0);
            Password_btn.setText("Hide Password");
        }
    }

    private void handleLogin() {
        String username = usertxt.getText().trim();
        char[] passChars = password.getPassword();
        String pass = new String(passChars);
        Arrays.fill(passChars, (char) 0);

        if (username.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            if (username.isEmpty()) {
                usertxt.requestFocus();
            } else {
                password.requestFocus();
            }
            return;
        }

        // Database check (replaces the hardcoded admin/1234)
        boolean found = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, pass);
            ResultSet RS = statement.executeQuery();
            found = RS.next();
            RS.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
            return;
        }

        if (found) {
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + username + "!",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            new Dashboard(username).setVisible(true);
            dispose();
        } else {
            attempts++;
            int remaining = MAX_ATTEMPTS - attempts;

            if (remaining <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Too many failed attempts. The application will be closed.",
                        "Access Denied", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password.\nAttempts remaining: " + remaining,
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                password.setText("");
                password.requestFocus();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}

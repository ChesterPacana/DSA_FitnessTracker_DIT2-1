import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {

    private JButton REGISTER_btn, Back_btn, Password_btn;
    private JLabel Labelusername, Labelpassword, Labelconfirm;
    private JTextField usertxt;
    private JPasswordField password, confirm;
    private char defaultEchoChar;

    Register() {
        setTitle("Sign-Up Page");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Labelusername = new JLabel("Username");
        Labelusername.setBounds(160, 40, 100, 30);
        add(Labelusername);

        usertxt = new JTextField();
        usertxt.setBounds(115, 70, 160, 30);
        add(usertxt);

        Labelpassword = new JLabel("Password");
        Labelpassword.setBounds(160, 110, 100, 30);
        add(Labelpassword);

        password = new JPasswordField();
        password.setBounds(115, 140, 160, 30);
        add(password);
        defaultEchoChar = password.getEchoChar();

        Labelconfirm = new JLabel("Confirm Password");
        Labelconfirm.setBounds(140, 180, 130, 30);
        add(Labelconfirm);

        confirm = new JPasswordField();
        confirm.setBounds(115, 210, 160, 30);
        add(confirm);

        Password_btn = new JButton("Show Password");
        Password_btn.setBounds(115, 250, 160, 25);
        add(Password_btn);

        REGISTER_btn = new JButton("Sign-Up");
        REGISTER_btn.setBounds(130, 295, 130, 30);
        add(REGISTER_btn);

        Back_btn = new JButton("Back");
        Back_btn.setBounds(130, 340, 130, 30);
        add(Back_btn);

        Password_btn.addActionListener(this);
        REGISTER_btn.addActionListener(this);
        Back_btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == Password_btn) {
            togglePasswordVisibility();
        } else if (source == REGISTER_btn) {
            handleRegister();
        } else if (source == Back_btn) {
            new Login().setVisible(true);
            dispose();
        }
    }

    private void togglePasswordVisibility() {
        if (password.getEchoChar() == (char) 0) {
            password.setEchoChar(defaultEchoChar);
            confirm.setEchoChar(defaultEchoChar);
            Password_btn.setText("Show Password");
        } else {
            password.setEchoChar((char) 0);
            confirm.setEchoChar((char) 0);
            Password_btn.setText("Hide Password");
        }
    }

    private void handleRegister() {
        String username = usertxt.getText().trim();
        String pass = new String(password.getPassword());
        String conf = new String(confirm.getPassword());

        if (username.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!pass.equals(conf)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.",
                    "Sign-Up Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker", "root", "");

            // check if the username already exists
            PreparedStatement check = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
            check.setString(1, username);
            ResultSet RS = check.executeQuery();
            boolean taken = RS.next();
            RS.close();
            check.close();

            if (taken) {
                JOptionPane.showMessageDialog(this, "That username is already taken.",
                        "Sign-Up Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                PreparedStatement insert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                insert.setString(1, username);
                insert.setString(2, pass);
                insert.executeUpdate();
                insert.close();

                JOptionPane.showMessageDialog(this, "Account created! You can now log in.");
                new Login().setVisible(true);
                dispose();
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
        }
    }
}

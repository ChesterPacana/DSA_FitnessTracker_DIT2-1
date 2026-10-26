import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {


    private JButton LOGIN_btn, SignUp_btn;
    private JLabel Labelusername, Labelpassword;
    private JTextField usertxt;
    private JPasswordField passwordField;


    Login(){
        setTitle("Log-in Page");
        setSize(400, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Labelusername = new JLabel("Username");
        Labelusername.setBounds(160,50,100,50);
        add(Labelusername);

        usertxt = new JTextField();
        usertxt.setBounds(115,90,160,30);
        add(usertxt);


        Labelpassword = new JLabel("Password");
        Labelpassword.setBounds(160,120,100,50);
        add(Labelpassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(115,160,160,30);
        add(passwordField);

        LOGIN_btn = new JButton("Log-in");
        LOGIN_btn.setBounds(130,220,130,30);
        add(LOGIN_btn);

        SignUp_btn = new JButton("Sign-Up");
        SignUp_btn.setBounds(130,270,130,30);
        add(SignUp_btn);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = usertxt.getText();
        String password = passwordField.getText();
        try{
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/fitness_tracker","root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");
            statement.setString(1, usertxt.getText());
            statement.setString(2, new String(passwordField.getPassword()));
            ResultSet RS = statement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error!");
        }
    }
}

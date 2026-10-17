import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {


    private JButton LOGIN_btn, Password_btn;
    private JLabel Labelusername, Labelpassword;
    private JTextField usertxt;
    private JPasswordField password;


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
        Labelpassword.setBounds(160,140,100,50);
        add(Labelpassword);

        password = new JPasswordField();
        password.setBounds(115,180,160,30);
        add(password);





    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

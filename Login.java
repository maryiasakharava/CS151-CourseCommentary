import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Login extends JFrame implements ActionListener {
    String usernameInput;
    String passwordInput;
    JLabel l1, l2, l3;   //label for email and password

    JTextField tf1; // email field

    JButton btn1; // login button

    JPasswordField p1; // password field

   // File f = new File("/users.txt");   //file path
   // int ln;







    public Login() {

        setTitle("Login");

        setVisible(true);

        setSize(800, 800);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Please Sign into your Account");

        l1.setForeground(Color.blue);

        l1.setFont(new Font("Serif", Font.ITALIC, 25));

        l2 = new JLabel("Email:");

        l3 = new JLabel("Password:");

        tf1 = new JTextField();

        p1 = new JPasswordField();

        btn1 = new JButton("Submit");

        l1.setBounds(100, 30, 400, 30);

        l2.setBounds(80, 70, 200, 30);

        l3.setBounds(80, 110, 200, 30);

        tf1.setBounds(300, 70, 200, 30);

        p1.setBounds(300, 110, 200, 30);

        btn1.setBounds(150, 160, 100, 30);

        add(l1);

        add(l2);

        add(tf1);

        add(l3);

        add(p1);

        add(btn1);

        btn1.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1){
             usernameInput = tf1.getText();
            char[] s4 = p1.getPassword();
             passwordInput = new String(s4);
            JOptionPane.showMessageDialog(btn1, "Logged in as:" + usernameInput);



        }

    }


}
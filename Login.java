import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class Login extends JFrame implements ActionListener {
    String usernameInput;
    String passwordInput;
    JLabel l1, l2, l3;   //label for email and password
    JTextField tf1; // email field
    JButton btn1; // login button
    JPasswordField p1; // password field
    HashMap<String, String[]> h;




    public Login() {
        setTitle("Login");
        setVisible(true);
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
        h = new HashMap<>();
        ReadInFile("users.txt");

    }

    public void ReadInFile(String filepath)
    {
        //Read in the values that are in the username, password, full name, and email fields into a hashmap
        try (BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split(":");
                String values = keyValue[1];
                this.h.put(keyValue[0], values.split(","));
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //this action performed method only acts if the action's source is pressing the loginButton
        if (e.getSource() == btn1) {
            //Start by taking in the text entered as username and password
            String s1 = tf1.getText();
            char[] p = p1.getPassword();
            //to make code more readable, I have extracted the first value in the String array, which is the password

            //the login is successful if the username is stored and the password matches
            if (this.h.containsKey(s1) && new String(p).equals(h.get(s1)[0].substring(1, h.get(s1)[0].length()))) {
                JOptionPane.showMessageDialog(btn1, "Logged in Successfully! Welcome " + h.get(s1)[1] + ", " + h.get(s1)[2].substring(0, h.get(s1)[2].length()-1));
                this.dispose();
                //if the username is contained but password does not match, needs different password
            } else if (this.h.containsKey(s1) && !new String(p).equals(h.get(s1)[0].substring(1, h.get(s1)[0].length()))) {
                JOptionPane.showMessageDialog(btn1, "Password Incorrect. Try again!");
                //if username is not contained, need to sign up
            } else {
                JOptionPane.showMessageDialog(btn1, "Account not found. Sign up for an account");
                this.dispose();
            }

            //if the action is not from the login button, empty every field
        } else {
            tf1.setText("");
            p1.setText("");
        }
    }

}
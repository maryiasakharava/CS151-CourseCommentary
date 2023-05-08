import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

//the Login page which will let users log in and provide an appropriate response to user being found or not found
public class Login extends JFrame implements ActionListener {
    Header l1;
    Label l2, l3;   //label for email and password
    JTextField tf1; // email field
    JButton btn1; // login button
    JPasswordField p1; // password field
    HashMap<String, String[]> h;

    Font f, f1;

    JColorChooser c, c1;

    public Login() {

        setTitle("Login");
        setVisible(true);
        setSize(800, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        c = new JColorChooser();
        c.setColor(0, 153,153);

        c1 = new JColorChooser();
        c1.setColor(255,255,204);

        f = new Font("DIALOG_INPUT", Font.PLAIN, 20);
        f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

        topPanel();

        middlePanel();

    }

    //The read in file will read in all users which have signed up
    //The users will all be stored in a hashmap which will then be traversed and matched with the user's username and password when they input it
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

    //this action performed function will give various popups if the password is incorrect or user is not found
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
                CourseCommentaryLoggedIn logged = new CourseCommentaryLoggedIn();
                this.dispose();

                //if the username is contained but password does not match, needs different password
            } else if (this.h.containsKey(s1) && !new String(p).equals(h.get(s1)[0].substring(1, h.get(s1)[0].length()))) {
                JOptionPane.showMessageDialog(btn1, "Password Incorrect. Try again!");
                //if username is not contained, need to sign up
            } else {
                JOptionPane.showMessageDialog(btn1, "Account not found. Sign up for an account");
                Login log = new Login();
                this.dispose();
            }

            //if the action is not from the login button, empty every field
        } else {
            tf1.setText("");
            p1.setText("");
        }
    }

    //the top panel which gives the title
    private void topPanel()
    {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        l1 = new Header("Please Sign into your Account", Header.Color.TAN);
        top.add(l1);
        top.setBackground(c.getColor());
        top.setBounds(0, 0, this.getWidth(), 35);
        add(top);
    }

    //the middle panel which has all the text fields to input the username and password
    private void middlePanel()
    {
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBounds(0, 30, this.getWidth(), this.getHeight()-35);
        middlePanel.setBackground(c1.getColor());
        l2 = new Label("Email:");
        l3 = new Label("Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Submit");
        l2.setBounds(140, 70, 200, 30);
        l3.setBounds(140, 110, 200, 30);
        tf1.setBounds(360, 70, 200, 30);
        p1.setBounds(360, 110, 200, 30);
        btn1.setBounds(360, 160, 100, 30);
        middlePanel.add(l2);
        middlePanel.add(l3);
        middlePanel.add(tf1);
        middlePanel.add(p1);
        middlePanel.add(btn1);

        //this button will return the user back to the home
        JButton back = new JButton("Return to Home");
        back.setBounds(50, 300, 150, 30);
        middlePanel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CourseCommentary c = new CourseCommentary();
            }
        });

        add(middlePanel);

        //refers back to the action listener class which will return the correct popup based on the  user's input
        btn1.addActionListener(this);
        h = new HashMap<>();
        ReadInFile("users.txt");
    }


}
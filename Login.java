import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
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

    Font f, f1;

    JColorChooser c, c1;

    ImageIcon i, newIcon;
    BufferedImage bi;
    Graphics g;


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

        i = new ImageIcon("My project-1 (2).png");
        Image i1 = i.getImage();
        bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        g = bi.createGraphics();
        g.drawImage(i1,250,600,200,130,null);
        newIcon = new ImageIcon(bi);


        middlePanel();

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
                CourseCommentaryLoggedIn logged = new CourseCommentaryLoggedIn();
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

    private void topPanel()
    {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        l1 = new JLabel("Please Sign into your Account");
        l1.setFont(f);
        l1.setForeground(c1.getColor());
        top.add(l1);
        top.setBackground(c.getColor());
        top.setBounds(0, 0, this.getWidth(), 35);
        add(top);
    }

    private void middlePanel()
    {
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBounds(0, 30, this.getWidth(), this.getHeight()-35);
        middlePanel.setBackground(c1.getColor());
        l2 = new JLabel("Email:");
        l3 = new JLabel("Password:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Submit");
        l2.setBounds(140, 70, 200, 30);
        l3.setBounds(140, 110, 200, 30);
        tf1.setBounds(360, 70, 200, 30);
        p1.setBounds(360, 110, 200, 30);
        btn1.setBounds(360, 160, 100, 30);
        l2.setFont(f1);
        l3.setFont(f1);
        l2.setForeground(c.getColor());
        l3.setForeground(c.getColor());
        middlePanel.add(l2);
        middlePanel.add(l3);
        middlePanel.add(tf1);
        middlePanel.add(p1);
        middlePanel.add(btn1);

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

        btn1.addActionListener(this);
        h = new HashMap<>();
        ReadInFile("users.txt");
    }


}
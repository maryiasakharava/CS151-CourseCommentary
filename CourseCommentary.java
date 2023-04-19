import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Map;


public class CourseCommentary extends JFrame  {
   // JFrame frame ;
   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton login;
    JButton signup;
    public CourseCommentary(){

        login = new JButton("Log in");
        signup = new JButton("Create an Account");
        setTitle("Course Search");
        setSize(800, 600);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton writeReviewButton = new JButton("Write Review");
        topPanel.add(writeReviewButton, BorderLayout.WEST);

        // Log In and Sign Up Buttons
        JPanel loginSignupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Log In");
        JButton signupButton = new JButton("Sign Up");
        loginSignupPanel.add(loginButton);
        loginSignupPanel.add(signupButton);
        topPanel.add(loginSignupPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Put a title next to search bar
        JLabel titleLabel = new JLabel("Course Search");
        centerPanel.add(titleLabel);
        // Search bar created
        JTextField searchBar = new JTextField(20);
        centerPanel.add(searchBar);

        // Make a button to enter searches
        JButton searchButton = new JButton("Search");
        centerPanel.add(searchButton);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
            }
            });
        writeReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Review r1 = null;
                try {
                    dispose();
                    r1 = new Review();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }





            }
        });
       // frame.add(signup);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup s1 = null;
                try {
                    dispose();
                    s1 = new Signup();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }




}


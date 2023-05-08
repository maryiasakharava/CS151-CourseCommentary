
//Home page -- includes the custom created logo and search feature
//Home page for unregistered users
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CourseCommentary extends JFrame  {
    JColorChooser c, c1;
    ImageIcon i;
    ImageIcon newIcon;

    BufferedImage bi;

    Graphics g;

    Font f1;

    public CourseCommentary(){

        setUndecorated(true);
        setVisible(true);

        c = new JColorChooser();
        c.setColor(0, 153,153);

        c1 = new JColorChooser();
        c1.setColor(255,255,204);

        f1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 25);

        //Loading in the custom logo to display in the center of the home page
        i = new ImageIcon("CourseCommentaryLogo.png");
        Image i1 = i.getImage();
        bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        g = bi.createGraphics();
        g.drawImage(i1,350,150,600,360,null);
        newIcon = new ImageIcon(bi);

        setTitle("Course Search");
        setSize(800, 800);
        setLayout(new BorderLayout());

        // Log In and Sign Up Buttons
        logInSignUpPanel();

        //search
        searchPanel();

        bottomPanel();

        setVisible(true);
    }

    //create panel which is on the top of the home page
    private void logInSignUpPanel()
    {
        JPanel loginSignupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginSignupPanel.setBackground(c.getColor());

        //create login and signup button which will be displayed at the top of the home page
        JButton loginButton = new JButton("Log In");

        JButton signupButton = new JButton("Sign Up");

        JButton close = new JButton("Close");


        //arranging the buttons on the panel and setting
        loginSignupPanel.add(loginButton);
        loginSignupPanel.add(signupButton);
        loginSignupPanel.add(close);
        add(loginSignupPanel, BorderLayout.NORTH);

        //redirect to the login page when login button clicked
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
            }
        });

        //redirect to the signup button when
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

        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
    }


    //creating the search panel which contains the search bar, search button, and the logo
    private void searchPanel()
    {
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(c1.getColor());
        // Put a title next to search bar
        Header titleLabel = new Header("Course Search", Header.Color.TEAL);
        titleLabel.setBounds(100, 50, 200, 25);

        centerPanel.add(titleLabel);
        // Search bar created
        JTextField searchBar = new JTextField(30);
        searchBar.setBounds(300, 50, 300, 25);
        centerPanel.add(searchBar);

        // Make a button to enter searches
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(600, 50, 150, 25);
        centerPanel.add(searchButton);

        JLabel icon = new JLabel(newIcon);
        icon.setBounds(0, 200, 800, 400);
        centerPanel.add(icon);
        add(centerPanel);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    ReviewDisplay rd = new ReviewDisplay(searchBar.getText());
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });



    }

    //bottom panel which just contains the border
    private void bottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(c.getColor());
        add(bottomPanel, BorderLayout.SOUTH);
    }

}


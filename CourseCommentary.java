
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CourseCommentary extends JFrame  {
   // JFrame frame ;
   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton login;
    JButton signup;

    JPanel topPanel;
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

        i = new ImageIcon("Images/My project-1 (2).png");
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

    private void logInSignUpPanel()
    {
        JPanel loginSignupPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginSignupPanel.setBackground(c.getColor());
        JButton loginButton = new JButton("Log In");
        loginButton.setBackground(c1.getColor());
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBackground(c1.getColor());
        loginButton.setBounds(400, 10, 200, 100);
        signupButton.setSize(150, 100);
        loginSignupPanel.add(loginButton);
        loginSignupPanel.add(signupButton);
        add(loginSignupPanel, BorderLayout.NORTH);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login log = new Login();
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

    private void searchPanel()
    {
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(c1.getColor());
        // Put a title next to search bar
        JLabel titleLabel = new JLabel("Course Search");
        titleLabel.setBounds(100, 50, 200, 25);
        titleLabel.setFont(f1);
        titleLabel.setForeground(c.getColor());

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


    }

    private void bottomPanel()
    {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(c.getColor());
        add(bottomPanel, BorderLayout.SOUTH);
    }

}


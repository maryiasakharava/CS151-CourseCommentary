import javax.swing.*;
import java.awt.*;

public class HomeSearchPage extends JFrame {
    public HomeSearchPage() {
        setTitle("Course Search");
        setSize(800, 600);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        //Write Review Button
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
    }
    public static void main(String[] args) {
        HomeSearchPage page = new HomeSearchPage();
        page.setVisible(true);
    }
}



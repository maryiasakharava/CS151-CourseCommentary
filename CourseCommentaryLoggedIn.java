import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CourseCommentaryLoggedIn extends JFrame{
        // JFrame frame ;
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton login;
        JButton signup;

        JColorChooser c, c1;
        Font f, f1;
        ImageIcon i, newIcon;
        BufferedImage bi;
        Graphics g;
        public CourseCommentaryLoggedIn(){

            setUndecorated(true);
            setVisible(true);

            c = new JColorChooser();
            c.setColor(0, 153,153);

            c1 = new JColorChooser();
            c1.setColor(255,255,204);

            f = new Font(Font.DIALOG_INPUT, Font.BOLD, 25);

            setTitle("Course Search");
            setSize(800, 800);
            setLayout(new BorderLayout());

            // Log In and Sign Up Buttons
            i = new ImageIcon("My project-1 (2).png");
            Image i1 = i.getImage();
            bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            g = bi.createGraphics();
            g.drawImage(i1,350,150,600,360,null);
            newIcon = new ImageIcon(bi);

            writeReview();

            searchPanel();

            bottomPanel();

        }

        private void writeReview()
        {
            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.setBackground(c.getColor());
            JButton writeReviewButton = new JButton("Write Review");
            JButton logOut = new JButton("Log Out");
            topPanel.add(writeReviewButton);
            topPanel.add(logOut);

            add(topPanel, BorderLayout.NORTH);

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

            logOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CourseCommentary c = null;
                    dispose();
                    c = new CourseCommentary();
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
        titleLabel.setFont(f);
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



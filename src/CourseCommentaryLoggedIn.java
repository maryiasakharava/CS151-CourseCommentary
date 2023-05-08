
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.IOException;

//the home page which is shown to registered users
public class CourseCommentaryLoggedIn extends JFrame{

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

            // imports the logo into the home page to display in the center of the home page
            i = new ImageIcon("CourseCommentaryLogo.png");
            Image i1 = i.getImage();
            bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            g = bi.createGraphics();
            g.drawImage(i1,350,150,600,360,null);
            newIcon = new ImageIcon(bi);

            //panels which are top, middle, bottom of the frame
            writeReview();

            searchPanel();

            bottomPanel();

        }

        //top panel which contains the write review buttons and the log out button
        private void writeReview()
        {

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            topPanel.setBackground(c.getColor());

            //write review and log out buttons
            JButton writeReviewButton = new JButton("Write Review");
            JButton logOut = new JButton("Log Out");
            JButton close = new JButton("Close");
            topPanel.add(writeReviewButton);
            topPanel.add(logOut);
            topPanel.add(close);

            add(topPanel, BorderLayout.NORTH);

            //redirect to the write review page
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

            //redirect to the unregistered user home page
            logOut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CourseCommentary c = null;
                    dispose();
                    c = new CourseCommentary();
                }
            });

            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    dispose();
                }
            });
        }


        private void searchPanel()
        {
            //creating the center panel which will have all the search components -- search bar and search button
            //inputs the logo in the center of the home page
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

            //creates a new review display page which takes in the search bar entry and searches for reviews that match
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    try {
                        ReviewDisplayLoggedIn rd = new ReviewDisplayLoggedIn(searchBar.getText());
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            JLabel icon = new JLabel(newIcon);
            icon.setBounds(0, 200, 800, 400);
            centerPanel.add(icon);
            add(centerPanel);
        }

        //the bottom panel which has the border
        private void bottomPanel()
        {
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(c.getColor());
            add(bottomPanel, BorderLayout.SOUTH);
        }
}



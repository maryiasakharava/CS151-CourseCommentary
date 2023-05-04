import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

//page which displays the reviews based upon the search made by the users
public class ReviewDisplay extends JFrame {
    private ArrayList<String[]> reviews2;
    private JTable display;
    JColorChooser c, c1;
    Double averageRating;
    double ratingSum;
    int countTakeAgain;
    int countReviews;

    Font f, f1;
    private String searchEntry;

    //the constructor takes the search entry as a parameter
    //this search entry determines which of the reviews from reviews.txt get printed into the table
    //in the search function, when the user presses search, the action listener calls this function w the search entry as a parameter
    public ReviewDisplay(String se) throws PrinterException {
        searchEntry = se;

        setSize(800, 800);
        setUndecorated(true);
        setVisible(true);

        c = new JColorChooser();
        c.setColor(0, 153, 153);

        c1 = new JColorChooser();
        c1.setColor(255, 255, 204);

        f = new Font(Font.DIALOG_INPUT, Font.BOLD, 25);
        f1 = new Font(Font.DIALOG_INPUT, Font.BOLD, 18);

        //the reviews for a specific course are all stored in the reviews.txt file as an Arraylist of strings which are attached to one hashmap
        //initialize the arraylist which will take in all the reviews for a course
        reviews2 = new ArrayList<String[]>();

        //read in file is the function which will read in all the relevant reviews from the txt file
        ReadInFile("reviews.txt");

        //if there are no reviews found for a search, a dialog pane pops up telling the user there are no reviews
        if(reviews2.size() == 0)
        {
            CourseCommentary c = new CourseCommentary();
            JOptionPane.showMessageDialog(null, "Sorry! No reviews found for " + searchEntry);
            dispose();
        }
        //if there are reviews found, all of the panels for the review display will be initialized
        else
        {
            backToHomePanel();
            displayPanel();
            JPanel bottom = new JPanel();
            bottom.setBackground(c.getColor());
            add(bottom, BorderLayout.SOUTH);
        }
    }

    //the function reads in reviews which are from the reviews.txt file into the arraylist of strings
    public void ReadInFile(String filepath) {
        //Read in the values that are in the username, password, full name, and email fields into a hashmap
        //only reads in the reviews if the key value- aka the course- matches the search entry
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split(":");
                if(keyValue[0].equals(searchEntry))
                {
                    String[] arr = keyValue[1].split(",");
                    reviews2.add(arr);
                    countReviews++;
                    if(arr[4].equals(" Yes")){
                        countTakeAgain++;
                    }
                    try{
                    int rating = Integer.valueOf(arr[3].substring(1));
                    ratingSum+=rating;
                    }
                    catch (NumberFormatException ex){
                        ex.printStackTrace();
                    }

                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    //top Panel on the top of the Review display which lets you return to the home page
    public void backToHomePanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(c.getColor());
        JButton backToHome = new JButton("Return to Home");
        topPanel.add(backToHome);

        //redirects user to home
        backToHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CourseCommentary home = new CourseCommentary();
            }
        });

        add(topPanel, BorderLayout.NORTH);
    }

    //the middle display panel contains a JTable which holds all the reviews which are associated with the search entry
    public void displayPanel() throws PrinterException {

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBounds(0, 30, getWidth(), getHeight() - 15);

        //default model which has six different columbs for the different aspects of the review
        display = new JTable();
        DefaultTableModel model = (DefaultTableModel) display.getModel();
        model.addColumn("Course");
        model.addColumn("Professor");
        model.addColumn("Review");
        model.addColumn("Rating");
        model.addColumn("Would Take Again");
        model.addColumn("Textbook Required");

        //uses textAreaRenderer class in order to allow text wrapping in the columns
        display.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());


        //this for loop adds each new review into the table on a new row and adds a row in between to create space in the table
        for(String[] rev: reviews2) {
            model.addRow(rev);
            model.addRow(new Object[] {"", "", "", ""});        }

        //this scrollPane contains the table
        JScrollPane jp = new JScrollPane(display);

        JPanel topPanel = new JPanel(new BorderLayout());

        //the topPanel is created to arrange the display table, header, and footer
        //header shows which search entry reviews are being shown
        topPanel.setBackground(c1.getColor());
        JLabel courseName = new JLabel("Showing reviews for " + searchEntry);
        courseName.setFont(f);
        courseName.setForeground(c.getColor());
        topPanel.add(courseName, BorderLayout.CENTER);

        //the bottom panel contains the statistics of the course; the average course rating and the percent of users who would take the class again
        JPanel stats = new JPanel(new FlowLayout());
        JLabel averageRating = new JLabel("Rating: "+aveRating() + "   ");
        JLabel takeAgain = new JLabel("Would take again: "+ percentTakeAgain()+"%");
        averageRating.setFont(f);
        averageRating.setForeground(c.getColor());
        takeAgain.setFont(f);
        takeAgain.setForeground(c.getColor());
        stats.add(averageRating);
        stats.setBackground(c1.getColor());
        stats.add(takeAgain);

        //the bottom panel containing the stats is added to display panel
        displayPanel.add(stats, BorderLayout.SOUTH);
        //top panel containing the title is added to displayPanel
        displayPanel.add(topPanel, BorderLayout.NORTH);
        //the table is added to the displayPanel
        displayPanel.add(jp, BorderLayout.CENTER);

        //the center panel is added to the frame and displayed in the middle
        add(displayPanel);
    }

    //average rating takes all of the ratings input by users and averages them
    public String aveRating(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        averageRating = ratingSum/countReviews;
        String formattedNumber = decimalFormat.format(averageRating);
        return formattedNumber;
    }

    //calculates how many users out of all reviews would take the course again
    public String percentTakeAgain(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        Double count = (countTakeAgain*1.0)/(countReviews*1.0);
        String formattedNumber = decimalFormat.format(count*100.0);
        return formattedNumber;
    }

}

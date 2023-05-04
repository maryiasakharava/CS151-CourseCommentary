import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        reviews2 = new ArrayList<String[]>();

        backToHomePanel();
        displayPanel();

        JPanel bottom = new JPanel();
        bottom.setBackground(c.getColor());
        add(bottom, BorderLayout.SOUTH);

    }

    public void ReadInFile(String filepath) {
        //Read in the values that are in the username, password, full name, and email fields into a hashmap
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

    public void backToHomePanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(c.getColor());
        JButton backToHome = new JButton("Return to Home");
        topPanel.add(backToHome);

        backToHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CourseCommentary home = new CourseCommentary();
            }
        });

        add(topPanel, BorderLayout.NORTH);
    }

    public void displayPanel() throws PrinterException {
        ReadInFile("reviews.txt");

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBounds(0, 30, getWidth(), getHeight() - 15);

        display = new JTable();
        DefaultTableModel model = (DefaultTableModel) display.getModel();
        model.addColumn("Course");
        model.addColumn("Professor");
        model.addColumn("Review");
        model.addColumn("Rating");
        model.addColumn("Would Take Again");
        model.addColumn("Textbook Required");

        display.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());


        for(String[] rev: reviews2) {
            model.addRow(rev);
            model.addRow(new Object[] {"", "", "", ""});        }

        JScrollPane jp = new JScrollPane(display);

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.setBackground(c1.getColor());
        JLabel courseName = new JLabel("Showing reviews for " + searchEntry);
        courseName.setFont(f);
        courseName.setForeground(c.getColor());
        topPanel.add(courseName, BorderLayout.CENTER);

        JPanel stats = new JPanel(new FlowLayout());
        JLabel averageRating = new JLabel("Rating: "+aveRating());
        JLabel takeAgain = new JLabel("Would take again: "+ percentTakeAgain()+"%");
        averageRating.setFont(f);
        averageRating.setForeground(c.getColor());
        takeAgain.setFont(f);
        takeAgain.setForeground(c.getColor());
        stats.add(averageRating);
        stats.setBackground(c1.getColor());
        stats.add(takeAgain);

        displayPanel.add(stats, BorderLayout.SOUTH);
        displayPanel.add(topPanel, BorderLayout.NORTH);
        displayPanel.add(jp, BorderLayout.CENTER);

        add(displayPanel);
    }
    public String aveRating(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        averageRating = ratingSum/countReviews;
        String formattedNumber = decimalFormat.format(averageRating);
        return formattedNumber;
    }
    public String percentTakeAgain(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        Double count = (countTakeAgain*1.0)/(countReviews*1.0);
        String formattedNumber = decimalFormat.format(count*100.0);
        return formattedNumber;
    }

}

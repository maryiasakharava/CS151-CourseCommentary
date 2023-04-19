import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Review extends JFrame implements ActionListener  {

  JPanel panel;
  Dimension screenSize;
  JLabel title;
  JLabel label;
  JLabel label2;
  JLabel label3;
  Font f;
  Font f2;
  JTextField course;
  JTextField prof;
  JTextArea review;
  JLabel diff;
  JMenuBar jb;
  JMenu men;
  JButton submit;
  HashMap<String, String> h;




  public Review() throws IOException
  {
    panel = new JPanel();
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screenSize.width/2, screenSize.height);
    add(panel);
    //frame.setVisible(true);
    //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //frame.setUndecorated(true);
    //frame.setVisible(true);
   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel.setLayout(null);

    title = new JLabel("Write your review: ");
    f = new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25);
    title.setBounds(100, 20, 250, 25);
    title.setHorizontalAlignment(JLabel.CENTER);
    panel.add(title);
    title.setFont(f);
    title.setForeground(Color.BLUE);

    f2 = new Font(Font.DIALOG_INPUT, Font.BOLD, 15);
    label = new JLabel("Course: ");
    label.setFont(f2);
    label.setBounds(title.getX(), title.getY() + 45, 120, 25);
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label);

    label2 = new JLabel("Professor:  ");
    label2.setFont(f2);
    label2.setBounds(title.getX(), title.getY() + 90, 120, 25);
    label2.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label2);

    label3 = new JLabel("Review:  ");
    label3.setFont(f2);
    label3.setBounds(title.getX(), title.getY() + 200, 120, 25);
    label3.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label3);

    course = new JTextField(30);
    course.setBounds(label.getX() + 100, title.getY() + 45, 100, 25);
    course.setHorizontalAlignment(JLabel.CENTER);
    panel.add(course);

    prof = new JTextField(30);
    prof.setBounds(label2.getX() + 100, title.getY() + 90, 100, 25);
    prof.setHorizontalAlignment(JLabel.CENTER);
    panel.add(prof);

    review = new JTextArea();
    review.setLineWrap(true);
    review.setBounds(label3.getX() + 100, title.getY() + 200, 400, 300);
    panel.add(review);

    diff = new JLabel("Course Difficulty:");
    diff.setBounds(label3.getX() - 20, label2.getY() + 30, 200, 25);

    jb = new JMenuBar();
    men = new JMenu("Course Difficulty");
    men.add(new JMenuItem("Easy"));
    men.add(new JMenuItem("Medium"));
    men.add(new JMenuItem("Hard"));
    jb.add(men);
    jb.setBounds(course.getX(), prof.getY() + 45, 135, 50);
    panel.add(jb);
    jb.setVisible(true);

    submit = new JButton("Submit Review");
    submit.setBounds(review.getX(), review.getY() + 350, 160, 25);
    submit.addActionListener(this);
    panel.add(submit);
    h = new HashMap<>();
    setDefaultCloseOperation(readIntoFile());
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    //set the password and store all user info into an array; all of this will be put into the hashmap and written into the file
    String c = new String(course.getText());
    String[] a = new String[3];
    a[0] = course.getText();
    a[1] = prof.getText();
    a[2] = review.getText();
    h.put(course.getText(), Arrays.toString(a));
    //read into the file
    try {
      readIntoFile();
    } catch (IOException exc) {
      throw new RuntimeException(exc);
    }

    //Show the user a message which displays their username
    JOptionPane.showMessageDialog(submit, "Review is successfully posted!");
    dispose();
    CourseCommentary comm = new CourseCommentary();

  }


  public int readIntoFile() throws IOException {

    //load all the info which is entered into the HashMap into the txt file
    //the username is stored and the array of password, full name, email is stored
    String filepath = "reviews.txt";
    BufferedWriter bf = null;

    try {
      //try catch block is because sometimes a file can not be written into
      // create new BufferedWriter for the output file
      bf = new BufferedWriter(new FileWriter(filepath, true));

      //write all the info into the file
      // iterate map entries
      for (HashMap.Entry<String, String> entry :
              h.entrySet()) {

        // put key and value separated by a colon
        bf.append(entry.getKey() + ":"
                + entry.getValue());

        // new line
        bf.newLine();
      }

      //flush the buffer writer to avoid confusion
      bf.flush();
      //if input is not appropriate then catch IOException
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      //this will run regardless of try or catch
      try {
        // always close the writer
        assert bf != null;
        bf.close();
      } catch (Exception e) {
      }
    }
   // CourseCommentary c = new CourseCommentary();
   // this.dispose();
    //to set this as the default close operation it needs to return an int
    return 1;
  }

}
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


//The Review page which is where users can write their reviews

public class Review extends JFrame implements ActionListener  {

  JLabel title, label, label2, label3, courseDIff, takeAgain, textbook;


  JTextField course;
  JTextField prof;
  JTextArea review;
  JMenuBar jb;
  JMenu men;
  JButton submit;
  HashMap<String, String> h;
  String s, s1, s2;
  JRadioButton j1, j2, j3 ,j4;

  JColorChooser c, c1;
  Font f, f1;



  public Review() throws IOException
  {
    setTitle("Write Review");
    setVisible(true);
    setSize(800, 500);
    setLayout(null);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    c = new JColorChooser();
    c.setColor(0, 153,153);

    c1 = new JColorChooser();
    c1.setColor(255,255,204);

    f = new Font("DIALOG_INPUT", Font.PLAIN, 20);
    f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

    topPanel();

    middlePanel();


    h = new HashMap<>();
    setDefaultCloseOperation(readIntoFile());
    setVisible(true);
  }

  //the action performed class which converts the information entered into the review into a usable form for writing it into the reviews.txt

  @Override
  public void actionPerformed(ActionEvent e) {

    //set the password and store all user info into an array; all of this will be put into the hashmap and written into the file
    String c = new String(course.getText());
    String[] a = new String[6];
    a[0] = course.getText();
    a[1] = prof.getText();
    a[2] = review.getText();
    a[3] = s;
    a[4] = s1;
    a[5] = s2;

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
    CourseCommentaryLoggedIn comm = new CourseCommentaryLoggedIn();

  }

  //the readIntoFile function which takes the review that has been entered and writes it into the reviews.txt file
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
                + entry.getValue().substring(1,entry.getValue().length()-1));


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

  //the top panel which contains the reviews title
  private void topPanel()
  {
    JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
    title = new JLabel("Write Your Review");
    title.setFont(f);
    title.setForeground(c1.getColor());
    top.add(title);
    top.setBackground(c.getColor());
    top.setBounds(0, 0, this.getWidth(), 35);
    add(top);
  }

  //the middle panel which contains all the text fields, buttons, and menu for ratings
  //all of the labels and buttons are aligned
  private void middlePanel()
  {

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 30, this.getWidth(), this.getHeight()-30);
    panel.setBackground(c1.getColor());

    //all user input text fields plus labels are included here
    label = new JLabel("Course: ");
    label.setFont(f1);
    label.setForeground(c.getColor());
    label.setBounds(140, 70, 200, 30);
    panel.add(label);

    label2 = new JLabel("Professor:  ");
    label2.setFont(f1);
    label2.setBounds(140, 110, 200, 30);
    label2.setForeground(c.getColor());
    panel.add(label2);

    label3 = new JLabel("Review:  ");
    label3.setFont(f1);
    label3.setBounds(140, 270, 200, 30);
    label3.setForeground(c.getColor());
    panel.add(label3);

    course = new JTextField(30);
    course.setBounds(360, 70, 200, 30);
    course.setHorizontalAlignment(JLabel.CENTER);
    panel.add(course);

    prof = new JTextField(30);
    prof.setBounds(360, 110, 200, 30);
    prof.setHorizontalAlignment(JLabel.CENTER);
    panel.add(prof);


    //These labels are for the features which are not text input
    courseDIff = new JLabel("Course Difficulty:  ");
    courseDIff.setFont(f1);
    courseDIff.setBounds(140, 150, 200, 30);
    courseDIff.setForeground(c.getColor());
    panel.add(courseDIff);

    takeAgain = new JLabel("Would take again:  ");
    takeAgain.setFont(f1);
    takeAgain.setBounds(140, 190, 200, 30);
    takeAgain.setForeground(c.getColor());
    panel.add(takeAgain);

    textbook = new JLabel("Textbooks required:  ");
    textbook.setFont(f1);
    textbook.setBounds(140, 230, 200, 30);
    textbook.setForeground(c.getColor());
    panel.add(textbook);

    //the menu bar which is giving the rating on a scale of 1-10
    jb = new JMenuBar();
    jb.setBounds(360, 150, 200, 30);

    men = new JMenu("Rate on scale from 1 to 10");
    JMenuItem item1 = new JMenuItem("1");
    men.add(item1);
    JMenuItem item2 = new JMenuItem("2");
    men.add(item2);
    JMenuItem item3 = new JMenuItem("3");
    men.add(item3);
    JMenuItem item4 = new JMenuItem("4");
    men.add(item4);
    JMenuItem item5 = new JMenuItem("5");
    men.add(item5);
    JMenuItem item6 = new JMenuItem("6");
    men.add(item6);
    JMenuItem item7 = new JMenuItem("7");
    men.add(item7);
    JMenuItem item8 = new JMenuItem("8");
    men.add(item8);
    JMenuItem item9 = new JMenuItem("9");
    men.add(item9);
    JMenuItem item10 = new JMenuItem("10");
    men.add(item10);
    //when any of the buttons in the menu is clicked they are stored in the array for the review
    item1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item1.getText();
      }
    });
    item2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item2.getText();
      }
    });
    item3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item3.getText();
      }
    });
    item4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item4.getText();

      }
    });
    item5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item5.getText();
      }
    });
    item6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item6.getText();      }
    });
    item7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item7.getText();      }
    });
    item8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item8.getText();      }
    });
    item9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item9.getText();      }
    });
    item10.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s = item10.getText();      }
    });

    jb.add(men);
    // jb.setBounds(360, 260, 150, 30);
    panel.add(jb);
    jb.setVisible(true);

    //radio buttons which say if the user would take the course again and if they need a textbook for the course
    //these inputs are also stored in the array
    j1 = new JRadioButton("Yes");
    j1.setText("Yes");
    j1.setBounds(360,190,60,30 );
    j1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s1 = j1.getText();      }
    });

    j2 = new JRadioButton("No");
    j2.setText("No");
    j2.setBounds(430,190,60,30 );
    j2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s1 = j2.getText();      }
    });
    ButtonGroup bg = new ButtonGroup();
    bg.add(j1);
    bg.add(j2);
    panel.add(j1);
    panel.add(j2);

    j3 = new JRadioButton("Yes");
    j3.setText("Yes");
    j3.setBounds(360,230,60,30 );
    j3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s2 = j3.getText();      }
    });
    j4 = new JRadioButton("No");
    j4.setText("No");
    j4.setBounds(430,230,60,30 );
    j4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        s2 = j4.getText();      }
    });

    ButtonGroup bg1 = new ButtonGroup();
    bg1.add(j3);
    bg1.add(j4);
    panel.add(j3);
    panel.add(j4);

    review = new JTextArea();
    review.setLineWrap(true);
    review.setBounds(360, 270, 200, 100);
    panel.add(review);

    //submit button and back button which submit the review(leading to the review being written into the reviews.txt file) and send us back to home page respectively
    submit = new JButton("Submit Review");
    submit.setBounds(360, 380, 150, 30);
    submit.addActionListener(this);
    panel.add(submit);

    JButton back = new JButton("Return to Home");
    back.setBounds(50, 400, 150, 30);
    panel.add(back);

    //back button which gets us back to home
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        CourseCommentaryLoggedIn c = new CourseCommentaryLoggedIn();

      }
    });


    add(panel);
  }


}
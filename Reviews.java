import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;


// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Reviews implements ActionListener {
    public static void main(String[] args)
    {
        JPanel panel = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setSize(screenSize.width/2, screenSize.height);
        frame.add(panel);
        //frame.setVisible(true);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        //frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);

        JLabel title = new JLabel("Write your review: ");
        Font  f  = new Font(Font.DIALOG_INPUT,  Font.BOLD|Font.ITALIC, 25);
        title.setBounds(100, 20, 250, 25);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title);
        title.setFont(f);
        title.setForeground(Color.BLUE);

        Font f2 = new Font(Font.DIALOG_INPUT, Font.BOLD, 15);
        JLabel label = new JLabel("Course: ");
        label.setFont(f2);
        label.setBounds(title.getX(), title.getY() + 45, 120, 25);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);

        JLabel label2 = new JLabel("Professor:  ");
        label2.setFont(f2);
        label2.setBounds(title.getX(), title.getY() + 90, 120, 25);
        label2.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label2);

        JLabel label3 = new JLabel("Review:  ");
        label3.setFont(f2);
        label3.setBounds(title.getX(), title.getY() + 200, 120, 25);
        label3.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label3);

        JTextField course = new JTextField(30);
        course.setBounds(label.getX() + 100, title.getY() + 45, 100, 25);
        course.setHorizontalAlignment(JLabel.CENTER);
        panel.add(course);

        JTextField prof = new JTextField(30);
        prof.setBounds(label2.getX() + 100, title.getY() + 90, 100, 25);
        prof.setHorizontalAlignment(JLabel.CENTER);
        panel.add(prof);

        JTextArea review = new JTextArea();
        review.setLineWrap(true);
        review.setBounds(label3.getX() + 100, title.getY() + 200, 400, 300);
        panel.add(review);

        JLabel diff = new JLabel("Course Difficulty:");
        diff.setBounds(label3.getX() - 20, label2.getY() + 30, 200, 25);


        JMenuBar jb = new JMenuBar();
        JMenu men = new JMenu("Course Difficulty");
        men.add(new JMenuItem("Easy"));
        men.add(new JMenuItem("Medium"));
        men.add(new JMenuItem("Hard"));
        jb.add(men);
        jb.setBounds(course.getX(), prof.getY() + 45, 135, 50);
        panel.add(jb);
        jb.setVisible(true);

        JButton submit = new JButton("Submit Review");
        submit.setBounds(review.getX(), review.getY() + 350, 160, 25);
        submit.addActionListener(new Reviews());
        panel.add(submit);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Review Submitted");
    }


}
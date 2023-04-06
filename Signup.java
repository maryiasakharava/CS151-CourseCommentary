import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener {
    JLabel label1, label2, label3, label4, label5;
    JTextField field1, field2, field3;
    JButton singupButton;
    JPasswordField password;
    RegisteredUser user;
   // File f = new File("/users");
  //  int ln;
/*
    void createFolder() {
        if (!f.exists()) {
            f.mkdirs();
        }

    }

 */
/*
    void readFile() {
        try {
            FileReader fr = new FileReader(f);
            System.out.println("file exists!");
        } catch (FileNotFoundException ex) {
            try {
                FileWriter fr = new FileWriter(f);
                System.out.println("File created");
            } catch (IOException ex1) {

            }
        }
    }

 */
/*
    void addData(String firstName, String lastName, String email, String password) {
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            for (int i = 0; i < ln; i++) {
                raf.readLine();
            }
            if (ln > 0) {
                raf.writeBytes("\r\n");
                raf.writeBytes("\r\n");
            }
            raf.writeBytes("First name: " + firstName + "\r\n");
            raf.writeBytes("Last name: " + lastName + "\r\n");
            raf.writeBytes("Email: " + email + "\r\n");
            raf.writeBytes("Password: " + password + "\r\n");

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }


    }
*/
    /*
    void countLines() {
        try {
            ln = 0;
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            for (int i = 0; raf.readLine() != null; i++) {
                ln++;
            }
            System.out.println("number of lines:" + ln);

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

     */

     Signup() {
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create an Account");
        label1 = new JLabel("Create a new Account");
        label1.setForeground(Color.blue);
        label1.setFont(new Font("Serif", Font.ITALIC, 25));
        label2 = new JLabel("First Name: ");
        label3 = new JLabel("Last Name: ");
        label4 = new JLabel("Email: ");
        label5 = new JLabel("Password:");
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        password = new JPasswordField();
        singupButton = new JButton("Submit");
        singupButton.addActionListener(this);
        label1.setBounds(100, 30, 400, 30);
        label2.setBounds(80, 70, 200, 30);
        label3.setBounds(80, 110, 200, 30);
        label4.setBounds(80, 150, 200, 30);
        label5.setBounds(80, 190, 200, 30);
        field1.setBounds(300, 70, 200, 30);
        field2.setBounds(300, 110, 200, 30);
        field3.setBounds(300, 150, 200, 30);
        password.setBounds(300, 190, 200, 30);
        singupButton.setBounds(200, 250, 100, 30);
        add(label1);
        add(label2);
        add(field1);
        add(label3);
        add(field2);
        add(label4);
        add(field3);
        add(label5);
        add(password);
        add(singupButton);



    }
    @Override
   public void actionPerformed(ActionEvent e){
        if (e.getSource() == singupButton){
           // int x = 0;
            String s1 = field1.getText();
            String s2 = field2.getText();
            String s3 = field3.getText();
            char[] s4 = password.getPassword();
            String s5 = new String(s4);
            user = new RegisteredUser(s1,s2,s3,s5);

            JOptionPane.showMessageDialog(singupButton, "Account Created Successfully!");
        }
        else{
            field1.setText("");
            field2.setText("");
            field3.setText("");
            password.setText("");
        }
   }
}

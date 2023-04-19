import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
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
    HashMap<String, String> h;


     public Signup() throws IOException{
        setSize(700, 700);
        setLayout(null);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        h = new HashMap<>();
        setDefaultCloseOperation(readIntoFile());
         setVisible(true);




     }
    @Override
    public void actionPerformed(ActionEvent ev) {
        //only run this if the source of action is the sign up button
        if (ev.getSource() == singupButton){
            char[] p = password.getPassword();

            //booleans to check if these values are all contained in the password
            boolean uC = false;
            boolean lC = false;
            boolean nC = false;
            boolean sC = false;

            for(char c: p)
            {
                //in the character array p for password, check for uppercase, lowercase, number, special character, and length
                if(Character.isUpperCase(c))
                {
                    uC = true;
                }
                else if(Character.isLowerCase(c))
                {
                    lC = true;
                }
                else if(Character.isDigit(c))
                {
                    nC = true;
                }
                else
                {
                    sC = true;
                }

            }

            //throw the appropriate PasswordException based on each boolean value set in the above for loop
            //in the catch, instead of throwing normal execption, we will do a popup which explains what the user needs to change
            if(p.length < 8)
            {
                try {
                    throw new Minimum8CharactersRequired("Password is too short.");
                } catch (Minimum8CharactersRequired ex) {
                    JOptionPane.showMessageDialog(singupButton, "Password is too short. Try again");
                }
            }

            if(!uC)
            {
                try{
                    throw new UpperCaseCharacterException("Needs uppercase letter");
                } catch (UpperCaseCharacterException ex)
                {
                    JOptionPane.showMessageDialog(singupButton, "Password needs an uppercase letter");
                }
            }

            if(!lC)
            {
                try{
                    throw new LowerCaseCharacterMissing("Needs lowercase letter");
                } catch (LowerCaseCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(singupButton, "Password needs a lowercase letter");
                }
            }

            if(!nC)
            {
                try{
                    throw new NumberCharacterMissing("Needs number");
                } catch (NumberCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(singupButton, "Password needs a number");
                }
            }

            if(!sC)
            {
                try{
                    throw new SpecialCharacterMissing("Needs special character");
                } catch (SpecialCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(singupButton, "Password needs a special character");
                }
            }

            //if none of the exceptions are thrown, and all these requirements are fulfilled
            if(p.length >= 8 && uC && lC && sC && nC)
            {
                Random r = new Random();
                //set the username

                //set the password and store all user info into an array; all of this will be put into the hashmap and written into the file
                String pass = new String(p);
                String[] a = new String[3];
                a[0] = pass;
                a[1] = field1.getText() + " " + field2.getText();
                a[2] = field3.getText();
                h.put(field3.getText(), Arrays.toString(a));
                //read into the file
                try {
                    readIntoFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //Show the user a message which displays their username
                JOptionPane.showMessageDialog(singupButton, "Account Created Successfully!");

                dispose();
                CourseCommentary c = new CourseCommentary();

            }
        }
        else{
            //if the action was not from the signupbutton, reset all the fields
            field1.setText("");
            field2.setText("");
            field3.setText("");
            password.setText("");
        }
    }
    public int readIntoFile() throws IOException {

        //load all the info which is entered into the HashMap into the txt file
        //the username is stored and the array of password, full name, email is stored
        String filepath = "users.txt";
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



        //to set this as the default close operation it needs to return an int
        return 1;
    }
}

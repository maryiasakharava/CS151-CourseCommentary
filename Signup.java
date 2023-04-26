import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

public class Signup extends JFrame implements ActionListener {
    JLabel label1, label2, label3, label4, label5;
    JTextField field1, field2, field3;
    JButton signupButton;
    JPasswordField password;
    RegisteredUser user;
    HashMap<String, String> h;

    JColorChooser c, c1;
    Font f, f1;

    ImageIcon i, newIcon;
    BufferedImage bi;
    Graphics g;


     public Signup() throws IOException{
         setVisible(true);
         setSize(800, 600);
         setLayout(null);
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

         c = new JColorChooser();
         c.setColor(0, 153,153);

         c1 = new JColorChooser();
         c1.setColor(255,255,204);

         f = new Font("DIALOG_INPUT", Font.PLAIN, 20);
         f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

         i = new ImageIcon("Images/My project-1 (2).png");
         Image i1 = i.getImage();
         bi = new BufferedImage(i1.getWidth(null),i1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
         g = bi.createGraphics();
         g.drawImage(i1,350,150,200,80,null);
         newIcon = new ImageIcon(bi);

         setTitle("Create an Account");

         topPanel();

         middlePanel();


         h = new HashMap<>();
         setDefaultCloseOperation(readIntoFile());

     }
    @Override
    public void actionPerformed(ActionEvent ev) {
        //only run this if the source of action is the sign up button
        if (ev.getSource() == signupButton){
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
                    JOptionPane.showMessageDialog(signupButton, "Password is too short. Try again");
                }
            }

            if(!uC)
            {
                try{
                    throw new UpperCaseCharacterException("Needs uppercase letter");
                } catch (UpperCaseCharacterException ex)
                {
                    JOptionPane.showMessageDialog(signupButton, "Password needs an uppercase letter");
                }
            }

            if(!lC)
            {
                try{
                    throw new LowerCaseCharacterMissing("Needs lowercase letter");
                } catch (LowerCaseCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(signupButton, "Password needs a lowercase letter");
                }
            }

            if(!nC)
            {
                try{
                    throw new NumberCharacterMissing("Needs number");
                } catch (NumberCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(signupButton, "Password needs a number");
                }
            }

            if(!sC)
            {
                try{
                    throw new SpecialCharacterMissing("Needs special character");
                } catch (SpecialCharacterMissing ex)
                {
                    JOptionPane.showMessageDialog(signupButton, "Password needs a special character");
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
                JOptionPane.showMessageDialog(signupButton, "Account Created Successfully!");

                dispose();
                CourseCommentaryLoggedIn c = new CourseCommentaryLoggedIn();

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

    private void topPanel()
    {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBackground(c.getColor());
        label1 = new JLabel("Sign Up for an Account");
        label1.setFont(f);
        label1.setForeground(c1.getColor());
        top.add(label1);
        top.setBounds(0, 0, this.getWidth(), 35);
        add(top);
    }

    private void middlePanel()
    {
        JPanel mid = new JPanel();
        mid.setBackground(c1.getColor());
        mid.setBounds(0, 30, this.getWidth(), this.getHeight()-30);
        mid.setLayout(null);
        label2 = new JLabel("First Name: ");
        label3 = new JLabel("Last Name: ");
        label4 = new JLabel("Email: ");
        label5 = new JLabel("Password:");
        label2.setFont(f1);
        label3.setFont(f1);
        label4.setFont(f1);
        label5.setFont(f1);
        label2.setForeground(c.getColor());
        label3.setForeground(c.getColor());
        label4.setForeground(c.getColor());
        label5.setForeground(c.getColor());
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        password = new JPasswordField();
        signupButton = new JButton("Submit");
        signupButton.addActionListener(this);
        label2.setBounds(140, 70, 200, 30);
        label3.setBounds(140, 110, 200, 30);
        label4.setBounds(140, 150, 200, 30);
        label5.setBounds(140, 190, 200, 30);
        field1.setBounds(360, 70, 200, 30);
        field2.setBounds(360, 110, 200, 30);
        field3.setBounds(360, 150, 200, 30);
        password.setBounds(360, 190, 200, 30);
        signupButton.setBounds(360, 250, 100, 30);
        mid.add(label2);
        mid.add(field1);
        mid.add(label3);
        mid.add(field2);
        mid.add(label4);
        mid.add(field3);
        mid.add(label5);
        mid.add(password);
        mid.add(signupButton);

        JButton back = new JButton("Return to Home");
        back.setBounds(50, 500, 150, 30);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CourseCommentary c = new CourseCommentary();
            }
        });

        mid.add(back);

        add(mid);

    }

}

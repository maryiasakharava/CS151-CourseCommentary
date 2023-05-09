import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;

//signup page which allows new users to sign up for an account
public class Signup extends JFrame implements ActionListener {
    Header label1;
    Label label2, label3, label4, label5;
    JTextField field1, field2, field3;
    JButton signupButton;
    JPasswordField password;

    RegisteredUser user;
    HashMap<String, String> h;
    ArrayList<String> usernames;

    JColorChooser c, c1;
    Font f, f1;

     public Signup() throws IOException{
         setVisible(true);
         setSize(800, 600);
         setLayout(null);
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

         //implements the colors and fonts for the page
         c = new JColorChooser();
         c.setColor(0, 153,153);

         c1 = new JColorChooser();
         c1.setColor(255,255,204);

         f = new Font("DIALOG_INPUT", Font.PLAIN, 20);
         f1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

         usernames = new ArrayList<>();
         ReadInFile("users.txt");
         setTitle("Create an Account");

         topPanel();

         middlePanel();

         //creates a new hashmap which will store the users
         h = new HashMap<>();
         //when closing this page, the new user which has been created will be written into the users.txt file
         setDefaultCloseOperation(readIntoFile());

     }

     //the action performed function makes sure the password is strong enough and the user is not already existing before allowing a successful signup
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

            for(String u: usernames)
            {
                if(field3.getText().equals(u))
                {
                    JOptionPane.showMessageDialog(signupButton, "This email has already been registered. Try again!");
                    return;
                }
            }

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

    public void ReadInFile(String filepath)
    {
        //Read in the usernames which are stored in the users.txt file
        try (BufferedReader br = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split(":");
                usernames.add(keyValue[0]);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    //the user info needs to be written into the file
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

    //the topPanel contains the title
    private void topPanel()
    {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBackground(c.getColor());
        label1 = new Header("Sign Up for an Account", Header.Color.TAN);
        top.add(label1);
        top.setBounds(0, 0, this.getWidth(), 35);
        add(top);
    }

    //the middle panel contains all the text fields which are required for creating a new user
    private void middlePanel()
    {
        JPanel mid = new JPanel();
        mid.setBackground(c1.getColor());
        mid.setBounds(0, 30, this.getWidth(), this.getHeight()-30);
        mid.setLayout(null);

        label2 = new Label("First Name: ");
        label3 = new Label("Last Name: ");
        label4 = new Label("Email: ");
        label5 = new Label("Password:");


        //creates the field for the first name, last name, the email, and password
        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();


        //the password field and submit buttons are created; the submit button goes to actionListener
        password = new JPasswordField();
        signupButton = new JButton("Submit");
        signupButton.addActionListener(this);

        //set the bounds for all the different labels and text fields
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

        //the back button returns to the home page
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

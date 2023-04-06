import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
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
import java.util.HashMap;
import java.util.Map;


public class CourseCommentary extends JFrame  {
   // JFrame frame ;
   // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    private Controller contr;
    RegisteredUser loggedinUser;
    public HashMap<String, RegisteredUser> users;
    public HashMap<String, Review> reviews ;
    JButton login;
    JButton signup;
    public CourseCommentary(Controller controller){
        this.contr = controller;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        users = new HashMap<String, RegisteredUser>();
        reviews = new HashMap<String, Review>();
         login = new JButton("Log in");
         signup = new JButton("Create an Account");
        frame.setLayout(null);
        frame.setSize(1000, 1000);
        login.setBounds(200, 200, 200, 30);
        signup.setBounds(200, 250, 200, 30);
        frame.add(login);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Login log = new Login();
               for (Map.Entry<String, RegisteredUser> entry: users.entrySet()){
                   if (entry.getKey().equals(log.usernameInput)){
                       if (entry.getValue().getPassword().equals(log.passwordInput)){
                           loggedinUser = entry.getValue();


                       }
                   }

               }
            }
            });
        frame.add(signup);

        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup enroll = new Signup();
                users.put(enroll.user.getEmail(), enroll.user);
            }
        });
        frame.setVisible(true);
    }
    public boolean loginAttempt(String email, String password) {
        if (users.containsKey(email) && users.get(email).getPassword().equals(password)) {
            loggedinUser = users.get(email);
            return true;
        }
        return false;
    }
    public void logoutAttempt() {
        loggedinUser = null;
    }
    public void addUser(RegisteredUser user) {
        users.put(user.getEmail(), user);
    }
    public RegisteredUser getCurrentUser() {
        return loggedinUser;
    }



}


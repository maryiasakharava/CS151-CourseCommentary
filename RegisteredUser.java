import java.util.ArrayList;

public class RegisteredUser extends User {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getPassword() {
        return password1;
    }

    public void setPassword(String password) {
        this.password1 = password;
    }

    private String password1;
    private ArrayList<Review> arr = new ArrayList<Review>();
    public RegisteredUser(String first, String last,String email, String password){
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password1 = password;
    }

    public void writeReview(){

    }
    public void deleteReview(){

    }
    public void seeMyReviews(){

    }


}
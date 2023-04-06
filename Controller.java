public class Controller {
        private CourseCommentary c;
        public Controller(){
            c = new CourseCommentary(this);
        }
        public boolean userLogin (String email, String password){
            return c.loginAttempt(email, password);
        }
        public void userLogout(){
            c.logoutAttempt();
        }
        public void addUser(RegisteredUser user){
            c.addUser(user);
        }
        public RegisteredUser loggedinUser(){
            return c.getCurrentUser();
        }
        public void setSystem(CourseCommentary c){
            this.c = c;
        }


}

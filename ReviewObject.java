public class ReviewObject {
    private String course;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    private String prof;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getTakeagain() {
        return takeagain;
    }

    public void setTakeagain(String takeagain) {
        this.takeagain = takeagain;
    }

    private String takeagain;

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    private String textbook;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    private String review;

    public ReviewObject(String c, String p, String r, String t, String tb, String rw){
        this.course = c;
        this.prof = p;
        this.rating = r;
        this.takeagain = t;
        this.textbook = tb;
        this.review = rw;
    }
}

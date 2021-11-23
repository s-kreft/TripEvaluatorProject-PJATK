package pl.edu.pj.mpr.tripevaluator;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String review;
    @OneToOne(cascade = CascadeType.ALL )
    private User author;
    private LocalDate postDate;
    private int rating;
    public Review() {}
    public Review(int id, String review, User author, LocalDate postDate, int rating) {
        this.id = id;
        this.review = review;
        this.author = author;
        this.postDate = postDate;
        setRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if(rating > 0 && rating < 6) {
            this.rating = rating;
        }
    }
}

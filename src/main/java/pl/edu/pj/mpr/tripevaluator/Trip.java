package pl.edu.pj.mpr.tripevaluator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name="Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String destination;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviewList;
    private double price;
    private LocalDate departureDate;
    private TransportType transportType;

    public Trip() {
    }

    public Trip(int id, String title, String destination, List reviewList, double price, LocalDate departureDate,
                TransportType transportType) {
        this.id = id;
        this.title = title;
        this.destination = destination;
        this.reviewList = reviewList;
        this.price = price;
        this.departureDate = departureDate;
        this.transportType = transportType;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }


}

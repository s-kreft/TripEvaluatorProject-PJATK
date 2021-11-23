package pl.edu.pj.mpr.tripevaluator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripEvaluatorService {
    private TripEvaluatorRepository repository;

    public TripEvaluatorService(TripEvaluatorRepository repository) {
        this.repository = repository;
    }


    public Review makeExampleReview() {
        User user1 = new User(1, "Zenek", "zenek@gmail.com");
        Review review = new Review(1,"Recka number jeden", user1, LocalDate.of(2020,1,8), 4);
        return review;
    }
    public Trip makeExampleTrip() {
        User user2 = new User(2, "Kasia", "kasia@gmail.com");
        User user3 = new User(3, "Basia", "basia@gmail.com");
        Review review2 = new Review(2,"Recenzja number dwa", user2, LocalDate.of(2020,5,11), 5);
        Review review3 = new Review(3,"Recenzja number trzy", user3, LocalDate.of(2020,7,21), 3);
        List<Review> recenzja = List.of(review2, review3);
        return new Trip(2,"tytul", "Kanary", recenzja, 350d, LocalDate.of(2021, 8, 15),TransportType.TRAIN);
    }

    public Trip saveTrip(Trip trip) {
        return repository.save(trip);
    }
    public List<Trip> findAllTrips() {
        return repository.findAll();
    }


}

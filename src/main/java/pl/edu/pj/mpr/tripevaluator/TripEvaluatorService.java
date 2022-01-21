package pl.edu.pj.mpr.tripevaluator;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Optional<Trip> findById(int id) {
        return repository.findById(id);
    }
    public Trip addReviev(TripPojo tripPojo) {
        Optional<Trip> optionalTrip = repository.findById(tripPojo.getId());
        Trip trip = optionalTrip.get();
        trip.getReviewList().add(tripPojo.getReview());

        return repository.save(trip);
    }
    public void deleteTrip(int id) {
        repository.deleteById(id);
    }

    public Trip getTripById(Integer id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public void addSuffixToTrip(Trip trip) {
        if (trip.getTitle() == null || trip.getTitle().isBlank()) {
            trip.setTitle("EMPTY");
        } else {
            trip.setTitle(trip.getTitle() + "_SUFFIX");
        }
    }
    public boolean isFullTrip(Trip trip) {
        return trip.getReviewList().size() > 10;
    }
    public void addReviewWithoutSave(Trip trip, Review review) {
        if(trip.getReviewList() != null) {
            trip.getReviewList().add(review);
        }
    }
    public boolean isExpensive(Trip trip) {
        if(trip.getPrice() > 2000) {
            return true;
        }
        return false;
    }
    public boolean isDeparted(Trip trip) {
        if(trip.getDepartureDate().isBefore(LocalDate.now())) {
            return true;
        }
        return false;
    }
    public boolean hasReviews(Trip trip) {
        if(trip.getReviewList() != null && trip.getReviewList().size() > 0) {
            return true;
        }
        return false;
    }

    public boolean existById(int id) {
        return repository.existsById(id);
    }
}

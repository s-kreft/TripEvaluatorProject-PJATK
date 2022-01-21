package pl.edu.pj.mpr.tripevaluator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripEvaluatorServiceTest {
    @Mock
    private TripEvaluatorRepository tripEvaluatorRepository;


    @InjectMocks
    private TripEvaluatorService service;

    @Test
    public void isNotFullTripWhenHasLessThanElevenReviews() {
        Trip trip = new Trip();
        trip.setReviewList(new ArrayList<Review>());
        Assertions.assertThat(service.isFullTrip(trip)).isFalse();
    }

    @Test
    public void isFullTripWhenHasMoreThanTenReviews() {
        Trip trip = new Trip();
        ArrayList reviews = new ArrayList<Review>();
        for(int i =0; i <= 10; i++) {
            reviews.add(new Review());

        }
        trip.setReviewList(reviews);
        Assertions.assertThat(service.isFullTrip(trip)).isTrue();
    }
    @Test
    public void addSuffixToTripWhenNull() {
        //Arrange
        Trip trip = new Trip();
        trip.setTitle(null);
        //Act
        service.addSuffixToTrip(trip);
        //Assert
        Assertions.assertThat(trip.getTitle()).isEqualTo("EMPTY");
    }
    @Test
    public void addSuffixToTripWhenBlank() {
        //Arrange
        Trip trip = new Trip();
        trip.setTitle("");
        //Act
        service.addSuffixToTrip(trip);
        //Assert
        Assertions.assertThat(trip.getTitle()).isEqualTo("EMPTY");
    }
    @Test
    public void addSuffixToTripWhenNotEmpty() {
        //Arrange
        Trip trip = new Trip();
        trip.setTitle("testTitle");
        //Act
        service.addSuffixToTrip(trip);
        //Assert
        Assertions.assertThat(trip.getTitle()).isEqualTo("testTitle_SUFFIX");
    }
    @Test
    public void isExpensiveMoreThan2000() {
        //Arrange
        Trip trip = new Trip();
        trip.setPrice(4000);
        //Act
        boolean result = service.isExpensive(trip);

        //Assert
        Assertions.assertThat(result).isTrue();
    }
    @Test
    public void isExpensiveLessThan2000() {
        //Arrange
        Trip trip = new Trip();
        trip.setPrice(1050.45);
        //Act
        boolean result = service.isExpensive(trip);
        //Assert
        Assertions.assertThat(result).isFalse();
    }
    @Test
    public void hasReviewsNull() {
        //Arrange
        Trip trip = new Trip();
        trip.setReviewList(null);
        //Act
        boolean result = service.hasReviews(trip);
        //Assert
        Assertions.assertThat(result).isFalse();
    }
    @Test
    public void hasReviewsTrue() {
        //Arrange
        Trip trip = new Trip();
        Review rewiev = new Review();
        trip.setReviewList(List.of(rewiev));
        //Act
        boolean result = service.hasReviews(trip);
        //Assert
        Assertions.assertThat(result).isTrue();
    }
    @Test
    public void isDepartedTrue() {
        //Arrange
        Trip trip = new Trip();
        LocalDate date = LocalDate.of(2021, 8, 20);
        trip.setDepartureDate(date);
        //Act
        boolean result = service.isDeparted(trip);
        //Assert
        Assertions.assertThat(result).isTrue();
    }
    @Test
    public void isDepartedFalse() {
        //Arrange
        Trip trip = new Trip();
        LocalDate date = LocalDate.of(2021, 12, 20);
        trip.setDepartureDate(date);
        //Act
        boolean result = service.isDeparted(trip);
        //Assert
        Assertions.assertThat(result).isFalse();
    }
    @Test
    public void addReviewWithoutSaveTrue() {
        //Arrange
        Trip trip = new Trip(1, "Wycieczka", "Malediwy", new ArrayList<Review>(), 450, LocalDate.now(), TransportType.TRAIN);
        Review review = new Review();
        //Act
        service.addReviewWithoutSave(trip, review);
        //Assert
        Assertions.assertThat(trip.getReviewList().contains(review)).isTrue();
    }

    @Test
    public void ShouldNotFindById() {
        int id = 1;
        when(tripEvaluatorRepository.findById(any())).thenReturn(Optional.empty());

        Optional<Trip> optionalTrip = service.findById(id);

        Assertions.assertThat(optionalTrip).isEqualTo(Optional.empty());
    }
    @Test
    public void deleteTripByIdTest() {
        int id = 1;
        service.deleteTrip(id);
        verify(tripEvaluatorRepository, times(1)).deleteById(id);
    }
    @Test
    public void getAllTripsFullTest() {
        when(tripEvaluatorRepository.findAll()).thenReturn(new ArrayList<>());
        var trips = service.findAllTrips();
        Assertions.assertThat(trips).isEqualTo(new ArrayList());
    }

}

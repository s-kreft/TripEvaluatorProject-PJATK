package pl.edu.pj.mpr.tripevaluator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TripEvaluatorServiceIt {

    @MockBean
    private TripEvaluatorRepository tripEvaluatorRepository;

    @Autowired
    private TripEvaluatorService service;

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

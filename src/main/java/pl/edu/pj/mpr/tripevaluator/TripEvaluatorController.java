package pl.edu.pj.mpr.tripevaluator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
public class TripEvaluatorController {
    private TripEvaluatorService tripEvaluatorService;
    public TripEvaluatorController(TripEvaluatorService tripEvaluatorService) {
        this.tripEvaluatorService = tripEvaluatorService;
    }
    @GetMapping("/rec")
    public ResponseEntity<Review> makeExampleReview() {
        return ResponseEntity.ok(tripEvaluatorService.makeExampleReview());
    }
    @GetMapping("/trip")
    public ResponseEntity<Trip> makeExampleTrip() {
        return ResponseEntity.ok(tripEvaluatorService.makeExampleTrip());
    }

    @PostMapping("/save")
    public ResponseEntity<Trip> save(@RequestBody Trip trip) {
        return ResponseEntity.ok(tripEvaluatorService.saveTrip(trip));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Trip>> findAll() {
        return ResponseEntity.ok(tripEvaluatorService.findAllTrips());
    }

}

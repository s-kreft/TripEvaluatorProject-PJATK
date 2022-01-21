package pl.edu.pj.mpr.tripevaluator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TripEvaluatorControllerIt {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSucess_addHello() throws Exception {
        mockMvc.perform(get("/review/hello"))
        .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    void shouldReturnSucess_addTrip() throws Exception {
        mockMvc.perform(get("/review/trip"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":2,\"title\":\"tytul\",\"destination\":\"Kanary\",\"reviewList\":[{\"id\":2,\"review\":\"Recenzja number dwa\",\"author\":{\"id\":2,\"name\":\"Kasia\",\"email\":\"kasia@gmail.com\"},\"postDate\":\"2020-05-11\",\"rating\":5},{\"id\":3,\"review\":\"Recenzja number trzy\",\"author\":{\"id\":3,\"name\":\"Basia\",\"email\":\"basia@gmail.com\"},\"postDate\":\"2020-07-21\",\"rating\":3}],\"price\":350.0,\"departureDate\":\"2021-08-15\",\"transportType\":\"TRAIN\"}"));
    }
    @Test
    void shouldReturnSucess_saveTrip() throws Exception {
        mockMvc.perform(post("/review/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":9,\"title\":\"tytul\",\"destination\":\"Kanary\",\"reviewList\":[{\"id\":8,\"review\":\"Recenzja number dwa\",\"author\":{\"id\":7,\"name\":\"Kasia\",\"email\":\"kasia@gmail.com\"},\"postDate\":\"2020-05-11\",\"rating\":5},{\"id\":6,\"review\":\"Recenzja number trzy\",\"author\":{\"id\":5,\"name\":\"Basia\",\"email\":\"basia@gmail.com\"},\"postDate\":\"2020-07-21\",\"rating\":3}],\"price\":350.0,\"departureDate\":\"2021-08-15\",\"transportType\":\"TRAIN\"}"))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnSucess_addRewiev() throws Exception {
        String tripPojo = "{\"review\":{\"id\":11,\"review\":\"Recenzja number dwa\",\"author\":{\"id\":12,\"name\":\"Kasia\",\"email\":\"kasia@gmail.com\"},\"postDate\":\"2020-05-11\",\"rating\":5},\"id\":1}";

        mockMvc.perform(post("/review/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"tytul\",\"destination\":\"Kanary\",\"reviewList\":[{\"id\":8,\"review\":\"Recenzja number dwa\",\"author\":{\"id\":10,\"name\":\"Kasia\",\"email\":\"kasia@gmail.com\"},\"postDate\":\"2020-05-11\",\"rating\":5},{\"id\":6,\"review\":\"Recenzja number trzy\",\"author\":{\"id\":7,\"name\":\"Basia\",\"email\":\"basia@gmail.com\"},\"postDate\":\"2020-07-21\",\"rating\":3}],\"price\":350.0,\"departureDate\":\"2021-08-15\",\"transportType\":\"TRAIN\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/review/addReview")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tripPojo))
                .andExpect(status().isOk());
    }
    @Test
    void ShouldReturnSucess_delete() throws Exception {
        mockMvc.perform(post("/review/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"tytul\",\"destination\":\"Kanary\",\"reviewList\":[{\"id\":8,\"review\":\"Recenzja number dwa\",\"author\":{\"id\":10,\"name\":\"Kasia\",\"email\":\"kasia@gmail.com\"},\"postDate\":\"2020-05-11\",\"rating\":5},{\"id\":6,\"review\":\"Recenzja number trzy\",\"author\":{\"id\":7,\"name\":\"Basia\",\"email\":\"basia@gmail.com\"},\"postDate\":\"2020-07-21\",\"rating\":3}],\"price\":350.0,\"departureDate\":\"2021-08-15\",\"transportType\":\"TRAIN\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/review/delete/1"))
                .andExpect(status().isNoContent());
    }
}

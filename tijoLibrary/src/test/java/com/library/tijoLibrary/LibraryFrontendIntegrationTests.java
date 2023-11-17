package com.library.tijoLibrary;

import com.library.tijoLibrary.models.Book;
import com.library.tijoLibrary.repositories.BookRepository;
import com.library.tijoLibrary.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest
public class LibraryFrontendIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testReservationAndExtensionWithFrontend() {

        Book book = new Book("Book Title", "Book Author", "ISBN");
        bookRepository.save(book);

        String reservationUrl = "/reservation";
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);
        String requestBody = "{ " +
                "\"bookId\": " + book.getId() + ", " +
                "\"startDate\": \"" + startDate + "\", " +
                "\"endDate\": \"" + endDate + "\" }";

        //todo: dodaj rzeczywistego klienta REST do interakcji z API
        String reservationResponse = restTemplate.postForObject(reservationUrl, requestBody, String.class);
        assertTrue(reservationResponse.contains("success"));
    }
}

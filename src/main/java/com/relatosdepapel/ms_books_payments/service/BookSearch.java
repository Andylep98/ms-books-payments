package com.relatosdepapel.ms_books_payments.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class BookSearch {

    private WebClient webClient = WebClient.create();
    // URL del microservicio ms-books-catalogue
    private String searchServiceUrl = "http://localhost:8080/books";

    public BookItem getBook(Long bookId) {
        try {
            return webClient.get()
                    .uri(searchServiceUrl + "/" + bookId)
                    .retrieve()
                    .bodyToMono(BookItem.class)
                    .block();
        } catch (WebClientResponseException e) {
            return null; // No existe o error al conectarse
        }
    }
}

// Clase auxiliar para mapear la respuesta del microservicio buscador
@Getter
@Setter
class BookItem {
    private Long id;
    private boolean active;
    private int stock;
}
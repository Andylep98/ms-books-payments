package com.relatosdepapel.ms_books_payments.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@Slf4j
@RequiredArgsConstructor
public class BookSearch {
    // URL del microservicio ms-books-catalogue
    private String searchServiceUrl = "http://buscador-ms/books";
    private final WebClient.Builder webClient;

    public BookItem getBook(String bookId) {
        try {


            return
                    webClient.build().get()
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
    private String isbn;
}
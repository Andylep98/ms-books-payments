package com.relatosdepapel.ms_books_payments.service;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class BookSearch {

    private WebClient webClient = WebClient.create();
    // @todo Cambiar esta URL según el microservicio buscador
    private String searchServiceUrl = "http://localhost:8082/api/items";

    public BookItem validateItem(Long itemId, Integer quantity) {
        // Verificar si el libro existe, está activo y en stock mediante
        // una petición GET al microservicio ms-books-catalogue
        try {
            BookItem item = webClient.get()
                    .uri(searchServiceUrl + "/" + itemId)
                    .retrieve()
                    .bodyToMono(BookItem.class)
                    .block();

            if (item == null || !item.isActive() || item.getStock() < quantity) {
                throw new RuntimeException("Libro no disponible o sin stock");
            }
            return item;
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Falló la validación del libro", e);
        }
    }
}

// Clase auxiliar para mapear la respuesta del microservicio buscador
@Getter
class BookItem {
    private Long id;
    private boolean active;
    private int stock;
}
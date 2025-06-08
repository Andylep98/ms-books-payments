package com.relatosdepapel.ms_books_payments.service;

import com.relatosdepapel.ms_books_payments.model.Purchase;
import com.relatosdepapel.ms_books_payments.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookSearch bookSearch;

    public PurchaseService(PurchaseRepository purchaseRepository, BookSearch bookSearch) {
        this.purchaseRepository = purchaseRepository;
        this.bookSearch = bookSearch;
    }

    public Purchase registerPurchase(Long itemId, Integer quantity) {
        // Validar el libro con el microservicio ms-books-catalogue
        BookItem item = bookSearch.validateItem(itemId, quantity);

        Purchase purchase = Purchase.builder()
                .itemId(itemId)
                .quantity(quantity)
                .purchaseDate(LocalDateTime.now())
                .status("COMPLETADO")
                .build();

        return purchaseRepository.save(purchase);
    }
}
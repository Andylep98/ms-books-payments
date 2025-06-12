package com.relatosdepapel.ms_books_payments.service;

import com.relatosdepapel.ms_books_payments.model.Purchase;
import com.relatosdepapel.ms_books_payments.repository.PurchaseRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookSearch bookSearch;

    public PurchaseService(PurchaseRepository purchaseRepository, BookSearch bookSearch) {
        this.purchaseRepository = purchaseRepository;
        this.bookSearch = bookSearch;
    }

    public Purchase registerPurchase(Long bookId, Integer quantity) {
        // Obtener el libro del microservicio ms-books-catalogue
        BookItem book = bookSearch.getBook(bookId);

        String status;
        if (book == null || !book.isActive() || book.getStock() < quantity) {
            status = "FALLIDA";
        } else {
            status = "COMPLETADA";
        }

        Purchase purchase = Purchase.builder()
                .bookId(bookId)
                .quantity(quantity)
                .purchaseDate(LocalDateTime.now())
                .status(status)
                .build();

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}

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
    private static final String BOOK_NOT_FOUND = "La compra ha fallado porque el libro no ha sido encontrado";
    private static final String BOOK_NOT_AVAILABLE = "La compra ha fallado porque el libro no está disponible para su compra";
    private static final String BOOK_NO_STOCK = "La compra ha fallado porque el libro no cuenta con stock";
    private static final String PURCHASE_SUCCESS = "La compra ha sido completada exitosamente";


    public PurchaseService(PurchaseRepository purchaseRepository, BookSearch bookSearch) {
        this.purchaseRepository = purchaseRepository;
        this.bookSearch = bookSearch;
    }

    public Purchase registerPurchase(String bookId, Integer quantity) {
        // Obtener el libro del microservicio ms-books-catalogue
        BookItem book = bookSearch.getBook(bookId);

        String status;
        String details;

        if (book == null) {
            status = "FALLIDA";
            details = BOOK_NOT_FOUND;
        } else if (!book.isActive()) {
            status = "FALLIDA";
            details = BOOK_NOT_AVAILABLE;
        } else if (book.getStock() < quantity) {
            status = "FALLIDA";
            details = BOOK_NO_STOCK;
        } else {
            status = "COMPLETADA";
            details = PURCHASE_SUCCESS;
        }

        Purchase purchase = Purchase.builder()
                .bookId(bookId)
                .quantity(quantity)
                .purchaseDate(LocalDateTime.now())
                .status(status)
                .details(details)
                .build();

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }
}

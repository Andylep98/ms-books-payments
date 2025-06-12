package com.relatosdepapel.ms_books_payments.controller;

import com.relatosdepapel.ms_books_payments.model.Purchase;
import com.relatosdepapel.ms_books_payments.service.PurchaseService;
import org.springframework.web.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // POST: Crear compra
    @PostMapping
    public Purchase createPurchase(@RequestBody PurchaseRequest request) {
        return purchaseService.registerPurchase(request.getBookId(), request.getQuantity());
    }

    // GET: Obtener todas las compras
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}

@Getter
@Setter
class PurchaseRequest {
    private Long bookId;
    private Integer quantity;
}
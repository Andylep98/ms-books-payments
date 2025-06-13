package com.relatosdepapel.ms_books_payments.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "status")
    private String status;

    @Column(name = "details")
    private String details;
}

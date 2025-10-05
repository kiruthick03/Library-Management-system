package com.example.library.controller;

import com.example.library.model.Transaction;
import com.example.library.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService txService;
    public TransactionController(TransactionService txService) { this.txService = txService; }

    @GetMapping
    public List<Transaction> list() { return txService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable Long id) {
        return txService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/borrow")
    public ResponseEntity<Transaction> borrow(@RequestParam Long userId, @RequestParam Long bookId) {
        Transaction t = txService.borrowBook(userId, bookId);
        return ResponseEntity.created(URI.create("/api/transactions/" + t.getId())).body(t);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Transaction> returnTx(@PathVariable Long id) {
        Transaction t = txService.returnBook(id);
        return ResponseEntity.ok(t);
    }
}

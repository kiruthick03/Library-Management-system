package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Transaction;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.TransactionRepository;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class TransactionService {
    private final TransactionRepository txRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public TransactionService(TransactionRepository txRepo, BookRepository bookRepo, UserRepository userRepo) {
        this.txRepo = txRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    
    public Transaction borrowBook(Long userId, Long bookId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (book.getAvailableCopies() <= 0)
            throw new RuntimeException("No available copies");
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        Transaction tx = new Transaction();
        tx.setBook(book);
        tx.setUser(user);
        tx.setBorrowedAt(LocalDateTime.now());
        return txRepo.save(tx);
    }

   
    public Transaction returnBook(Long txId) {
        Transaction tx = txRepo.findById(txId).orElseThrow(() -> new RuntimeException("Transaction not found"));
        if (tx.getReturnedAt() != null)
            throw new RuntimeException("Already returned");
        Book book = tx.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);
        tx.setReturnedAt(LocalDateTime.now());
        return txRepo.save(tx);
    }

    public List<Transaction> findAll() { return txRepo.findAll(); }
    public Optional<Transaction> findById(Long id) { return txRepo.findById(id); }
}

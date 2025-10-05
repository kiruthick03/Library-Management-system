package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {
    @Test
    void saveAndFind() {
        BookRepository repo = Mockito.mock(BookRepository.class);
        BookService svc = new BookService(repo);

        Book b = new Book();
        b.setTitle("Test");
        when(repo.save(any(Book.class))).thenReturn(b);
        when(repo.findById(1L)).thenReturn(Optional.of(b));

        Book saved = svc.save(b);
        assertNotNull(saved);
        Optional<Book> got = svc.findById(1L);
        assertTrue(got.isPresent());
        verify(repo, times(1)).save(any(Book.class));
    }
}

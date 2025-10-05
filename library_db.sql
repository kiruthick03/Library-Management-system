CREATE DATABASE  library_db;
USE library_db;


DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);


CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    category VARCHAR(100),
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1
);


CREATE TABLE transactions (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrowed_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    returned_at DATETIME NULL,
    CONSTRAINT fk_transaction_user FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_transaction_book FOREIGN KEY (book_id)
        REFERENCES books (id) ON DELETE CASCADE
);


INSERT INTO users (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Williams', 'bob@example.com'),
('Charlie Brown', 'charlie@example.com');


INSERT INTO books (title, author, category, total_copies, available_copies) VALUES
('Effective Java', 'Joshua Bloch', 'Programming', 5, 5),
('Clean Code', 'Robert C. Martin', 'Software Engineering', 3, 3),
('Database Systems', 'Elmasri & Navathe', 'Databases', 4, 4),
('Spring in Action', 'Craig Walls', 'Frameworks', 6, 6),
('Head First Java', 'Kathy Sierra', 'Programming', 2, 2);


SELECT * FROM users;
SELECT * FROM books;
SELECT * FROM transactions;

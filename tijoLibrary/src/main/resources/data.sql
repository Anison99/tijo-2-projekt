DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS History;
DROP TABLE IF EXISTS BookStatuses;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Category;

CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE Book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    author VARCHAR(255),
    category_id BIGINT ,
    isReserved BOOLEAN
);
INSERT INTO Book (title, author, isReserved) VALUES
('Tytuł Książki 1', 'Autor 1', FALSE),
('Tytuł Książki 2', 'Autor 2', FALSE),
('Tytuł Książki 3', 'Autor 3', FALSE);


CREATE TABLE BookStatuses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    isReserved BOOLEAN NOT NULL DEFAULT FALSE,
    isAvailable BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (book_id) REFERENCES Book(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);


CREATE TABLE History (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL
);


CREATE TABLE Rating (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    rating INT NOT NULL,
    recension VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (book_id) REFERENCES Book(id)
);

CREATE TABLE Category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);
INSERT INTO Category (category_name) VALUES
('Thriller'),
('Komedia'),
('Romans'),
('Kryminał'),
('Reportaż');
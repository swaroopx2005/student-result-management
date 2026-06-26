CREATE TABLE books(
    book_id SERIAL PRIMARY KEY,
    title TEXT,
    author TEXT,
    category TEXT,
    quantity INT
);
CREATE TABLE members(
    member_id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT
);
CREATE TABLE issued_books(
    issue_id SERIAL PRIMARY KEY,
    book_id INT REFERENCES books(book_id),
    member_id INT REFERENCES members(member_id),
    issue_date DATE,
    return_date DATE
);
INSERT INTO books(title,author,category,quantity)
VALUES
('C Programming','Dennis Ritchie','Programming',5),
('Java Basics','James Gosling','Programming',3);

INSERT INTO members(name,email)
VALUES
('Ajay','ajay@gmail.com');
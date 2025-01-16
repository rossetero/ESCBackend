CREATE TABLE records (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    telegram VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    option VARCHAR(50) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
);
INSERT INTO records (name, telegram, phone, option) VALUES
('Alice', '@alice123', '+12345678901', 'Option 1'),
('Bob', '@bob456', '+19876543210', 'Option 2');

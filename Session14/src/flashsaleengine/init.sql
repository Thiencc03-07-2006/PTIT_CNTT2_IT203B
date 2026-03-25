-- Tạo database
CREATE DATABASE IF NOT EXISTS flash_sale_db;
USE flash_sale_db;

-- Bảng Users
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Products
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_code VARCHAR(50) NOT NULL UNIQUE,
    product_name VARCHAR(200) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    stock_quantity INT NOT NULL CHECK (stock_quantity >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Orders
CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'COMPLETED',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Bảng Order_Details
CREATE TABLE IF NOT EXISTS order_details (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Stored Procedure: Lấy top 5 người dùng mua nhiều nhất
DELIMITER //
CREATE PROCEDURE SP_GetTopBuyers()
BEGIN
    SELECT 
        u.id,
        u.username,
        u.full_name,
        COUNT(o.id) AS total_orders,
        SUM(o.total_amount) AS total_spent
    FROM users u
    INNER JOIN orders o ON u.id = o.user_id
    GROUP BY u.id, u.username, u.full_name
    ORDER BY total_spent DESC
    LIMIT 5;
END //
DELIMITER ;

-- Stored Procedure: Thống kê doanh thu theo danh mục
DELIMITER //
CREATE PROCEDURE SP_GetCategoryRevenue()
BEGIN
    SELECT 
        p.category,
        COUNT(od.id) AS total_items_sold,
        SUM(od.quantity * od.unit_price) AS total_revenue
    FROM products p
    INNER JOIN order_details od ON p.id = od.product_id
    GROUP BY p.category
    ORDER BY total_revenue DESC;
END //
DELIMITER ;

-- Thêm dữ liệu mẫu
INSERT INTO users (username, email, full_name) 
VALUES
	('john_doe', 'john@example.com', 'John Doe'),
	('jane_smith', 'jane@example.com', 'Jane Smith'),
	('bob_wilson', 'bob@example.com', 'Bob Wilson'),
	('alice_brown', 'alice@example.com', 'Alice Brown'),
	('charlie_davis', 'charlie@example.com', 'Charlie Davis');

INSERT INTO products (product_code, product_name, category, price, stock_quantity)
VALUES
	('IP15PRO', 'iPhone 15 Pro', 'Electronics', 29999999.00, 50),
	('MBP16', 'MacBook Pro 16"', 'Electronics', 45999999.00, 30),
	('GAMING_KB', 'Mechanical Keyboard', 'Accessories', 1500000.00, 100),
	('GAMING_MOUSE', 'Gaming Mouse', 'Accessories', 800000.00, 150),
	('TSHIRT_01', 'Premium T-Shirt', 'Fashion', 299000.00, 200),
	('JEANS_01', 'Slim Fit Jeans', 'Fashion', 599000.00, 120);
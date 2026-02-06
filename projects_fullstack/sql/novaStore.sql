---*-*-*-*-*-*-*-*-*-*-*-*----- 
-- NovaStore E-Commerce Data Management System
-- Author: Damla Su Bilge
-- Date: February 5, 2026
---*-*-*-*-*-*-*-*-*-*-*-*---

---*-*-*-*-*-*-*-*-*-*-*-*---
-- PART 1: DATABASE AND TABLE CREATION
---*-*-*-*-*-*-*-*-*-*-*-*---
USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'NovaStoreDB')
BEGIN
    ALTER DATABASE NovaStoreDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE NovaStoreDB;
END
GO

CREATE DATABASE NovaStoreDB;
GO

USE NovaStoreDB;
GO

-- First task is creating the tables. 
-- There are five tables of is categories, products, customers, orders, order details.

CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY IDENTITY(1,1),
    CategoryName VARCHAR(50) NOT NULL
);
GO

CREATE TABLE Products (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    ProductName VARCHAR(100) NOT NULL,
    Price DECIMAL(10,2),
    Stock INT DEFAULT 0,
    CategoryID INT,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
GO

CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    FullName VARCHAR(50),
    City VARCHAR(20),
    Email VARCHAR(100) UNIQUE
);
GO

CREATE TABLE Orders (
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    CustomerID INT,
    OrderDate DATETIME DEFAULT GETDATE(),
    TotalAmount DECIMAL(10,2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
GO

CREATE TABLE OrderDetails (
    DetailID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
GO

---*-*-*-*-*-*-*-*-*-*-*-*---
-- PART 2: DATA INSERTION
-- At this part, I implemented 5 categories as declared in the assignment. 
-- Then Inserted products with product name, price stock, categoryId as parameters.
---*-*-*-*-*-*-*-*-*-*-*-*---

-- categories

INSERT INTO Categories (CategoryName) VALUES 
('Elektronik'),
('Giyim'),
('Kitap'),
('Kozmetik'),
('Ev ve Yaşam');
GO

-- products
INSERT INTO Products (ProductName, Price, Stock, CategoryID) VALUES 
('iPhone 15 Pro', 45000.00, 15, 1),
('Samsung Galaxy S24', 38000.00, 20, 1),
('MacBook Air M3', 55000.00, 8, 1),
('Nike Air Max', 3500.00, 25, 2),
('Levi''s 501 Kot Pantolon', 1200.00, 30, 2),
('Zara Deri Ceket', 2800.00, 12, 2),
('Suç ve Ceza - Dostoyevski', 150.00, 40, 3),
('1984 - George Orwell', 120.00, 35, 3),
('Satranç - Stefan Zweig', 90.00, 50, 3),
('MAC Ruby Woo Ruj', 850.00, 18, 4),
('The Ordinary Serum', 450.00, 22, 4),
('Dyson V15 Süpürge', 18000.00, 5, 5);
GO

-- customers
INSERT INTO Customers (FullName, City, Email) VALUES 
('Ahmet Yılmaz', 'İstanbul', 'ahmet.yilmaz@email.com'),
('Ayşe Demir', 'Ankara', 'ayse.demir@email.com'),
('Mehmet Kaya', 'İzmir', 'mehmet.kaya@email.com'),
('Zeynep Çelik', 'Bursa', 'zeynep.celik@email.com'),
('Can Öztürk', 'Antalya', 'can.ozturk@email.com'),
('Elif Yıldız', 'Ankara', 'elif.yildiz@email.com');
GO

-- 10 orders with order details.
INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(1, '2026-01-15 10:30:00', 46200.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(1, 1, 1), (1, 7, 8);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(2, '2026-01-18 14:20:00', 3500.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(2, 4, 1);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(3, '2026-01-20 09:15:00', 57350.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(3, 3, 1), (3, 10, 1), (3, 8, 20);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(4, '2026-01-22 16:45:00', 3000.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(4, 5, 2), (4, 9, 4);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(5, '2026-01-25 11:00:00', 18000.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(5, 12, 1);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(6, '2026-01-28 13:30:00', 39250.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(6, 2, 1), (6, 11, 3);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(1, '2026-01-30 10:00:00', 2800.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(7, 6, 1);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(2, '2026-02-01 15:20:00', 76900.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(8, 3, 1), (8, 1, 1);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(3, '2026-02-03 12:10:00', 1350.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(9, 11, 3);
GO

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount) VALUES 
(4, '2026-02-04 09:45:00', 4550.00);
INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
(10, 4, 1), (10, 7, 7);
GO

---*-*-*-*-*-*-*-*-*-*-*-*---
-- PART 3: QUERIES AND ANALYSIS
---*-*-*-*-*-*-*-*-*-*-*-*---

-- products with stock less than 20, ordered by stock descending
SELECT ProductName, Stock
FROM Products
WHERE Stock < 20
ORDER BY Stock DESC;
GO

-- customer order information with JOIN
SELECT 
    C.FullName AS 'Müşteri Adı',
    C.City AS 'Şehir',
    O.OrderDate AS 'Sipariş Tarihi',
    O.TotalAmount AS 'Toplam Tutar'
FROM Customers C
INNER JOIN Orders O ON C.CustomerID = O.CustomerID
ORDER BY O.OrderDate DESC;
GO

-- products purchased by specific customer (Ahmet Yılmaz)
SELECT 
    C.FullName AS 'Müşteri',
    P.ProductName AS 'Ürün Adı',
    P.Price AS 'Fiyat',
    Cat.CategoryName AS 'Kategori',
    OD.Quantity AS 'Adet'
FROM Customers C
INNER JOIN Orders O ON C.CustomerID = O.CustomerID
INNER JOIN OrderDetails OD ON O.OrderID = OD.OrderID
INNER JOIN Products P ON OD.ProductID = P.ProductID
INNER JOIN Categories Cat ON P.CategoryID = Cat.CategoryID
WHERE C.FullName = 'Ahmet Yılmaz';
GO

-- product count by category using GROUP BY
SELECT 
    Cat.CategoryName AS 'Kategori',
    COUNT(P.ProductID) AS 'Ürün Sayısı'
FROM Categories Cat
LEFT JOIN Products P ON Cat.CategoryID = P.CategoryID
GROUP BY Cat.CategoryName
ORDER BY COUNT(P.ProductID) DESC;
GO

-- total revenue by customer, ordered descending
SELECT 
    C.FullName AS 'Müşteri Adı',
    C.City AS 'Şehir',
    SUM(O.TotalAmount) AS 'Toplam Ciro'
FROM Customers C
INNER JOIN Orders O ON C.CustomerID = O.CustomerID
GROUP BY C.FullName, C.City
ORDER BY SUM(O.TotalAmount) DESC;
GO

-- days elapsed since each order using DATEDIFF
SELECT 
    O.OrderID AS 'Sipariş No',
    C.FullName AS 'Müşteri',
    O.OrderDate AS 'Sipariş Tarihi',
    DATEDIFF(DAY, O.OrderDate, GETDATE()) AS 'Geçen Gün Sayısı'
FROM Orders O
INNER JOIN Customers C ON O.CustomerID = C.CustomerID
ORDER BY O.OrderDate DESC;
GO

---*-*-*-*-*-*-*-*-*-*-*-*---
-- PART 4: ADVANCED DATABASE OBJECTS
---*-*-*-*-*-*-*-*-*-*-*-*---

-- create VIEW for order summary
CREATE VIEW vw_SiparisOzet AS
SELECT 
    C.FullName AS MusteriAdi,
    O.OrderDate AS SiparisTarihi,
    P.ProductName AS UrunAdi,
    OD.Quantity AS Adet,
    P.Price AS BirimFiyat,
    (OD.Quantity * P.Price) AS ToplamTutar
FROM Customers C
INNER JOIN Orders O ON C.CustomerID = O.CustomerID
INNER JOIN OrderDetails OD ON O.OrderID = OD.OrderID
INNER JOIN Products P ON OD.ProductID = P.ProductID;
GO

-- test the VIEW
SELECT * FROM vw_SiparisOzet;
GO

-- Task 2: Database Backup
-- Note: Backup command for SQL Server (not supported in Azure SQL Edge)
-- BACKUP DATABASE NovaStoreDB 
-- TO DISK = 'C:\Yedek\NovaStoreDB.bak'
-- WITH FORMAT, INIT, NAME = 'NovaStoreDB-Full Database Backup';
-- GO

-- ============================================
-- SUMMARY
-- ============================================

SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES 
WHERE TABLE_TYPE = 'BASE TABLE' 
ORDER BY TABLE_NAME;
GO

SELECT 'Kategoriler' AS Tablo, COUNT(*) AS Kayit FROM Categories
UNION ALL
SELECT 'Ürünler', COUNT(*) FROM Products
UNION ALL
SELECT 'Müşteriler', COUNT(*) FROM Customers
UNION ALL
SELECT 'Siparişler', COUNT(*) FROM Orders
UNION ALL
SELECT 'Sipariş Detayları', COUNT(*) FROM OrderDetails;
GO
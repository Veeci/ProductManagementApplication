CREATE DATABASE IF NOT EXISTS ProductDB;
USE ProductDB;

-- Create the SanPham table
CREATE TABLE IF NOT EXISTS SanPham (
    maSP VARCHAR(50) PRIMARY KEY,
    tenSP VARCHAR(100),
    gia DOUBLE,
    soLuong INT,
    maDM VARCHAR(50),
    FOREIGN KEY (maDM) REFERENCES DanhMuc(maDM)
);

-- Create the DanhMuc table
CREATE TABLE IF NOT EXISTS DanhMuc (
    maDM VARCHAR(50) PRIMARY KEY,
    tenDM VARCHAR(100)
);

-- Create the TaiKhoan table
CREATE TABLE IF NOT EXISTS TaiKhoan (
    tenDangNhap VARCHAR(50) PRIMARY KEY,
    matKhau VARCHAR(100),
    hoTen VARCHAR(100),
    diaChi VARCHAR(255),
    soDT VARCHAR(20)
);

-- Insert data into DanhMuc table
INSERT INTO DanhMuc (maDM, tenDM) VALUES
('DM001', 'Electronics'),
('DM002', 'Clothing'),
('DM003', 'Books'),
('DM004', 'Home Appliances'),
('DM005', 'Toys');

-- Insert data into SanPham table
INSERT INTO SanPham (maSP, tenSP, gia, soLuong, maDM) VALUES
('SP001', 'Laptop', 1200, 10, 'DM001'),
('SP002', 'T-shirt', 20, 50, 'DM002'),
('SP003', 'Novel', 15, 100, 'DM003'),
('SP004', 'Refrigerator', 800, 5, 'DM004'),
('SP005', 'Action Figure', 10, 30, 'DM005');
-- Insert additional rows as needed...

-- Insert data into TaiKhoan table
INSERT INTO TaiKhoan (tenDangNhap, matKhau, hoTen, diaChi, soDT) VALUES
('user1', 'password1', 'John Doe', '123 Main St, City', '123-456-7890'),
('user2', 'password2', 'Jane Smith', '456 Elm St, Town', '987-654-3210'),
('user3', 'password3', 'Alice Johnson', '789 Oak St, Village', '555-123-4567'),
('user4', 'password4', 'Bob Brown', '101 Pine St, Hamlet', '321-654-9870'),
('user5', 'password5', 'Eve White', '202 Cedar St, Borough', '999-888-7777');

INSERT INTO SanPham (maSP, tenSP, gia, soLuong, maDM) VALUES
('SP006', 'Smartphone', 800, 20, 'DM001'),
('SP007', 'Jeans', 40, 30, 'DM002'),
('SP008', 'Cookbook', 25, 80, 'DM003'),
('SP009', 'Microwave', 150, 15, 'DM004'),
('SP010', 'Barbie Doll', 15, 40, 'DM005'),
('SP011', 'Tablet', 400, 10, 'DM001'),
('SP012', 'Dress', 50, 25, 'DM002'),
('SP013', 'Dictionary', 20, 70, 'DM003'),
('SP014', 'Vacuum Cleaner', 200, 8, 'DM004'),
('SP015', 'LEGO Set', 30, 50, 'DM005'),
('SP016', 'Headphones', 50, 15, 'DM001'),
('SP017', 'Sweater', 35, 20, 'DM002'),
('SP018', 'Magazine', 5, 200, 'DM003'),
('SP019', 'Coffee Maker', 100, 12, 'DM004'),
('SP020', 'Board Game', 20, 30, 'DM005'),
('SP021', 'Smartwatch', 120, 8, 'DM001'),
('SP022', 'Skirt', 25, 15, 'DM002'),
('SP023', 'Notebook', 10, 150, 'DM003'),
('SP024', 'Blender', 80, 10, 'DM004'),
('SP025', 'Puzzle', 15, 40, 'DM005');
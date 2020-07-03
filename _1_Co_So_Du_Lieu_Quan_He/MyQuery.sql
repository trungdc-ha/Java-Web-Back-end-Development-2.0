
/* Lấy tất cả thông tin của table customers */
SELECT * FROM customers

/* Lấy tất cả thông tin cột customername, phone, city, country của table customers */
SELECT customerName, phone, city, country FROM customers 

/* hiển thị thông tin khách hàng có tên là Atelier Graphique. */
SELECT * FROM customers WHERE customerName = 'Atelier Graphique'

/* Kết quả trả về hiển thị thông tin khách hàng có tên chứa ký tự A.*/
SELECT * FROM customers WHERE customername like '%A%'

/* Kết quả trả về hiển thị thông tin khách hàng có city ở một trong những vùng Nantes, Las Vegas, Warszawa, NYC. */
SELECT * FROM customers WHERE city IN ('Nantes',' Las Vegas',' Warszawa','NYC') 

-- - Viết câu lệnh hiện thị tất cả các dòng có tên là Huong
select *
from HOCVIEN
where TEN = 'Huong';
-- - Viết câu lệnh lấy ra tổng số tiền của Huong
select sum(SOTIEN)
from HOCVIEN
where TEN = 'Huong';
-- - Viết câu lệnh lấy ra tên danh sách của tất cả học viên, không trùng lặp
select distinct TEN
from HOCVIEN
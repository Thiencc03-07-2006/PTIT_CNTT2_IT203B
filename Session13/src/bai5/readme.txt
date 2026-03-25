Phân tích Rủi ro & Edge Cases

Kịch bản "Tiền vào túi - Giường vẫn trống": Nhân viên thực hiện thu tiền tạm ứng thành công (ghi vào bảng tài chính), nhưng ngay sau đó mạng lag khiến câu lệnh cập nhật trạng thái giường bị thất bại. Kết quả: Bệnh nhân mất tiền nhưng hệ thống báo giường vẫn trống, dẫn đến việc xếp chồng người khác vào cùng một giường.
Kịch bản "Dữ liệu rác (Input Mismatch)": Người dùng nhập chữ "Năm trăm" vào ô số tiền hoặc nhập "Hai mươi" vào ô tuổi. Nếu không dùng Scanner.hasNextInt() hoặc try-catch, chương trình sẽ văng lỗi InputMismatchException và sập (crash) ngay lập tức, làm gián đoạn việc tiếp nhận các bệnh nhân khác.
Kịch bản "Giường ảo": Nhân viên nhập một Mã giường không tồn tại trong Database. Nếu không kiểm tra rowsAffected == 0 (Bẫy dữ liệu ảo), hệ thống vẫn báo "Tiếp nhận thành công", trừ tiền bệnh nhân nhưng thực tế không có giường nào được gán cho họ.

Cấu trúc Database

Bảng GiuongBenh: ma_giuong (PK), ten_phong, trang_thai (0: Trống, 1: Có người).
Bảng BenhNhan: ma_bn (PK), ten_bn, tuoi, ma_giuong (FK).
Bảng TaiChinh: id (PK), ma_bn (FK), so_tien_tam_ung, ngay_thu.

Luồng xử lý Logic

1.Nhận Input (Tên, Tuổi, Mã giường, Tiền).
2.Mở Connection -> setAutoCommit(false).
3.Bước 1: INSERT vào bảng BenhNhan. Lấy ra GeneratedKey (ID bệnh nhân vừa tạo).
4.Bước 2: UPDATE bảng GiuongBenh set trang_thai = 1 nơi ma_giuong = ?. Kiểm tra nếu rows == 0 -> rollback.
5.Bước 3: INSERT vào bảng TaiChinh với ID bệnh nhân ở bước 1.
6.Nếu tất cả trơn tru -> conn.commit().
7.Nếu có bất kỳ Exception nào -> conn.rollback().
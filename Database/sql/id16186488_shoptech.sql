-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th7 27, 2021 lúc 03:11 AM
-- Phiên bản máy phục vụ: 10.3.16-MariaDB
-- Phiên bản PHP: 7.3.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `id16186488_shoptech`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `mathietbi` int(11) NOT NULL,
  `tenthietbi` varchar(5000) NOT NULL,
  `giathietbi` int(11) NOT NULL,
  `soluongthietbi` int(11) NOT NULL,
  `ngaydat` varchar(20) NOT NULL,
  `emaildat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `mathietbi`, `tenthietbi`, `giathietbi`, `soluongthietbi`, `ngaydat`, `emaildat`) VALUES
(66, 66, 33, 'Vivo V20 - 8GB/128GB - Chính Hãng\n', 7050000, 1, '15/07/2021', 'obama@gmail.com'),
(67, 67, 28, 'Samsung Galaxy Fit 2 (R220)\n', 690000, 1, '15/07/2021', 'obama@gmail.com'),
(68, 68, 3, 'Samsung Galaxy Note 20 Ultra 5G Chính Hãng\n', 25790000, 1, '15/07/2021', 'obama@gmail.com'),
(69, 69, 24, 'Huami Amazfit GTR 2 Leather\n', 4290000, 1, '15/07/2021', 'obama@gmail.com'),
(70, 70, 32, 'MacBook Air 13\" - M1 256GB 2020 - Chính hãng\n', 23300000, 1, '16/07/2021', 'obama@gmail.com'),
(71, 71, 12, 'Apple Watch SE GPS - Chính hãng (VN/A)\nMacbook Pro 13 Inch – M1 512GB 2020 – Chính Hãng\n', 51070000, 3, '17/07/2021', 'obama@gmail.com'),
(72, 72, 20, 'iPad Air 3 - 10.5\" - 64GB\n', 12690000, 1, '18/07/2021', 'obama@gmail.com'),
(74, 74, 23, 'Xiaomi Mi Watch Lite - Chính hãng\n', 1450000, 1, '19/07/2021', 'hvt102@gmail.com'),
(75, 75, 22, 'Samsung Galaxy Tab A8 Plus\n', 6390000, 1, '24-07-2021', 'hvt102@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `id` int(11) NOT NULL,
  `noidung` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `rating` double NOT NULL,
  `ngaydanhgia` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `idsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`id`, `noidung`, `rating`, `ngaydanhgia`, `email`, `hoten`, `avatar`, `idsanpham`) VALUES
(23, 'Giao hàng hơi lâu nha', 3, '20/07/2021', 'obama@gmail.com', 'Nguyễn Văn Giàu', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626356170337.png?alt=media&token=8e691367-b04d-4d8e-98a3-89d1edddbe61', 1),
(25, 'Quá oke, cho 5 sao ủng hộ shop', 5, '20/07/2021', 'hvt102@gmail.com', 'Hồ Văn Đồ', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626179449835.png?alt=media&token=2d59d27f-8a11-4236-9cca-3542ecf4c2db', 1),
(26, 'Test 1 ngày thấy pin khá trâu ae à', 4.5, '20/07/2021', 'hvt102@gmail.com', 'Hồ Văn Đồ', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626179449835.png?alt=media&token=2d59d27f-8a11-4236-9cca-3542ecf4c2db', 1),
(53, 'Điện thoại quá ngon trong tầm giá', 4, '20/07/2021', 'obama@gmail.com', 'Nguyễn Văn Giàu', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626356170337.png?alt=media&token=8e691367-b04d-4d8e-98a3-89d1edddbe61', 1),
(54, 'Good', 5, '20/07/2021', 'vangiau.recca@gmail.com', 'Nguyễn Văn Giàu', 'https://i.imgur.com/VZwd48U.png', 1),
(55, 'Cấu hình cao, giá rẻ, pin trâu', 4, '20/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 33),
(56, 'Chất lượng oke, giao hàng hơi chậm nên cho 4 sao', 4, '20/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 19),
(57, 'Quá ngon', 5, '20/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 20),
(58, 'Hàng cũng được có điều là giao hơi chậm nha shop, hy vọng lần sau sẽ tốt hơn, sẽ ủng hộ shop dài dài nè hehehe', 5, '20/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 20),
(59, 'Tạm', 3, '20/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 22),
(60, 'Máy mượt, pin trâu, ủng hộ shop', 5, '21/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 1),
(61, 'Máy đẹp, đúng hàng flaship', 4, '21/07/2021', 'vangiau.recca@gmail.com', 'Chu Bá Thông', 'https://i.imgur.com/VZwd48U.png', 3),
(62, 'Ủng hộ shop dài dài', 5, '21/07/2021', 'vangiau.recca@gmail.com', 'Chu Bá Thông', 'https://i.imgur.com/VZwd48U.png', 3),
(63, 'Máy dùng khá mượt, nhưng sóng hơi yếu', 3, '21/07/2021', 'vangiau.recca@gmail.com', 'Nguyễn Văn Giàu', 'https://i.imgur.com/VZwd48U.png', 3),
(64, 'Pin tuột khá nhanh, khá thất vọng', 2, '21/07/2021', 'hvt102@gmail.com', 'Hồ Văn Đồ', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626179449835.png?alt=media&token=2d59d27f-8a11-4236-9cca-3542ecf4c2db', 3),
(65, 'Chất lượng tốt', 5, '21/07/2021', 'hvt102@gmail.com', 'Hồ Văn Đồ', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626845384527.png?alt=media&token=3635ac88-d623-4f73-ad43-ee53622aa1cb', 30),
(66, 'Tạm ổn trong tầm giá', 4, '23/07/2021', 'obama@gmail.com', 'Chu Bá Thông', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9', 6),
(67, 'Máy đẹp, cấu hình ổn trong tầm giá', 5, '24/07/2021', 'hvt102@gmail.com', 'Hồ Văn Tèo', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626845736245.png?alt=media&token=fdb1d34c-9ec2-4d15-8fa8-dab3f12b2b69', 17);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `diachi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `diachi`) VALUES
(63, 'Nguyễn Văn Giàu', 343479496, '117 Cống Quỳnh, Nguyễn Cư Trinh, Quận 1'),
(64, 'Trần Văn Ba', 383737636, 'Cà Mau'),
(65, 'Trần Văn Ba', 383737636, 'Cà Mau'),
(66, 'Trần Văn Cát', 372727738, 'Quận Phú Nhuận'),
(67, 'Hồ Văn Đú', 737378474, 'Bạc Liêu'),
(68, 'Lê Văn Đạt', 473728387, 'Quận Gò Vấp'),
(69, 'Hồ Văn Hồng', 377263737, 'Quận 5'),
(70, 'Lê Văn Cáp', 372738727, 'Hà Nội'),
(71, 'Võ Ngọc Loan', 386151727, 'Bến Lức, Long An'),
(72, 'Chu Bá Thông', 337373884, 'Bến Tre'),
(73, 'Chu Văn Tùng', 372737838, 'Đồng Tháp'),
(74, 'Hồ Văn Hơi', 346464258, 'Cần Thơ'),
(75, 'Trần Thị Hạnh Phúc', 374747488, 'Bạc Liêu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaithietbi`
--

CREATE TABLE `loaithietbi` (
  `id` int(11) NOT NULL,
  `tenloai` varchar(200) NOT NULL,
  `hinhanhloai` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaithietbi`
--

INSERT INTO `loaithietbi` (`id`, `tenloai`, `hinhanhloai`) VALUES
(1, 'Điện thoại', 'https://i.pinimg.com/originals/1e/39/9e/1e399e9e9b31191566f3e152ddf03e69.png'),
(2, 'Laptop', 'https://www.lifia.org/wp-content/uploads/2017/09/laptop.png'),
(3, 'Tablet', 'https://images.vexels.com/media/users/3/128862/isolated/preview/5b021d17fb3643d144434b4cc6c3a74c-tablet-flat-icon-by-vexels.png'),
(4, 'Đồng hồ', 'https://e7.pngegg.com/pngimages/345/179/png-clipart-time-attendance-clocks-computer-icons-alarm-clocks-clock-angle-digital-clock.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `matkhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `hoten` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `avatar` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `email`, `matkhau`, `hoten`, `diachi`, `avatar`) VALUES
(15, 'hvt102@gmail.com', 'anhyeuem1', 'Hồ Văn Tèo', 'Bạc Liêu', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626845736245.png?alt=media&token=fdb1d34c-9ec2-4d15-8fa8-dab3f12b2b69'),
(16, 'obama@gmail.com', '666666', 'Chu Bá Thông', 'Ấp Bình Thọ Trung, Xã Bình Phan, Chợ Gạo, Tiền Giang', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1626794287845.png?alt=media&token=54c85c37-8fd8-4bbd-a271-d04e9cfe5ab9'),
(18, 'admin@gmail.com', '666666', 'Nguyễn Văn Giàu', 'Chợ Gạo, Tiền Giang', 'https://firebasestorage.googleapis.com/v0/b/shoptech-306114.appspot.com/o/image1627114966162.png?alt=media&token=cea2ad0e-f068-4c40-8a1e-d29c6a99812f');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thietbi`
--

CREATE TABLE `thietbi` (
  `id` int(11) NOT NULL,
  `tenthietbi` varchar(200) NOT NULL,
  `giagoc` int(15) NOT NULL,
  `giatri` int(15) NOT NULL,
  `hinhanhthietbi` varchar(250) NOT NULL,
  `mota` varchar(10000) NOT NULL,
  `idthietbi` int(3) NOT NULL,
  `hinhanhchitiet2` varchar(250) NOT NULL,
  `hinhanhchitiet3` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `thietbi`
--

INSERT INTO `thietbi` (`id`, `tenthietbi`, `giagoc`, `giatri`, `hinhanhthietbi`, `mota`, `idthietbi`, `hinhanhchitiet2`, `hinhanhchitiet3`) VALUES
(1, 'Điện thoại Xiaomi Redmi 9T (6GB/128GB)', 5590000, 4990000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/xiaomi-redmi-9t-xanh-12a856ba-201e-4eac-8d22-290dcd1a7ef0-eda33d61-0a40-4777-9e32-f15c787f76ec.png', '♦ Màn hình: IPS LCD, 6.53\", Full HD+\r\n♦ Hệ điều hành:	Android 10\r\n♦ Camera sau: Chính 48 MP & Phụ 8 MP, 2 MP, 2 MP\r\n♦ Camera trước:	8 MP\r\n♦ CPU: Snapdragon 662 8 nhân\r\n♦ RAM: 4 GB\r\n♦ Bộ nhớ trong: 64 GB\r\n♦ Thẻ nhớ: MicroSD, hỗ trợ tối đa 512 GB\r\n♦ Thẻ SIM: 2 Nano SIM, Hỗ trợ 4G\r\n♦ Dung lượng pin: 6000 mAh, có sạc nhanh', 1, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/thiet-ke-khong-ten-10-2491ad9d-222d-460b-bebc-38fa4d99cd26.png', 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/thiet-ke-khong-ten-9-fb349f2c-e7d1-43f8-8f25-28d432df4491.png'),
(3, 'Samsung Galaxy Note 20 Ultra 5G Chính Hãng', 26600000, 25790000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/cfa4542b-01c0-4d7b-9008-078a30d19236-1536e9e5-7f06-4aab-85d4-8c5b844c1009.png', '♦ Màn hình: 6.9\", 2K+, Dynamic AMOLED 2X, 1440 x 3088 Pixel\r\n♦ Camera sau: 108.0 MP + 12.0 MP + 12.0 MP\r\n♦ Camera: Selfie 10.0 MP\r\n♦ RAM: 12 GB\r\n♦ Bộ nhớ trong: 256 GB\r\n♦ CPU: Exynos 990\r\n♦ GPU: ARM Mali-G77 MP11\r\n♦ Dung lượng pin: 4500 mAh\r\n♦ Thẻ sim: 2, 2 Nano SIM hoặc 1 eSIM, 1 Nano SIM\r\n♦ Hệ điều hành: Android 10.0\r\n♦ Xuất xứ: Việt Nam', 1, 'https://hoanghamobile.com/i/preview/Uploads/2020/10/27/image-removebg-preview%20(5).png', 'https://hoanghamobile.com/i/preview/Uploads/2020/10/27/image-removebg-preview%20(4).png'),
(5, 'Realme 7 Pro 8GB/128GB Chính Hãng', 8500000, 7900000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/realme-7-pro-xanh-ngoc-c2e73dd4-afff-4042-a47b-4a9784eb4939.png', '♦ Công nghệ màn hình: Super AMOLED\r\n♦ Độ phân giải: Full HD+ (1080 x 2400 Pixels)\r\n♦ Màn hình rộng: 6.4\"\r\n♦ Tốc độ CPU: 2 nhân 2.3 Ghz & 6 nhân 1.8 Ghz\r\n♦ Hệ điều hành: Android 10\r\n♦ Chip xử lý (CPU): Snapdragon 720G 8 nhân\r\n♦ Chip đồ họa (GPU): Adreno 618\r\n♦ RAM: 8 GB\r\n♦ Kích thước: Dài 160.9 mm - Ngang 74.3 mm - Dày 8.7 mm\r\n♦ Dung lượng pin: 4500 mAh', 1, 'https://sites.google.com/site/dienthoai4u/_/rsrc/1600745597118/android/realme-7-pro-gia-bao-nhieu-ngay-nao-ban-ra-co-nen-mua/realme-7-pro-gia-bao-nhieu-ngay-nao-ban-ra-co-nen-mua.jpg', 'https://i.imgur.com/UrKBAnz.png'),
(6, 'Vsmart Joy 4 3G/64GB Hàng chính hãng', 3990000, 3090000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/vsmart-joy-4-905e0a88-4ddc-4ca1-b635-76106b6ad9f8.png', '♦ Công nghệ màn hình: LTPS IPS LCD\r\n♦ Độ phân giải: Full HD+ (1080 x 2340 Pixels)\r\n♦ Màn hình rộng: 6.53\"\r\n♦ Tốc độ CPU: 4 nhân 2.0 GHz & 4 nhân 1.8 GHz\r\n♦ Chip đồ họa (GPU): Adreno 610\r\n♦ RAM: 4 GB\r\n♦ Kích thước: Dài 163.65 mm - Ngang 77.65 mm - Dày 9.15 mm\r\n♦ Trọng lượng: 216.4 g\r\n♦ Dung lượng pin: 5000 mAh', 1, 'https://i.imgur.com/d36Fvur.png', 'https://hoanghamobile.com/Uploads/Content/2020/09/11/vsmart--dien-thoai-di-dong-vsmart-joy-4-3gb-64gb-chinh-hang-4.jpg'),
(7, 'Xiaomi POCO M3 – Chính hãng', 4090000, 3390000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/976efe1f-cad1-4903-8674-08d8da532dfe.png', '♦ Màn hình: IPS LCD, 6.53\", Full HD+ (1080 x 2340 Pixels)\r\n♦ Hệ điều hành:	Android 10\r\n♦ Camera sau: Chính 48 MP & Phụ 2 MP, 2 MP\r\n♦ Camera trước:	8 MP\r\n♦ CPU: Snapdragon 662 8 nhân\r\n♦ RAM: 4 GB\r\n♦ Bộ nhớ trong:	128 GB\r\n♦ Thẻ nhớ: MicroSD\r\n♦ Thẻ SIM: 2 Nano SIM\r\n♦ Dung lượng pin: 6000 mAh, Sạc pin nhanh, Siêu tiết kiệm pin', 1, 'https://fonesmart.com.vn/images/products/2021/04/08/original/xiaomi-poco-m3-slide-1_1617883897.jpg', 'https://cdn.tgdd.vn/Products/Images/42/231107/xiaomi-poco-m3-104120-024144.jpg'),
(8, 'iPhone 12 128GB Chính Hãng', 23400000, 22490000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/iphone12-pro-2-784ad745-5245-4930-9ee2-17b3afa76f7a.png', '♦ Công nghệ màn hình: OLED\r\n♦ Độ phân giải: 1170 x 2532 Pixels\r\n♦ Màn hình rộng: 6.1\"\r\n♦ Tốc độ CPU: 2 nhân 3.1 GHz & 4 nhân 1.8 GHz\r\n♦ Hệ điều hành: iOS 14\r\n♦ Chip xử lý (CPU): Apple A14 Bionic 6 nhân\r\n♦ Chip đồ họa (GPU): Apple GPU 6 nhân\r\n♦ RAM: 4 GB\r\n♦ Kích thước: Dài 146.7 mm - Ngang 71.5 mm - Dày 7.4 mm\r\n♦ Trọng lượng: 164 g\r\n♦ Dung lượng pin: 2815 mAh', 1, 'https://www.viettablet.com/images/news/46/iphone-12-mau-den.jpg', 'https://phuckhangmobile.com/image/tren-tay-va-danh-gia-nhanh-iphone-12-mini-mau-den-nho-nhan-xinh-xan-va-cuc-dang-yeu-8-21632j.jpg'),
(9, 'Oppo Reno5 5G 8GB/128GB chính hãng', 11000000, 10230000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/87744682-9941-4f29-84ae-f284515f4373-817e1b8b-58b0-4236-87a0-98156ef9aa1d.png', '♦ Công nghệ màn hình: AMOLED\r\n♦ Độ phân giải: Full HD+ (1080 x 2400 Pixels)\r\n♦ Màn hình rộng: 6.43\"\r\n♦ Hệ điều hành: Android 11\r\n♦ Tốc độ CPU: 2 nhân 2.3 Ghz & 6 nhân 1.8 Ghz\r\n♦ Chip xử lý (CPU): Snapdragon 720G 8 nhân\r\n♦ Chip đồ họa (GPU): Adreno 618\r\n♦ RAM: 8 GB\r\n♦ Kích thước: Dài 159.1 mm - Ngang 73.4 mm - Dày 7.7 mm\r\n♦ Trọng lượng: 171 g\r\n♦ Dung lượng pin: 4310 mAh', 1, 'https://c8n8e4j6.rocketcdn.me/wp-content/uploads/2020/12/OPPO-Reno5.jpg', 'https://cellphones.com.vn/sforum/wp-content/uploads/2020/12/7d607111ly1glj2tiar6dj26pc4gwe81-scaled.jpg'),
(10, 'OPPO A93 8GB/128GB CHÍNH HÃNG', 7290000, 5990000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/oppo-a93-percentage-c4-percentage-91en-e4aca1de-9a0a-4c4d-b06f-7a3d7b821873.png', '♦ Công nghệ màn hình: AMOLED\r\n♦ Độ phân giải: Full HD+ (1080 x 2400 Pixels)\r\n♦ Màn hình rộng: 6.43\"\r\n♦ Tốc độ CPU: 2 nhân 2.2 GHz & 6 nhân 2.0 GHz\r\n♦ Chip đồ họa (GPU): IMG PowerVR GM9446\r\n♦ RAM: 8 GB\r\n♦ Kích thước: Dài 160.1 mm - Ngang 73.77 mm - Dày 7.48 mm\r\n♦ Trọng lượng: 164 g\r\n♦ Dung lượng pin: 4000 mAh', 1, 'https://cdn.tgdd.vn/Files/2020/10/01/1295077/oppo-a93-28_800x450.jpg', 'http://tapchicongthuong.vn/images/20/10/1/oppo_a93.jpg'),
(11, 'MacBook Air 13 inch – M1 256GB 2020 – Chính Hãng', 25000000, 24550000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/macbook-air-2020-m1-thumbnail.png', 'Apple MacBook Air 13\" - M1 256GB 2020 ra mắt cực kì ấn tượng với con chip M1 mới được thiết kế dành riêng cho MacBook tăng hiệu suất CPU nhanh hơn tới 3.5 lần, tuổi thọ pin dài nhất từ ​​trước đến nay trên Macbook Air, lên tới 16h sử dụng liên tục. Máy mới nguyên seal, đầy đủ phụ kiện, kích hoạt bảo hành đủ 12 tháng chính hãng.', 2, 'https://tuanlocstore.vn/images/thumbs/0010955_macbook-pro-retina-touch-bar-2017-mpxx2_600.jpeg', 'https://thegioiso365.com/wp-content/uploads/2018/12/aHR0cHM6Ly93d3cubGFwdG9wbWFnLmNvbS9pbWFnZXMvdXBsb2Fkcy81MjY2L2cvbWFjYm9vay1haXItMjAxNy0wMDcuanBn.jpg'),
(12, 'MacBook Pro 13 inch – M1 512GB 2020 – Chính Hãng', 36990000, 35890000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/macbook-pro-m1-silver.png', 'Apple MacBook Pro 13\" - M1 512GB 2020 ra mắt cực kì ấn tượng với con chip M1 mới được thiết kế dành riêng cho MacBook tăng hiệu suất CPU nhanh hơn tới 3.5 lần, tuổi thọ pin dài nhất từ ​​trước đến nay trên Macbook Pro, lên tới 18h sử dụng liên tục. Máy mới nguyên seal, đầy đủ phụ kiện, kích hoạt bảo hành đủ 12 tháng chính hãng.', 2, 'https://didongviet.vn/pub/media/wysiwyg/Macbook-Pro-13inch-2019/macbook-pro-13-inch-2019-didongviet1.jpg', 'https://phukienmac.vn/wp-content/uploads/2020/02/cach-chon-sac-macbook.jpg'),
(13, 'Mac Mini M1 – 512GB 2020 (MGNT3SA/A)', 23490000, 22500000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/mac-mini-m1-2020-silver.png', 'Mac Mini M1 - 512GB 2020 (MGNT3SA/A) ra mắt cực kì ấn tượng với con chip M1 mới được thiết kế dành riêng cho dòng Mac giúp tăng cường hiệu suất CPU nhanh hơn tới 3.5 lần, thời gian xử lý mọi tác vụ được giảm xuống đáng kể. Máy mới nguyên seal, đầy đủ phụ kiện, kích hoạt bảo hành đủ 12 tháng chính hãng.', 2, 'https://img.fpt.shop/w_665/h_374/f_jpg/cmpr_10/m_letterbox_ffffff_100/https://fptshop.com.vn/Uploads/Originals/2020/12/7/637429304704506202_mac-mini-2020-m1-7.jpg', 'https://laptopvang.com/wp-content/uploads/2020/10/apple-mac-mini-la-cai-gi.jpg'),
(14, 'Mac Mini M1 – 256GB 2020 (MGNR3SA/A)', 21100000, 20700000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/mac-mini-m1-2020-grey.png', 'Mac Mini M1 - 256GB 2020 (MGNR3SA/A) ra mắt cực kì ấn tượng với con chip M1 mới được thiết kế dành riêng cho dòng Mac giúp tăng cường hiệu suất CPU nhanh hơn tới 3.5 lần, thời gian xử lý mọi tác vụ được giảm xuống đáng kể. Máy mới nguyên seal, đầy đủ phụ kiện, kích hoạt bảo hành đủ 12 tháng chính hãng.', 2, 'https://mac365.vn/wp-content/uploads/2019/10/danh-gia-mac-mini-2018-3.jpg', 'https://mac365.vn/wp-content/uploads/2019/10/danh-gia-mac-mini-2018-4.jpg'),
(15, 'MacBook Pro 13″ – M1 256GB – Apple Việt Nam', 33090000, 31890000, 'https://bizweb.dktcdn.net/thumb/large/100/420/160/products/macbook-pro-m1-grey.png', 'Apple MacBook Pro 13\" - M1 256GB 2020 ra mắt cực kì ấn tượng với con chip M1 mới được thiết kế dành riêng cho MacBook tăng hiệu suất CPU nhanh hơn tới 3.5 lần, tuổi thọ pin dài nhất từ ​​trước đến nay trên Macbook Pro, lên tới 18h sử dụng liên tục. Máy mới nguyên seal, đầy đủ phụ kiện, kích hoạt bảo hành đủ 12 tháng chính hãng', 2, 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/macbook-pro-13-m1-8.jpg', 'https://fptshop.com.vn/uploads/images/tin-tuc/128363/Originals/macbook-air-m1-2020-01.jpg'),
(16, 'Apple iPad Pro - 512GB - Chính hãng', 37000000, 35600000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/06/apple-ipad-pro-12-9-2020-2.png', 'iPad Pro 12.9” chắc chắn sẽ thuộc hàng những chiếc máy tính bảng với màn hình đẹp nhật. Tấm nền Liquid Retina có mật độ điểm ảnh 264ppi đem lại khả năng hiển thị màu sắc chính xác hàng đầu. Tần số quét lên tới 120Hz và công nghệ ProMotion chắc chắn sẽ đem lại trải nghiệm thị giác vô cùng mượt mà cho người dùng. Màn hình của iPad Pro 2020 có độ sáng lên tới 600 nit, kết hợp cùng lớp phủ chống chói và chống vân tay, giúp giảm khả năng phản quang của màn hình xuống chỉ còn 1,8%. Với chiếc máy tính bảng này, trải nghiệm làm việc ngoài trời hoặc ở nơi có độ sáng cao chắc chắn sẽ không làm bạn thất vọng.', 3, 'https://didongviet.vn/pub/media/wysiwyg/thiet-ke-iPad-pro-2021-12.9inch-m1-512gb-wifi-5g-didongviet.jpg', 'https://didongviet.vn/pub/media/wysiwyg/iPad-pro-2021-12.9inch-m1-512gb-wifi-5g-didongviet.jpg'),
(17, 'Huawei Tab T3 8.0', 3760000, 3590000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/24/t3-tab-8.0.png', 'Huawei Tab T3 8.0 là một chiếc máy tính bảng giá rẻ đến từ Huawei, sở hữu rất nhiều ưu điểm đáng giá, nổi trội nhất là cấu hình rất tốt trong phân khúc. Nếu bạn đang tìm kiếm một chiếc tablet cỡ nhỏ, đa năng vừa có thể xem phim giải trí, lướt web, hay chơi game… với một mức giá mềm thì MediaPad T3-8 của Huawei là một sự lựa chọn vô cùng hợp lý. Ngoại hình của Huawei Tab T3 8.0 chắc chắn sẽ khiến người dùng “yêu ngay từ cái nhìn đầu tiên”. Máy sở hữu kiểu dáng nhỏ gọn, thanh mảnh, nhưng cũng khá cứng cáp và sang trọng nhờ lớp vỏ làm bằng kim loại, khác biệt hoàn toàn với các sản phẩm dùng vỏ nhựa cùng phân khúc. Máy có độ dày chỉ khoảng 7.5mm tương đương với một chiếc smartphone cao cấp, các góc máy được bo tròn nhẹ và tinh tế, mặt lưng có thiết kế cong nhẹ giúp ôm sát tay người dùng hơn. Trọng lượng của máy vào khoảng 350 gram phù hợp và không gây khó khăn khi cầm tay lướt web, hoặc mang vác.', 3, 'https://cdn.tgdd.vn/Products/Images/522/110578/huawei-mediapad-t3-80-145221-035250.jpg', 'https://salt.tikicdn.com/cache/w1200/media/catalog/product/5/_/5.u5488.d20170920.t143122.188879.jpg'),
(18, 'Huawei MediaPad M5 Lite', 7860000, 6990000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/23/Huawei%20MediaPad%20M5%20Lite.png', 'Huawei MediaPad M5 Lite sở hữu màn hình IPS Full HD 10.1 inch. Màn hình kích thước lớn chắc chắn sẽ giúp bạn thoải mái xem phim, chơi game, làm việc... Độ phân giải 1920x1200 pixels cũng sẽ mang đến hình ảnh hiển thị sắc nét, đúng chuẩn. Bên cạnh đó, máy còn được trang bị công nghệ Huawei ClaiVu nhằm tăng độ sáng và độ nét cho hình ảnh. Huawei đã lựa chọn kim loại nguyên khối để chế tác nên MediaPad M5 Lite. Không những vậy, các cạnh của máy cũng được thiết kế bo tròn nhằm mang đến cảm giác thoải mái cho người sử dụng. Thiết kế này cũng tăng thêm tính thẩm mỹ, vẻ đẹp sang trọng cho sản phẩm. Mặt lưng phía sau được làm từ kính cong 2,5D giúp cầm nắm dễ dàng hơn. Với độ dày chỉ 7,7mm, bạn có thể cho chiếc máy tính bảng này vào túi và mang đến bất kỳ đâu.', 3, 'https://cdn.tgdd.vn/Files/2018/09/29/1121014/mediapad-1_1280x720-800-resize.jpg', 'https://photo-cms-sggp.zadn.vn/Uploaded/2021/yfsgf/2019_12_04/hinh03_jixv.png'),
(19, 'Apple iPad Air 10.9\" - Chính Hãng', 15800000, 14990000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/04/10-9-wifi-64.png', 'Trong những năm qua, Apple luôn nỗ lực nâng cấp các sản phẩm của mình. Các dòng máy tính bảng của Apple không chỉ được hoàn thiện về thiết kế mà hiệu suất sử dụng cũng được tối đa hóa. Chiếc iPad Air 10.9 inch (2020) – Wifi – 64GB chính hãng được nhà sản xuất trang bị con chip A14 Bionic hiện đại. Đây là con chip đầu tiên của Apple được sản xuất trên tiến trình 5nm. Với 11.8 tỷ bóng bán dẫn và cấu trúc 6 nhân, con chip A14 Bionic đã tăng hiệu suất hơn 40% so với thế hệ cũ. 14 Bionic cũng được tích hợp công nghệ Neutral Engine cho khả năng xử lý đồ họa tốt hơn khi sử dụng.', 3, 'https://hoanghamobile.com/Uploads/Content/2020/09/17/apple--may-tinh-bang-apple-ipad-air-10-9-2020-wifi-64gb-chinh-hang-apple-viet-nam-3.jpg', 'https://minhtuanmobile.com/wp-content/uploads/2020/06/Apple_iPadOS_Today-View_060319_big.large_.jpg'),
(20, 'iPad Air 3 - 10.5\" - 64GB', 14200000, 12690000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/16/Apple-iPad-Air-3.png', 'Sở hữu màn hình kích thước 10.5 inches, một kích thước nằm giữa iPad tiêu chuẩn – 9.7 inches và iPad Pro – 11 inches. Độ phân giải màn hình là 2224 x 1668 pixels, mật độ điểm ảnh 264 ppi, hỗ trợ công nghệ True Tone.\r\niPad Air 2019 được tối ưu tốt phần khung bezel ở cả cạnh trên và dưới dành cho camera và nút Home touch ID. Có thể nhận thấy rõ ràng phần viền bezel trên iPad Air 2019 mỏng hơn nhiều so với iPad tiêu chuẩn, từ đó giúp tỷ lệ hiển thị màn hình lớn hơn, cho trải nghiệm người dùng tuyệt vời hơn.', 3, 'https://cellphones.com.vn/sforum/wp-content/uploads/2020/03/chuong-trinh-sua-chua-cho-iPad-Air-3-1.jpg', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/ipad-air-3-5.jpg'),
(21, 'Samsung Galaxy Tab S6 Lite', 9290000, 8190000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/16/Tab-S6-Lite.png', 'Samsung hiện là một trong những nhà sản xuất điện thoại thông minh hàng đầu trên thị trường. Bên cạnh những mẫu smartphone với thiết kế thời thượng và hiệu năng vượt trội, gã khổng lồ công nghệ Hàn Quốc còn phát hành dòng sản phẩm máy tính bảng mang tên Galaxy Tab. Mới đây, hãng đã cho ra mắt máy tính bảng Galaxy Tab S6 Lite. Đây được coi là phiên bản rút bọn của dòng Galaxy Tab S6 đã “làm mưa làm gió” trong thời gian trước. Samsung hướng đến những khách hàng ưa thích máy tính bảng Galaxy Tab S6 nhưng chỉ cần chi trả cho mức giá phải chăng hơn. Hãy cùng Hoàng Hà Mobile khám phá xem máy tính bảng Samsung Galaxy Tab S6 Lite chính hãng có những ưu điểm vượt trội nào.', 3, 'https://cdn.tgdd.vn/Products/Images/522/219912/samsung-galaxy-tab-s6-lite1-1.jpg', 'https://cellphones.com.vn/sforum/wp-content/uploads/2020/04/samsung-galaxy-tab-s6-lite-gia-ban-face-960x505.jpg'),
(22, 'Samsung Galaxy Tab A8 Plus', 6900000, 6390000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/22/Samsung-Galaxy-Tab-A8-Plus-2019---P205---Ch%C3%ADnh-h%C3%A3ng.png', 'Samsung Galaxy Tab A8 Plus  là chiếc máy tính bảng sở hữu thiết kế bắt mắt, trọng lượng nhẹ có thể mang theo đi chơi hoặc đi làm. Thông số kỹ thuật tầm trung nhưng vẫn đáp ứng tốt các tác vụ thông thường. Siêu mỏng, siêu nhẹ là hai đặc điểm nổi bật riêng của Galaxy Tab A Plus. Do toàn bộ khung được làm từ nhựa nguyên khối chứ không phải kim loại hay kính nên trọng lượng máy nhẹ hơn đáng kể. Với độ dày chỉ 8.9mm, bạn có thể dễ dàng cầm máy bằng một tay. Vì rất nhẹ và nhỏ gọn nên Galaxy Tab A8 Plus luôn có khả năng đồng hành cùng bạn trong mọi chuyến công tác hoặc đi chơi xa.', 3, 'https://cdn.tgdd.vn/Products/Images/522/200631/samsung-galaxy-tab-a8-plus-p205-1.jpg', 'https://cdn.tgdd.vn/Products/Images/522/200631/samsung-galaxy-tab-a8-plus-p205-3.jpg'),
(23, 'Xiaomi Mi Watch Lite - Chính hãng', 1890000, 1450000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2021/01/22/watch-live.png', 'Đồng hồ thông minh Xiaomi Mi Watch Lite gây ấn tượng với người dùng bởi thiết kế độc đáo. Thay vì màn hình tròn như thế hệ trước thì Mi Watch Lite được trang bị mặt vuông với 4 góc bo tròn mềm mại. Bên cạnh đó, khung viền của đồng hồ được làm bằng nhôm chắc chắn mang đến sự sang trọng và bền bỉ vượt trội. Dây đeo chất liệu silicon dẻo và mềm đi kèm sản phẩm mang lại cảm giác êm ái và dễ chịu khi đeo trên tay. Người dùng có thể lựa chọn ba phiên bản màu bao gồm trắng, đen và xanh dương với thân máy và dây đeo đồng màu. \r\nBên cạnh phải được trang bị nút bấm vật lý duy nhất giúp người dùng có thể dễ dàng điều chỉnh thiết bị.', 4, 'https://genk.mediacdn.vn/139269124445442048/2021/2/3/dscf6152-1612361638037657509805.jpg', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/xiaomi-mi-watch-lite-3.jpg'),
(24, 'Huami Amazfit GTR 2 Leather', 5100000, 4290000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2021/01/22/gtr-2-classic.png', 'Huami Amazfit GTR 2 sở hữu một thiết kế cực kì sang trọng với phần khung viền được hoàn thiện hoàn toàn bằng thép không gỉ cao cấp nhất. Việc được hoàn thiện bằng chất liệu này mang tới một độ bền hoàn hảo dành cho thiết bị. Không chỉ vậy nó còn thể hiện được độ sang trọng và quý phái, tinh tế của Huami Amazfit GTR 2, khiến cho mẫu đồng hồ này không chỉ là một chiếc đồng hồ đơn thuần mà còn là một phụ kiện trang sức giá trị.', 4, 'https://mimaxstore.com/wp-content/uploads/2021/01/GTR-2-title.png', 'https://bizweb.dktcdn.net/100/326/151/products/amazfit-gtr-2-ufficiale-design-immagini-specifiche-prezzo-uscita-01.jpg'),
(25, 'Apple Watch SE (4G)', 10200000, 9090000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2021/01/12/watch-se-lte-silver-loop.png', 'Không chỉ mang trên mình một thiết kế thời thượng, hiện đại, màn hình Retina sắc nét cùng hàng loạt tính năng hữu ích khác mà Apple Watch SE phiên bản hỗ trợ Cellular còn có khả năng kết nối Internet độc lập không cần tới bất kì một chiếc điện thoại nào. Apple Watch SE GPS Cellular 44mm sở hữu một thiết kế viền nhôm cực kì bóng bẩy cùng với phần dây đeo được làm từ chất liệu vải bền bỉ, khỏe khoắn nhưng cũng không kém phần thời trang. Đeo Apple Watch SE trên tay, bạn sẽ có một cảm giác cực kì thoải mái, không bị cấn, bị đau hay quá khó chịu bất kể là bạn có đeo trong một thời gian dài. Màn hình Retina với phần viền đạt vát mỏng tốt đa, mang tới một thiết kế năng động, trẻ trung, cực kì tinh tế.', 4, 'https://hoanghamobile.com/Uploads/2021/01/26/apple-watch-se_637472497992150119.jpg', 'https://cdn.tgdd.vn/Products/Images/7077/229058/apple-watch-se-lte-40mm-vien-nhom-day-cao-su-240920-1009355.jpg'),
(26, 'Huawei Watch Fit - Chính hãng', 2790000, 2490000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/05/huawei-watch-fit.png', 'Nhìn chung, thiết kế của chiếc đồng hồ thông minh Huawei Watch Fit chính hãng trông khá giống với Apple Watch nhưng màn hình được kéo dài hơn. Đồng hồ có kích thước 46 mm x 30 mm x 10.7 mm và trọng lượng chỉ khoảng 34 g nên khi đeo không hề có cảm giác nặng tay. Phần dây đeo làm từ chất liệu silicon và có thể dễ dàng thay thế. Sản phẩm có sẵn tùy chọn màu steel. Với thiết kế nhỏ gọn nhưng vẫn nổi bật, chiếc Huawei Watch Fit sẽ là một sự lựa chọn của bạn. ', 4, 'https://huaweiviet.vn/wp-content/uploads/2020/08/Dong-ho-huawei-watch-fit-chinh-hang-21.jpg', 'https://huaweiviet.vn/wp-content/uploads/2020/08/Dong-ho-huawei-watch-fit-chinh-hang-06-768x512.jpg'),
(27, 'Apple Watch SE GPS - Chính hãng (VN/A)', 7900000, 7090000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/04/se-40.png', 'Apple Watch SE 40mm GPS mang trên mình một thiết kế thời thượng, cực kì năng động và cực kì cá tính, đậm chất thời trang như các thế hệ Apple Watch trước đây mà “Táo khuyết” đã dày công thiết kế. Đặc biệt, chất liệu dây đeo của Apple Watch SE được hoàn thiện từ chất liệu silicone dẻo, giúp dây đeo này có độ đàn hồi tốt, nhờ đó người dùng có thể đeo thiết bị trong cả một ngày dài mà không lo bị mỏi, vướng hoặc đau tay. Và chất liệu silicone này cũng sẽ giúp cho người dùng có thể dễ dàng vệ sinh cũng như hạn chế bụi bẩn bám vào dây đeo', 4, 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/apple-watch-se-6.jpg', 'https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/QuanLNH2/apple-watch-se-1.jpg'),
(28, 'Samsung Galaxy Fit 2 (R220)', 790000, 690000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/30/image-removebg-preview-23.png', 'Đồng hồ thông minh Samsung Galaxy Fit 2 là phụ hiện với nhiều tính năng hiện đại nhưng giá thành lại tương đối rẻ. Chiếc đồng hồ này có thời lượng sử dụng lên tới 15 ngày nên không cần phải lo sạc pin liên tục. Samsung Galaxy Fit 2 thực sự rất nhỏ gọn vì chỉ nặng khoảng 30g. Khi đeo trên tay không hề có cảm giác nặng nề hay vướng víu. Mặt đồng hồ có kích thước 1.5 inch kết hợp cùng dây đeo silicon ôm vào cổ tay. Hai phiên bản màu sắc đen và đỏ đều rất cá tính, năng động và phù hợp với đa số khách hàng. \r\nSamsung đã trang bị màn hình AMOLED cho đồng hồ nhằm giúp cho màu sắc hiển thị sống động, độ tương phản cao. Đặc biệt loại màn hình này tiết kiệm điện năng hơn rất nhiều.', 4, 'https://didongviet.vn/pub/media/wysiwyg/V_ng_eo_tay_th_ng_minh_Samsung_Galaxy_Fit_2SM-R220.jpg', 'https://cdn.tgdd.vn/Products/Images/7077/231313/Kit/samsung-galaxy-fit2-do-note-1.jpg'),
(29, 'Apple Watch Series 6 (GPS)', 11400000, 10190000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/04/s6-40.png', 'Apple Watch Series 6 có thiết kế tổng thể hình chữ nhật bo cong tương tự dòng thế hệ tiền nhiệm. Phần viền của chiếc đồng hồ này được hoàn thiện từ nhôm nhám Series 7000 có độ bền tối ưu, đem lại cảm giác chắc chắn và cứng cáp trên cổ tay bạn. Với nhiều các phiên bản màu khác nhau bao gồm xám, bạc và vàng hồng. Mặt trước của sản phẩm là màn hình Retina được làm tràn viền. Nhờ kính cường lực bảo vệ, bạn sẽ không cần lo lắng về những vết xước trong quá trình sử dụng.', 4, 'https://cdn.tgdd.vn/Products/Images/7077/229055/Slider/apple-watch-s6-44mm-vien-nhom-day-cao-su-221020-1221140.jpg', 'https://www.duchuymobile.com/images/thumbnails/480/266/detailed/36/can-canh-may_5rs3-la.jpg'),
(30, 'Apple Watch Series 6 - Dây thép', 18000000, 16990000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/04/s6-40-lte-stan-milan.png', 'Apple Watch Series 6 (4G) 40mm viền thép dây thép sở hữu một bộ vỏ vói viền thép cưc kì sang trọng và bóng bẩy cùng phần dây đeo cũng được hoàn thiện từ chất liệu thép bền bỉ. Đặc biệt phần dây thép này được thiết kế theo kiểu dạng các sợi thép đan xen lại với nhau, vừa tạo nên cảm giác bền bỉ, chắc chắn, lại vẫn toát lên được vẻ sang trọng vốn có của thiết bị. Ngoài ra thiết kế dây đẹo dạng này cũng tạo nên sự thoải mái và chống thấm mồ hôi dành cho người dùng. Màn hình Retina với phần viền benzel giờ đây đã được Apple vát mỏng tối đa, mang tới một thiết kế cực kì tinh tế và hiện đại.', 4, 'https://hoanghamobile.com/Uploads/2021/02/22/s6-4g-44-2.jpg', 'https://cdn.tgdd.vn/Files/2020/07/30/1275239/so-sanh-apple-watch-nhom-va-thep-nen-mua-loai-nao-2.jpg'),
(31, 'MacBook Air 13\" (2020) - 256GB - Chính Hãng', 22890000, 21890000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/09/16/Macbook-air.png', 'MacBook Air 13 2020 có độ phân giải màn hình 2560 x 1600 mang lại tới bốn triệu điểm ảnh, những nội dung cho ra có độ chi tiết cao, văn bản sắc nét và rõ ràng. Không thiểu thiếu là công nghệ True Tone giúp tự động điều chỉnh điểm trắng của màn hình để phù hợp với nhiệt độ màu môi trường xung quanh. \r\nMàn hình của MacBook Air 13 2020 là dạng màn hình kính nên đôi khi có thể hơi loá một chút khi ra ngoài nắng, nhưng khi kết hợp với kiểu thiết kế viền bezels siêu mỏng thì đây là bộ đôi cực ăn ý cho trải nghiệm điện ảnh hoàn hảo. MacBook Air 13 2020 phiên bản 256GB sử dụng vi xử lý Core i3 (2 lõi xung nhịp 1.1GHz), có thể chạy tối đa lên tới 3.2GHz cùng bộ nhớ đệm 4MB L3.', 2, 'https://24hstore.vn/upload_images/images/SEO/macbook-air-13-inch-2020-1.jpg', 'https://cdn.tgdd.vn/Products/Images/44/220173/Slider/apple-macbook-air-2020-290720-0215180.jpg'),
(32, 'MacBook Air 13\" - M1 256GB 2020 - Chính hãng', 24000000, 23300000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/17/macmoi256.png', 'Là một chiếc ultrabook cao cấp, MacBook Air mang trên mình tất cả thẩm mỹ tinh tế của Apple trong thiết kế sản phẩm. MacBook Air M1 được hoàn thiện từ chất liệu nhôm nguyên khối, không chỉ nhẹ mà còn rất sang trọng. Trọng lượng chỉ 1,29kg cũng giúp người dùng tiện lợi mang đi sử dụng ở mọi nơi. Khách hàng có thể lựa chọn ba phiên bản màu khác nhau phù hợp với cả tính mỗi người, bao gồm xám, bạc và vàng. MacBook Air được trang bị màn hình Retina kích thước 13,3 inch có độ phân giải 2560x1600. Màn hình này hỗ trợ công nghệ True Tone, dải màu rộng P3 và có độ sáng lên tới 400 nit, đem lại màu sắc và độ tương phản tốt ngay cả khi làm việc trong không gian có ánh sáng mạnh như ngoài trời nắng.', 2, 'https://didongviet.vn/pub/media/wysiwyg/Macbook/macbook-air-m1/macbook-air-m1-2020-256gb-danh-gia.png', 'https://fptshop.com.vn/uploads/images/tin-tuc/128363/Originals/macbook-air-m1-2020-07.jpg'),
(33, 'Vivo V20 - 8GB/128GB - Chính hãng', 7050000, 6050000, 'https://hoanghamobile.com/i/productlist/ts/Uploads/2020/11/09/v20.png', '♦ Kích thước màn hình: 6.44 inches\r\n♦ Độ phân giải: 1080 x 2400 pixels\r\n♦ Hệ điều hành: Android 11, Funtouch 10.5\r\n♦ Chipset: Snapdragon 720G (8 nm)\r\n♦ Dung lượng RAM: 8 GB\r\n♦ Bộ nhớ trong: 128 GB\r\n♦ Camera sau: 64 MP\r\n♦ Camera trước: 44 MP\r\n♦ Pin: Li-Po 4000 mAh, sạc nhanh 33W\r\n♦ Loại CPU: Octa-core (2x2.3 GHz Kryo 465 Gold & 6x1.8 GHz Kryo 465 Silver)\r\n♦ GPU: Adreno 618\r\n♦ Kích thước: 161.3 x 74.2 x 7.4 mm\r\n♦ Trọng lượng: 171 g\r\n♦ Quay video: 4K@30fps, 1080p@30/60fps, gyro-EIS\r\n♦ Thẻ SIM: 2 SIM (Nano-SIM)\r\n♦ Khe cắm thẻ nhớ: microSDXC\r\n♦ Wi-Fi: Wi-Fi 802.11 a/b/g/n/ac, dual-band, Wi-Fi Direct, hotspot\r\n♦ Bluetooth: 5.1, A2DP, LE\r\n♦ GPS: A-GPS, GLONASS, GALILEO, BDS', 1, 'https://img.websosanh.vn/v2/users/root_product/images/dien-thoai-vivo-v20-8gb/6bzjv1fvowi2h.jpg', 'https://cdn.nguyenkimmall.com/images/companies/_1/Content/dien-thoai/Vivo/dien-thoai-vivo-v20-8gb-128gb-xanh-hong-sac-sieu-toc.png');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `madonhang` (`madonhang`);

--
-- Chỉ mục cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaithietbi`
--
ALTER TABLE `loaithietbi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Chỉ mục cho bảng `thietbi`
--
ALTER TABLE `thietbi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT cho bảng `loaithietbi`
--
ALTER TABLE `loaithietbi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `thietbi`
--
ALTER TABLE `thietbi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

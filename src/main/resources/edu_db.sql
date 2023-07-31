CREATE DATABASE  IF NOT EXISTS `edu_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `edu_db`;
-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: localhost    Database: edu_db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bai_viet`
--

DROP TABLE IF EXISTS `bai_viet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bai_viet` (
  `bai_vietid` int NOT NULL AUTO_INCREMENT,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  `noi_dung_ngan` varchar(1000) DEFAULT NULL,
  `ten_bai_viet` varchar(50) DEFAULT NULL,
  `ten_tac_gia` varchar(50) DEFAULT NULL,
  `thoi_gian_tao` date DEFAULT NULL,
  `chu_deid` int DEFAULT NULL,
  `tai_khoanid` int DEFAULT NULL,
  PRIMARY KEY (`bai_vietid`),
  KEY `fk_baiviet_chude` (`chu_deid`),
  KEY `fk_baiviet_taikhoan` (`tai_khoanid`),
  CONSTRAINT `fk_baiviet_chude` FOREIGN KEY (`chu_deid`) REFERENCES `chu_de` (`chu_deid`),
  CONSTRAINT `fk_baiviet_taikhoan` FOREIGN KEY (`tai_khoanid`) REFERENCES `tai_khoan` (`tai_khoanid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bai_viet`
--

LOCK TABLES `bai_viet` WRITE;
/*!40000 ALTER TABLE `bai_viet` DISABLE KEYS */;
INSERT INTO `bai_viet` VALUES (1,'/image/baiviet','Nội Dung','Nội Dung Ngắn','Bài Viết','Cộng tác viên','2023-07-31',1,2),(3,'/image/baiviet','Nội Dung','Nội Dung Ngắn','Bài Viết 1','Cộng tác viên','2023-07-31',1,2);
/*!40000 ALTER TABLE `bai_viet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chu_de`
--

DROP TABLE IF EXISTS `chu_de`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chu_de` (
  `chu_deid` int NOT NULL AUTO_INCREMENT,
  `noi_dung` varchar(255) DEFAULT NULL,
  `ten_chu_de` varchar(50) DEFAULT NULL,
  `loai_bai_vietid` int DEFAULT NULL,
  PRIMARY KEY (`chu_deid`),
  KEY `fk_chude_loaibaiviet` (`loai_bai_vietid`),
  CONSTRAINT `fk_chude_loaibaiviet` FOREIGN KEY (`loai_bai_vietid`) REFERENCES `loai_bai_viet` (`loai_bai_vietid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chu_de`
--

LOCK TABLES `chu_de` WRITE;
/*!40000 ALTER TABLE `chu_de` DISABLE KEYS */;
INSERT INTO `chu_de` VALUES (1,'Con Trỏ','Kiến Thức Về Con Trỏ trong C',2);
/*!40000 ALTER TABLE `chu_de` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dang_ky_hoc`
--

DROP TABLE IF EXISTS `dang_ky_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dang_ky_hoc` (
  `dang_ky_hocid` int NOT NULL AUTO_INCREMENT,
  `ngay_bat_dau` date DEFAULT NULL,
  `ngay_dang_ky` date DEFAULT NULL,
  `ngay_ket_thuc` date DEFAULT NULL,
  `hoc_vienid` int DEFAULT NULL,
  `khoa_hocid` int DEFAULT NULL,
  `tai_khoanid` int DEFAULT NULL,
  `tinh_trang_hocid` int DEFAULT NULL,
  PRIMARY KEY (`dang_ky_hocid`),
  KEY `fk_dangkyhoc_hocvien` (`hoc_vienid`),
  KEY `fk_dangkyhoc_khoahoc` (`khoa_hocid`),
  KEY `fk_dangkyhoc_taikhoan` (`tai_khoanid`),
  KEY `fk_dangkyhoc_tinhtranghoc` (`tinh_trang_hocid`),
  CONSTRAINT `fk_dangkyhoc_hocvien` FOREIGN KEY (`hoc_vienid`) REFERENCES `hoc_vien` (`hoc_vienid`),
  CONSTRAINT `fk_dangkyhoc_khoahoc` FOREIGN KEY (`khoa_hocid`) REFERENCES `khoa_hoc` (`khoa_hocid`),
  CONSTRAINT `fk_dangkyhoc_taikhoan` FOREIGN KEY (`tai_khoanid`) REFERENCES `tai_khoan` (`tai_khoanid`),
  CONSTRAINT `fk_dangkyhoc_tinhtranghoc` FOREIGN KEY (`tinh_trang_hocid`) REFERENCES `tinh_trang_hoc` (`tinh_trang_hocid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dang_ky_hoc`
--

LOCK TABLES `dang_ky_hoc` WRITE;
/*!40000 ALTER TABLE `dang_ky_hoc` DISABLE KEYS */;
INSERT INTO `dang_ky_hoc` VALUES (1,'2023-07-25','2023-07-20','2023-07-31',1,1,1,1),(2,'2023-07-20','2023-07-15','2023-07-25',2,2,2,2),(3,'2023-07-15','2023-07-10','2023-07-20',3,2,3,4),(4,'2023-07-15','2023-07-10','2023-07-20',4,1,NULL,3);
/*!40000 ALTER TABLE `dang_ky_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoc_vien`
--

DROP TABLE IF EXISTS `hoc_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoc_vien` (
  `hoc_vienid` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `phuong_xa` varchar(255) DEFAULT NULL,
  `quan_huyen` varchar(255) DEFAULT NULL,
  `so_dien_thoai` varchar(255) DEFAULT NULL,
  `so_nha` varchar(255) DEFAULT NULL,
  `tinh_thanh` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hoc_vienid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoc_vien`
--

LOCK TABLES `hoc_vien` WRITE;
/*!40000 ALTER TABLE `hoc_vien` DISABLE KEYS */;
INSERT INTO `hoc_vien` VALUES (1,'abc1@example.com','/image/anh1.jpg','Kim Soo Hook','2000-06-16','Xuan Dinh','Bac Tu Liem','0123456789','123 ','Ha Noi'),(2,'abc@example.com','/image/anh2.jpg','Lee Min Ho ','2000-06-16','Xuan Dinh','Bac Tu Liem','012345678','123 ','Ha Noi'),(3,'abc3@example.com','/image/anh3.jpg','Kim Bum','2000-06-16','Xuan Dinh','Bac Tu Liem','0213456789','123 ','Ha Noi'),(4,'abc4@example.com','/image/anh4.jpg','Kim Tan','2000-06-16','Xuan Dinh','Bac Tu Liem','0214356789','123 ','Ha Noi'),(6,'abcd@example.com','image.jpg','Nguyen Van A','2000-01-01','Phuc Xa','Ba Dinh','0123456798','123','Ha Noi'),(7,'abc7@example.com','image/anh7.jpg','Nguyen Van B','2000-01-01','Phuc Xa','Ba Dinh','0123457698','123','Ha Noi'),(9,'bdat1606@gmail.com','image/anh8.jpg','Bui Huu Dat','2000-06-16','Phuc Xa','Ba Dinh','0338612896','123','Ha Noi');
/*!40000 ALTER TABLE `hoc_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoa_hoc`
--

DROP TABLE IF EXISTS `khoa_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoa_hoc` (
  `khoa_hocid` int NOT NULL AUTO_INCREMENT,
  `gioi_thieu` varchar(255) DEFAULT NULL,
  `hinh_anh` varchar(255) DEFAULT NULL,
  `hoc_phi` double DEFAULT NULL,
  `noi_dung` varchar(255) DEFAULT NULL,
  `so_hoc_vien` int DEFAULT NULL,
  `so_luong_mon` int DEFAULT NULL,
  `ten_khoa_hoc` varchar(50) DEFAULT NULL,
  `thoi_gian_hoc` int DEFAULT NULL,
  `loai_khoa_hocid` int DEFAULT NULL,
  PRIMARY KEY (`khoa_hocid`),
  KEY `fk_khoahoc_loaikhoahoc` (`loai_khoa_hocid`),
  CONSTRAINT `fk_khoahoc_loaikhoahoc` FOREIGN KEY (`loai_khoa_hocid`) REFERENCES `loai_khoa_hoc` (`loai_khoa_hocid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoa_hoc`
--

LOCK TABLES `khoa_hoc` WRITE;
/*!40000 ALTER TABLE `khoa_hoc` DISABLE KEYS */;
INSERT INTO `khoa_hoc` VALUES (1,'Giới Thiệu','/image/python.png',20000000,'Nội Dung',22,4,'Python',2,1),(2,'Giới Thiệu','/image/java.png',20000000,'Nội Dung',20,4,'JAVA',2,1),(3,'Giới Thiệu','/image/java.png',20000000,'Nội Dung',20,4,'.NET',2,1),(4,'Giới Thiệu','/image/react.png',20000000,'Nội Dung',20,4,'ReactJS',2,2),(5,'Giới Thiệu','/image/vue.png',20000000,'Nội Dung',20,4,'VueJS',2,2),(7,'Giới Thiệu','/image/angular.png',20000000,'Nội Dung',20,4,'AngularJS',2,2),(8,'Giới Thiệu','/image/flutter.png',20000000,'Nội Dung',20,4,'Flutter',2,2);
/*!40000 ALTER TABLE `khoa_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_bai_viet`
--

DROP TABLE IF EXISTS `loai_bai_viet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_bai_viet` (
  `loai_bai_vietid` int NOT NULL AUTO_INCREMENT,
  `ten_loai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`loai_bai_vietid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_bai_viet`
--

LOCK TABLES `loai_bai_viet` WRITE;
/*!40000 ALTER TABLE `loai_bai_viet` DISABLE KEYS */;
INSERT INTO `loai_bai_viet` VALUES (1,'Thông Báo'),(2,'Bài Giảng');
/*!40000 ALTER TABLE `loai_bai_viet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_khoa_hoc`
--

DROP TABLE IF EXISTS `loai_khoa_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_khoa_hoc` (
  `loai_khoa_hocid` int NOT NULL AUTO_INCREMENT,
  `ten_loai_khoa_hoc` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`loai_khoa_hocid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_khoa_hoc`
--

LOCK TABLES `loai_khoa_hoc` WRITE;
/*!40000 ALTER TABLE `loai_khoa_hoc` DISABLE KEYS */;
INSERT INTO `loai_khoa_hoc` VALUES (1,'Back-End'),(2,'Front-End ');
/*!40000 ALTER TABLE `loai_khoa_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen_han`
--

DROP TABLE IF EXISTS `quyen_han`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen_han` (
  `quyen_hanid` int NOT NULL AUTO_INCREMENT,
  `ten_quyen_han` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`quyen_hanid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen_han`
--

LOCK TABLES `quyen_han` WRITE;
/*!40000 ALTER TABLE `quyen_han` DISABLE KEYS */;
INSERT INTO `quyen_han` VALUES (1,'Admin'),(2,'Cộng Tác Viên'),(3,'Quản Lý Giáo Dục');
/*!40000 ALTER TABLE `quyen_han` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `tai_khoanid` int NOT NULL AUTO_INCREMENT,
  `mat_khau` varchar(50) DEFAULT NULL,
  `tai_khoan` varchar(50) DEFAULT NULL,
  `ten_nguoi_dung` varchar(50) DEFAULT NULL,
  `quyen_hanid` int DEFAULT NULL,
  PRIMARY KEY (`tai_khoanid`),
  KEY `fk_taikhoan_quyenhan` (`quyen_hanid`),
  CONSTRAINT `fk_taikhoan_quyenhan` FOREIGN KEY (`quyen_hanid`) REFERENCES `quyen_han` (`quyen_hanid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (1,'Bui16620#','LA','Admin',1),(2,'Bui16620#','LA_CTV1','CTV1',2),(3,'Bui16620#','LA_QL1','QuanLy1',3),(5,'Bui16620#','LT_QL2','Quản Lý 2',3);
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tinh_trang_hoc`
--

DROP TABLE IF EXISTS `tinh_trang_hoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tinh_trang_hoc` (
  `tinh_trang_hocid` int NOT NULL AUTO_INCREMENT,
  `ten_tinh_trang` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`tinh_trang_hocid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tinh_trang_hoc`
--

LOCK TABLES `tinh_trang_hoc` WRITE;
/*!40000 ALTER TABLE `tinh_trang_hoc` DISABLE KEYS */;
INSERT INTO `tinh_trang_hoc` VALUES (1,'Chờ Duyệt'),(2,'Đang Học'),(3,'Chưa Hoàn Thành'),(4,'Học Xong');
/*!40000 ALTER TABLE `tinh_trang_hoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'edu_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-31  8:45:37

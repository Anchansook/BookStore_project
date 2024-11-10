-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------
-- 온라인 서점 데이터베이스

-- -----------------------------------------------------
-- Schema book_store
--
-- 온라인 서점 데이터베이스
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `book_store` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `book_store` ;

-- -----------------------------------------------------
-- Table `book_store`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Users` (
  `user_id` VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
  `user_name` VARCHAR(10) NOT NULL COMMENT '사용자 이름',
  `user_email` VARCHAR(30) NOT NULL COMMENT '사용자 이메일',
  `user_password` VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
COMMENT = '사용자 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT COMMENT '주문 아이디',
  `user_id` VARCHAR(45) NOT NULL COMMENT '사용자 아이디 (Users 테이블에서 user_id 참조)',
  `order_date` DATETIME NOT NULL COMMENT '주문 날짜(시간도 포함)',
  `order_state` VARCHAR(20) NOT NULL DEFAULT '결제 대기' COMMENT '주문 상태 (‘결제 대기’, ‘결제 완료’, ‘배송 중’, ‘발송 완료’)',
  `book_count` INT NOT NULL COMMENT '책 수량',
  `total_price` INT NOT NULL COMMENT '주문 총가격',
  PRIMARY KEY (`order_id`),
  INDEX `orders_fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `orders_fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `book_store`.`Users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '주문 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT COMMENT '카테고리 아이디',
  `category_name` VARCHAR(30) NOT NULL COMMENT '카테고리 이름',
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
COMMENT = '카테고리 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Books` (
  `book_id` INT NOT NULL COMMENT '책 아이디 (일련번호)',
  `book_name` VARCHAR(100) NOT NULL COMMENT '책 이름',
  `book_author` VARCHAR(100) NOT NULL COMMENT '책 저자',
  `book_price` INT NOT NULL COMMENT '책 가격',
  `discount_rate` INT NULL COMMENT '책 할인율 (할인이 없을 수도 있음)',
  `created_at` VARCHAR(30) NOT NULL COMMENT '책 등록일',
  `updated_at` VARCHAR(30) NULL COMMENT '책 수정일 (수정이 없을 수도 있음)',
  `category_id` INT NULL COMMENT '카테고리 아이디 (categories 테이블에서 category_id 참조)',
  PRIMARY KEY (`book_id`),
  INDEX `books_fk_category_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `books_fk_category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `book_store`.`Categories` (`category_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '책 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Order_items` (
  `order_item_id` INT NOT NULL AUTO_INCREMENT COMMENT '주문상품 아이디',
  `order_id` INT NOT NULL COMMENT '주문 아이디 (Orders 테이블 order_id 참조)',
  `book_id` INT NULL COMMENT '책 아이디 (Books 테이블 book_id 참조)',
  `quantity` INT NOT NULL COMMENT '주문 상품 수량',
  `item_price` INT NOT NULL COMMENT '주문상품 가격',
  PRIMARY KEY (`order_item_id`),
  INDEX `order_items_fk_order_id_idx` (`order_id` ASC) VISIBLE,
  INDEX `order_items_fk_book_id_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `order_items_fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `book_store`.`Orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `order_items_fk_book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_store`.`Books` (`book_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '주문 상품 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Reviews` (
  `review_id` INT NOT NULL AUTO_INCREMENT COMMENT '리뷰 아이디',
  `book_id` INT NULL COMMENT '책 아이디 (Books 테이블 book_id 참조)',
  `user_id` VARCHAR(20) NULL COMMENT '유저 아이디 (Users 테이블 user_id 참조)',
  `review_rating` TINYINT NOT NULL COMMENT '리뷰 평점 COMMENT (1, 2, 3, 4, 5)',
  `review_content` VARCHAR(255) NULL COMMENT '리뷰 내용',
  `review_date` DATE NOT NULL COMMENT '리뷰 작성 날짜 (DATE.NOW())',
  PRIMARY KEY (`review_id`),
  INDEX `reviews_fk_book_id_idx` (`book_id` ASC) VISIBLE,
  INDEX `reviews_fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `reviews_fk_book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_store`.`Books` (`book_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `reviews_fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `book_store`.`Users` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '리뷰 테이블';


-- -----------------------------------------------------
-- Table `book_store`.`Book_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`Book_category` (
  `book_category_id` INT NOT NULL AUTO_INCREMENT COMMENT '책 & 카테고리 매핑 아이디',
  `book_id` INT NOT NULL COMMENT '책 아이디 (Books 테이블 book_id 참조)',
  `category_id` INT NOT NULL COMMENT '카테고리 아이디 (Categories 테이블 category_id 참조)',
  PRIMARY KEY (`book_category_id`),
  INDEX `book_category_fk_book_id_idx` (`book_id` ASC) VISIBLE,
  INDEX `book_category_fk_category_id_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `book_category_fk_book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `book_store`.`Books` (`book_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `book_category_fk_category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `book_store`.`Categories` (`category_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '책 & 카테고리 매핑 테이블';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

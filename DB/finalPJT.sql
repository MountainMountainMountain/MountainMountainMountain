-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema finalMountainPJT
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema finalMountainPJT
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `finalMountainPJT` DEFAULT CHARACTER SET utf8 ;
USE `finalMountainPJT` ;

-- -----------------------------------------------------
-- Table `finalMountainPJT`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`User` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(50) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `birth_date` TIMESTAMP NOT NULL,
  `gender` CHAR(1) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `point` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Mountain`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Mountain` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `latitude` INT NOT NULL,
  `longitude` INT NOT NULL,
  `altitude` INT NOT NULL,
  `course` INT NOT NULL DEFAULT 1,
  `difficulty` FLOAT NOT NULL DEFAULT 0,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fee` INT NOT NULL DEFAULT 0,
  `state` VARCHAR(45) NOT NULL,
  `town` VARCHAR(45) NOT NULL,
  `point` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) INVISIBLE,
  UNIQUE INDEX `course_UNIQUE` (`course` ASC) VISIBLE,
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`ChatInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`ChatInfo` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `mountain_serial` INT UNSIGNED NOT NULL,
  `date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_ChatManager_Mountain1_idx` (`mountain_serial` ASC) VISIBLE,
  CONSTRAINT `fk_ChatManager_Mountain1`
    FOREIGN KEY (`mountain_serial`)
    REFERENCES `finalMountainPJT`.`Mountain` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Chat` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chatInfo_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  `content` TEXT NULL,
  `create_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_Chat_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_Chat_ChatInfo1_idx` (`chatInfo_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Chat_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Chat_ChatInfo1`
    FOREIGN KEY (`chatInfo_serial`)
    REFERENCES `finalMountainPJT`.`ChatInfo` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Comment` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `mountain_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  `content` TEXT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `calorie` INT NOT NULL DEFAULT 0,
  `turnaround` INT NOT NULL DEFAULT 0,
  `view_count` INT NOT NULL DEFAULT 0,
  `star` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_Comment_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_Comment_Mountain1_idx` (`mountain_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Mountain1`
    FOREIGN KEY (`mountain_serial`)
    REFERENCES `finalMountainPJT`.`Mountain` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`CommentFile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`CommentFile` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_serial` INT UNSIGNED NOT NULL,
  `comment_serial` INT UNSIGNED NOT NULL,
  `file_name` VARCHAR(500) NOT NULL,
  `file_size` INT NOT NULL,
  `file_content_type` VARCHAR(500) NOT NULL,
  `file_url` VARCHAR(500) NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_CommentFile_Comment1_idx` (`comment_serial` ASC) VISIBLE,
  INDEX `fk_CommentFile_User1_idx` (`user_serial` ASC) VISIBLE,
  CONSTRAINT `fk_CommentFile_Comment1`
    FOREIGN KEY (`comment_serial`)
    REFERENCES `finalMountainPJT`.`Comment` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CommentFile_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Follow` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_serial` INT UNSIGNED NOT NULL,
  `to_serial` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) INVISIBLE,
  INDEX `fk_Follow_User_idx` (`from_serial` ASC) VISIBLE,
  INDEX `fk_Follow_User1_idx` (`to_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Follow_User`
    FOREIGN KEY (`from_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Follow_User1`
    FOREIGN KEY (`to_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Reply` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  `content` TEXT NOT NULL,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_Reply_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_Reply_Comment1_idx` (`comment_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Reply_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reply_Comment1`
    FOREIGN KEY (`comment_serial`)
    REFERENCES `finalMountainPJT`.`Comment` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`ChatUserManager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`ChatUserManager` (
  `serial` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chatInfo_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_ChatUserManager_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_ChatUserManager_ChatInfo1_idx` (`chatInfo_serial` ASC) VISIBLE,
  CONSTRAINT `fk_ChatUserManager_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ChatUserManager_ChatInfo1`
    FOREIGN KEY (`chatInfo_serial`)
    REFERENCES `finalMountainPJT`.`ChatInfo` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- 테이블 `finalMountainPJT`.`User`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`User` (`id`, `password`, `name`, `birth_date`, `gender`, `email`, `point`) VALUES
('user1', 'pass1', 'John Doe', '1990-01-01', 'M', 'john@example.com', 100),
('user2', 'pass2', 'Jane Doe', '1992-05-15', 'F', 'jane@example.com', 150),
('user3', 'pass3', 'Alice Smith', '1985-09-30', 'F', 'alice@example.com', 200),
('user4', 'pass4', 'Bob Johnson', '1988-07-20', 'M', 'bob@example.com', 50),
('user5', 'pass5', 'Charlie Brown', '1994-03-12', 'M', 'charlie@example.com', 300),
('user6', 'pass6', 'Emma Wilson', '1991-11-05', 'F', 'emma@example.com', 75),
('user7', 'pass7', 'Michael Lee', '1987-04-18', 'M', 'michael@example.com', 220),
('user8', 'pass8', 'Sophia Garcia', '1996-08-22', 'F', 'sophia@example.com', 180);

-- 테이블 `finalMountainPJT`.`Mountain`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`Mountain` (`name`, `latitude`, `longitude`, `altitude`, `course`, `difficulty`, `fee`, `state`, `town`, `point`) VALUES
('Mountain A', 37.1234, 127.5678, 1000, 1, 3.5, 5000, 'California', 'Town A', 1200),
('Mountain B', 38.2345, 128.6789, 1500, 2, 4.0, 6000, 'New York', 'Town B', 1500),
('Mountain C', 39.3456, 129.7890, 2000, 3, 4.5, 7000, 'Texas', 'Town C', 1800),
('Mountain D', 40.4567, 130.8901, 2500, 4, 5.0, 8000, 'Florida', 'Town D', 2000),
('Mountain E', 41.5678, 131.9012, 3000, 5, 5.5, 9000, 'Washington', 'Town E', 2200),
('Mountain F', 42.6789, 132.0123, 3500, 6, 6.0, 10000, 'Oregon', 'Town F', 2500),
('Mountain G', 43.7890, 133.1234, 4000, 7, 6.5, 11000, 'Colorado', 'Town G', 2800),
('Mountain H', 44.8901, 134.2345, 4500, 8, 7.0, 12000, 'Arizona', 'Town H', 3000);

-- 테이블 `finalMountainPJT`.`ChatInfo`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`ChatInfo` (`mountain_serial`, `date`) VALUES
(1, '2024-05-17 09:00:00'),
(2, '2024-05-17 10:00:00'),
(3, '2024-05-17 11:00:00'),
(4, '2024-05-17 12:00:00'),
(5, '2024-05-17 13:00:00'),
(6, '2024-05-17 14:00:00'),
(7, '2024-05-17 15:00:00'),
(8, '2024-05-17 16:00:00');

-- 테이블 `finalMountainPJT`.`Chat`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`Chat` (`chatInfo_serial`, `user_serial`, `content`, `create_date`) VALUES
(1, 1, 'Hello from user1', '2024-05-17 09:05:00'),
(2, 2, 'Hi there!', '2024-05-17 10:15:00'),
(3, 3, 'Nice weather for hiking!', '2024-05-17 11:25:00'),
(4, 4, 'Anyone know the trail difficulty?', '2024-05-17 12:35:00'),
(5, 5, 'Let''s meet at the trailhead.', '2024-05-17 13:45:00'),
(6, 6, 'Looking forward to the hike!', '2024-05-17 14:55:00'),
(7, 7, 'Don''t forget water and snacks!', '2024-05-17 15:05:00'),
(8, 8, 'See you all there!', '2024-05-17 16:15:00');

-- 테이블 `finalMountainPJT`.`Comment`에 데이터 이어서 삽입
INSERT INTO `finalMountainPJT`.`Comment` (`title`, `mountain_serial`, `user_serial`, `content`, `calorie`, `turnaround`, `view_count`, `star`) VALUES
('Peaceful hike', 1, 2, 'Enjoyed the tranquility of the forest.', 250, 3, 90, 4),
('Trail markers needed', 2, 3, 'Got lost a few times due to poor signage.', 150, 2, 70, 3),
('Crowded trail', 3, 4, 'Trail was very crowded, recommend going early.', 100, 1, 40, 3),
('Wildlife sightings', 4, 5, 'Saw deer and rabbits along the trail.', 300, 4, 120, 4),
('Trail cleanup', 5, 6, 'Picked up some trash along the trail.', 50, 1, 30, 3),
('Favorite lookout spot', 6, 7, 'Love stopping at this overlook.', 350, 5, 180, 5),
('Trail closures', 7, 8, 'Some trails were closed due to maintenance.', 200, 3, 60, 4),
('Great hike for beginners', 8, 1, 'Took my friends on their first hike, they loved it!', 180, 2, 100, 4);

-- 테이블 `finalMountainPJT`.`CommentFile`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`CommentFile` (`user_serial`, `comment_serial`, `file_name`, `file_size`, `file_content_type`, `file_url`) VALUES
(1, 1, 'hike_photo1.jpg', 1024, 'image/jpeg', 'https://example.com/hike_photo1.jpg'),
(2, 2, 'hike_photo2.jpg', 2048, 'image/jpeg', 'https://example.com/hike_photo2.jpg'),
(3, 3, 'hike_photo3.jpg', 3072, 'image/jpeg', 'https://example.com/hike_photo3.jpg'),
(4, 4, 'hike_photo4.jpg', 4096, 'image/jpeg', 'https://example.com/hike_photo4.jpg'),
(5, 5, 'hike_photo5.jpg', 5120, 'image/jpeg', 'https://example.com/hike_photo5.jpg'),
(6, 6, 'hike_photo6.jpg', 6144, 'image/jpeg', 'https://example.com/hike_photo6.jpg'),
(7, 7, 'hike_photo7.jpg', 7168, 'image/jpeg', 'https://example.com/hike_photo7.jpg'),
(8, 8, 'hike_photo8.jpg', 8192, 'image/jpeg', 'https://example.com/hike_photo8.jpg');

-- 테이블 `finalMountainPJT`.`Follow`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`Follow` (`from_serial`, `to_serial`) VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 1);

-- 테이블 `finalMountainPJT`.`Reply`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`Reply` (`comment_serial`, `user_serial`, `content`, `reg_date`) VALUES
(1, 2, 'Glad you enjoyed it!', '2024-05-17 09:10:00'),
(2, 3, 'Sorry to hear that, hope you found your way!', '2024-05-17 10:20:00'),
(3, 4, 'Agreed, it can get busy on weekends.', '2024-05-17 11:30:00'),
(4, 5, 'Sounds like a fun hike!', '2024-05-17 12:40:00'),
(5, 6, 'Thank you for helping keep the trails clean!', '2024-05-17 13:50:00'),
(6, 7, 'One of the best views around!', '2024-05-17 15:00:00'),
(7, 8, 'Maintenance is important for trail safety.', '2024-05-17 16:10:00'),
(8, 1, 'Glad your friends enjoyed it!', '2024-05-17 17:00:00');

-- 테이블 `finalMountainPJT`.`ChatUserManager`에 데이터 삽입
INSERT INTO `finalMountainPJT`.`ChatUserManager` (`chatInfo_serial`, `user_serial`) VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 1);

-- User 테이블 조회
SELECT * FROM User;

-- Mountain 테이블 조회
SELECT * FROM Mountain;

-- ChatInfo 테이블 조회
SELECT * FROM ChatInfo;

-- Chat 테이블 조회
SELECT * FROM Chat;

-- Comment 테이블 조회
SELECT * FROM Comment;

-- Reply 테이블 조회
SELECT * FROM Reply;

-- CommentFile 테이블 조회
SELECT * FROM CommentFile;

-- Follow 테이블 조회
SELECT * FROM Follow;

-- ChatUserManager 테이블 조회
SELECT * FROM ChatUserManager;

DROP SCHEMA IF EXISTS `finalMountainPJT`;

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
  `latitude` FLOAT NOT NULL,
  `longitude` FLOAT NOT NULL,
  `altitude` INT NOT NULL,
  `course` INT NOT NULL DEFAULT 1,
  `difficulty` FLOAT NOT NULL DEFAULT 0,
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fee` INT NOT NULL DEFAULT 0,
  `state` VARCHAR(45) NOT NULL,
  `town` VARCHAR(45) NOT NULL,
  `point` INT UNSIGNED NOT NULL,
  `weather_NX` INT NULL,
  `weather_NY` INT NULL,
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
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ChatUserManager_ChatInfo1`
    FOREIGN KEY (`chatInfo_serial`)
    REFERENCES `finalMountainPJT`.`ChatInfo` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Chat` (
  `serial` BIGINT UNSIGNED NOT NULL,
  `ChatUserManager_serial` INT UNSIGNED NOT NULL,
  `content` TEXT NULL,
  `create_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_Chat_ChatUserManager1_idx` (`ChatUserManager_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Chat_ChatUserManager1`
    FOREIGN KEY (`ChatUserManager_serial`)
    REFERENCES `finalMountainPJT`.`ChatUserManager` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_Mountain1`
    FOREIGN KEY (`mountain_serial`)
    REFERENCES `finalMountainPJT`.`Mountain` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_CommentFile_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `finalMountainPJT`.`Follow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`Follow` (
  `serial` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `from_serial` INT UNSIGNED NOT NULL,
  `to_serial` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) INVISIBLE,
  INDEX `fk_Follow_User_idx` (`from_serial` ASC) VISIBLE,
  INDEX `fk_Follow_User1_idx` (`to_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Follow_User`
    FOREIGN KEY (`from_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Follow_User1`
    FOREIGN KEY (`to_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Reply_Comment1`
    FOREIGN KEY (`comment_serial`)
    REFERENCES `finalMountainPJT`.`Comment` (`serial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- User 테이블 데이터 삽입
INSERT INTO User (id, password, name, birth_date, gender, email, point)
VALUES
('user1', 'password1', 'John Doe', '1990-01-01', 'M', 'john@example.com', 100),
('user2', 'password2', 'Jane Doe 가나', '1992-05-15', 'F', 'jane@example.com', 150),
('user3', 'password3', 'Alice Smith', '1985-11-20', 'F', 'alice@example.com', 200),
('user4', 'password4', 'Bob Johnson', '1988-03-10', 'M', 'bob@example.com', 120),
('user5', 'password5', 'Charlie Brown', '1995-07-05', 'M', 'charlie@example.com', 180),
('user6', 'password6', 'Emma Watson', '1983-09-30', 'F', 'emma@example.com', 220),
('user7', 'password7', 'Michael Jordan', '1990-02-17', 'M', 'michael@example.com', 300),
('user8', 'password8', 'Taylor Swift', '1989-12-13', 'F', 'taylor@example.com', 250),
('user9', 'password9', 'David Beckham', '1975-05-02', 'M', 'david@example.com', 270),
('user10', 'password10', 'Adele', '1988-05-05', 'F', 'adele@example.com', 320);

-- Mountain 테이블에 한국에 있는 실제 산 데이터 삽입 및 날씨 관측소의 동경과 북위 좌표 추가
INSERT INTO Mountain (name, latitude, longitude, altitude, course, difficulty, fee, state, town, point, weather_NX, weather_NY)
VALUES
('한라산', 33.3617, 126.5292, 1950, 1, 6.0, 3000, '제주도', '서귀포시', 300, 60, 127),
('지리산', 35.354167, 127.730833, 1915, 2, 7.0, 2000, '경상도', '산청군', 280, 63, 124),
('설악산', 38.1192, 128.4658, 1708, 3, 6.5, 3500, '강원도', '양양군', 320, 66, 131),
('덕유산', 35.908333, 127.740833, 1614, 4, 5.5, 1500, '전라도', '무주군', 250, 67, 129),
('소백산', 36.915291, 128.465714, 1577, 5, 5.0, 1000, '충청도', '단양군', 220, 68, 130),
('태백산', 37.099722, 128.930556, 1567, 6, 5.5, 1200, '강원도', '태백시', 230, 69, 133),
('오대산', 37.788611, 128.566667, 1265, 7, 4.5, 800, '강원도', '홍천군', 180, 70, 128),
('내장산', 35.488056, 126.933889, 1241, 8, 4.0, 700, '전라도', '정읍시', 160, 71, 126),
('치악산', 37.414722, 128.033056, 1288, 9, 6.0, 2000, '강원도', '원주시', 280, 73, 107),
('가야산', 35.783056, 128.102222, 799, 10, 3.5, 500, '경상도', '김해시', 120, 73, 123),
('명지산', 37.9552, 127.5250, 1267, 11, 5.0, 0, '수도권', '가평군', 250, 69, 134);


-- ChatInfo 테이블 데이터 삽입
INSERT INTO ChatInfo (mountain_serial, date)
VALUES
(1, '2024-05-01 10:00:00'),
(2, '2024-05-02 11:00:00'),
(3, '2024-05-03 12:00:00'),
(4, '2024-05-04 13:00:00'),
(5, '2024-05-05 14:00:00'),
(6, '2024-05-06 15:00:00'),
(7, '2024-05-07 16:00:00'),
(8, '2024-05-08 17:00:00'),
(9, '2024-05-09 18:00:00'),
(10, '2024-05-10 19:00:00');

-- ChatUserManager 테이블 데이터 삽입
INSERT INTO ChatUserManager (chatInfo_serial, user_serial)
VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 3),
(3, 4),
(4, 4),
(4, 5),
(5, 5),
(5, 6);

-- Chat 테이블 데이터 삽입
INSERT INTO Chat (serial, ChatUserManager_serial, content)
VALUES
(1, 1, 'Hello!'),
(2, 1, 'Hi there!'),
(3, 2, 'How are you?'),
(4, 2, 'I am fine, thanks!'),
(5, 3, 'What is your plan for the hike?'),
(6, 3, 'I plan to start early morning.'),
(7, 4, 'That sounds like a good plan.'),
(8, 4, 'Yes, I hope to catch the sunrise.'),
(9, 5, 'Are you bringing any snacks?'),
(10, 5, 'Yes, I have packed some sandwiches.');

-- Comment 테이블 데이터 삽입
INSERT INTO Comment (title, mountain_serial, user_serial, content, calorie, turnaround, view_count, star)
VALUES
('Great hike!', 1, 1, 'I had a wonderful time hiking Everest.', 500, 5, 100, 4),
('Challenging trail', 2, 2, 'K2 was quite a challenge but worth it!', 600, 6, 120, 5),
('Beautiful scenery', 3, 3, 'Kangchenjunga offers breathtaking views.', 450, 4, 90, 4),
('Loved the experience', 4, 4, 'Lhotse was an amazing experience.', 550, 5, 110, 4),
('Amazing journey', 5, 5, 'Makalu trek was unforgettable.', 480, 5, 96, 4),
('Enjoyable climb', 6, 6, 'Cho Oyu was a pleasant climb.', 420, 4, 84, 4),
('Incredible views', 7, 7, 'Dhaulagiri offers stunning views of the Himalayas.', 550, 5, 110, 4),
('Unique experience', 8, 8, 'Manaslu trek was a unique experience.', 500, 5, 100, 4),
('Tough but rewarding', 9, 9, 'Nanga Parbat was tough but rewarding.', 650, 7, 130, 5),
('Unforgettable journey', 10, 10, 'Annapurna trek was an unforgettable journey.', 470, 5, 94, 4),
('rweqfsda journey', 10, 1, 'Annapurna trek was an unforgettable journey.', 470, 5, 94, 4);

-- CommentFile 테이블 데이터 삽입
INSERT INTO CommentFile (user_serial, comment_serial, file_name, file_size, file_content_type, file_url)
VALUES
(1, 1, 'everest.jpg', 1024, 'image/jpeg', 'https://example.com/files/everest.jpg'),
(2, 2, 'k2.jpg', 2048, 'image/jpeg', 'https://example.com/files/k2.jpg'),
(3, 3, 'kangchenjunga.jpg', 1536, 'image/jpeg', 'https://example.com/files/kangchenjunga.jpg'),
(4, 4, 'lhotse.jpg', 1280, 'image/jpeg', 'https://example.com/files/lhotse.jpg'),
(5, 5, 'makalu.jpg', 2560, 'image/jpeg', 'https://example.com/files/makalu.jpg'),
(6, 6, 'cho_oyu.jpg', 1792, 'image/jpeg', 'https://example.com/files/cho_oyu.jpg'),
(7, 7, 'dhaulagiri.jpg', 2048, 'image/jpeg', 'https://example.com/files/dhaulagiri.jpg'),
(8, 8, 'manaslu.jpg', 1536, 'image/jpeg', 'https://example.com/files/manaslu.jpg'),
(9, 9, 'nanga_parbat.jpg', 3072, 'image/jpeg', 'https://example.com/files/nanga_parbat.jpg'),
(10, 10, 'annapurna.jpg', 2048, 'image/jpeg', 'https://example.com/files/annapurna.jpg');

-- Follow 테이블 데이터 삽입
INSERT INTO Follow (from_serial, to_serial)
VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 3),
(2, 4),
(2, 5),
(3, 4),
(3, 5),
(3, 6),
(4, 5);

-- Reply 테이블 데이터 삽입
INSERT INTO Reply (comment_serial, user_serial, content)
VALUES
(1, 2, 'Glad you enjoyed it!'),
(2, 3, 'It sure is!'),
(3, 4, 'I am good, thanks!'),
(4, 5, 'Me too!'),
(5, 6, 'I plan to start early too.'),
(6, 7, 'Yes, it is beautiful.'),
(7, 8, 'Absolutely stunning!'),
(8, 9, 'I hope you get to see it.'),
(9, 10, 'Yes, some energy bars.'),
(10, 1, 'Don''t forget water!');


-- User 테이블 조회
SELECT * FROM User
LIMIT 300
;

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
SELECT * FROM Follow
LIMIT 1000;

-- ChatUserManager 테이블 조회
SELECT * FROM ChatUserManager;


SELECT *
FROM Chat
WHERE ChatUserManager_serial In (SELECT serial
                                FROM ChatUserManager
                                WHERE chatInfo_serial = 1);


SELECT u.serial AS userSerial, m.serial AS mountainSerial, m.name AS mountainName, c.reg_date AS commentRegDate, m.difficulty AS difficulty, m.state AS state
FROM User u, Mountain m, comment c
WHERE u.serial = c.user_serial AND m.serial = c.mountain_serial AND c.user_serial = 1;

SELECT * FROM comment, user u
WHERE comment.user_serial = u.serial;

SELECT 
    c.serial AS serial,
    c.title AS title,
    c.mountain_serial AS mountainSerial,
    c.user_serial AS userSerial,
    u.id AS id,
    u.name AS name,
    u.gender AS gender,
    u.point AS point,
    c.content AS content,
    c.reg_date AS regDate,
    c.update_date AS updateDate,
    c.calorie AS calorie,
    c.turnaround AS turnaround,
    c.view_count AS view_count
FROM 
    comment c
JOIN 
    user u ON c.user_serial = u.serial;

SELECT 
    c.serial AS serial,
    c.title AS title,
    c.mountain_serial AS mountainSerial,
    c.user_serial AS userSerial,
    u.id AS id,
    u.name AS name,
    u.gender AS gender,
    u.point AS point,
    c.content AS content,
    c.reg_date AS regDate,
    c.update_date AS updateDate,
    c.calorie AS calorie,
    c.turnaround AS turnaround,
    c.view_count AS viewCount
FROM 
    comment c
JOIN 
    user u ON c.user_serial = u.serial;

INSERT INTO User (id, password, name, birth_date, gender, email)
VALUES ('aecvxdr', 'qwerasdfzxcv', '가나다라마', '1990-01-01 09:00:00.0', 'F', 'fc@awefds.da');

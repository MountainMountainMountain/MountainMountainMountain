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
  `point` INT NULL DEFAULT 0,
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
-- Table `finalMountainPJT`.`ChatManager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `finalMountainPJT`.`ChatManager` (
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
  `chatManager_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  `content` TEXT NULL,
  `create_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_Chat_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_Chat_ChatManager1_idx` (`chatManager_serial` ASC) VISIBLE,
  CONSTRAINT `fk_Chat_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Chat_ChatManager1`
    FOREIGN KEY (`chatManager_serial`)
    REFERENCES `finalMountainPJT`.`ChatManager` (`serial`)
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
  `chatmanager_serial` INT UNSIGNED NOT NULL,
  `user_serial` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`serial`),
  UNIQUE INDEX `serial_UNIQUE` (`serial` ASC) VISIBLE,
  INDEX `fk_ChatUserManager_User1_idx` (`user_serial` ASC) VISIBLE,
  INDEX `fk_ChatUserManager_ChatManager1_idx` (`chatmanager_serial` ASC) VISIBLE,
  CONSTRAINT `fk_ChatUserManager_User1`
    FOREIGN KEY (`user_serial`)
    REFERENCES `finalMountainPJT`.`User` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ChatUserManager_ChatManager1`
    FOREIGN KEY (`chatmanager_serial`)
    REFERENCES `finalMountainPJT`.`ChatManager` (`serial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO User (id, password, name, age, gender, email)
VALUES ();

SELECT * FROM user;
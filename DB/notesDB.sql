-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Notes
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Notes` ;

-- -----------------------------------------------------
-- Schema Notes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Notes` DEFAULT CHARACTER SET utf8 ;
USE `Notes` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `email` VARCHAR(500) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `details` TEXT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `completed` TINYINT NOT NULL DEFAULT 0,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_note_to_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_note_to_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS note@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'note'@'localhost' IDENTIFIED BY 'note';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'note'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `Notes`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `enabled`, `email`, `role`) VALUES (1, 'admin', 'admin', 'admin', '$2a$10$m00bLLAIE1Dqjd/86UIPV.frTP.Uv5P5ZMmjp/YxVafdzXQdxDLTK', 1, 'admin@admin.com', 'admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `enabled`, `email`, `role`) VALUES (2, 'basic', 'user', 'user', '$2a$10$m00bLLAIE1Dqjd/86UIPV.frTP.Uv5P5ZMmjp/YxVafdzXQdxDLTK', 1, 'user@user.com', 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `Notes`;
INSERT INTO `note` (`id`, `title`, `details`, `created`, `updated`, `completed`, `user_id`) VALUES (1, 'admin testing', 'It is me, the Admin. I am putting a test note in here.', DEFAULT, DEFAULT, 0, 1);
INSERT INTO `note` (`id`, `title`, `details`, `created`, `updated`, `completed`, `user_id`) VALUES (2, 'Note to Self', 'Today is a great day', DEFAULT, DEFAULT, 0, 2);
INSERT INTO `note` (`id`, `title`, `details`, `created`, `updated`, `completed`, `user_id`) VALUES (3, 'Daily Routine ', 'Eat, Sleep, Code, Play, Repeat', DEFAULT, DEFAULT, 0, 2);

COMMIT;


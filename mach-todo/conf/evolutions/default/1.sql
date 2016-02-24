# --- !Ups

-- -----------------------------------------------------
-- Table `USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `USER` (
  `USER_ID` BIGINT NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`USER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PROJECT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `PROJECT` (
  `PROJECT_ID` BIGINT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(255) NOT NULL,
  `USER_USER_ID` BIGINT NOT NULL,
  PRIMARY KEY (`PROJECT_ID`),
  UNIQUE INDEX `TITLE_UNIQUE` (`TITLE` ASC),
  INDEX `fk_PROJECT_USER_idx` (`USER_USER_ID` ASC),
  CONSTRAINT `fk_PROJECT_USER`
    FOREIGN KEY (`USER_USER_ID`)
    REFERENCES `USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TASK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TASK` (
  `TASK_ID` BIGINT NOT NULL,
  `TITLE` VARCHAR(255) NOT NULL,
  `DESCRIPTION` TEXT NULL,
  `DUE_DATE` DATE NULL,
  `STATUS` VARCHAR(45) NOT NULL,
  `USER_USER_ID` BIGINT NOT NULL,
  `PROJECT_PROJECT_ID` BIGINT NOT NULL,
  PRIMARY KEY (`TASK_ID`),
  INDEX `fk_TASK_USER1_idx` (`USER_USER_ID` ASC),
  INDEX `fk_TASK_PROJECT1_idx` (`PROJECT_PROJECT_ID` ASC),
  CONSTRAINT `fk_TASK_USER1`
    FOREIGN KEY (`USER_USER_ID`)
    REFERENCES `USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TASK_PROJECT1`
    FOREIGN KEY (`PROJECT_PROJECT_ID`)
    REFERENCES `PROJECT` (`PROJECT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

# --- !Downs

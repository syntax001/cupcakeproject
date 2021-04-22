-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cupcake_shop
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cupcake_shop`;

-- -----------------------------------------------------
-- Schema cupcake_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cupcake_shop` DEFAULT CHARACTER SET utf8;
USE `cupcake_shop`;

-- -----------------------------------------------------
-- Table `cupcake_shop`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake_shop`.`users`;

CREATE TABLE IF NOT EXISTS `cupcake_shop`.`users`
(
    `customer_id`  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(45)  NOT NULL,
    `email`        VARCHAR(45)  NOT NULL,
    `phone_number` VARCHAR(45)  NOT NULL,
    `balance`      INT          NOT NULL DEFAULT 0,
    `password`     VARCHAR(45)  NOT NULL,
    `role`         VARCHAR(20)  NOT NULL DEFAULT 'customer',
    PRIMARY KEY (`customer_id`),
    UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake_shop`.`shopping_cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake_shop`.`shopping_cart`;

CREATE TABLE IF NOT EXISTS `cupcake_shop`.`shopping_cart`
(
    `shopping_cart_id` INT UNSIGNED NOT NULL,
    `customer_id`      INT UNSIGNED NOT NULL,
    PRIMARY KEY (`shopping_cart_id`, `customer_id`),
    INDEX `fk_shopping_cart_customers1_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_shopping_cart_customers1`
        FOREIGN KEY (`customer_id`)
            REFERENCES `cupcake_shop`.`users` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake_shop`.`cupcake_toppings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake_shop`.`cupcake_toppings`;

CREATE TABLE IF NOT EXISTS `cupcake_shop`.`cupcake_toppings`
(
    `cupcake_toppings_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `topping`             VARCHAR(45)  NULL,
    `price`               INT          NOT NULL,
    PRIMARY KEY (`cupcake_toppings_id`),
    UNIQUE INDEX `topping_UNIQUE` (`topping` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake_shop`.`cupcake_bottoms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake_shop`.`cupcake_bottoms`;

CREATE TABLE IF NOT EXISTS `cupcake_shop`.`cupcake_bottoms`
(
    `cupcake_bottoms_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `bottom`             VARCHAR(45)  NULL,
    `price`              INT          NOT NULL,
    PRIMARY KEY (`cupcake_bottoms_id`),
    UNIQUE INDEX `topping_UNIQUE` (`bottom` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cupcake_shop`.`cupcake_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cupcake_shop`.`cupcake_orders`;

CREATE TABLE IF NOT EXISTS `cupcake_shop`.`cupcake_orders`
(
    `order_id`            INT          NOT NULL AUTO_INCREMENT,
    `cupcake_toppings_id` INT UNSIGNED NOT NULL,
    `cupcake_bottoms_id`  INT UNSIGNED NOT NULL,
    `amount`              INT          NOT NULL,
    `customer_id`         INT UNSIGNED NOT NULL,
    PRIMARY KEY (`order_id`, `cupcake_toppings_id`, `cupcake_bottoms_id`, `customer_id`),
    INDEX `fk_cupcake_orders_cupcake_toppings1_idx` (`cupcake_toppings_id` ASC) VISIBLE,
    INDEX `fk_cupcake_orders_cupcake_bottoms1_idx` (`cupcake_bottoms_id` ASC) VISIBLE,
    INDEX `fk_cupcake_orders_users1_idx` (`customer_id` ASC) VISIBLE,
    CONSTRAINT `fk_cupcake_orders_cupcake_toppings1`
        FOREIGN KEY (`cupcake_toppings_id`)
            REFERENCES `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_cupcake_orders_cupcake_bottoms1`
        FOREIGN KEY (`cupcake_bottoms_id`)
            REFERENCES `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_cupcake_orders_users1`
        FOREIGN KEY (`customer_id`)
            REFERENCES `cupcake_shop`.`users` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cupcake_shop`.`cupcake_toppings`
-- -----------------------------------------------------
START TRANSACTION;
USE `cupcake_shop`;
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Chocolate', 5);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Blueberry', 5);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Rasberry', 5);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Crispy', 6);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Strawberry', 6);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Rum/Raisin', 7);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Orange', 8);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Lemon', 8);
INSERT INTO `cupcake_shop`.`cupcake_toppings` (`cupcake_toppings_id`, `topping`, `price`)
VALUES (DEFAULT, 'Blue cheese', 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cupcake_shop`.`cupcake_bottoms`
-- -----------------------------------------------------
START TRANSACTION;
USE `cupcake_shop`;
INSERT INTO `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`, `bottom`, `price`)
VALUES (DEFAULT, 'Chocolate', 5);
INSERT INTO `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`, `bottom`, `price`)
VALUES (DEFAULT, 'Vanilla', 5);
INSERT INTO `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`, `bottom`, `price`)
VALUES (DEFAULT, 'Nutmeg', 5);
INSERT INTO `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`, `bottom`, `price`)
VALUES (DEFAULT, 'Pistacio', 6);
INSERT INTO `cupcake_shop`.`cupcake_bottoms` (`cupcake_bottoms_id`, `bottom`, `price`)
VALUES (DEFAULT, 'Almond', 7);

COMMIT;



CREATE SCHEMA IF NOT EXISTS `product_catagory` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `product_catagory` ;


CREATE TABLE IF NOT EXISTS `product_catagory`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enabled` BIT(1) NULL DEFAULT b'0',
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB 
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_catagory`.`cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cart_users1_idx` (`user_id` ASC) )
ENGINE = InnoDB 
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `product_catagory`.`catagories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB 
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_catagory`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `catagory_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_catagories1_idx` (`catagory_id` ASC) )
ENGINE = InnoDB 
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_catagory`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB 
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_catagory`.`cart_products` (
  `carts_id` INT NOT NULL,
  `products_id` INT NOT NULL,
  PRIMARY KEY (`carts_id`, `products_id`),
  INDEX `fk_cart_has_products_products1_idx` (`products_id` ASC) ,
  INDEX `fk_cart_has_products_cart_idx` (`carts_id` ASC) )
ENGINE = InnoDB 
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_catagory`.`users_roles` (
  `users_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `roles_id`),
  INDEX `fk_users_has_roles_roles1_idx` (`roles_id` ASC) ,
  INDEX `fk_users_has_roles_users1_idx` (`users_id` ASC) )
ENGINE = InnoDB 
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


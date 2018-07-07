-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema TFVitor
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TFVitor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TFVitor` DEFAULT CHARACTER SET latin1 ;
USE `TFVitor` ;

-- -----------------------------------------------------
-- Table `TFVitor`.`Aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Aula` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Aula` (
  `idAula` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`idAula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Clientes` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `cpf` VARCHAR(12) NULL DEFAULT NULL,
  `endereco` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Aula_Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Aula_Clientes` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Aula_Clientes` (
  `Aula_idAula` INT(11) NOT NULL,
  `Clientes_idCliente` INT(11) NOT NULL,
  PRIMARY KEY (`Aula_idAula`, `Clientes_idCliente`),
  INDEX `fk_Aula_has_Clientes_Clientes1_idx` (`Clientes_idCliente` ASC),
  INDEX `fk_Aula_has_Clientes_Aula1_idx` (`Aula_idAula` ASC),
  CONSTRAINT `fk_Aula_has_Clientes_Aula1`
    FOREIGN KEY (`Aula_idAula`)
    REFERENCES `TFVitor`.`Aula` (`idAula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_has_Clientes_Clientes1`
    FOREIGN KEY (`Clientes_idCliente`)
    REFERENCES `TFVitor`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Produto` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `quantidade` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idProduto`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Vendas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Vendas` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Vendas` (
  `idCliente` INT(11) NOT NULL,
  `idProduto` INT(11) NOT NULL,
  PRIMARY KEY (`idCliente`, `idProduto`),
  INDEX `fk_Clientes_has_Produto_Clientes1_idx` (`idCliente` ASC),
  INDEX `fk_Clientes_has_Produto_Produto1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_Clientes_has_Produto_Clientes1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `TFVitor`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Clientes_has_Produto_Produto1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `TFVitor`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

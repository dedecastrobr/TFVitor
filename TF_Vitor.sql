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
-- Table `TFVitor`.`Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Clientes` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Clientes` (
  `idCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `cpf` INT(12) NULL DEFAULT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Estoque`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Estoque` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Estoque` (
  `idEstoque` INT NOT NULL AUTO_INCREMENT,
  `Quantidade` INT NULL,
  PRIMARY KEY (`idEstoque`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TFVitor`.`Produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Produto` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `idEstoque` INT NOT NULL,
  PRIMARY KEY (`idProduto`, `idEstoque`),
  INDEX `fk_Produto_Estoque_idx` (`idEstoque` ASC),
  CONSTRAINT `fk_Produto_Estoque`
    FOREIGN KEY (`idEstoque`)
    REFERENCES `TFVitor`.`Estoque` (`idEstoque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TFVitor`.`Aula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Aula` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Aula` (
  `idAula` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`idAula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TFVitor`.`Vendas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Vendas` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Vendas` (
  `idCliente` INT(11) NOT NULL,
  `idProduto` INT NOT NULL,
  `idEstoque` INT NOT NULL,
  PRIMARY KEY (`idCliente`, `idProduto`, `idEstoque`),
  INDEX `fk_Clientes_has_Produto_Produto1_idx` (`idProduto` ASC, `idEstoque` ASC),
  INDEX `fk_Clientes_has_Produto_Clientes1_idx` (`idCliente` ASC),
  CONSTRAINT `fk_Clientes_has_Produto_Clientes1`
    FOREIGN KEY (`idCliente`)
    REFERENCES `TFVitor`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Clientes_has_Produto_Produto1`
    FOREIGN KEY (`idProduto` , `idEstoque`)
    REFERENCES `TFVitor`.`Produto` (`idProduto` , `idEstoque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TFVitor`.`Aula_Clientes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TFVitor`.`Aula_Clientes` ;

CREATE TABLE IF NOT EXISTS `TFVitor`.`Aula_Clientes` (
  `Aula_idAula` INT NOT NULL,
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
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

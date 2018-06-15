-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Natura
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Natura
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Natura` DEFAULT CHARACTER SET utf8 ;
USE `Natura` ;

-- -----------------------------------------------------
-- Table `Natura`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Natura`.`Clientes` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Telefone` INT(12) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Endereco` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC),
  UNIQUE INDEX `Telefone_UNIQUE` (`Telefone` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Natura`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Natura`.`Pedidos` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `PrecoTotal` FLOAT NOT NULL,
  `DataPedido` DATETIME NOT NULL,
  `Clientes_idCliente` INT NOT NULL,
  `DataVenda` DATETIME NULL,
  PRIMARY KEY (`idPedido`, `Clientes_idCliente`),
  UNIQUE INDEX `idPedido_UNIQUE` (`idPedido` ASC),
  INDEX `fk_Pedidos_Clientes1_idx` (`Clientes_idCliente` ASC),
  CONSTRAINT `fk_Pedidos_Clientes1`
    FOREIGN KEY (`Clientes_idCliente`)
    REFERENCES `Natura`.`Clientes` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Natura`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Natura`.`Produtos` (
  `idProd` INT NOT NULL AUTO_INCREMENT,
  `CodProduto` INT(6) NOT NULL,
  `Descricao` VARCHAR(45) NOT NULL,
  `Preco` FLOAT NOT NULL,
  `Pagina` INT(4) NOT NULL,
  `QtdEstoque` INT NOT NULL,
  PRIMARY KEY (`idProd`, `CodProduto`),
  UNIQUE INDEX `CodProd_UNIQUE` (`idProd` ASC),
  UNIQUE INDEX `CodProduto_UNIQUE` (`CodProduto` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Natura`.`Contem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Natura`.`Contem` (
  `Pedidos_idPedido` INT NOT NULL,
  `Produtos_idProd` INT NOT NULL,
  `Produtos_CodProduto` INT(6) NOT NULL,
  PRIMARY KEY (`Pedidos_idPedido`, `Produtos_idProd`, `Produtos_CodProduto`),
  INDEX `fk_Pedidos_has_Produtos_Produtos1_idx` (`Produtos_idProd` ASC, `Produtos_CodProduto` ASC),
  INDEX `fk_Pedidos_has_Produtos_Pedidos1_idx` (`Pedidos_idPedido` ASC),
  CONSTRAINT `fk_Pedidos_has_Produtos_Pedidos1`
    FOREIGN KEY (`Pedidos_idPedido`)
    REFERENCES `Natura`.`Pedidos` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_has_Produtos_Produtos1`
    FOREIGN KEY (`Produtos_idProd` , `Produtos_CodProduto`)
    REFERENCES `Natura`.`Produtos` (`idProd` , `CodProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Natura`.`Itens_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Natura`.`Itens_Pedido` (
  `idItens` INT NOT NULL AUTO_INCREMENT,
  `idPedido` INT NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idItens`),
  UNIQUE INDEX `idItens_UNIQUE` (`idItens` ASC),
  INDEX `fk_Produto_idx` (`idProduto` ASC),
  INDEX `fk_Pedido_idx` (`idPedido` ASC),
  CONSTRAINT `fk_Pedido`
    FOREIGN KEY (`idPedido`)
    REFERENCES `Natura`.`Pedidos` (`idPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `Natura`.`Produtos` (`idProd`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

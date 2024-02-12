CREATE TABLE Amministratore(
    email_admin VARCHAR(50) PRIMARY KEY,
    password_admin VARCHAR(400),
    nome_admin VARCHAR(50)
);

CREATE TABLE Parcheggio(
    id_parcheggio INT PRIMARY KEY,
    luogo_parcheggio VARCHAR(50),
    numero_posti_parcheggio INT
);

CREATE TABLE Bicicletta (
    id_bici INT PRIMARY KEY,
    categoria_bici VARCHAR(50),
    prezzo_bici INT,
    id_parcheggio INT,
    disponibile Boolean,
    km_effettuati int,
    FOREIGN KEY (id_parcheggio) REFERENCES Parcheggio(id_parcheggio)
);



CREATE TABLE Equipaggamento (
    id_equipaggiamento INT PRIMARY KEY,
    nome_equipaggiamento VARCHAR(50) NOT NULL,
    prezzo_equipaggiamento DECIMAL(10, 2)
);


CREATE TABLE Cliente(
    email_cliente varchar(50) primary key,
    nome_cliente VARCHAR(50),
    cognome_cliente VARCHAR (50),
    password_cliente VARCHAR (400)
);

CREATE TABLE Possiede (
    id_bici INT,
    id_equipaggiamento INT,
    PRIMARY KEY (id_bici, id_equipaggiamento),
    FOREIGN KEY (id_bici) REFERENCES Bicicletta(id_bici),
    FOREIGN KEY (id_equipaggiamento) REFERENCES Equipaggamento(id_equipaggiamento)
);

CREATE TABLE Tariffa (
    nome_tariffa VARCHAR(100) NOT NULL PRIMARY KEY,
    orario_inizio_tariffa TIME NOT NULL,
    orario_fine_tariffa TIME NOT NULL,
    prezzo_tariffa INT not null,
    categoria_bici VARCHAR(50) NOT NULL  
);

CREATE TABLE Prenota(
    email_cliente VARCHAR(50)  not null, 
    id_bici Int not null, 
    ora_inizio_prenota TIME not null, 
    ora_fine_prenota TIME, 
    PRIMARY KEY (id_bici, email_cliente), 
    FOREIGN KEY (id_bici) REFERENCES Bicicletta(id_bici), 
    FOREIGN KEY (email_cliente) REFERENCES Cliente(email_cliente) 
);


CREATE TABLE Paga(
    id_bici INT not null,
    email_cliente VARCHAR(50)  not null, 
    km_effettuati Int,
    metodo_pagamento VARCHAR(50) NOT NULL,
    importo DECIMAL(10,2),
    FOREIGN KEY (id_bici) REFERENCES Bicicletta(id_bici), 
    FOREIGN KEY (email_cliente) REFERENCES Cliente(email_cliente) 
);
INSERT INTO Amministratore (email_admin, password_admin, nome_admin)VALUES ('a1@a1.it', SHA2('P1', 256), 'gianni');
INSERT INTO Amministratore (email_admin, password_admin, nome_admin)VALUES ('a1@a1.it', SHA2('P1', 256), 'gianni');

INSERT INTO Cliente (email_cliente, nome_cliente, cognome_cliente,password_cliente)VALUES ('c1@c1.it','mario','rossi', SHA2('P1', 256));


DROP TABLE IF EXISTS NOTE;
DROP TABLE IF EXISTS EST_TAGE;
DROP TABLE IF EXISTS PAPER_DOCUMENT;
DROP TABLE IF EXISTS ELECTRONIC_DOCUMENT;
DROP TABLE IF EXISTS DOCUMENT;
DROP TABLE IF EXISTS MOT_CLEF;
DROP TABLE IF EXISTS CATEGORIE_MOT_CLEF;
DROP TABLE IF EXISTS CRITERE;
DROP TABLE IF EXISTS DOMAINE;

/* CREATION DES TABLES */

CREATE TABLE DOMAINE(
	idDomaine INT NULL AUTO_INCREMENT,
	nomDomaine VARCHAR(50),
	PRIMARY KEY (idDomaine)
	);
	
CREATE TABLE CRITERE(
	idCritere INT NULL AUTO_INCREMENT,
	nomCritere VARCHAR(30),
	idDomaine INT NOT NULL,
	PRIMARY KEY(idCritere),
	FOREIGN KEY(idDomaine) REFERENCES DOMAINE(idDomaine)
	);
	
CREATE TABLE CATEGORIE_MOT_CLEF(
	idCategorieMotClef INT NOT NULL AUTO_INCREMENT,
	nomCategorieMotClef VARCHAR(30),
	idDomaine INT NOT NULL,
	PRIMARY KEY (idCategorieMotClef),
	FOREIGN KEY (idDomaine) REFERENCES DOMAINE(idDomaine)
	);
	
CREATE TABLE MOT_CLEF(
	idMotClef INT NOT NULL AUTO_INCREMENT,
	libelle VARCHAR(30),
	idCategorieMotClef INT NOT NULL,
	PRIMARY KEY (idMotClef),
	FOREIGN KEY(idCategorieMotClef) REFERENCES CATEGORIE_MOT_CLEF(idCategorieMotClef)
	);	
	
CREATE TABLE DOCUMENT(
	idDocument INT NOT NULL AUTO_INCREMENT,
	titre VARCHAR(30),
	PRIMARY KEY (idDocument)
	);	
	
CREATE TABLE ELECTRONIC_DOCUMENT(
	idElectronicDocument INT NOT NULL AUTO_INCREMENT,
	link VARCHAR(300),
	idDocument INT NOT NULL,
	PRIMARY KEY (idElectronicDocument),
	FOREIGN KEY(idDocument) REFERENCES DOCUMENT(idDocument)
	);
	
CREATE TABLE PAPER_DOCUMENT(
	idPaperDocument INT NOT NULL AUTO_INCREMENT,
	commentaire TEXT,
	idDocument INT NOT NULL,
	PRIMARY KEY (idPaperDocument),
	FOREIGN KEY(idDocument) REFERENCES DOCUMENT(idDocument)
	);
	
CREATE TABLE EST_TAGE(
	idDocument INT NOT NULL,
	idMotClef INT NOT NULL,
	FOREIGN KEY(idDocument) REFERENCES DOCUMENT(idDocument),
	FOREIGN KEY(idMotClef) REFERENCES MOT_CLEF(idMotClef)
	);
	
CREATE TABLE NOTE(
	idNote INT NOT NULL AUTO_INCREMENT,
	etoiles INT,
	idCritere INT NOT NULL,
	idDocument INT NOT NULL,
	PRIMARY KEY(idNote),
	FOREIGN KEY(idDocument) REFERENCES DOCUMENT(idDocument),
	FOREIGN KEY(idCritere) REFERENCES CRITERE(idCritere)
	);	
	
-- /* INSERTION DES ELEMENTS DANS LES TABLES */

INSERT INTO DOMAINE VALUES
(null, 'Sport'),
(null, 'Musique'),
(null, 'Cuisine'),
(null, 'Automobile');

INSERT INTO DOCUMENT VALUES
(null, 'Document 1'),
(null, 'Document 2');

INSERT INTO CRITERE VALUES
(null, 'Prix', 4),
(null, 'Instruments', 2),
(null, 'Qualité', 3),
(null, 'Bonne photo', 4),
(null, 'Temps de course', 1);

INSERT INTO NOTE VALUES
(null, 1, 1, 1),
(null, 2, 2, 2),
(null, 3, 3, 1),
(null, 4, 4, 2),
(null, 5, 5, 2);

INSERT INTO CATEGORIE_MOT_CLEF VALUES
(null, 'Lieux', 1),
(null, 'Genre', 2),
(null, 'Plat', 3),
(null, 'Marque', 4);

INSERT INTO MOT_CLEF VALUES
(null, 'Belfort', 1),
(null, 'Rock', 2),
(null, 'Classique', 2),
(null, 'Raddon', 1),
(null, 'Dessert', 3),
(null, 'Entrée', 3),
(null, 'Peugeot', 4);



INSERT INTO ELECTRONIC_DOCUMENT VALUES
(null, 'http://monpremierdocumentelectronique.fr', 2);

INSERT INTO PAPER_DOCUMENT VALUES
(null, 'ceci est un commentaire', 1);

INSERT INTO EST_TAGE VALUES
(1, 2),
(2, 1);
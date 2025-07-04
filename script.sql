DROP DATABASE biblio;
CREATE DATABASE IF NOT EXISTS biblio;
USE biblio;

--gestion de staff
CREATE TABLE type_staff (
    id_type_staff INT AUTO_INCREMENT PRIMARY KEY,
    type_staff VARCHAR(100)
);

INSERT INTO type_staff VALUES
(1, 'bibliothecaire'),
(2, 'finance');

CREATE TABLE staff (
    id_staff INT AUTO_INCREMENT PRIMARY KEY,
    id_type_staff INT, 
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- stocke le hash, jamais le mot de passe en clair
    FOREIGN KEY (id_type_staff) REFERENCES type_staff(id_type_staff)
);

INSERT INTO staff VALUES
(1, 1, 'bibliothequeR', 'bibliothequeR');

-- TABLE : type d'adhérent
CREATE TABLE type_adherent (
    id_type_adherent INT AUTO_INCREMENT PRIMARY KEY,
    type_adherent VARCHAR(50) UNIQUE,
    duree_emprunt_auth INT, -- en jours
    quotat_auth INT,
    duree_penalite INT -- en jours
);

INSERT INTO type_adherent (type_adherent, duree_emprunt_auth, quotat_auth, duree_penalite) VALUES 
('professeur', 30, 5, 90),
('etudiant', 15, 3, 180),
('pro_staff', 40, 5, 90),
('anonyme', 0, 0, 0); -- emprunt interdit

-- TABLE : statut de l'adhérent (actif ou non)
CREATE TABLE statut (
    id_statut INT AUTO_INCREMENT PRIMARY KEY,
    statut VARCHAR(20) UNIQUE
);

INSERT INTO statut (statut) VALUES 
('actif'),
('inactif');

-- TABLE : adherent
CREATE TABLE adherent (
    id_adherent INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    dtn DATE,
    identifiant VARCHAR(50) UNIQUE,
    id_type_adherent INT,
    id_statut INT,
    abonnement_paye BOOLEAN DEFAULT FALSE,
    sanction_jusqua DATE DEFAULT NULL,
    FOREIGN KEY (id_type_adherent) REFERENCES type_adherent(id_type_adherent),
    FOREIGN KEY (id_statut) REFERENCES statut(id_statut)
);

-- ajouter une dizaine d adherents certains actif, d autres non (actif si abonnement paye, inactif si abonnement non paye) , et certains sanctionne (tu penses qu on devrait ajouter 'sanctionne' dans table statut ou non?)
-- 10 adhérents avec différentes situations (commenté)
INSERT INTO adherent (nom, prenom, dtn, identifiant, id_type_adherent, id_statut, abonnement_paye, sanction_jusqua) VALUES
('Rakoto', 'Jean', '1990-01-01', 'PROF001', 1, 1, TRUE, NULL),               -- professeur actif
('Rasoa', 'Lalao', '2001-06-12', 'ETUD001', 2, 1, TRUE, NULL),              -- étudiant actif
('Randria', 'Mickael', '1985-03-08', 'PROS001', 3, 1, TRUE, NULL),          -- staff actif
('Rahari', 'Tiana', '1998-09-15', 'ETUD002', 2, 2, FALSE, NULL),            -- étudiant inactif (abonnement non payé)
('Rakoto', 'Miora', '1996-12-22', 'PROF002', 1, 1, TRUE, '2025-09-01'),     -- professeur sanctionné
('Rabe', 'Sitraka', '2000-11-11', 'ETUD003', 2, 1, TRUE, NULL),             -- étudiant actif
('Ravo', 'Nirina', '1994-04-04', 'PROS002', 3, 1, TRUE, '2025-12-01'),      -- staff sanctionné
('Ranaivo', 'Zo', '2003-07-21', 'ETUD004', 2, 2, FALSE, NULL),              -- étudiant inactif
('Andry', 'Mamy', '2002-05-05', 'ETUD005', 2, 1, TRUE, NULL),               -- étudiant actif
('Inconnu', 'Anonyme', '2000-01-01', 'ANON001', 4, 1, FALSE, NULL);         -- anonyme (non autorisé)

-- TABLE : livre
CREATE TABLE livre (
    id_livre INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(255),
    auteur VARCHAR(255),
    edition VARCHAR(100),
    langue VARCHAR(50),
    mots_cles TEXT,
    description TEXT,
    note FLOAT,
    commentaires TEXT
);


-- TABLE : exemplaire
CREATE TABLE exemplaire (
    id_exemplaire INT AUTO_INCREMENT PRIMARY KEY,
    id_livre INT,
    disponible BOOLEAN DEFAULT TRUE,
    perdu BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_livre) REFERENCES livre(id_livre)
);

-- ajouter 15 livres 3 exemplaires chacun
-- 15 livres simples
INSERT INTO livre (titre, auteur, edition, langue, mots_cles, description, note, commentaires) VALUES
('Java pour les Nuls', 'Pierre Dupont', 'Eyrolles', 'francais', 'java,programmation', 'Introduction a Java', 4.2, 'Tres bon livre'),
('Spring Framework', 'Julie Rami', 'Dunod', 'francais', 'spring,java,framework', 'Spring explique', 4.5, ''),
('Algorithmique', 'Thomas Durand', 'PUF', 'francais', 'algo,tri,structure', 'Les bases des algorithmes', 3.8, ''),
('BD Les Sisters', 'William', 'Delcourt', 'francais', 'bande dessinee,histoire', 'Histoire drole', 4.7, ''),
('Data Science Basics', 'Jane Doe', 'OReilly', 'anglais', 'data,python,analyse', 'Analyse de donnees', 4.0, ''),
('HTML & CSS', 'Mike Smith', 'Eyrolles', 'anglais', 'web,html,css', 'Bases du web', 3.9, ''),
('SQL pour Debutants', 'Claire Dubois', 'Dunod', 'francais', 'sql,bd,database', 'Langage SQL', 4.1, ''),
('PHP Modern', 'Lucas Martin', 'ENI', 'francais', 'php,mvc,web', 'PHP avance', 3.6, ''),
('Gestion de Projet', 'Sophie Blanc', 'Vuibert', 'francais', 'gestion,agile,scrum', 'Management agile', 4.3, ''),
('Intelligence Artificielle', 'Paul Lefevre', 'MIT Press', 'anglais', 'ia,neural,ml', 'Bases de l IA', 4.4, ''),
('Python Avance', 'Lina S.', 'Packt', 'anglais', 'python,avance,oop', 'Programmation Python', 4.6, ''),
('Systemes d exploitation', 'John Doe', 'OReilly', 'anglais', 'os,linux,unix', 'Les OS expliques', 4.2, ''),
('Reseaux Informatiques', 'Georges Paul', 'PUF', 'francais', 'reseaux,tcp/ip', 'Le reseau simplement', 3.8, ''),
('Securite Web', 'Laura C.', 'ENI', 'francais', 'cyber,web,attaque', 'Securite web', 4.0, ''),
('UX Design', 'Valerie L.', 'Eyrolles', 'francais', 'ux,design,interface', 'Creer de meilleures interfaces', 4.5, '');


-- Génération des 45 exemplaires
INSERT INTO exemplaire (id_livre)
SELECT id_livre FROM livre
CROSS JOIN (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3) AS t;


-- TABLE : emprunt
CREATE TABLE emprunt (
    id_emprunt INT AUTO_INCREMENT PRIMARY KEY,
    id_adherent INT,
    id_exemplaire INT,
    date_emprunt DATE,
    date_retour_prevue DATE,
    date_retour_effective DATE DEFAULT NULL,
    FOREIGN KEY (id_adherent) REFERENCES adherent(id_adherent),
    FOREIGN KEY (id_exemplaire) REFERENCES exemplaire(id_exemplaire)
);

-- ajouter des donnees d emprunt sur differentes dates avec les bonnes donnees
-- Exemple d’emprunts valides
INSERT INTO emprunt (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue) VALUES
(1, 1, '2025-06-20', '2025-07-20'),  -- professeur actif
(2, 2, '2025-06-22', '2025-07-07'),  -- étudiant actif
(3, 3, '2025-06-23', '2025-08-02'),  -- pro/staff actif
(6, 4, '2025-06-24', '2025-07-09'),  -- étudiant actif

-- un adhérent sanctionné emprunte (logiquement faux mais mis pour test)
(5, 5, '2025-06-25', '2025-07-25');


-- actions dans historiques d emprunts
CREATE TABLE actions_histo_emprunt (
    id_action INT AUTO_INCREMENT PRIMARY KEY,
    action VARCHAR(50)
);

INSERT INTO actions_histo_emprunt VALUES
(1, 'emprunt'),
(2, 'retour'),
(3, 'prolongation'),
(4, 'reservation');

-- TABLE : historique_emprunt
CREATE TABLE historique_emprunt (
    id_historique INT AUTO_INCREMENT PRIMARY KEY,
    id_emprunt INT,
    id_action INT, -- emprunt, retour, prolongation...
    date_action DATE,
    FOREIGN KEY (id_emprunt) REFERENCES emprunt(id_emprunt),
    FOREIGN KEY (id_action) REFERENCES actions_histo_emprunt(id_action)
);

-- ajouter des donnees d historique d emrpunt correspondant aux emprunts dans la table precedente et des adherents reels enregistres et actifs 
-- Historique correspondant aux emprunts
INSERT INTO historique_emprunt (id_emprunt, id_action, date_action) VALUES
(1, 1, '2025-06-20'),
(2, 1, '2025-06-22'),
(3, 1, '2025-06-23'),
(4, 1, '2025-06-24'),
(5, 1, '2025-06-25'); -- test sanctionné
-- rq * commente pour que ca soit facile pour moi de verifier si les donnees coincident bien , sont bien logiques stp

-- Retour dans les temps
UPDATE emprunt SET date_retour_effective = '2025-07-05' WHERE id_emprunt = 2;
INSERT INTO historique_emprunt (id_emprunt, id_action, date_action) VALUES (2, 2, '2025-07-05');

-- Retour en retard (prévu 07/09, retour 07/20)
UPDATE emprunt SET date_retour_effective = '2025-07-20' WHERE id_emprunt = 4;
INSERT INTO historique_emprunt (id_emprunt, id_action, date_action) VALUES (4, 2, '2025-07-20');

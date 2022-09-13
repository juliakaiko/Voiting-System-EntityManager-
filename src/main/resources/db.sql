CREATE DATABASE IF NOT EXISTS myDB;

ALTER DATABASE myDB --изменяет атрибуты базы данных --
  DEFAULT CHARACTER SET utf8  -- utf8/ utf8mb4кодировка, преобразующая номера ячеек таблицы Юникод в двоичн. код --
  DEFAULT COLLATE  utf8_general_ci; -- utf8_general_ci /  utf8mb4_unicode_ciспособ, с помощью которого следует упорядочивать и сравнивать данные в БД -- 
--с MySQL 5.5.3 utf8mb4, а не utf8 --

DROP TABLE mydb.elector;
DROP TABLE mydb.candidateDetails;
DROP TABLE mydb.candidate;
DROP TABLE mydb.admin;

CREATE TABLE IF NOT EXISTS candidate (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    firstName VARCHAR (100) default NULL,
    lastName VARCHAR (100) default NULL,
    age INTEGER default NULL,
    voices BIGINT default NULL --INTEGER был!  --
);

CREATE TABLE IF NOT EXISTS candidateDetails
(
    --id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,--
    candidate_id BIGINT UNSIGNED NOT NULL, --UNSIGNED только положительные числа --
    FOREIGN KEY (candidate_id) REFERENCES candidate(id) ON DELETE CASCADE,
    UNIQUE (candidate_id),  --только для связи один к одному--
    education VARCHAR (100) default NULL,
    annualIncome INTEGER default NULL,
    property VARCHAR (100) default NULL,
    electionPromises VARCHAR (100) default NULL
);

CREATE TABLE IF NOT EXISTS elector (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    voted BIT DEFAULT false,
    candidate_id BIGINT UNSIGNED,   --UNSIGNED только положительные числа --
    FOREIGN KEY (candidate_id) REFERENCES candidate(id) ON DELETE CASCADE,
    firstName VARCHAR(255) default NULL,
    lastName VARCHAR(255) default NULL,
    age INTEGER default NULL,
    login VARCHAR (255) default NULL UNIQUE,
    password VARCHAR  (255) default NULL, -- VARBINARY для хэширования! Было VARCHAR--
    passportSeries VARCHAR (255) default NULL,
    passportNum VARCHAR (255) default NULL   
);

CREATE TABLE IF NOT EXISTS admin
(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR (255) default NULL,
    password VARCHAR (255) default NULL
);

INSERT INTO `candidate` (firstName, lastName, age, voices)
VALUES
  ('Ivan', 'Ivanov', '56','0'),
  ('Sidor', 'Sidorov', '45','0' ),
  ('Kirill', 'Kirilov', '60', '0');


INSERT INTO `candidatedetails` (candidate_id, education, annualIncome, property, electionPromises)
VALUES 
  (1,'Department of Politics and International Studies of University of Cambridge', '100000', 
'A semi-detached house on the outskirts of London', 'Import substitution and economic growth'),
   (2, 'Department of Government of Harvard University', '80000', 
'An apartment in California', 'Environmental protection and development of science and IT technologies'),
   (3, 'The Department of Politics and International Relations of University of Oxford', '90000', 
'A townhouse in London', 'Independent foreign policy and economic reforms');

INSERT INTO `admin` (login, password)
VALUES 
  ('admin', 'aea0e147'); --хэш-пароль вместо admin123 --


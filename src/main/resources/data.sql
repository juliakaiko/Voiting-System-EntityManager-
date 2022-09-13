INSERT INTO `candidate` (firstName, lastName, age, voices)
VALUES
  ('Ivan', 'Ivanov', '56', '0'),
  ('Sidor', 'Sidorov', '45', '0'),
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
  ('admin', 'admin123');

--UPDATE elector SET password = MD5(password); --
--UPDATE elector SET  password = SHA('secret_password');--

--INSERT INTO elector (password) values (aes_encrypt('12345678','key'));--

--INSERT INTO `elector` (login, password) VALUES ('Katya', '1234567');--
--UPDATE `elector` SET password = (AES_ENCRYPT('"julia123"','key')) WHERE id=1;--

--SELECT login, CAST(AES_DECRYPT(password,'key') as char(50)) FROM elector;--
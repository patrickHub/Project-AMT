DROP SCHEMA IF EXISTS MVCDemo;
CREATE SCHEMA MVCDemo;
USE MVCDemo;

--
-- Table structure for table `user`
--

CREATE TABLE user (
  userID SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  PRIMARY KEY  (userID)
);

INSERT INTO user(email, login, password)
	VALUES("root@gmail.ch", "root", "root");
	
INSERT INTO user(email, login, password)
	VALUES("patrick@heig-vd.ch", "patrick", "patrick");
	
INSERT INTO user(email, login, password)
	VALUES("laurent@heig-vd.ch", "laurent", "laurent");
	
INSERT INTO user(email, login, password)
	VALUES("olivier@heig-vd.ch", "olivier", "olivier");
	
INSERT INTO user(email, login, password)
	VALUES("sebastian@gmail.ch", "sebastian", "sebastian");
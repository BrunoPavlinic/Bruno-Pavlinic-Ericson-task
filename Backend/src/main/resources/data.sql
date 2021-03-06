DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Board;
DROP TABLE IF EXISTS Board_List;
DROP TABLE IF EXISTS Card;

CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL
);

CREATE TABLE Board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user INT,
    name VARCHAR(250)
);

CREATE TABLE Board_List (
    id INT AUTO_INCREMENT PRIMARY KEY,
    board INT,
    name VARCHAR(250)
);

CREATE TABLE Card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    list INT,
    text NVARCHAR
);

ALTER TABLE Board ADD FOREIGN KEY (user) REFERENCES User(id) ON DELETE CASCADE;
ALTER TABLE Board_List ADD FOREIGN KEY (board) REFERENCES Board(id) ON DELETE CASCADE;
ALTER TABLE Card ADD FOREIGN KEY (list) REFERENCES Board_List(id) ON DELETE CASCADE;
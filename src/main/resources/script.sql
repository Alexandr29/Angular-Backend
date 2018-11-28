CREATE TABLE user(id INT NOT NULL AUTO_INCREMENT,age INT,firstName VARCHAR(255),lastName VARCHAR(255),password VARCHAR(255),salary BIGINT,username VARCHAR(255),PRIMARY KEY (id))ENGINE=MyISAM;

INSERT INTO User (id, firstname, lastname, username, password, salary, age) VALUES (1, '1','1', '1','1', 3456, 33);
INSERT INTO User (id, firstname, lastname, username, password, salary, age)  VALUES (2, 'Tom', 'Asr', 'tom234', '$2a$04$QED4arFwM1AtQWkR3JkQx.hXxeAk/G45NiRd3Q4ElgZdzGYCYKZOW', 7823, 23);
INSERT INTO User (id, firstname, lastname, username, password, salary, age)  VALUES (3, 'Adam', 'Psr', 'adam', '$2a$04$WeT1SvJaGjmvQj34QG8VgO9qdXecKOYKEDZtCPeeIBSTxxEhazNla', 4234, 45);
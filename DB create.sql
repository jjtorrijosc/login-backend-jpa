/*CREATE DATABASE usuarios;
*/
CREATE TABLE usuario (
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30),
	password VARCHAR(30),
	email VARCHAR(60),
	provider VARCHAR(30),
	id_provider VARCHAR(100)
);


CREATE TABLE sesion (
   user_id BIGINT NOT NULL,
   acceso DATETIME NOT NULL,
   CONSTRAINT `sesion_user_id`
		FOREIGN KEY (user_id) REFERENCES usuario (user_id)
);
/*CREATE DATABASE usuarios;
*/
CREATE TABLE usuarios (
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(30),
	password VARCHAR(30),
	email VARCHAR(60),
	provider VARCHAR(30),
	id_provider VARCHAR(100)
);


CREATE TABLE sesiones (
   user_id BIGINT NOT NULL,
   acceso DATETIME NOT NULL,
   CONSTRAINT `sesion_user_id`
		FOREIGN KEY (user_id) REFERENCES usuarios (user_id)
);
CREATE TABLE Especie (
                         id SERIAL  NOT NULL ,
                         nombre VARCHAR(100)      ,
                         nombre_cientifico VARCHAR(100)      ,
                         PRIMARY KEY(id));

CREATE TABLE Dueño (
                       id SERIAL  NOT NULL ,
                       nombre VARCHAR(50)    ,
                       tipo_identificacion VARCHAR(2)    ,
                       identificacion VARCHAR(15)    ,
                       ciudad VARCHAR(40)    ,
                       direccion VARCHAR(40)    ,
                       telefono VARCHAR(20)      ,
                       PRIMARY KEY(id));

CREATE TABLE Raza (
                      id SERIAL  NOT NULL ,
                      id_especie INTEGER   NOT NULL ,
                      nombre VARCHAR(100)      ,
                      PRIMARY KEY(id, id_especie)  ,
                      FOREIGN KEY(id_especie)
                          REFERENCES Especie(id));

CREATE INDEX Raza_FKIndex1 ON Raza (id_especie);

CREATE INDEX IFK_pertenece ON Raza (id_especie);

CREATE TABLE Paciente (
                          id SERIAL  NOT NULL ,
                          id_especie INTEGER   NOT NULL ,
                          id_raza INTEGER   NOT NULL ,
                          id_dueño INTEGER   NOT NULL ,
                          nombre VARCHAR(25)    ,
                          fecha_nacimiento DATE    ,
                          fecha_registro DATE      ,
                          PRIMARY KEY(id_especie, id_raza, id_dueño, id)    ,
                          FOREIGN KEY(id_raza, id_especie)
                              REFERENCES Raza(id, id_especie),
                          FOREIGN KEY(id_dueño)
                              REFERENCES Dueño(id));

CREATE INDEX Paciente_FKIndex1 ON Paciente (id_raza, id_especie);
CREATE INDEX Paciente_FKIndex2 ON Paciente (id_dueño);

CREATE INDEX IFK_posee ON Paciente (id_raza, id_especie);
CREATE INDEX IFK_tiene ON Paciente (id_dueño);
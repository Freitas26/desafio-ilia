drop table IF EXISTS REGISTROS;
create table REGISTROS(
  id int not null AUTO_INCREMENT,
  dia varchar(30) not null,
  entrada varchar(30),
  pausa varchar(30),
  retorno varchar(30),
  saida varchar(30),
  PRIMARY KEY ( id )
);

INSERT INTO REGISTROS (id, dia, entrada, pausa, retorno, saida)
VALUES
(1, '2023-05-01','08:00:00','12:00:00','13:00:00','18:00:00'),
(2, '2023-05-02','08:00:00','12:00:00','13:00:00','18:00:00'),
(3, '2023-05-03', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(4, '2023-05-04', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(5, '2023-05-05', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(6, '2023-05-06', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(7, '2023-05-07', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(8, '2023-05-08', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(9, '2023-05-09', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(10, '2023-05-10', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(11, '2023-05-11','08:00:00','12:00:00','13:00:00','18:00:00'),
(12, '2023-05-12','08:00:00','12:00:00','13:00:00','18:00:00'),
--(13, '2023-05-13', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(14, '2023-05-14', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(15, '2023-05-15', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(16, '2023-05-16', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(17, '2023-05-17', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(18, '2023-05-18', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(19, '2023-05-19', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(20, '2023-05-20', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(21, '2023-05-21','08:00:00','12:00:00','13:00:00','18:00:00'),
(22, '2023-05-22','08:00:00','12:00:00','13:00:00','18:00:00'),
(23, '2023-05-23', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(24, '2023-05-24', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(25, '2023-05-25', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(26, '2023-05-26', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(27, '2023-05-27', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
--(28, '2023-05-28', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(29, '2023-05-29', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(30, '2023-05-30', '08:00:00', '12:00:00', '13:00:00', '18:00:00'),
(31, '2023-05-31', '08:00:00', '12:00:00', '13:00:00', '18:00:00');



CREATE DATABASE dbusercrud;

DROP DATABASE dbusercrud;

USE dbusercrud;

DROP TABLE `dbusercrud`.`t_user`;
CREATE TABLE `dbusercrud`.`t_user`(
  `uid` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50),
  `password` VARCHAR(50),
  `email` VARCHAR(30),
  `sex` VARCHAR(2),
  `province` VARCHAR(50),
  `hobby` VARCHAR(30),
  PRIMARY KEY (`uid`)
);

SELECT * FROM `dbusercrud`.`t_user`;

SELECT userinfo0_.uid AS uid1_0_,
   userinfo0_.email AS email2_0_,
   userinfo0_.hobby AS hobby3_0_,
   userinfo0_.password AS password4_0_,
   userinfo0_.province AS province5_0_,
   userinfo0_.sex AS sex6_0_,
   userinfo0_.username AS username7_0_
FROM t_user userinfo0_
WHERE (userinfo0_.username LIKE ?) AND LENGTH(userinfo0_.password)=3
create database bookManagement;
use bookManagement;

create table books
(
bookID int not null primary key,
genre varchar(30),
author varchar(30) default 'dan gian',
price float,
descriptions text,
fk_borrorOrderID int,
foreign key(fk_borrorOrderID) references borrowOrder(borrorOrderID)
);

create table students
(
studentID int not null primary key,
studentName varchar(30) not null,
email varchar(30),
image varchar(30),
address varchar(30)
);
alter table students
add borrowStatus boolean default false;

create table borrowOrder
(
borrorOrderID int not null primary key,
expiredDate date not null,
borrowDate date not null,
fk_studentID int not null,
foreign key(fk_studentID) references Students(studentID)
);

create table Categories(
bookGenre varchar(30) not null primary key
);

alter table books
add fk_bookGenre varchar(30) not null,
add foreign key (fk_bookGenre) references Categories(bookGenre)
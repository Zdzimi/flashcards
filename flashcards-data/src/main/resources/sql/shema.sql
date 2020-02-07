drop schema if exists flashcards;
create schema flashcards;
use flashcards;

create table category(
	category_id int not null auto_increment primary key,
    category_name char(30),
    last_update date
);

create table flashcard(
	flashcard_id int not null auto_increment primary key,
    question char(150),
    answer char(150),
    flashcard_status int,
    category_id int not null,
    foreign key category_id_fk (category_id) references category(category_id)
);
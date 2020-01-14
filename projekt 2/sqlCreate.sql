create table address (
    id uuid not null,
    address varchar(255),
    country varchar(255),
    post_code varchar(255),
    student_id uuid,
    primary key (id)
    foreign key (student_id) references student on delete cascade
)

create table student (
    id uuid not null,
    name varchar(255),
    second_name varchar(255),
    surname varchar(255),
    group_id int8,
    primary key (id)
    foreign key (group_id) references lecture_group on delete set null
)

create table student_lectures (
    students_id uuid not null,
    lectures_id int8 not null,
    primary key (students_id, lectures_id)
    foreign key (lectures_id) references lecture on delete cascade
    foreign key (students_id) references student on delete cascade
)

create table lecture (
    id int8 not null,
    max_students int4,
    name varchar(255),
    range varchar(255),
    primary key (id)
)

create table lecture_group (
    id int8 not null,
    max_students int4,
    name varchar(255),
    specialization varchar(255),
    primary key (id)
)

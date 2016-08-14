create table config (
  id                        bigint not null,
  key                       varchar(255),
  value                     varchar(255),
  primary key (id))
;

create table payment (
  id                        bigint not null,
  student_id                bigint not null,
  term_number               integer not null,
  amount                    bigint not null,
  payment_type_id           bigint not null,
  primary key (id))
;


create table payment_type (
  id                        bigint not null,
  value                     varchar(255),
  primary key (id))
;



insert into payment_type(id,value) values
    (0,'هدیه ورود'),
    (1,'کمک هزینه'),
    (2,'کمک شهریه'),
    (3,'تشویق ارتقا'),
    (4,'هدیه ممتازین'),
    (5,'هزینه اسکان'),
    (6,'هزینه پایان نامه'),
    (7,'هدیه فارغ التحصیلان');

create sequence payment_seq;
ALTER TABLE payment ALTER COLUMN ID BIGINT DEFAULT (NEXT VALUE FOR PUBLIC.payment_seq) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.payment_seq SELECTIVITY 100;

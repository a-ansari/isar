create table educational_degree (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_educational_degree primary key (id))
;

create table educational_field (
  id                        bigint not null,
  value                     varchar(255),
  educational_group_id      bigint,
  constraint pk_educational_field primary key (id))
;

create table educational_group (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_educational_group primary key (id))
;

create table educational_trend (
  id                        bigint not null,
  value                     varchar(255),
  educational_field_id      bigint,
  constraint pk_educational_trend primary key (id))
;

create table educational_type (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_educational_type primary key (id))
;

create table graduation_status (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_graduation_status primary key (id))
;

create table professor (
  id                        bigint not null,
  value                     varchar(255),
  educational_field_id      bigint,
  constraint pk_professor primary key (id))
;

create table segment (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_segment primary key (id))
;

create table student (
  id                        bigint not null,
  student_number            bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  national_number           bigint,
  birth_cert_number         bigint,
  birth_date                varchar(255),
  issue_place               varchar(255),
  father_name               varchar(255),
  gender                    integer,
  marriage                  integer,
  diploma_type              varchar(255),
  diploma_average           double,
  diploma_year              integer,
  diploma_place             varchar(255),
  segment_id                bigint,
  segment_info              varchar(255),
  segment_rank              integer,
  total_rank                integer,
  entrance_year             integer,
  educational_type_id       bigint,
  educational_degree_id     bigint,
  educational_group_id      bigint,
  educational_field_id      bigint,
  educational_trend_id      bigint,
  professor_id              bigint,
  graduation_status_id      bigint,
  graduation_info           varchar(255),
  address                   varchar(255),
  phone_number              varchar(255),
  mobile_number             varchar(255),
  emergency_number          varchar(255),
  email                     varchar(255),
  account_number            varchar(255),
  last_update_time          timestamp not null,
  constraint ck_student_gender check (gender in (0,1)),
  constraint ck_student_marriage check (marriage in (0,1)),
  constraint uq_student_student_number unique (student_number),
  constraint pk_student primary key (id))
;

create table term (
  id                        bigint not null,
  student_id                bigint not null,
  term_number               integer not null,
  taken_units               integer not null,
  passed_units              integer not null,
  deleted_units             integer not null,
  failed_units              integer not null,
  unspecified_units         integer not null,
  zero_units                integer not null,
  term_average              double not null,
  total_average             double not null,
  term_status_id            bigint not null,
  constraint pk_term primary key (id))
;

create table term_status (
  id                        bigint not null,
  value                     varchar(255),
  constraint pk_term_status primary key (id))
;

create sequence educational_degree_seq;

create sequence educational_field_seq;

create sequence educational_group_seq;

create sequence educational_trend_seq;

create sequence educational_type_seq;

create sequence graduation_status_seq;

create sequence professor_seq;

create sequence segment_seq;

create sequence student_seq;

create sequence term_seq;

create sequence term_status_seq;

alter table educational_field add constraint fk_educational_field_education_1 foreign key (educational_group_id) references educational_group (id) on delete restrict on update restrict;
create index ix_educational_field_education_1 on educational_field (educational_group_id);
alter table educational_trend add constraint fk_educational_trend_education_2 foreign key (educational_field_id) references educational_field (id) on delete restrict on update restrict;
create index ix_educational_trend_education_2 on educational_trend (educational_field_id);
alter table professor add constraint fk_professor_educationalField_3 foreign key (educational_field_id) references educational_field (id) on delete restrict on update restrict;
create index ix_professor_educationalField_3 on professor (educational_field_id);
alter table student add constraint fk_student_segment_4 foreign key (segment_id) references segment (id) on delete restrict on update restrict;
create index ix_student_segment_4 on student (segment_id);
alter table student add constraint fk_student_educationalType_5 foreign key (educational_type_id) references educational_type (id) on delete restrict on update restrict;
create index ix_student_educationalType_5 on student (educational_type_id);
alter table student add constraint fk_student_educationalDegree_6 foreign key (educational_degree_id) references educational_degree (id) on delete restrict on update restrict;
create index ix_student_educationalDegree_6 on student (educational_degree_id);
alter table student add constraint fk_student_educationalGroup_7 foreign key (educational_group_id) references educational_group (id) on delete restrict on update restrict;
create index ix_student_educationalGroup_7 on student (educational_group_id);
alter table student add constraint fk_student_educationalField_8 foreign key (educational_field_id) references educational_field (id) on delete restrict on update restrict;
create index ix_student_educationalField_8 on student (educational_field_id);
alter table student add constraint fk_student_educationalTrend_9 foreign key (educational_trend_id) references educational_trend (id) on delete restrict on update restrict;
create index ix_student_educationalTrend_9 on student (educational_trend_id);
alter table student add constraint fk_student_professor_10 foreign key (professor_id) references professor (id) on delete restrict on update restrict;
create index ix_student_professor_10 on student (professor_id);
alter table student add constraint fk_student_graduationStatus_11 foreign key (graduation_status_id) references graduation_status (id) on delete restrict on update restrict;
create index ix_student_graduationStatus_11 on student (graduation_status_id);
alter table term add constraint fk_term_student_12 foreign key (student_id) references student (id) on delete restrict on update restrict;
create index ix_term_student_12 on term (student_id);
alter table term add constraint fk_term_termStatus_13 foreign key (term_status_id) references term_status (id) on delete restrict on update restrict;
create index ix_term_termStatus_13 on term (term_status_id);



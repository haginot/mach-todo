# --- !Ups

create table "TASK" ("TITLE" VARCHAR NOT NULL PRIMARY KEY,"STATUS" VARCHAR NOT NULL);

# --- !Downs

drop table "TASK";
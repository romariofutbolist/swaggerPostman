--liquibase formatted sql

--changeset artemev:1

CREATE INDEX st_name_idx ON student (name);

--changeset artemev:2

CREATE INDEX fc_color_name_idx ON faculty (name, color);

CREATE EXTENSION "uuid-ossp";
select * from pg_extension;
CREATE TABLE public.user_info
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    dossier_id varchar NOT NULL,
    email varchar (50) NOT NULL,
    phone_number varchar(15),
    CONSTRAINT user_info_pkey PRIMARY KEY (id)
);
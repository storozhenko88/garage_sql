CREATE TABLE public.cars
(   car_id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    brand text,
    owner_id integer,
    primary key (car_id)
);

CREATE TABLE public.owners
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name text,
    surname text,
    primary key (user_id)
);

CREATE TABLE public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    email text,
    password text,
    first_name text,
    last_name text,
    primary key (id)
);
CREATE TABLE public.cars
(   car_id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    brand text,
    owner_id integer,
    primary key (car_id)
);

CREATE TABLE public.users
(
    user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
    name text,
    surname text,
    primary key (user_id)
);
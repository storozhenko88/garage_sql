CREATE TABLE "cars"
(   car_id integer NOT NULL AUTO_INCREMENT,
    brand text,
    owner_id integer,
    primary key (car_id)
);

CREATE TABLE "users"
(
    user_id integer NOT NULL AUTO_INCREMENT,
    name text,
    surname text,
    primary key (user_id)
);


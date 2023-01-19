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

//INSERT INTO "cars" (car_id ,brand, owner_id) VALUES (1, 'bmw', 1 );

DELETE FROM cav_line;
DELETE FROM cav_shopping_cart;
DELETE FROM cav_client;
DELETE FROM cav_owner;
DELETE FROM cav_user;
DELETE FROM cav_address;

DELETE FROM cav_bottles;
DELETE FROM cav_colors;
DELETE FROM cav_regions;

INSERT INTO cav_address
(street , zip_code  , city) VALUES
    ('123 Trois ptits chats', '12300', 'Catcity'),
    ('42 Rue du voyageur', '42000', 'Galaxy');


INSERT INTO cav_user
(login  , password  , last_name , first_name , role) VALUES
    ('log1', 'pwd1', 'NEYMAR'   , 'Jean'   , 'client'),
    ('log2', 'pwd2', 'CASSIN'   , 'Marc'   , 'client'),
    ('log3', 'pwd3', 'BONNEAU'  , 'Jean'   , 'client'),
    ('log4', 'pwd4', 'DRUKER'   , 'Michel' , 'owner'),
    ('log5', 'pwd5', 'BERGER'   , 'Michel' , 'owner'),
    ('log6', 'pwd6', 'POLNAREF' , 'Michel' , 'owner'),
    ('log7', 'pwd7', 'BALTAZAR' , 'Picsou' , 'user'),
    ('log8', 'pwd8', 'DUCK'     , 'Donald' , 'user'),
    ('log9', 'pwd9', 'MOUSE'    , 'Mickey' , 'user');

INSERT INTO cav_client
(login  , address_id) VALUES
    ('log1', (SELECT cav_address.id FROM cav_address WHERE street = '123 Trois ptits chats')),
    ('log2', (SELECT cav_address.id FROM cav_address WHERE street = '42 Rue du voyageur')),
    ('log3', NULL);

INSERT INTO cav_owner
(login  ,  client_number) VALUES
    ('log4',  '85203183000015'),
    ('log5',  '85203183000023'),
    ('log6',  '85203183000038');

INSERT INTO cav_shopping_cart
(order_number, total_price, paid, client_id) VALUES
    ('C00123', 3*11.45, 1, 'log1'),
    ('C00170', 3*11.45, 1, 'log1'),
    (null, 3*11.45, 0, 'log1');

INSERT INTO cav_line
(quantity, price, shopping_cart_id) VALUES
    (3, 3*11.45, (SELECT cav_shopping_cart.id FROM cav_shopping_cart WHERE order_number = 'C00123'));


INSERT INTO cav_regions (name) VALUES ('Pays de la Loire'), ('Grand Est'), ('Nouvelle Aquitaine');
INSERT INTO cav_colors (name) VALUES ('Rosé'), ('Rouge'), ('Blanc');

INSERT INTO cav_bottles (name, sparkling, vintage, quantity, price, region_id, color_id)
VALUES
    ('Blanc du domaine ENI école', 0, '2022', 1298, 23.95, (SELECT id FROM cav_regions WHERE name = 'Pays de la Loire'), (SELECT id FROM cav_colors WHERE name = 'Blanc')),
    ('Rouge du domaine ENI école', 0, '2018', 987, 11.45, (SELECT id FROM cav_regions WHERE name = 'Pays de la Loire'), (SELECT id FROM cav_colors WHERE name = 'Rouge')),
    ('Blanc du domaine ENI service', 1, '2022', 111, 34, (SELECT id FROM cav_regions WHERE name = 'Grand Est'), (SELECT id FROM cav_colors WHERE name = 'Blanc')),
    ('Rouge du domaine ENI service', 0, '2012', 344, 8.15, (SELECT id FROM cav_regions WHERE name = 'Pays de la Loire'), (SELECT id FROM cav_colors WHERE name = 'Rouge')),
    ('Rosé du domaine ENI', 0, '2022', 1987, 33, (SELECT id FROM cav_regions WHERE name = 'Nouvelle Aquitaine'), (SELECT id FROM cav_colors WHERE name = 'Rosé'));
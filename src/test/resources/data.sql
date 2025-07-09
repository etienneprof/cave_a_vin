
DELETE FROM cav_line;
DELETE FROM cav_shopping_cart;
DELETE FROM cav_client;
DELETE FROM cav_address;

INSERT INTO cav_address
(street , zip_code  , city) VALUES
    ('123 Trois ptits chats', '12300', 'Catcity'),
    ('42 Rue du voyageur', '42000', 'Galaxy');

INSERT INTO cav_client
(login  , password  , last_name , first_name    , address_id) VALUES
    ('log1', 'pwd1', 'NEYMAR'   , 'Jean'    , (SELECT cav_address.id FROM cav_address WHERE street = '123 Trois ptits chats')),
    ('log2', 'pwd2', 'CASSIN'   , 'Marc'    , (SELECT cav_address.id FROM cav_address WHERE street = '42 Rue du voyageur')),
    ('log3', 'pwd3', 'BONNEAU'  , 'Jean'    , NULL);

INSERT INTO cav_shopping_cart
(order_number, total_price, paid) VALUES
    ('C00123', 3*11.45, 1);

INSERT INTO cav_line
(quantity, price, shopping_cart_id) VALUES
    (3, 3*11.45, (SELECT cav_shopping_cart.id FROM cav_shopping_cart WHERE order_number = 'C00123'));


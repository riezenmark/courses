--product(id, name, type_id, expired_date, price)
--type(id, name)

select p.id, p.name, p.price, t.name as type from
product as p join type as t on p.type_id = t.id where
t.name = 'СЫР';

select * from product as p where p.name like '%мороженое%';

select * from product as p where p.expired_date < 1;

select * from product as p where p.price = (select max(price) from product);

select count(p.id) from product as p join type as t on p.type_id = t.id where t.name = 'тип продукта';

select p.id, p.name, p.price, t.name as type from
product as p join type as t on p.type_id = t.id where
t.name = 'СЫР' and t.name = 'МОЛОКО';

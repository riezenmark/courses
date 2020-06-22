select c.number, cc.type as carcase_type,
cc.manufacturer as carcase_manufacturer,
e.fuel, e.manufacturer as engine_manufacturer,
t.type as transmission_type,
t.manufacturer as transmission_manufacturer
from car as c join carcase as cc
on c.carcase_id = cc.id join engine as e
on c.engine_id = e.id join transmission as t
on c.transmission_id = t.id;

select cc.type as carcase_type,
cc.manufacturer as carcase_manufacturer
from car as c right outer join carcase as cc
on c.carcase_id = cc.id where c.number is null;

select e.fuel, e.manufacturer from engine as e left outer join
car as c on e.id = c.engine_id where c.number is null;

select t.type, t.manufacturer from transmission as t
left outer join car as c on t.id = c.transmission_id
where c."number" is null;

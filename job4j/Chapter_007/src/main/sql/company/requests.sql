select p.name, c.name as company from
person as p join company as c on
p.company_id = c.id where
p.company_id != 5;

select count(company_id) as number_of_persons, c.name as company_name from
person as p join company as c on
p.company_id = c.id group by company_id, c.name
order by number_of_persons desc limit 1;

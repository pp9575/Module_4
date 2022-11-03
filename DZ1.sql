CREATE TABLE department (id serial primary key, name varchar(30), isprofit boolean);

INSERT INTO department (name, isprofit) 
VALUES ('Бухгалтерия', 'no'), ('Кредитный отдел', 'yes'),
	('Отдел продаж', 'yes'), ('Правление', 'no');
	
CREATE TABLE employee (id serial primary key, full_name varchar(50), salary integer,
			department_id integer REFERENCES department (id));
					   
INSERT INTO employee (full_name, salary, department_id) 
VALUES ('Петров Иван', '30000', '3'), 'Иванова Наталья', '50000', '1'),
	('Мирских Петр', '100000', '4'), ('Улюкаев Владимир', '200000', '4'),
	('Заморский Виктор', '70000', '2');	

SELECT * FROM employee
WHERE department_id = 4;

SELECT SUM(salary) FROM employee;

SELECT full_name, isprofit
FROM department AS d
INNER JOIN employee AS e
ON d.id = e.department_id
WHERE d.isprofit = true;

SELECT * FROM employee
WHERE salary BETWEEN 10000 AND 100000;

DELETE FROM employee
WHERE id = 3;

UPDATE department
SET name = 'Депозитный отдел', isprofit = 'false'
WHERE id = 2;

SELECT *
FROM employee
WHERE full_name ILIKE '%иван%';

SELECT d.name AS отдел, AVG (salary) AS "средняя зарплата"
FROM department AS d
INNER JOIN employee AS e
ON d.id = e.department_id
GROUP BY d.name;

DROP TABLE department, employee;

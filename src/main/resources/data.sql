INSERT INTO role (`id`, `name`) 
select '1', 'Developer'
where not exists (select *from role where id=1);

INSERT INTO role (`id`, `name`) 
select '2', 'Employee'
where not exists (select *from role where id=2);


INSERT INTO role (`id`, `name`) select '2', 'Employee'
where not exists (select *from role);;

INSERT INTO department (`id`, `department_name`) select '1', 'ECE'
where not exists (select *from department where id=1);

INSERT INTO department (`id`, `department_name`) select '2', 'CSE'
where not exists (select *from department where id=2);

commit;


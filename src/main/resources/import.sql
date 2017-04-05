-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert users
-- 1 = role id
insert into user (username,enabled,password,role_id) values ('gottlieb_wolf',true,'password',1);
insert into user (username,enabled,password,role_id) values ('andy_wolf',true,'password',1);

-- Insert tasks
-- inserts initial data into the database when starting the application
insert into task (complete,description) values (true,'Code Task entity');
insert into task (complete,description) values (false,'Discuss users and roles');
insert into task (complete,description) values (false,'Enable Spring Security');
insert into task (complete,description) values (false,'Test application');
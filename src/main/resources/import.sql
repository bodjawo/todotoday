-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert users
-- 1 = role id
insert into user (username,enabled,password,role_id) values ('gottlieb_wolf',true,'password',1);
insert into user (username,enabled,password,role_id) values ('andy_wolf',true,'password',1);

-- Insert tasks
-- inserts initial data into the database when starting the application
insert into task (complete,description, user_id) values (true,'Code Task entity',1);
insert into task (complete,description, user_id) values (false,'Discuss users and roles',1);
insert into task (complete,description, user_id) values (false,'Enable Spring Security',2);
insert into task (complete,description, user_id) values (false,'Test application',2);
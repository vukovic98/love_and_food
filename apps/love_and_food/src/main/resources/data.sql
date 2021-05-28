/*insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');*/

insert into admin(user_id, enabled, email, password) values 
(100, true, 'admin@admin.com', '$2y$12$GD/wuk7wwrDSQ2ICr.CK1OZKYxZB9Far0kUpFARhrIP2GS4huD9tu ');

insert into user_authority(user_id, authority_id) values (100, 1)
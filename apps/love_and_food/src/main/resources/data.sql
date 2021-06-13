insert into authority (name) values ('ROLE_ADMIN');
insert into authority (name) values ('ROLE_USER');

insert into admin(user_id, enabled, email, password) values 
(100, true, 'admin@admin.com', '$2y$10$vpw/lerK.jMstpE9tnkVSe0eIlCkH37JwLUBWIJWRwZC4zi7AshXO');

insert into user_authority(user_id, authority_id) values (100, 1)
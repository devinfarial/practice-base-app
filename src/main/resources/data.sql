--H2 DATABASE

--USERS
INSERT INTO public.users (id, email, enabled, locked, password, username) VALUES(11, 'dfarial17@gmail.com', true, false, '$2a$10$AQMvERg25Rp0ghl4ICoJYuMxqu7gpwHn5QuZbLxUvNbK.1YTOI65C', 'devin');

--ROLES
INSERT INTO public.roles (id, name) VALUES(1, 'ROLE_STAFF');
INSERT INTO public.roles (id, name) VALUES(2, 'ROLE_ADMIN');

--H2 DATABASE

--USERS
INSERT INTO public.users (id, email, enabled, locked, password, username) VALUES(11, 'dfarial17@gmail.com', true, false, '$2a$10$AQMvERg25Rp0ghl4ICoJYuMxqu7gpwHn5QuZbLxUvNbK.1YTOI65C', 'devin');
INSERT INTO public.users (id, email, enabled, locked, password, username) VALUES(12, 'farial@gmail.com', false, false, '$2a$10$AQMvERg25Rp0ghl4ICoJYuMxqu7gpwHn5QuZbLxUvNbK.1YTOI65C', 'farial');
INSERT INTO public.users (id, email, enabled, locked, password, username) VALUES(13, 'test2@gmail.com', false, false, '$2a$10$AQMvERg25Rp0ghl4ICoJYuMxqu7gpwHn5QuZbLxUvNbK.1YTOI65C', 'test_token_expired');

--ROLES
INSERT INTO public.roles (id, name) VALUES(1, 'ROLE_STAFF');
INSERT INTO public.roles (id, name) VALUES(2, 'ROLE_ADMIN');

--EMAIL_VERFICATION
INSERT INTO public.email_verification(id, confirmed_at, created_at, expires_at, token, user_id)VALUES(17, NULL, '2022-09-25 16:54:57.018', '2077-09-25 17:09:57.018', 'aa8c0ef7-d9a2-47f1-9ff5-855dea4ceea5', 12);
INSERT INTO public.email_verification(id, confirmed_at, created_at, expires_at, token, user_id)VALUES(18, NULL, '2099-09-25 16:54:57.018', '2022-09-25 17:09:57.018', 'bb0v0sj2-d9a2-47f1-9ff5-855dea4ceea5', 13);

/*add Roles*/
INSERT INTO public.t_role(id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');


/*add Admin account
password is 191220*/
insert into users(id, password, username)
values( 1, '$2a$10$sT.5EfS4AHbnyucJbimrgeZQbkYN698W.SrsCmJlG6c.k2FoafA2W', 'Admin');
insert into users_roles(user_id, roles_id)
values(1, 2);



insert into book(id, author, category, cost, name, pages, year)
values
(1, 'Пушкин', 'Роман', 18, 'Евгений Онегин', 230, 1878),
(2, 'Достоевский', 'Роман', 67, 'Идиот', 211, 1861),
(3, 'Л.Н.Толстой', 'Роман-эпопея', 119, 'Война и мир', 830, 1865);
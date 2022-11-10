INSERT INTO "user" ("name", "password", created_at)
    VALUES
	('Yurii', 'M3rzkii', 'now'),
	('Vlad', 'password', '01/02/2022 14:53:19'),
	('Konstantin', 'qwerty', 'yesterday');

INSERT INTO post (text, created_at, user_id)
    VALUES
	('Vsem chmoki', 'yesterday', '1'),
	('Dratuti', '06.11.2022', '2'),
	('Хороший день', '09.11.2022 12:56:32', '2');

INSERT INTO "comment" (text, post_id, user_id, created_at)
    VALUES
	('Moy coomment', '1', '1', 'now'),
	('text something', '2', '1', 'yesterday');

INSERT INTO "like" (user_id, post_id, comment_id)
    VALUES
	('1', '1', NULL),
	('1', NULL, '2');
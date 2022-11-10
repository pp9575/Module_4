CREATE TABLE "user" (
	id serial primary key,
	name varchar(30) NOT NULL UNIQUE,
	password varchar(30) NOT NULL,
	created_at timestamp
);

CREATE TABLE post (
	id serial primary key,
	text varchar(100) NOT NULL,
	created_at timestamp,
	user_id int REFERENCES "user"(id)
);

CREATE TABLE comment (
	id serial primary key,
	text varchar(100) NOT NULL,
	post_id int REFERENCES post(id) NOT NULL,
	user_id int REFERENCES "user"(id) NOT NULL,
	created_at timestamp
);

CREATE TABLE "like" (
	id serial primary key,
	user_id int REFERENCES "user"(id) NOT NULL,
	post_id int REFERENCES post(id),
	comment_id int REFERENCES "comment"(id),
	CHECK (post_id is NOT NULL OR comment_id is NOT NULL)
);
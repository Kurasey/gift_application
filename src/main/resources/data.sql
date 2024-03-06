DROP TABLE IF EXISTS user_details CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS gift_details CASCADE;
DROP TABLE IF EXISTS gift_detail_categories CASCADE;
DROP TABLE IF EXISTS favorite_gifts CASCADE;
DROP TABLE IF EXISTS subscription_details CASCADE;
DROP TABLE IF EXISTS subscription_available_categories CASCADE;
DROP TABLE IF EXISTS booking_details CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

CREATE TABLE IF NOT EXISTS user_details(
    user_id bigserial PRIMARY KEY,
    username varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255)  NOT NULL,
    accountNonExpired boolean NOT NULL,
    enabled boolean NOT NULL,
    accountNonLocked boolean NOT NULL,
    credentialsNonExpired boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles (
	user_id bigint NOT NULL,
	role varchar(255) NOT NULL,
	PRIMARY KEY (user_id, role)
);

CREATE TABLE IF NOT EXISTS gift_details(
    gift_id bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    descriprion text,
    article_number varchar (255),
    link text,
    commentary text,
    rate smallint NOT NULL,
	CHECK (rate>=0 AND rate<=10)
);

CREATE TABLE IF NOT EXISTS gift_detail_categories (
	categories_order integer NOT NULL,
	category_id bigint NOT NULL,
	gift_detail_id bigint NOT NULL,
	PRIMARY KEY (category_id, gift_detail_id)
);

CREATE TABLE IF NOT EXISTS favorite_gifts(
    favorite_id bigserial PRIMARY KEY,
    gift_id bigint NOT NULL,
	user_id bigint NOT NULL,
    rate smallint NOT NULL,
    comment text,
	CHECK (rate>=0 AND rate<=10)
);

CREATE TABLE IF NOT EXISTS subscription_details(
	subscription_id bigserial PRIMARY KEY,
	receiver_id bigint NOT NULL,
	subscriber_id bigint NOT NULL,
	available_min_rate smallint NOT NULL,
	available_max_rate smallint NOT NULL,
	CHECK (available_min_rate>=0 AND available_min_rate<=10),
	CHECK (available_max_rate>=0 AND available_max_rate<=10)
);

CREATE TABLE IF NOT EXISTS subscription_available_categories(
	subscription_id bigint NOT NULL,
	category_id bigint NOT NULL,
	PRIMARY KEY (subscription_id, category_id)
);

CREATE TABLE IF NOT EXISTS booking_details(
	booking_ig bigserial PRIMARY KEY,
	gift_ig bigint UNIQUE NOT NULL,
	user_booked_id bigint NOT NULL,
	user_gift_id bigint NOT NULL,
	booked_at date NOT NULL
);

CREATE TABLE IF NOT EXISTS categories(
	category_id bigserial PRIMARY KEY,
	user_id bigint NOT NULL,
	description text,
	rate smallint NOT NULL,
	commentary text,
	available_on_default boolean NOT NULL,
	CHECK (rate>=0 AND rate<=10)
);

CREATE UNIQUE INDEX ON user_details (username);
ALTER TABLE IF EXISTS user_roles ADD FOREIGN KEY (user_id) REFERENCES user_details(user_id);
ALTER TABLE IF EXISTS gift_details ADD FOREIGN KEY (user_id) REFERENCES user_details(user_id);
ALTER TABLE IF EXISTS gift_detail_categories ADD FOREIGN KEY (category_id) REFERENCES categories (category_id);
ALTER TABLE IF EXISTS gift_detail_categories ADD FOREIGN KEY (gift_detail_id) REFERENCES gift_details(gift_id);
ALTER TABLE IF EXISTS favorite_gifts ADD FOREIGN KEY (gift_id) REFERENCES gift_details (gift_id);
ALTER TABLE IF EXISTS favorite_gifts ADD FOREIGN KEY (user_id) REFERENCES user_details (user_id);
ALTER TABLE IF EXISTS subscription_details ADD FOREIGN KEY (receiver_id) REFERENCES user_details (user_id);
ALTER TABLE IF EXISTS subscription_details ADD FOREIGN KEY (subscriber_id) REFERENCES user_details (user_id);
ALTER TABLE IF EXISTS subscription_available_categories ADD FOREIGN KEY (subscription_id) REFERENCES subscription_details (subscription_id);
ALTER TABLE IF EXISTS subscription_available_categories ADD FOREIGN KEY (category_id) REFERENCES categories (category_id);
ALTER TABLE IF EXISTS booking_details ADD FOREIGN KEY (gift_ig) REFERENCES gift_details (gift_id);
ALTER TABLE IF EXISTS booking_details ADD FOREIGN KEY (user_booked_id) REFERENCES user_details (user_id);
ALTER TABLE IF EXISTS booking_details ADD FOREIGN KEY (user_gift_id) REFERENCES user_details(user_id);
ALTER TABLE IF EXISTS categories ADD FOREIGN KEY (user_id) REFERENCES user_details (user_id);

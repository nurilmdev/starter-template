CREATE TABLE `mst_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) not null,
  `name` varchar(150) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `creation_date` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `last_update_by` varchar(20) DEFAULT NULL,
  `last_update_date` datetime(6) DEFAULT NULL,
  `enabled_flag` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_email_UNIQUE` (`email`)
);

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `creation_date` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `last_update_by` varchar(20) DEFAULT NULL,
  `last_update_date` datetime(6) DEFAULT NULL,
  `enabled_flag` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `creation_date` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  `last_update_by` varchar(20) DEFAULT NULL,
  `last_update_date` datetime(6) DEFAULT NULL,
  `enabled_flag` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`id`),
  foreign key(user_id) references mst_user(id),
  foreign key(role_id) references role(id)
);

insert into mst_user (id, username, password, name, email, created_by)
			values(1, 'admin', '$2a$10$KWog4jQUoHm6hls/BFweO.7JUhZuE2EqUW/w3z6/m5/2LF5IPKMVq', 'admin', 'admin@email.com', 'SYSTEM');

insert into role (id, name, created_by)
            values(1, 'ROLE_ADMIN', 'SYSTEM');

insert into user_roles(id, user_id, role_id, created_by)
            values(1, 1, 1, 'SYSTEM');

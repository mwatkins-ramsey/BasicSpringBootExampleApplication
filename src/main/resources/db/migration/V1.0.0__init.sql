use messaging;

CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname  VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS messages
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    message_body LONGTEXT NOT NULL,
    FOREIGN KEY (author_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    INDEX(author_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS users_messages
(
    user_id BIGINT(11),
    message_id BIGINT(11),
    PRIMARY KEY(user_id, message_id),
    INDEX(user_id),
    INDEX(message_id),
    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    FOREIGN KEY (message_id)
        REFERENCES messages(id)
        ON DELETE CASCADE
) ENGINE=INNODB;
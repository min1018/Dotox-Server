-- 기존 테이블이 존재할 경우 삭제
DROP TABLE IF EXISTS FriendRequest;
DROP TABLE IF EXISTS FriendList;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS oauth2_authorized_client;
DROP TABLE IF EXISTS UserEntity;


CREATE TABLE oauth2_authorized_client (
                                          client_registration_id VARCHAR(100) NOT NULL,
                                          principal_name VARCHAR(200) NOT NULL,
                                          access_token_type VARCHAR(100),
                                          access_token_value BLOB NOT NULL,
                                          access_token_issued_at TIMESTAMP,
                                          access_token_expires_at TIMESTAMP,
                                          access_token_scopes VARCHAR(1000),
                                          refresh_token_value BLOB,
                                          refresh_token_issued_at TIMESTAMP,
                                          PRIMARY KEY (client_registration_id, principal_name)
);

CREATE TABLE UserEntity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE Account (
    account_id VARCHAR(255) NOT NULL PRIMARY KEY,
    nick_name VARCHAR(255),
    email VARCHAR(255),
    hobby VARCHAR(255),
    gender VARCHAR(255),
    age VARCHAR(255),
    screen_time BIGINT
);


CREATE TABLE FriendList (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id1 VARCHAR(255) NOT NULL,
    id2 VARCHAR(255) NOT NULL,
    id1_screentime TIMESTAMP,
    id2_screentime TIMESTAMP,
    FOREIGN KEY (id1) REFERENCES Account(account_id),
    FOREIGN KEY (id2) REFERENCES Account(account_id)
);

CREATE TABLE FriendRequest (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    request_id VARCHAR(255) NOT NULL,
    requested_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (request_id) REFERENCES Account(account_id),
    FOREIGN KEY (requested_id) REFERENCES Account(account_id)
);



-- 기존 테이블이 존재할 경우 삭제
DROP TABLE IF EXISTS FriendRequest;
DROP TABLE IF EXISTS FriendList;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS oauth2_authorized_client;

-- oauth2_authorized_client 테이블 생성
CREATE TABLE oauth2_authorized_client (
                                          client_registration_id VARCHAR(100) NOT NULL,
                                          principal_name VARCHAR(200) NOT NULL,
                                          access_token_type VARCHAR(100) NOT NULL,
                                          access_token_value BLOB NOT NULL,
                                          access_token_issued_at TIMESTAMP NOT NULL,
                                          access_token_expires_at TIMESTAMP NOT NULL,
                                          access_token_scopes VARCHAR(1000) DEFAULT NULL,
                                          refresh_token_value BLOB DEFAULT NULL,
                                          refresh_token_issued_at TIMESTAMP DEFAULT NULL,
                                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                                          PRIMARY KEY (client_registration_id, principal_name)
);

-- Account 테이블 생성
CREATE TABLE Account (
                         account_id VARCHAR(255) NOT NULL PRIMARY KEY,
                         nick_name VARCHAR(255),
                         email VARCHAR(255),
                         hobby VARCHAR(255),
                         gender VARCHAR(255),
                         age VARCHAR(255)
);

-- FriendList 테이블 생성
CREATE TABLE FriendList (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            id1 VARCHAR(255),
                            id2 VARCHAR(255),
                            id1_screentime TIMESTAMP,
                            id2_screentime TIMESTAMP,
                            FOREIGN KEY (id1) REFERENCES Account(account_id) ON DELETE CASCADE,
                            FOREIGN KEY (id2) REFERENCES Account(account_id) ON DELETE CASCADE
);

-- FriendRequest 테이블 생성
CREATE TABLE FriendRequest (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               request_id VARCHAR(255),
                               requested_id VARCHAR(255),
                               FOREIGN KEY (request_id) REFERENCES Account(account_id) ON DELETE CASCADE,
                               FOREIGN KEY (requested_id) REFERENCES Account(account_id) ON DELETE CASCADE
);

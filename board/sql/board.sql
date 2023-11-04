CREATE DATABASE board;
use board;
CREATE TABLE board (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    content TEXT NOT NULL
)


# MySQL 프로시저 testDataInsert는 테스트 데이터를 board 테이블에 삽입하기 위한 것
# 프로시저를 실행하면, '제목1', '내용1'부터 시작하여 '제목120', '내용120'까지 총 120개의 행이 board 테이블에 삽입
DROP PROCEDURE IF EXISTS testDataInsert;
CREATE PROCEDURE testDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 120 DO
        INSERT INTO board(title, content)
            VALUES (concat('제목',i), concat('내용',i));
        SET i = i +1;
        end while;
end;

CALL testDataInsert();

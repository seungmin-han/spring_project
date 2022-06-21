drop schema `pizza`;

create schema `pizza` character set `utf8`;

use `pizza`;

#회원 테이블
CREATE TABLE `member` (
	`memberSeq` BIGINT AUTO_INCREMENT,
    `memberFirstName` VARCHAR(50) NOT NULL,
    `memberLastName` VARCHAR(50) NOT NULL,
    `memberId` VARCHAR(50) NOT NULL,
    `memberPw` VARCHAR(255) NOT NULL,
    `memberNickName` VARCHAR(16) NOT NULL,
    `memberEmail` VARCHAR(255) NOT NULL,
    `memberDob` DATE NOT NULL,
    `memberGenderCd` CHAR(1) DEFAULT 'O' COMMENT 'M:남성, F:여성, O:기타',
    `memberDelNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:존재, 1:삭제',
    `memberAdminNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:회원, 1:관리자',
    primary key(`memberSeq`),
    unique key(`memberId`)
);

#회원 태그 테이블
CREATE TABLE `memberTag` (
	`memberTagSeq` BIGINT AUTO_INCREMENT,
    `memberTagName` varchar(16),
    `memberSeq` BIGINT, 
    PRIMARY KEY(`memberTagSeq`),
    CONSTRAINT `fk_member_memberTag`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


#그룹 테이블
CREATE TABLE `tbl_group` (
	`groupSeq` BIGINT AUTO_INCREMENT,
    `groupName` VARCHAR(50) NOT NULL,
    `groupText` VARCHAR(100) NOT NULL,
    `groupJoinAcceptNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:자동가입, 1:승인가입',
    `groupDelNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:존재, 1:삭제',
	`memberSeq` BIGINT NOT NULL,
    PRIMARY KEY(`groupSeq`),
    UNIQUE KEY(`groupName`),
    CONSTRAINT `fk_member_group`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `groupImage` (
	`groupImageSeq` BIGINT AUTO_INCREMENT,
    `groupImageName` varchar(200) not null,
    `groupImageType` varchar(200) not null,
    `groupImagePath` TEXT not null,
    `groupImageSize` int not null,
    `groupImageDelNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:존재, 1:삭제',
    `groupImageThumNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:업로드된 사진, 1:그룹 대표사진',
    `groupSeq` BIGINT NOT NULL,
    PRIMARY KEY(`groupImageSeq`),
    CONSTRAINT `fk_group_groupImage`
    FOREIGN KEY (`groupSeq`)
    REFERENCES `pizza`.`tbl_group` (`groupSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

#그룹 태그 테이블
CREATE TABLE `groupTag` (
	`groupTagSeq` BIGINT AUTO_INCREMENT,
    `groupTagName` VARCHAR(16) NOT NULL, 
    `groupSeq` BIGINT, 
    PRIMARY KEY(`groupTagSeq`),
    CONSTRAINT `fk_group_groupTag`
    FOREIGN KEY (`groupSeq`)
    REFERENCES `pizza`.`tbl_group` (`groupSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

#그룹 가입 상태 테이블
CREATE TABLE `joinStatus` (
	`joinStatusSeq` BIGINT AUTO_INCREMENT,
    `joinStatusCd` CHAR(1) NOT NULL COMMENT 'N:추방, Y:가입, W:대기',
    `memberSeq` BIGINT,
    `groupSeq` BIGINT,
    PRIMARY KEY(`joinStatusSeq`),
    CONSTRAINT `fk_member_joinStatus`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_group_joinStatus`
    FOREIGN KEY (`groupSeq`)
    REFERENCES `pizza`.`tbl_group` (`groupSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

#게시판 목록 테이블
CREATE TABLE `board` (
	`boardSeq` BIGINT AUTO_INCREMENT,
    `boardName` VARCHAR(32) NOT NULL,
    `boardDelNy` TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '0:존재, 1:삭제',
    PRIMARY KEY(`boardSeq`)
);

#게시판 말머리 테이블
CREATE TABLE `subject` (
	`subjectSeq` BIGINT AUTO_INCREMENT,
    `subjectName` VARCHAR(20) NOT NULL,
    `boardSeq` BIGINT NOT NULL,
    PRIMARY KEY(`subjectSeq`),
    CONSTRAINT `fk_board_subject`
    FOREIGN KEY (`boardSeq`)
    REFERENCES `pizza`.`board` (`boardSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    
);

#게시글 테이블
CREATE TABLE `post` (
	`postSeq` BIGINT AUTO_INCREMENT,
    `postTitle` VARCHAR(80) NOT NULL,
    `postDate` DATETIME DEFAULT NOW(),
    `postPublicNy` TINYINT UNSIGNED DEFAULT 0 COMMENT '0:그룹공개, 1:전체공개',
    `postContent` VARCHAR(2002) NOT NULL,
    `postView` INT DEFAULT 0,
    `postLike` INT DEFAULT 0,
    `postDelNy` TINYINT UNSIGNED DEFAULT 0 COMMENT '0:존재, 1:삭제',
    `boardSeq` BIGINT, 
    `subjectSeq` BIGINT,
    `memberSeq` BIGINT,
    `groupSeq` BIGINT DEFAULT NULL COMMENT 'NULL: 전체공개',
    PRIMARY KEY(`postSeq`),
    CONSTRAINT `fk_member_post`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_board_post`
    FOREIGN KEY (`boardSeq`)
    REFERENCES `pizza`.`board` (`boardSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_subject_post`
    FOREIGN KEY (`subjectSeq`)
    REFERENCES `pizza`.`subject` (`subjectSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_group_post`
    FOREIGN KEY (`groupSeq`)
    REFERENCES `pizza`.`tbl_group` (`groupSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

#댓글 테이블
CREATE TABLE `comment` (
	`commentSeq` BIGINT AUTO_INCREMENT,
    `commentContent` VARCHAR(512) NOT NULL,
    `commentDate` DATETIME DEFAULT NOW(),
    `commentDelNy` TINYINT UNSIGNED DEFAULT 0 COMMENT '0:존재,1:삭제',
    `postSeq` BIGINT,
    `memberSeq` BIGINT,
	PRIMARY KEY(`commentSeq`),
    CONSTRAINT `fk_member_comment`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_post_comment`
    FOREIGN KEY (`postSeq`)
    REFERENCES `pizza`.`post` (`postSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

#채팅 테이블
CREATE TABLE `chat` (
	`chatSeq` BIGINT AUTO_INCREMENT,
    `chatContent` VARCHAR(1002) NOT NULL,
    `chatDate` DATETIME DEFAULT NOW(),
    `memberSeq` BIGINT,
    `groupSeq` BIGINT,
    PRIMARY KEY(`chatSeq`),
    CONSTRAINT `fk_member_chat`
    FOREIGN KEY (`memberSeq`)
    REFERENCES `pizza`.`member` (`memberSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_group_chat`
    FOREIGN KEY (`groupSeq`)
    REFERENCES `pizza`.`tbl_group` (`groupSeq`)
    ON DELETE CASCADE
    ON UPDATE CASCADE

);
desc `group`;
show tables;

select * from tbl_group;
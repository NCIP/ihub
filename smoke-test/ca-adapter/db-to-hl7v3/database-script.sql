
DROP TABLE PATIENT;

-- 
-- TABLE: PATIENT
--

CREATE TABLE PATIENT(
    PATIENT_ID			varchar(50)       NOT NULL,
    ASSIGNING_AUTHORITY		varchar(50)	  NOT NULL,
    GIVEN_NAME                  varchar(50)       NOT NULL,
    FAMILY_NAME		        varchar(50)	  NOT NULL,
    BIRTH_DATE			date	  	  NOT NULL,
    GENDER			varchar(6)	  NOT NULL,
    STATUS			varchar(25)	  NOT NULL,
    CONSTRAINT PK_PATIENT_ID PRIMARY KEY (PATIENT_ID)
)
;



DELETE FROM PATIENT;

INSERT INTO PATIENT VALUES ('1', 'NCI', 'John', 'Doe', '09/16/1965', 'Male', 'OPEN');
INSERT INTO PATIENT VALUES ('2', 'NCI', 'Mary', 'Doe', '09/16/1968', 'Female', 'OPEN');
INSERT INTO PATIENT VALUES ('3', 'NCI', 'Estee', 'Lauder', '09/16/1960', 'Female', 'OPEN');
INSERT INTO PATIENT VALUES ('4', 'NCI', 'Peter', 'Hensen', '09/16/1971', 'Male', 'OPEN');
INSERT INTO PATIENT VALUES ('5', 'NCI', 'Landon', 'Donovan', '09/16/1977', 'Male', 'OPEN');
INSERT INTO PATIENT VALUES ('6', 'NCI', 'Lisa', 'Lesley', '09/16/1972', 'Female', 'OPEN');
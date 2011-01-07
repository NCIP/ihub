DROP TABLE IF EXISTS COMPILED_TARGET_RESPONSE;
DROP TABLE IF EXISTS MESSAGE;
DROP TABLE IF EXISTS TYPE_CODE_IHUB;
DROP TABLE IF EXISTS TYPE_IHUB;

--
-- TABLE: TYPE_IHUB
--

CREATE TABLE TYPE_IHUB
(
   TYPE_ID       numeric(5, 0) NOT NULL,
   NAME          varchar(50) NOT NULL,
   DESCRIPTION   varchar(100),
   CONSTRAINT PK_TYPE_IHUB PRIMARY KEY(TYPE_ID)
);

--
-- TABLE: TYPE_CODE_IHUB
--

CREATE TABLE TYPE_CODE_IHUB
(
   CODE          varchar(50) NOT NULL,
   DESCRIPTION   varchar(100),
   TYPE_ID       numeric(5, 0) NOT NULL,
   CONSTRAINT PK_TYPE_CODE_IHUB PRIMARY KEY(CODE)
);

ALTER TABLE TYPE_CODE_IHUB ADD CONSTRAINT FK_TYPECODE_TYPEID_TYPE
    FOREIGN KEY (TYPE_ID)
    REFERENCES TYPE_IHUB(TYPE_ID);

--
-- TABLE: MESSAGE
--

CREATE TABLE MESSAGE
(
   MESSAGE_ID                varchar(50) NOT NULL,
   METADATA                  text NOT NULL,
   PAYLOAD                   text NOT NULL,
   MESSAGE_STATUS            varchar(50) NOT NULL,
   TIME_RECEIVED             timestamp NOT NULL DEFAULT now(),
   SCHEMA_DEFINITION         varchar(200),
   OVERALL_RESPONSE_DATA     text,
   TIME_COMPLETED            timestamp,
   OVERALL_RESPONSE_STATUS   varchar(50),
   CONSTRAINT PK_MESSAGE PRIMARY KEY(MESSAGE_ID)
);

ALTER TABLE MESSAGE ADD CONSTRAINT FK_MESSAGE_MSGSTATUS_TYPECODE
    FOREIGN KEY (MESSAGE_STATUS)
    REFERENCES TYPE_CODE_IHUB(CODE);

ALTER TABLE MESSAGE ADD CONSTRAINT FK_MESSAGE_OVRLRSPSTATUS_TYPECODE
    FOREIGN KEY (OVERALL_RESPONSE_STATUS)
    REFERENCES TYPE_CODE_IHUB(CODE);

--
-- TABLE: COMPILED_TARGET_RESPONSE
--

CREATE TABLE COMPILED_TARGET_RESPONSE
(
    COMPILED_TARGET_RESPONSE_ID INT NOT NULL AUTO_INCREMENT ,
    MESSAGE_ID     VARCHAR(50)       NOT NULL,
    TARGET_DETAILS             VARCHAR(50)       NOT NULL,
    COMPILED_RESPONSE                 TEXT,
    TARGET_RESPONSE_STATUS              VARCHAR(50)       NOT NULL,
    TIME_COMPLETED                   TIMESTAMP,
    CONSTRAINT PK_COMPILED_TARGET_RESPONSE PRIMARY KEY (COMPILED_TARGET_RESPONSE_ID)
);

ALTER TABLE COMPILED_TARGET_RESPONSE ADD CONSTRAINT FK_COMPTRGTRSP_MSGSVCREQID_MSGSVCREQ
    FOREIGN KEY (MESSAGE_ID)
    REFERENCES MESSAGE(MESSAGE_ID);

ALTER TABLE COMPILED_TARGET_RESPONSE ADD CONSTRAINT FK_COMPTRGTRSP_TRGTDETAILS_TYPECODE
    FOREIGN KEY (TARGET_DETAILS)
    REFERENCES TYPE_CODE_IHUB(CODE);

ALTER TABLE COMPILED_TARGET_RESPONSE ADD CONSTRAINT FK_COMPTRGTRSP_TRGTRSPSTATUS_TYPECODE
    FOREIGN KEY (TARGET_RESPONSE_STATUS)
    REFERENCES TYPE_CODE_IHUB(CODE);
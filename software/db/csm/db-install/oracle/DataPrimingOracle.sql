-- 
-- The following entries creates a super admin application incase you decide 
-- to use this database to run UPT also. In that case you need to provide
-- the project login id and name for the super admin.
-- However in incase you are using this database just to host the application's
-- authorization schema, these enteries are not used and hence they can be left as 
-- it is.
--

insert into csm_user (USER_ID,LOGIN_NAME,FIRST_NAME,LAST_NAME,PASSWORD,UPDATE_DATE)
values (CSM_USER_USER_ID_SEQ.nextval,'ihubadmin','IntegrationHub','Administrator','zJPWCwDeSgG8j2uyHEABIQ==',current_date)
/

-- 
-- The following entry is for your application. 
-- Replace <<application_context_name>> with your application name.
--

INSERT INTO csm_application(APPLICATION_ID,APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES (csm_applicati_application__seq.nextval,'ihub','caBIG Integration Hub',0,0,current_date)
/

INSERT INTO csm_application(APPLICATION_ID,APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES (csm_applicati_application__seq.nextval,'CLM','Common Logging Module',0,0,current_date)
/

insert into csm_protection_element(PROTECTION_ELEMENT_ID,PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values (CSM_PROTECTIO_PROTECTION_E_SEQ.nextval, 'APPLICATION_NAME:ihub','Common Logging Module for ihub Protection Element','APPLICATION_NAME:ihub',(select application_id from csm_application where application_name = 'CLM'),current_date)
/

insert into csm_protection_group(PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
values (CSM_PROTECTIO_PROTECTION_G_SEQ.nextval,'Application_ihub', 'CLM Protection Group for caBIG Integration Hub', (select application_id from csm_application where application_name = 'CLM'), 0, current_date)
/

insert into csm_role(role_id, role_name, role_description, application_id, active_flag, update_date)
values (csm_role_role_id_seq.nextval, 'READ_Role_for_CLM', 'Read role for CLM', (select application_id from csm_application where application_name = 'CLM'), 1, current_date)
/

insert into csm_user_group_role_pg(USER_GROUP_ROLE_PG_ID, USER_ID,  ROLE_ID, PROTECTION_GROUP_ID, UPDATE_DATE)
values (CSM_USER_GROU_USER_GROUP_R_SEQ.nextval, (select user_id from csm_user where login_name = 'ihubadmin'), (select role_id from csm_role where role_name = 'READ_Role_for_CLM'), (select protection_group_id from csm_protection_group where PROTECTION_GROUP_NAME = 'Application_ihub'), current_date)
/

insert into csm_user_pe(USER_PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_ID, USER_ID)
values (CSM_USER_PE_USER_PROTECTIO_SEQ.nextval, (select PROTECTION_ELEMENT_ID from csm_protection_element where PROTECTION_ELEMENT_NAME = 'APPLICATION_NAME:ihub'), (select user_id from csm_user where login_name = 'ihubadmin'))
/


COMMIT
/

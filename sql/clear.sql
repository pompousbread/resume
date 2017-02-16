/*

	this will remove all tables from the database to start over.

*/

/*this add-on removes the foreign keys so this table can be dropped.*/
drop table c_section cascade constraints;
drop table company cascade constraints;
drop table job_profile cascade constraints;
drop table job cascade constraints;
drop table knowledge_skill cascade constraints;
drop table job_prereq;
drop table has_skill;
drop table has_course;
drop table takes;
drop table c_req;
drop table works;
drop table course cascade constraints;
drop table courseSet;
drop table phone_number;
drop table person;



/*
drop table department;
drop table instructor;
drop table person;
drop table prereq;
drop table student;
drop table teaches;
drop table time_slot;
*/

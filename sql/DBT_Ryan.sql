/*
	Ryan Heinrich
*/
/* comments like this must not be on the same line as code.*/


create table person
	(per_id		varchar(8), 
	 p_name			varchar(20) not null, 
	 address		varchar(60),
	 email			varchar(30), 
	 gender			varchar(2),
	 primary key (per_id)
	);
	


create table phone_number
	(
	per_id		varchar(8),
	digits	numeric(10,0),	
	primary key(digits),
	foreign key (per_id) references person on delete cascade
	);	

/* might need to make another table to support multiple specialites*/
create table company
	(
	company_name	varchar(20),
	company_id		varchar(9),
	address			varchar(60),
	zip_code		numeric(5,0),
	primary_sector	varchar(40),
	speciality		varchar(40),
	website			varchar(40),
	primary key(company_id)
	);

/* if spec = 0 nothing. if > 0 special */
/* many jobs connect to 1 profile.*/ 
create table job_profile
	(
	jp_code			varchar(8),
	title			varchar(40),
	description		varchar(70),
	avg_pay			numeric(20),
	spec_jp_id		numeric(10),
	primary key(jp_code)
	);
	
/* status: either active or inactive 
	a foreign key to works could make my life easier
	status is either filled, vacant, unavailable.
*/
create table job(
	company_id		varchar(9),
	job_code		varchar(8),
	jp_code			varchar(8),
	pay_type		varchar(10),
	pay_rate		numeric(20,0),
	status			varchar(20), 
	starting_pay	numeric(20,0),
	primary key(company_id, job_code),
	foreign key(company_id) references company on delete cascade,
	foreign key(jp_code) references job_profile on delete cascade
	);
	
create table knowledge_skill
	(
	ks_code			varchar(8),
	skill_title			varchar(40),
	s_description		varchar(80),
	skill_level		varchar(20),
	primary key(ks_code)	
	);
	
create table job_prereq
	(jp_code		varchar(8), 
	 ks_code		varchar(8),
	 primary key (jp_code, ks_code),
	 foreign key (jp_code) references job_profile on delete cascade,
	 foreign key (ks_code) references knowledge_skill on delete cascade
	);
	
/* status: available or unavailable */
create table course
	(c_code			varchar(8), 
	 c_title			varchar(50), 
	 course_level	varchar(20),
	 c_description	varchar(100),
	 status			varchar(20),
	 retail_price	numeric(10,2),
	 primary key (c_code)
	);
	

create table c_section
	(c_code			varchar(8), 
    sec_no			varchar(8),
	sec_year	numeric(4,0),
	 semester		varchar(6),
	 complete_date	varchar(20),
	 offered_by		varchar(50), 
	 format			varchar(50),
	 primary key (c_code, sec_no, sec_year),
	 foreign key (c_code) references course on delete cascade
	);
	
	/* must have same foreign key as job's primary */
/*	
create table has_profile
	(company_id		varchar(9),
	 job_code  varchar(8),
	  jp_code   varchar(8),
	  person_id   varchar(8),
	  primary key(job_code, jp_code),
	  foreign key(jp_code) references job_profile on delete cascade,
	  foreign key(person_id) references person on delete cascade
	 );
*/
	 
/* foreign key(company_id, job_code) references job on delete cascade, */
	  
create table has_skill
	(per_id   varchar(8),
	ks_code  varchar(8),
	primary key(per_id, ks_code),
	foreign key(per_id) references person on delete cascade,
	foreign key(ks_code) references knowledge_skill on delete cascade
	);
	
/* has_course connects a skill with all the courses required to obtain said skill. */
/* this will eventually be changed to courseTo_skill */
create table has_course
	(ks_code  varchar(8),
	c_code    varchar(8),
	primary key(ks_code, c_code),
	foreign key(ks_code) references knowledge_skill on delete cascade,
	foreign key(c_code) references course on delete cascade
	);
	
	
create table takes 
	(per_id		varchar(8), 
    c_code			varchar(8),
    sec_no          varchar(8),
    sec_year		numeric(4,0),
	grade   		varchar(20),
	primary key (per_id, c_code, sec_no, sec_year),	 
	foreign key (per_id) references person on delete cascade,
	foreign key (c_code, sec_no, sec_year) references c_section on delete cascade,
	foreign key(c_code) references course on delete cascade
	);
	
/* foreign key(c_code, sec_no, sec_year) references c_section on delete cascade, */
/* c_prereq_code  varchar(8), */
/* foreign key(c_prereq_code) references course on delete cascade */

create table c_req
	(c_code  varchar(8),
	 prereq_code varchar(8),
	 primary key(c_code, prereq_code),
	 foreign key(c_code) references course on delete cascade
	 );



/* need start date because someone can leave and regain a job */
create table works	 
	(per_id		varchar(8),
	 job_code		varchar(8),
	 start_date		varchar(10),
	 end_date		varchar(10),
	 primary key(per_id, job_code, start_date),
	 foreign key(per_id) references person on delete cascade
	 );

/* foreign key(company_id, job_code) references job on delete cascade */
/* a table with 2 primary keys need 2 foreign keys.*/

/* a max of three courses in a set 
	size = #ofcourses
*/
create table courseSet 
	(csetID 	varchar(3),
	c_code1 	varchar(8), 
	c_code2 	varchar(8), 
	c_code3 	varchar(8), 
	set_size 	numeric(2,0),
	primary key(csetID)
);






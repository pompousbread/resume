/*
	Ryan Heinrich	
*/
delete from person;
/*delete from phone;*/
delete from company;
delete from job;
delete from job_profile;
delete from knowledge_skill;
delete from job_prereq;
delete from course;
delete from c_section;
delete from has_skill;
delete from has_course;
delete from takes;
delete from c_req;
delete from works;
delete from courseSet; 




	/* Susan : lead nurse | Steve : janitor | Walter : Prog/CEO | Sarah : nurse/librarian | Adam : Retired-Accountant */
	/* Olga: brain surgeon | Author : tutor | Harrison Audio engineer | Olivia Accountant*/
	insert into person values('33355', 'Susan', '2345 Flower ave, Metairie, 70118', 'susan@hotmail.com', 'F');
	insert into person values('44444', 'Steve', '8787 Kansas st, New Orleans, 70456', 'steve@hotmail.com', 'M');
	insert into person values('55555', 'Walter', '9090 Charters st, New Orleans, 70456', 'Walt@hotmail.com', 'M');
	insert into person values('33333', 'Sarah', '432 Rose ave, Mandeville, 70999', 'Sarah@hotmail.com', 'F');
	insert into person values('54321', 'Adam', '2222 Hotdog ave, Queens, 10005', 'Adam@gmail.com', 'M');
	insert into person values('11111', 'Olga', '17 Happyton st, Jackson, 34342', 'olga@gmail.com', 'F');
 	insert into person values('54111', 'Author', 'In clouds', 'Author@apple.com', 'M');
 	
 	--works at studio
 	insert into person values('00100', 'Harrison', '914 Yellowbrick rd, Kansas city, MO, 43435', 'Harrison@yahoo.com', 'M');
 	--accountant replacing Author
 	insert into person values('01210', 'Olivia', '212 Omaha Dr, Brooklyn, NY, 70148', 'Livi@gmail.com', 'F'); 
 		
 	/* three unemployed people  lucas is the control. he is unemployeed and qualified for another vacant job.*/
 	insert into person values('99999', 'Zarya', '12 Main st, Baton Rouge, 70000', 'BigZ@yahoo.com', 'F');
	insert into person values('88888', 'Elizabeth', '232 Boulder st, New York City, 10003', 'lisa@yahoo.com', 'F');
 	insert into person values('77777', 'Lucas', '909 Yellowbrick rd, Kansas city, 43435', 'Lucas@gmail.com', 'M');
 	
	
	insert into company values('Downtown Hospital', '123456777', 'bourbon st, New Orleans, LA', '71111', 'Medical', 'medical equipment sales', 'health.com');
	insert into company values('Google', '123456789', 'Camp st, New Orleans, LA', '70123', 'Technology', 'marketing', 'google.com');
	insert into company values('UNO', '111111111', '2000 Lakeshore Dr, Brooklyn, NY', ' 70148', 'Education', 'Computer Science', 'uno.edu');
	insert into company values('Midcity Studio', '222222222', '3000 Magazine st. Kanas City, MO', '70118', 'Entertainment', 'producing high qualify audio recordings', 'MidcityRecords.com');

	
	insert into job_profile values('777666', 'lead nurse', 'delivering babies', '38000', '333');
	insert into job_profile values('666655', 'janitor', 'cleans the floors', '40000', '0');
	insert into job_profile values('345222', 'nurse', 'delivering babies assits patients', '30000', '123');
	insert into job_profile values('888888', 'programmer', 'develops java programs', '30000', '0');
	insert into job_profile values('888889', 'CEO', 'runs company', '90000', '0');
	insert into job_profile values('666666', 'librarian', 'maintains library', '30000' , '0');
	insert into job_profile values('111111', 'Trigonometry tutor', 'assits student in understanding Trig.', '20000', '0');
	insert into job_profile values('121212', 'Brain surgeon', 'Fixes peoples brains', '150000', '0');
	insert into job_profile values('999888', 'Astronaut', 'explores space', '400000', '0');
	insert into job_profile values('222333', 'Accountant', 'manages money on a large scale', '100000', '0');
	insert into job_profile values('654321', 'Studio Intern', 'learn the in and outs of studio recording', '15000', '0');
	/* jp that no one has */
	insert into job_profile values('123123', 'Audio Engineer', 'records and mixes for high profile recording artists', '75000', '0');

	/* I added the dot to make it easier to notice. */
	insert into course values('123.4444', 'Ultrasound Basics', 'intermediate', 'teaches how to use ultra sound equipment', 'available', '300');
	insert into course values('123.4567', 'Intermediate Biology', 'intermediate', 'teaches fundamentals biological science', 'available', '600');
	insert into course values('222.4567', 'Trigonometry', 'intermediate', 'teaches geometric functions', 'available', '900');
	insert into course values('333.4567', 'Algebra', 'beginner', ' teach use of variables in math', 'available', '100');
	/* business classes */
	insert into course values('555.5577', 'Business Admin', 'beginner', ' teaches how to run a business', 'available', '200');
	insert into course values('555.4444', 'Business Payroll Basics', 'beginner', 'teaches how to run a payroll', 'available', '300');
	insert into course values('555.2222', 'Produce Waste Balance', 'intermediate', 'teaches to the mangage loss and gain', 'available', '315');
	insert into course values('555.7777', 'Taxation and Business modeling', 'intermediate', 'complex aspects about business', 'available', '400');
	insert into course values('555.8888', 'Global Market Economics', 'advanced', 'a view of global commerce markets', 'available', '1000');
	/*  for janitor*/
	insert into course values('111.1234', 'Cleaning basics', 'beginner', 'teaches the proper way to clean', 'available', '30');
	/* astronaut */
	insert into course values('999.0000', 'Fluid Engineering', 'intermediate', 'Understanding the physics of fluids', 'available', '500');
	insert into course values('999.1111', 'Rocket Science', 'intermediate', 'Learning Rocket make up and physics', 'available', '500');
	insert into course values('999.2222', 'Astrophyisics', 'advanced', 'study of outer space laws of physics', 'available', '900');
	insert into course values('999.3333', 'Spacecraft pilot', 'very difficult', 'experience propelling a spacecraft', 'available', '900');

	/* both are for business right now.  */	
	insert into courseSet values('222', '555.5577', '555.7777', '555.8888', '3');
	insert into courseSet values('010', '555.4444', '555.7777', '', '2');

	
	/* algebra is the prereq to trig*/
	insert into c_req values('222.4567','333.4567');

	insert into c_section values('123.4444', '001', '2016', 'Fall', '12/10/2016', 'Delagato', 'classroom');
	insert into c_section values('123.4567', '001', '2016', 'Spring', '05/15/2016', 'UNO', 'classroom');
	insert into c_section values('123.4567', '003', '2016', 'Spring', '03/10/2016', 'LSU', 'classroom');
	insert into c_section values('222.4567', '003', '2014', 'Fall', '02/12/2014', 'UNO', 'classroom');
	/* business  */		
	insert into c_section values('555.4444', '001', '2010', 'Fall', '12/11/2010', 'LSU', 'classroom');	
	insert into c_section values('555.5577', '001', '2010', 'Fall', '12/11/2010', 'LSU', 'classroom');	
	insert into c_section values('555.7777', '001', '2010', 'Spring', '02/12/2011', 'LSU', 'classroom');
	insert into c_section values('555.8888', '002', '2011', 'Fall', '12/12/2011', 'LSU', 'classroom');						

	insert into takes values('33355', '123.4444', '001', '2016', 'not finished');
	insert into takes values('33333', '123.4567', '001', '2016', 'A+');
	insert into takes values('55555', '555.5577', '001', '2010', 'A+');
	

	/* steve worked a job left and came back*/
	insert into works values('44444', '7777755', '09/09/2003', '05/14/2007');
	insert into works values('44444', '7777755', '11/24/2008', 'current');
	/* Susan lead nurse */
	insert into works values('33355', '7777778', '06/21/2005', 'current');
	/* Sarah nurse then librarian */
	insert into works values('33333', '3333333', '01/31/2005', '01/31/2008');
	insert into works values('33333', '7777777', '02/05/2008', 'current');
	/* Walter programmer then CEO */
	insert into works values('55555', '1238888', '03/03/1995', '04/04/2009');
	insert into works values('55555', '7888889', '04/05/2009', 'current');
	/* Olga brain surgeon */
	insert into works values('11111', '7878787', '04/05/2009', 'current');
	/* Author trig tutor*/
	insert into works values('54111', '1111222', '04/05/2009', 'current');
	/* Adam used to be an accountant */
	insert into works values('54321', '2233445', '05/17/1995', '01/31/2014');
	/* only Zarya and Elizabeth have had a job as studio intern */
	insert into works values('99999', '9999998', '07/17/2010', '09/15/2015');
	insert into works values('88888', '1111112', '07/17/2010', '10/20/2015');
	/* Harrison , Olivia */
	insert into works values('00100', '0001000', '03/17/1998', 'current');
	insert into works values('01210', '0012100', '02/15/2014', 'current');


	insert into knowledge_skill values('6262', 'CPR certified', 'can perform CPR on patient', 'beginner');
	insert into knowledge_skill values('5959', 'lawn mower', 'using lawn mower and weed wacker', 'beginner');
	insert into knowledge_skill values('4343', 'dish washer', 'cleaning dishes using machine', 'beginner');
	insert into knowledge_skill values('4312', 'UFC fighter', 'martial arts', 'intermediate');	
	insert into knowledge_skill values('7777', 'Trig certified', 'can teach Trigonometry', 'intermediate');
	/* brain surgeon skills */
	insert into knowledge_skill values('9999', 'Advanced Anatomy', 'provides strong understanding of anatomy', 'advanced');
	insert into knowledge_skill values('8989', 'Medical PH.D', 'achieved PHD in medical science', 'advanced');
	/* programmer skill */
	insert into knowledge_skill values('3333', 'bachelor in Comp Sci', 'Have a strong grasp on many programming aspects', 'advanced');
	/* CEO skills */
	insert into knowledge_skill values('1122', 'bachelor in business', ' basic understanding of businesses', 'beginner');
	insert into knowledge_skill values('4422', 'entrepreneur license', 'ability to create a business', 'intermidate');
	insert into knowledge_skill values('2233', 'masters in business', 'Have a strong grasp on many business operations', 'advanced');
	/* astronaut skills */
	insert into knowledge_skill values('1234', 'Fluid Engineering', 'blah blah not important.', 'intermediate');
	insert into knowledge_skill values('1222', 'Rocket Science', 'rockets are cool', 'intermediate');
	insert into knowledge_skill values('0101', 'Astrophyisics', 'study of outer space laws of phyisics', 'advanced');
	insert into knowledge_skill values('0000', 'Spacecraft pilot', 'experience propelling a spacecraft', 'very difficult');
	/* CPA certification */
	insert into knowledge_skill values('6666', 'CPA cert', 'knowledge of taxation and core accounting skills', 'advanced');
	/* audio record experience */
	insert into knowledge_skill values('9876', 'Studio experience', 'Over 5 years working in high profile studio', 'advanced');
	/* high level business */
	insert into knowledge_skill values('0001', 'Stock certified', 'Certified to deal with stocks', 'advanced');
	
	/* lead nurse prereq */
	insert into job_prereq values('777666' , '6262');
	insert into job_prereq values('777666' , '8989');
	/* janitor prereq*/
	insert into job_prereq values('666655' , '5959');
	insert into job_prereq values('666655' , '4343');
	/* trig prereq, lib no prereq*/
	insert into job_prereq values('111111' , '7777');
	/* brain surgeon prereqs */
	insert into job_prereq values('121212' , '6262');
	insert into job_prereq values('121212' , '9999');
	insert into job_prereq values('121212' , '8989');
	/* programmer prereq */
	insert into job_prereq values('888888' , '3333');
	/* nurse CPR certified */
	insert into job_prereq values('345222' , '6262');
	/* CEO bachelor and masters */
	insert into job_prereq values('888889' , '1122');
	insert into job_prereq values('888889' , '2233');
	/* Astronaut prereq: RocketSci, Fluid Eng, pilot, Astro */
	insert into job_prereq values('999888' , '1222');
	insert into job_prereq values('999888' , '1234');
	insert into job_prereq values('999888' , '0000');
	insert into job_prereq values('999888' , '0101');
	/* accountant prereq */
	insert into job_prereq values('222333' , '6666');
	/* audio engineer prereq */
	insert into job_prereq values('123123' , '9876');
	
	
	/* ultrasound : Ultrasound basics */
	insert into has_course values('6262', '123.4444'); 
	/* intermediate bio */
	insert into has_course values('6262', '123.4567');
	/* trig : cert */ 
	insert into has_course values('7777', '222.4567');
	/* set: business: stock cert */
	insert into has_course values('1122', '555.5577');
	insert into has_course values('2233', '555.7777');
	insert into has_course values('0001', '555.8888');
	/* intro bis course*/
	insert into has_course values('1122', '555.4444');
	/* entrepreneur license */
	insert into has_course values('4422', '555.2222');
	/* one class for both janitor skills */
	insert into has_course values('5959', '111.1234');
	insert into has_course values('4343', '111.1234');
	/* course for Astronaut */
	insert into has_course values('1234', '999.0000');
	insert into has_course values('1222', '999.1111');
	insert into has_course values('0101', '999.2222');
	insert into has_course values('0000', '999.3333');

	
	/* Susan */
	insert into has_skill values('33355', '6262');
	insert into has_skill values('33355', '8989');
	/* Steve dishwasher, UFC, CPR cert */
	insert into has_skill values('44444', '4343');
	insert into has_skill values('44444', '4312');
	insert into has_skill values('44444', '6262');
	/* Sarah: engineering & rocket Science & CPR cert */
	insert into has_skill values('33333', '1234');
	insert into has_skill values('33333', '1222');
	insert into has_skill values('33333', '6262');
	/* Olga: med skills */
	insert into has_skill values('11111', '6262');
	insert into has_skill values('11111', '9999');
	insert into has_skill values('11111', '8989');
	/* Walter: prog bachelor and business degrees, trig cert */
	insert into has_skill values('55555', '3333');
	insert into has_skill values('55555', '1122');
	insert into has_skill values('55555', '2233');
	insert into has_skill values('55555', '7777');

	/* Zarya and Elizabeth has studio exp. */
	insert into has_skill values('99999', '9876');
	insert into has_skill values('88888', '9876');
	/* Lucas CPR sert */
	insert into has_skill values('77777', '6262');
	/* Harrison ; Olivia */
	insert into has_skill values('00100', '9876');
	insert into has_skill values('01210', '6666');
	insert into has_skill values('01210', '7777');
	/* Adam trig cert,  */
	insert into has_skill values('54321', '7777');
	insert into has_skill values('54321', '6666');

	/* steve left and rejoined job.  Do I need 2 jobs...Nope! */
	/* lead nurse, janitor, programmer, CEO, nurse, librarian, Brain Surgeon, Trig. tutor  */
	
	/* hopefully this doesn't mess with any of my old queries. */
	
	insert into job values('123456777', '7777778', '777666', 'salary', '80000', 'filled', '60000');
	insert into job values('123456777', '7777755', '666655', 'salary', '41000', 'filled', '50000');
	insert into job values('123456789', '1238888', '888888', 'wage', '40.0', 'resigned', '20.0');
	insert into job values('123456789', '7888889', '888889', 'salary', '2000000', 'filled', '500000');
	insert into job values('123456777', '3333333', '345222', 'salary', '54000', 'resigned', '50000');
	insert into job values('123456789', '7777777', '666666', 'wage', '20.0', 'filled', '15.0');
	insert into job values('123456777', '7878787', '121212', 'salary', '300000', 'filled', '200000');
	insert into job values('111111111', '1111222', '111111', 'wage', '16.0', 'filled', '20.0');
	/* Adam accountant UNO */
	--this should be unavailable or resigned.
	insert into job values('111111111', '2233445', '222333', 'salary', '70000', 'retired', '100000');
	/* Harrison, audio tek, Olivia, accountant */
	insert into job values('222222222', '0001000', '123123', 'wage', '50.0', 'filled', '10.0');
 	insert into job values('111111111', '0012100', '222333', 'salary', '65000', 'filled', '80000');
	/* old internship Zarya Elizabeth */
	insert into job values('222222222', '9999998', '123123', 'wage', '14.0', 'resigned', '9.0');
	insert into job values('222222222', '1111112', '123123', 'wage', '14.0', 'resigned', '9.0');
	/* 2 vacant audio eng. and 2 vacant nurse. vacant brain surgeon */
	insert into job values('222222222', '1212121', '123123', 'salary', '70000', 'vacant', '70000');
	insert into job values('222222222', '2323232', '123123', 'salary', '70000', 'vacant', '70000');
	insert into job values('123456777', '8675309', '345222', 'salary', '50000', 'vacant', '50000');
	insert into job values('123456777', '2225555', '345222', 'salary', '50000', 'vacant', '50000');
 	insert into job values('123456777', '0120123', '121212', 'salary', '100000', 'vacant', '70000');


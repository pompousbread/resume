/* 
Ryan Heinrich
*/


/*
start clear.sql;
start DBT_Queries.sql;
start DBI_Ryan_02.sql;
commit;
*/
/* 1: List a company’s workers by names. */
/* input1: 123456777 output:
P_NAME             
Olga                
Sarah               
Steve               
Susan
*/
/*input2: 222222222 output:
P_NAME             
Harrison            
Zarya               
Elizabeth  
	
*/
select DISTINCT p_name
from person natural join works natural join job
where company_id = '123456777';

/* 2: List a company’s staff by salary in descending order.*/
/* input1: Downtown Hospital correct output
P_NAME                 PAY_RATE PAY_TYPE
Olga                     100000 salary    
Steve                     41000 salary    
Susan                     40000 salary   
*/ 
/*input 2: Google
P_NAME                 PAY_RATE PAY_TYPE 
Walter                  2000000 salary    
Sarah                     54000 salary 
*/


select distinct  p_name, pay_rate, pay_type
from    person natural join works natural join job
where 	 pay_type = 'salary' and 
	 per_id in (select  per_id 
                   from    works natural join company natural join job
                   where   company_name = 'Downtown Hospital' 
                   and end_date = 'current')
order by pay_rate desc;


/* 3: List companies labor cost (total salaries and wage rates by 1920 hours) in descending order.*/
/* correct output : one output.
COMPANY_NAME         TOTAL_COST
Google                  2038400
Downtown Hospital        421000
Midcity Studio            96000
UNO                       95720
*/

with salary (company_name, total_salary) as (select company_name, sum(pay_rate) 
                                          from company natural join job natural join works
                                          where pay_type = 'salary' and end_date = 'current'
                                          group by company_name),
wage (company_name, total_wage) as (select company_name, sum(pay_rate) * 1920 
                                 from company natural join job natural join works 
                                 where pay_type = 'wage' and end_date = 'current'
                                 group by company_name),
total as((select * 
              from salary) 
              union 
              (select * 
               from wage)) 
select company_name, sum(total_salary) as total_cost
from  total
group by company_name
order by total_cost desc;


/* 4: Find all the jobs a person is currently holding and worked in the past. */
/* input1: 55555
PER_ID   P_NAME               COMPANY_I TITLE                                    START_DATE END_DATE 
55555    Walter               123456789 programmer                               03/03/1995 04/04/2009
55555    Walter               123456789 CEO                                      04/05/2009 current   
*/
/*input2: 33333
PER_ID   P_NAME               COMPANY_I TITLE                                    START_DATE END_DATE 
33333    Sarah                123456777 nurse                                    01/31/2005 01/31/2008
33333    Sarah                123456789 librarian                                02/05/2008 current

*/
select per_id, p_name, company_id, title, start_date, end_date
from person natural join works natural join job natural join job_profile
where per_id = '55555';

/* 5: List a person’s knowledge/skills in a readable format. */
/* input1: Steve
SKILL_TITLE                              KS_CODE
CPR certified                            6262    
dish washer                              4343    
UFC fighter                              4312 
*/
/* input2: Walter
SKILL_TITLE                              KS_CODE
Trig certified                           7777    
bachelor in Comp Sci                     3333    
bachelor in business                     1122    
masters in business                      2233
*/

select skill_title, ks_code
from knowledge_skill natural join has_skill natural join person
where p_name = 'Steve';

/* 6: List the skill gap of a worker between his/her job(s) and his/her skills. */
/* input1: 44444 (Steve) 
KS_CODE
5959
*/
/* input2: 55555 (has all needed skills)
no rows selected
*/

(select ks_code  
from person natural join works natural join job natural join job_profile natural join job_prereq
where per_id = '44444')
minus
(select ks_code 
from has_skill 
where per_id = '44444');

/* 7: List the required knowledge/skills of a job profile in a readable format. */
/* input1: 666655 correct output
TITLE                                    KS_CODE
lawn mower                               5959    
dish washer                              4343
*/
/*input2: 777666
SKILL_TITLE                              KS_CODE
---------------------------------------- --------
CPR certified                            6262    
Medical PH.D                             8989 
*/

select skill_title, ks_code
from knowledge_skill natural join job_prereq
where jp_code = '666655';

/* 8: List a person’s missing knowledge/skills for a specific job in a readable format. */
/* input1: jp_code: 666655  correct output
TITLE                                    KS_CODE
dish washer                              4343    
lawn mower                               5959
*/
/* input2: jp_code: 121212
SKILL_TITLE                              KS_CODE
Advanced Anatomy                         9999    
CPR certified                            6262    
Medical PH.D                             8989
*/

(select skill_title, ks_code
from knowledge_skill natural join job_prereq
where jp_code = '666655')
minus
(select skill_title, ks_code
from person natural join has_skill natural join knowledge_skill
where per_id = '55555');

/* 9: List the courses (course id and title) that each alone teaches all the missing knowledge/skills for a person to
pursue a specific job.*/

/* list he courses that alone can qualify someone for a job. */
/* input1: 3452222 jp_code
C_CODE   C_TITLE                                          
123.4444 Ultrasound Basics                                 
123.4567 Birthing Biology
*/
/*input2: 111111 jp_code
C_CODE   C_TITLE                                          
222.4567 Trigonometry      
*/

select c_code, c_title
from   course natural join has_course chs
where not exists( 
                  (select ks_code 
                  from  job_prereq
                  where jp_code = '345222')
                  minus(
                  select ks_code
                  from HAS_COURSE hs
                  where hs.ks_code = chs.ks_code
                  ));

/* 10: Suppose the skill gap of a worker and the requirement of a desired job can be covered by one course. Find the
“quickest” solution for this worker. Show the course, section information and the completion date. */
/* input1: 345222
C_CODE   SEC_NO   C_TITLE                                                 MONTH        DAY
123.4567 003      Intermediate Biology                                        3         10
*/
/*input2: 111111
C_CODE   SEC_NO   C_TITLE                                                 MONTH        DAY
222.4567 003      Trigonometry                                                3         14
222.4567 002      Trigonometry                                                3          1
*/
with classFor_job(c_code, sec_no, c_title, month, day) as  (select c_code, sec_no , c_title, TO_NUMBER(substr(complete_date, 0,2)), TO_NUMBER(substr(complete_date, 4, 2))
                        from   course natural join has_course chs natural join c_section
                        where not exists( 
                            (select ks_code 
                              from  job_prereq
                              where jp_code = '345111')
                              minus(
                              select ks_code
                              from HAS_COURSE hs
                              where hs.ks_code = chs.ks_code
                        ))and sec_year = 2016 )

select c_code,sec_no, c_title, month, day
from classFor_job
where month = (select min(month)
                from classFor_job
              );




/* 11: Find the cheapest course to make up one’s skill gap by showing the course to take and the cost (of the section price). */
/* correct output: 
C_CODE   C_TITLE                                            RETAIL_PRICE
123.4444 Ultrasound Basics                                           300
*/
with skill_gap (ks_code) as (select ks_code
                             from  job_prereq
                             where ks_code not in (select ks_code
                                                   from  has_skill
                                                   where per_id = '55555'))
				--skill_gap: skills a person does not have that connect to jp_profiles.-- 
select c_code, c_title, retail_price
from course natural join c_section natural join has_course
where ks_code in (select ks_code
                   from  skill_gap sk) and retail_price in (select min(retail_price)
                                                            from course natural join has_course
                                                            where ks_code in (select ks_code
                                                            from  skill_gap));
                                                            
/* 
12: If query #9 returns nothing, then find the course sets that their combination covers all the missing knowledge/skills 
for a person to pursue a specific job. The considered course sets will not include more than three courses. If multiple course 
sets are found, list the course sets (with their course IDs) in the order of the ascending order of the course sets’ total costs. 

	the course set will be the business courses. the job will be the CEO job.
*/
/*	desired output
CSE   SET_SIZE      PRICE
222          3       1600
010          2        700
*/

with missing_skills (ks_code) as (select ks_code
                             from  job_prereq
                             where jp_code = '888889' and ks_code not in (select ks_code
                                                   from  has_skill
                                                   where per_id = '77777'))
    --^all skills a person does not have for a jp

    ,CourseSet_Skill(csetID, ks_code) AS (
      SELECT csetID, ks_code
      FROM courseSet CSet JOIN has_course HS ON CSet.c_code1=HS.c_code 
      UNION
      SELECT csetID, ks_code
      FROM courseSet CSet JOIN has_course HS ON CSet.c_code2=HS.c_code 
      UNION
      SELECT csetID, ks_code
      FROM courseSet CSet JOIN has_course HS ON CSet.c_code3=HS.c_code
     )
     --^all skills that are attached to a course Set
   
    , Cover_CSet(csetID, set_size) AS (
    SELECT csetID, set_size
    FROM courseSet CSet WHERE NOT EXISTS (
    SELECT ks_code
    FROM missing_skills
      MINUS
    SELECT ks_code
    FROM courseSet_Skill CSKs 
    WHERE CSKs.csetID = Cset.csetID
    ) 
    )
    --^ the cSet_ID that will teach a specific person all the skills for a JP.
    , Set_Price(csetID, price) as (select csetID, sum(retail_price)
                                  from courseSet CS, course C
                                  where CS.c_code1 = C.c_code or CS.c_code2 = C.c_code or CS.c_code3 = C.c_code
                                  group by CS.csetID
                                  )
   --^ the price of all courseSets.
  select *
  from Cover_CSet natural join Set_Price;
                  
                  

/* 13: List all the job profiles that a person is qualified for.*/
/* input1: 11111 correct output
JP_CODE  TITLE                                  
777666   lead nurse                              
345222   nurse                                   
888889   CEO                                     
666666   librarian                               
121212   Brain surgeon 
*/
/* input2: 55555
JP_CODE  TITLE                                  
888888   programmer                              
888889   CEO                                     
666666   librarian                               
111111   Trigonometry tutor                      
654321   Studio Intern
*/

select distinct P.jp_code, P.title
from job_profile P
where not exists ((select ks_code
                   from job_prereq S
                   where S.jp_code = P.jp_code) 
                   minus
                   (select ks_code
                    from has_skill
                    where per_id = '11111'));

/* 14: Find the job with the highest pay rate for a person according to his/her skill qualification. */
/* going to use sarah because she is qualified to be a nurse but is a libraian.*/

/* specify a person.  Then using their skills find what job profiles they are qualified for.
	Then find which one has the highest rate.
 */
 /* input1: 33333
 JOB_CODE TITLE                                      PAY_RATE PAY_TYPE 
3333333  nurse                                         54000 salary
*/
/* input2: 33355
JOB_CODE TITLE                                      PAY_RATE PAY_TYPE 
7777778  lead nurse                                    80000 salary
*/
 with  total_wage (jp_code, job_code, title, pay_rate, pay_type) as (select jp_code, job_code, title, (pay_rate * 1920), pay_type
                                                            from job natural join job_profile
                                                            where pay_type = 'wage'),
  total_salary (jp_code, job_code, title, pay_rate, pay_type) as (select jp_code, job_code, title, pay_rate, pay_type
                                                        from job natural join job_profile
                                                        where pay_type = 'salary'),
  the_total as ((select * 
                 from total_wage) 
                union 
                (select * 
                 from total_salary))
 select job_code, title, pay_rate, pay_type
 from the_total
 where jp_code in (select distinct P.jp_code
                   from job_profile P
                   where not exists ((select ks_code
                                      from job_prereq S
                                      where S.jp_code = P.jp_code) 
                                     minus
                                     (select ks_code
                                      from has_skill
                                      where per_id = '33333')))
  and pay_rate = (select max(pay_rate)
                  from the_total
                  where jp_code in (select distinct P.jp_code
                  from job_profile P
                  where not exists ((select ks_code
                                     from job_prereq S
                                     where S.jp_code = P.jp_code) 
                                     minus
                                     (select ks_code
                                      from has_skill
                                      where per_id = '33333'))));



/* 15: List all the names along with the emails of the persons who are qualified for a job profile.*/
/* input1: 345222   correct output 
P_NAME               EMAIL                        
Susan                susan@hotmail.com             
Sarah                Sarah@hotmail.com             
Olga                 olga@gmail.com
*/
/* input2: 111111
P_NAME               EMAIL                        
Walter               Walt@hotmail.com              
Adam                 Adam@gmail.com                
Olivia               Livi@gmail.com
*/
select p_name, email
from person P
where not exists ((select ks_code
                   from job_prereq S
                   where jp_code = '345222') 
                   minus
                   (select ks_code
                    from has_skill
                    where P.per_id = per_id));

/* 16: When a company cannot find any qualified person for a job, a secondary solution is to find a person who is almost
qualified to the job. Make a “missing-one” list that lists people who miss only one skill for a specified job profile. */
/* I'm going to use my missing on list to find Sarah and Steve who are one skill away for the lead nurse job*/
/* input: 777666 output
PER_ID 
33333   
44444   
77777 
*/
/* input: 121212
PER_ID 
33355 
*/
WITH PersonRequiredSkillCnt(per_id, skillCnt) AS ( SELECT per_id, COUNT(ks_code)
                                                  FROM has_skill NATURAL JOIN job_prereq 
                                                  WHERE jp_code = '777666'
                                                  GROUP BY per_id)

SELECT per_id
FROM PersonRequiredSkillCnt
WHERE skillCnt = (SELECT COUNT(*) - 1
                  FROM job_prereq
                  WHERE jp_code = '777666');
                  
/* 17: List the skillID and the number of people in the missing-one list for a given job profile in the ascending order of
the people counts. */
/*input: 777666    correct output:
KS_CODE                PPL_MISSING_SKILL_FOR_JOB
8989                                           2
*/
/* input: 888888
KS_CODE                PPL_MISSING_SKILL_FOR_JOB
3333                                          11
*/
with required_skills as (select ks_code 
                       from  job_prereq 
                       where jp_code = '777666'),                      
skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code)
                                         from person p,  required_skills 
                                         where ks_code in ((select ks_code
                                                            from required_skills) 
                                                            minus
                                                            (select ks_code 
                                                             from has_skill
                                                             where per_id = p.per_id))  
                                                            group by per_id))
select ks_code, count(per_id) as ppl_missing_skill_for_job
from  skills_missing M, knowledge_skill
where ks_code in ((select ks_code
                   from required_skills) 
                  minus
                  (select ks_code 
                   from has_skill
                  where per_id = M.per_id))  
and amnt_missing = 1
group by ks_code
order by ppl_missing_skill_for_job asc;

/* 18: Suppose there is a new job profile that has nobody qualified. List the persons who miss the least number of skills
and report the “least number”.*/
/* using Astronaut for my query. */
/* input1: 999888
PER_ID                              LEAST_NUMBER
33333                                          2
*/
/* input:888889
PER_ID                              LEAST_NUMBER
00100                                          2
01210                                          2
11111                                          2
33333                                          2
33355                                          2
44444                                          2
54111                                          2
54321                                          2
77777                                          2
88888                                          2
99999                                          2
*/

with required_skills as (select ks_code 
                       from  job_prereq 
                       where jp_code = '999888'),                      
skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code)
                                         from person p,  required_skills 
                                         where ks_code in ((select ks_code
                                                            from required_skills) 
                                                            minus
                                                            (select ks_code 
                                                             from has_skill
                                                             where per_id = p.per_id))  
                                                            group by per_id))
select per_id, amnt_missing as least_number
from  skills_missing
where amnt_missing = (select min(amnt_missing)
                     from skills_missing)
order by per_id asc;


/* 19: For a specified job profile and a given small number k, make a “missing-k” list that lists the people’s IDs and the
number of missing skills for the people who miss only up to k skills in the ascending order of missing skills. */
/* many people have at least one prereq for the brain surgeon job so I chose that one.*/
/* output
PER_ID                              AMNT_MISSING
33333                                          1
44444                                          1
54231                                          2
54111                                          2
55555                                          2
*/

with required_skills as (select ks_code 
                       from  job_prereq 
                       where jp_code = '777666'),                      
skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code)
                                         from person p,  required_skills 
                                         where ks_code in ((select ks_code
                                                            from required_skills) 
                                                            minus
                                                            (select ks_code 
                                                             from has_skill
                                                             where per_id = p.per_id))  
                                                            group by per_id))
                                                            
select per_id, amnt_missing
from skills_missing
where amnt_missing <= 2
order by amnt_missing asc;

/* 20: skip for now. */

/* 21: In a local or national crisis, we need to find all the people who once held a job of the special job-profile identifier. */
/* I created a variable spec_jp_id for crisis workers. if > 0 then qualify. */

select per_id
from person natural join works natural join job natural join job_profile
where spec_jp_id > 0;

/* 22: Find all the unemployed people who once held a job of the given job-profile identifier.   */
/* find unemployed people then see if they had job '222333': accountant. */
/* output
PER_ID 
54321 
*/
with unemployed as ((select per_id, p_name
					from person)
					minus
					(select per_id, p_name
					from person natural join works
					where end_date = 'current'))

select per_id
from unemployed natural join works natural join job
where jp_code = '222333';

/* 23: Find out the biggest employer in terms of number of employees or the total amount of salaries and wages paid to
employees. */
/* displays all companies their total workers and total costs. */
/*
COMPANY_NAME                                    EMPLOYEE_NUM                       SUM(TOTAL_SALARY)
Google                                                     3                                 2038400
UNO                                                        2                                   95720
Midcity Studio                                             1                                   96000
Downtown Hospital                                          5                                  421000
*/

with employed as (select per_id, p_name
                  from person natural join works
                  where end_date = 'current')
             ,company_total (company_name, company_id, employee_num) as(select company_name, company_id, count(per_id)
                                                              from employed natural join works natural join job natural join company
                                                              group by company_id, company_name
                                                              )
                                                              
            ,salary (company_name, total_salary) as (select company_name, sum(pay_rate) 
                                          from company natural join job natural join works
                                          where pay_type = 'salary' and end_date = 'current'
                                          group by company_name)
            ,wage (company_name, total_wage) as (select company_name, sum(pay_rate) * 1920 
                                 from company natural join job natural join works 
                                 where pay_type = 'wage' and end_date = 'current'
                                 group by company_name),
the_total as((select * 
              from salary) 
              union 
              (select * 
               from wage)) 
               
select company_name, employee_num, sum(total_salary)
from  the_total natural join company_total
group by company_name, employee_num;

/* 24: Find out the job distribution among business sectors; find out the biggest sector in terms of number of employees
or the total amount of salaries and wages paid to employees. 
*/

/*                             
 8 rows selected 

PRIMARY_SECTOR                                                     ALL_EMPLOYEES
Medical                                                                        3

PRIMARY_SECTOR                                                    SECTOR_TOTAL_COST
Technology                                                               5028800
*/
-- For finding job distribution among business sectors

with currently_employed (per_id, company_id, company_name, primary_sector, job_code, jp_code, title, start_date, end_date) as (select per_id, company_id, company_name, primary_sector, job_code, jp_code, title, start_date, end_date
                                                                                   from works natural join job natural join job_profile natural join company
                                                                                   where end_date = 'current'),
sector_employees (all_employees, primary_sector) as (select count(per_id), primary_sector
                                                    from currently_employed
                                                    group by primary_sector)                                                      
select primary_sector, all_employees
from sector_employees
where all_employees = (select max(all_employees)
                         from sector_employees)
order by all_employees desc;

-- Finding sector with highest salary                   
with salary (primary_sector, total_salary) as (select primary_sector, sum(pay_rate) 
                                          from company natural join job natural join works
                                          where pay_type = 'salary' and end_date = 'current'
                                          group by primary_sector),
wage (primary_sector, total_wage) as (select primary_sector, sum(pay_rate) * 1920 
                                 from company natural join job natural join works
                                 where pay_type = 'wage' and end_date = 'current'
                                 group by primary_sector),
total_payroll (total_cost, primary_sector) as((select total_salary, primary_sector 
                                           from salary) 
                                           union 
                                           (select total_wage, primary_sector 
                                            from wage)),
                                                               
sector_total (primary_sector, sector_total_cost) as (select primary_sector, sum(total_cost)
                                                 from total_payroll
                                                 group by primary_sector)
                                                
select primary_sector, sum(sector_total_cost) as sector_total_cost
from  sector_total
where sector_total_cost = (select max(sector_total_cost) from sector_total)
group by primary_sector
order by sector_total_cost desc;

/* 25: Find out the ratio between the people whose earnings increase and those whose earning decrease; find the average
rate of earning improvement for the workers in a specific business sector. */
/* output
COMPANY_NAME         PRIMARY_SECTOR                                   SUM(YEARLY_CHANGE/TOTAL_PEOPLE)
UNO                  Education                                                                  -4300
Google               Technology                                                                107750
Downtown Hospital    Medical                                                                     5000
Midcity Studio       Entertainment                                                               4300
*/

/*	part 1 
output
	                 PAY_DECREASING_PERCENT
                                   .375
*/
with price_difference (per_id, job_code, difference) as (SELECT per_id, job_code, (pay_rate - starting_pay) AS difference
                                                  FROM job natural join works
                                                  where status = 'filled' and end_date = 'current'
                                                ) 
      , count_neg(total_neg) as (select count(difference)
                                  from price_difference natural join job
                                  where difference < 0 and status = 'filled'
                                )
      , total_employed(job_total) as (select count(job_code)
                                  from job
                                  where status = 'filled'
      	select (N.total_neg * 1.0 / E.job_total) as pay_decrease_percent
		from total_employed E, count_neg N;
                
/*part 2 
COMPANY_NAME         PRIMARY_SECTOR                                   SUM(YEARLY_CHANGE/TOTAL_PEOPLE)
UNO                  Education                                                                  -4300
Google               Technology                                                                107750
Downtown Hospital    Medical                                                                     5000
Midcity Studio       Entertainment                                                               4300            
*/                

with price_difference (per_id, job_code, difference) as (SELECT per_id, job_code, (pay_rate - starting_pay) AS difference
                                                  FROM job natural join works
                                                  where status = 'filled' and end_date = 'current'
                                                ) 
      , count_neg(total_neg) as (select count(difference)
                                  from price_difference natural join job
                                  where difference < 0 and status = 'filled'
                                )
      , total_employed(job_total) as (select count(job_code)
                                  from job
                                  where status = 'filled'
                                )
      ,years_employed(per_id, job_code, job_years) as (select per_id, job_code, (2016 - cast( substr(start_date, 7, 4) as int))
                                        from works natural join job
                                        where end_date = 'current'
                                    )
       ,wage_diff (per_id, income_change) as (select per_id, difference * 1920 
                                 from person natural join works natural join job natural join price_difference 
                                 where pay_type = 'wage' and end_date = 'current'
                                 --group by per_id
                                 )
      ,salary_diff (per_id, income_change) as (select per_id, difference 
                                          from  works natural join job natural join price_difference
                                          where pay_type = 'salary' and end_date = 'current'
                                          --group by per_id
                                          )
     ,the_total as((select * 
              from salary_diff) 
              union 
              (select * 
               from wage_diff))                       
                                 
      ,yearly_earning_change(per_id, job_code, yearly_change) as(select per_id, job_code, ROUND((income_change / job_years) ,-2) as yearly_change
                                                from the_total natural join years_employed
                                                )
      ,sector_ppl_count (primary_sector, total_people) as (select primary_sector, count(job_code) 
                                          from job natural join company
                                          where status = 'filled'
                                          group by primary_sector)
                                             
select company_name, primary_sector, sum(yearly_change / total_people)
from yearly_earning_change natural join job natural join company natural join sector_ppl_count
group by company_name, primary_sector;


/* 26: Find the job_profile with the most unfilled jobs and the largest number of unemployeed people
who are qualified for this profile. */
/* ouput
TITLE                                    JP_CODE                            NUM_QUALIFIED  VACANCIES
nurse                                    345222                                         1          2
Audio Engineer                           123123                                         2          2
*/
with unemployed (per_id, p_name) as ((select per_id, p_name
                                from person)
                                minus
                                (select per_id, p_name
                                from person natural join works
                                where end_date = 'current'
                                ))
    ,vacant_profiles (jp_code, vacancies) as (select jp_code, count(job_code)
                                from job_profile natural join job
                                where status = 'vacant'
                                group by jp_code
                                )
	,qualified_people (num_qualified, jp_code) as (select count(per_id), req.jp_code
                                from unemployed P, job_prereq req
                                where not exists ((	select ks_code
                                                  from job_prereq S
                                                  where req.jp_code = S.jp_code
                                                  ) 
                                                  minus
                                                  (select ks_code
                                                  from has_skill
                                                  where P.per_id = per_id))
                                group by jp_code
                                )                           
select title, jp_code, (vacancies - num_qualified) as result
from qualified_people natural join job_profile natural join vacant_profiles;


/* 27:Find the courses that can help most jobless people find a job by training them toward the 
job profiles that have the most openings due to lack of qualified workers.

		
*/
/* 
JP_CODE                             NUM_UNQUALIF C_FOR_JO C_TITLE                                            KS_CODE
121212                                        10 123.4567 Intermediate Biology                               6262    
121212                                        10 123.4444 Ultrasound Basics                                  6262    
*/
with not_qualified (jp_code, title, per_id)  as (select distinct P.jp_code, P.title, R.per_id
                                                 from job_profile P, has_skill R, job J
                                                 where exists ((select ks_code
                                                                from job_prereq S
                                                                where S.jp_code = P.jp_code) 
                                                                minus
                                                               (select ks_code
                                                                from has_skill
                                                                where per_id = R.per_id))
                                                                and J.jp_code = P.jp_code and J.status = 'vacant'
                                                                --^this line changes everything.
                                                                ),
        --^ every jp_code that every person is not qualified for.

num_unqualified (jp_code, num_unqualif) as (select jp_code, count(per_id)
                                            from not_qualified
                                            group by jp_code),
       --^ number of people not qualified for each profile.
 --select * from num_not_qualified;
                                            
most_vacant_jp as (select jp_code
                            from num_unqualified
                            where num_unqualif = (select max(num_unqualif)
                                                from num_unqualified)),
                                                --,
      --^the job profiles with the most unqualified people. 10 right now.
 --select * from most_vacant_job_profile;                                         
                                                                                         
skills_needed (jp_code, missing_skill) as  (select jp_code, ks_code
                                            from  job_prereq natural join knowledge_skill
                                            where jp_code in (select jp_code
                                                             from most_vacant_jp)),
   --^The skills needed to get a certain job with the less amount of qualified people.                                   
    --select * from skills_needed;                                                                   
courses_for_job (jp_code, c_for_job, c_title, ks_code, missing_skill) as (select jp_code, c_code, c_title, ks_code, missing_skill
                                                       from course natural join has_course natural join skills_needed
                                                       where ks_code = missing_skill)
                                   
select jp_code, num_unqualif, c_for_job, c_title, ks_code
from courses_for_job natural join num_unqualified
order by jp_code asc;

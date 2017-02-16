/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

/**
 *
 * @author Ryan Heinrich
 */
public class QueryMessages {
 
    public QueryMessages(){    
    }
    
    public String getQuery1(){
        return "select DISTINCT p_name from person natural join works natural join job where company_id = '123456777'";
    
    }
    
    public String getQuery2(){
        return "select distinct p_name, pay_rate, pay_type from person natural join works natural join job where pay_type = 'salary' and per_id in (select  per_id from works natural join company natural join job where company_name = 'Downtown Hospital' and end_date = 'current') order by pay_rate desc";
        //return "select DISTINCT p_name from person natural join works natural join job where company_id = '123456777'";
    }
    
    public String getQuery3(){
        return "with salary (company_name, total_salary) as (select company_name, sum(pay_rate) from company natural join job natural join works where pay_type = 'salary' and end_date = 'current' group by company_name), wage (company_name, total_wage) as (select company_name, sum(pay_rate) * 1920 from company natural join job natural join works where pay_type = 'wage' and end_date = 'current' group by company_name), total as((select * from salary) union (select * from wage)) select company_name, sum(total_salary) as total_cost from  total group by company_name order by total_cost desc";
    
    }
    
    public String getQuery4(){
        return "select per_id, p_name, company_id, title, start_date, end_date from person natural join works natural join job natural join job_profile where per_id = '55555'";
    
    }
    
    public String getQuery5(){
        return "select skill_title, ks_code from knowledge_skill natural join has_skill natural join person where p_name = 'Steve'";
    }
    
    public String getQuery6(){
        return "(select ks_code from person natural join works natural join job natural join job_profile natural join job_prereq where per_id = '44444') minus (select ks_code from has_skill where per_id = '44444')";
    }
    
    public String getQuery7(){
        return "select skill_title, ks_code from knowledge_skill natural join job_prereq where jp_code = '666655'";
    }
    
    public String getQuery8(){
        return "(select skill_title, ks_code\n" +
"from knowledge_skill natural join job_prereq\n" +
"where jp_code = '666655')\n" +
"minus\n" +
"(select skill_title, ks_code\n" +
"from person natural join has_skill natural join knowledge_skill\n" +
"where per_id = '55555')";
    }
    
    public String getQuery9(){
        return "select c_code, c_title from   course natural join has_course chs where not exists((select ks_code from  job_prereq where jp_code = '345222') minus(select ks_code from HAS_COURSE hs where hs.ks_code = chs.ks_code))";
    }
    
    public String getQuery10(){
        return "with classFor_job(c_code, c_title, month, day) as  (select c_code, c_title, TO_NUMBER(substr(complete_date, 0,2)), TO_NUMBER(substr(complete_date, 4, 2)) from course natural join has_course chs natural join c_section where not exists((select ks_code from  job_prereq where jp_code = '345222') minus( select ks_code from HAS_COURSE hs  where hs.ks_code = chs.ks_code))and sec_year = 2016) select c_code, c_title, month, day from classFor_job where month = (select min(month) from classFor_job)";
    }
    
    public String getQuery11(){
        return "with skill_gap (ks_code) as (select ks_code\n" +
"                             from  job_prereq\n" +
"                             where ks_code not in (select ks_code\n" +
"                                                   from  has_skill\n" +
"                                                   where per_id = '33355'))\n" +
"select c_code, c_title, retail_price\n" +
"from course natural join c_section natural join has_course\n" +
"where ks_code in (select ks_code\n" +
"                   from  skill_gap sk) and retail_price in (select min(retail_price)\n" +
"                                                            from course natural join has_course\n" +
"                                                            where ks_code in (select ks_code\n" +
"                                                            from  skill_gap))";
    }
    
    
    //skipping 12 for now.
    public String getQuery12(){
        return "with missing_skills (ks_code) as (select ks_code from  job_prereq where jp_code = '888889' and ks_code not in (select ks_code from  has_skill where per_id = '77777')) ,CourseSet_Skill(csetID, ks_code) AS ( SELECT csetID, ks_code FROM courseSet CSet JOIN has_course HS ON CSet.c_code1=HS.c_code  UNION SELECT csetID, ks_code FROM courseSet CSet JOIN has_course HS ON CSet.c_code2=HS.c_code UNION SELECT csetID, ks_code FROM courseSet CSet JOIN has_course HS ON CSet.c_code3=HS.c_code ) , Cover_CSet(csetID, set_size) AS ( SELECT csetID, set_size FROM courseSet CSet WHERE NOT EXISTS ( SELECT ks_code FROM missing_skills MINUS SELECT ks_code FROM courseSet_Skill CSKs WHERE CSKs.csetID = Cset.csetID ) ) , Set_Price(csetID, price) as (select csetID, sum(retail_price) from courseSet CS, course C where CS.c_code1 = C.c_code or CS.c_code2 = C.c_code or CS.c_code3 = C.c_code group by CS.csetID) select * from Cover_CSet natural join Set_Price";
        //return "";
    }
    
    public String getQuery13(){
        return "select distinct P.jp_code, P.title from job_profile P where not exists ((select ks_code from job_prereq S where S.jp_code = P.jp_code) minus (select ks_code from has_skill where per_id = '11111'))";
    }
    
    public String getQuery14(){
        return " with  total_wage (jp_code, job_code, title, pay_rate, pay_type) as (select jp_code, job_code, title, (pay_rate * 1920), pay_type from job natural join job_profile where pay_type = 'wage'), total_salary (jp_code, job_code, title, pay_rate, pay_type) as (select jp_code, job_code, title, pay_rate, pay_type from job natural join job_profile where pay_type = 'salary'), the_total as ((select * from total_wage) union (select * from total_salary)) select job_code, title, pay_rate, pay_type from the_total where jp_code in (select distinct P.jp_code from job_profile P where not exists ((select ks_code from job_prereq S where S.jp_code = P.jp_code) minus (select ks_code from has_skill where per_id = '33333'))) and pay_rate = (select max(pay_rate) from the_total where jp_code in (select distinct P.jp_code from job_profile P where not exists ((select ks_code from job_prereq S where S.jp_code = P.jp_code) minus (select ks_code from has_skill where per_id = '33333'))))";
    }
    
    public String getQuery15(){
        return "select p_name, email from person P where not exists ((select ks_code from job_prereq S where jp_code = '345222') minus (select ks_code from has_skill where P.per_id = per_id))";
    }
    
    public String getQuery16(){
        return "WITH PersonRequiredSkillCnt(per_id, skillCnt) AS ( SELECT per_id, COUNT(ks_code)\n" +
"                                                  FROM has_skill NATURAL JOIN job_prereq \n" +
"                                                  WHERE jp_code = '777666'\n" +
"                                                  GROUP BY per_id)\n" +
"\n" +
"SELECT per_id\n" +
"FROM PersonRequiredSkillCnt\n" +
"WHERE skillCnt = (SELECT COUNT(*) - 1\n" +
"                  FROM job_prereq\n" +
"                  WHERE jp_code = '777666')";
    }
    
    public String getQuery17(){
        return "with required_skills as (select ks_code from  job_prereq where jp_code = '777666'), skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code) from person p,  required_skills where ks_code in ((select ks_code from required_skills) minus (select ks_code from has_skill where per_id = p.per_id)) group by per_id)) select ks_code, count(per_id) as ppl_missing_skill_for_job from  skills_missing M, knowledge_skill where ks_code in ((select ks_code from required_skills) minus (select ks_code from has_skill where per_id = M.per_id)) and amnt_missing = 1 group by ks_code order by ppl_missing_skill_for_job asc";
    }
    
    public String getQuery18(){
    return "with required_skills as (select ks_code from  job_prereq where jp_code = '999888'), skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code) from person p,  required_skills where ks_code in ((select ks_code from required_skills) minus (select ks_code from has_skill where per_id = p.per_id)) group by per_id)) select per_id, amnt_missing as least_number from  skills_missing where amnt_missing = (select min(amnt_missing) from skills_missing) order by per_id asc";
    }
    
    public String getQuery19(){
        return "with required_skills as (select ks_code from  job_prereq where jp_code = '777666'), skills_missing (per_id, amnt_missing) as ((select per_id, count(ks_code) from person p,  required_skills where ks_code in ((select ks_code from required_skills) minus (select ks_code from has_skill where per_id = p.per_id)) group by per_id)) select per_id, amnt_missing from skills_missing where amnt_missing <= 2 order by amnt_missing asc";
    }

    public String getQuery20(){
        return "select p_name from person";
    }
    
    public String getQuery21(){
        return "select per_id\n" +
"from person natural join works natural join job natural join job_profile\n" +
"where spec_jp_id > 0";
    }
    
    public String getQuery22(){
        return "with unemployed as ((select per_id, p_name from person) minus (select per_id, p_name from person natural join works where end_date = 'current')) select per_id from unemployed natural join works natural join job where jp_code = '222333'";
    }
    
    public String getQuery23(){
        return "with employed as (select per_id, p_name\n" +
"                  from person natural join works\n" +
"                  where end_date = 'current')\n" +
"             ,company_total (company_name, company_id, employee_num) as(select company_name, company_id, count(per_id)\n" +
"                                                              from employed natural join works natural join job natural join company\n" +
"                                                              group by company_id, company_name\n" +
"                                                              )\n" +
"                                                              \n" +
"            ,salary (company_name, total_salary) as (select company_name, sum(pay_rate) \n" +
"                                          from company natural join job natural join works\n" +
"                                          where pay_type = 'salary' and end_date = 'current'\n" +
"                                          group by company_name)\n" +
"            ,wage (company_name, total_wage) as (select company_name, sum(pay_rate) * 1920 \n" +
"                                 from company natural join job natural join works \n" +
"                                 where pay_type = 'wage' and end_date = 'current'\n" +
"                                 group by company_name),\n" +
"the_total as((select * \n" +
"              from salary) \n" +
"              union \n" +
"              (select * \n" +
"               from wage)) \n" +
"               \n" +
"select company_name, employee_num, sum(total_salary)\n" +
"from  the_total natural join company_total\n" +
"group by company_name, employee_num";
    }
    public String getQuery24(){
        
        return "with currently_employed (per_id, company_id, company_name, primary_sector, job_code, jp_code, title, start_date, end_date) as (select per_id, company_id, company_name, primary_sector, job_code, jp_code, title, start_date, end_date\n" +
"                                                                                   from works natural join job natural join job_profile natural join company\n" +
"                                                                                   where end_date = 'current'),\n" +
"sector_employees (all_employees, primary_sector) as (select count(per_id), primary_sector\n" +
"                                                    from currently_employed\n" +
"                                                    group by primary_sector)                                                      \n" +
"select primary_sector, all_employees\n" +
"from sector_employees\n" +
"where all_employees = (select max(all_employees)\n" +
"                         from sector_employees)\n" +
"order by all_employees desc";
    }
    
    public String getQuery24b(){
        return "with salary (primary_sector, total_salary) as (select primary_sector, sum(pay_rate) \n" +
"                                          from company natural join job natural join works\n" +
"                                          where pay_type = 'salary' and end_date = 'current'\n" +
"                                          group by primary_sector),\n" +
"wage (primary_sector, total_wage) as (select primary_sector, sum(pay_rate) * 1920 \n" +
"                                 from company natural join job natural join works\n" +
"                                 where pay_type = 'wage' and end_date = 'current'\n" +
"                                 group by primary_sector),\n" +
"total_payroll (total_cost, primary_sector) as((select total_salary, primary_sector \n" +
"                                           from salary) \n" +
"                                           union \n" +
"                                           (select total_wage, primary_sector \n" +
"                                            from wage)),\n" +
"                                                               \n" +
"sector_total (primary_sector, sector_total_cost) as (select primary_sector, sum(total_cost)\n" +
"                                                 from total_payroll\n" +
"                                                 group by primary_sector)\n" +
"                                                \n" +
"select primary_sector, sum(sector_total_cost) as sector_total_cost\n" +
"from  sector_total\n" +
"where sector_total_cost = (select max(sector_total_cost) from sector_total)\n" +
"group by primary_sector\n" +
"order by sector_total_cost desc";
    }
    
     public String getQuery25(){
        return "with price_difference (per_id, job_code, difference) as (SELECT per_id, job_code, (pay_rate - starting_pay) AS difference\n" +
"                                                  FROM job natural join works\n" +
"                                                  where status = 'filled' and end_date = 'current'\n" +
"                                                ) \n" +
"      , count_neg(total_neg) as (select count(difference)\n" +
"                                  from price_difference natural join job\n" +
"                                  where difference < 0 and status = 'filled'\n" +
"                                )\n" +
"      , total_employed(job_total) as (select count(job_code)\n" +
"                                  from job\n" +
"                                  where status = 'filled')\n" +
"      	select (N.total_neg * 1.0 / E.job_total) as pay_decrease_percent\n" +
"		from total_employed E, count_neg N";
    }
     public String getQuery25b(){
         return "with price_difference (per_id, job_code, difference) as (SELECT per_id, job_code, (pay_rate - starting_pay) AS difference\n" +
"                                                  FROM job natural join works\n" +
"                                                  where status = 'filled' and end_date = 'current'\n" +
"                                                ) \n" +
"      , count_neg(total_neg) as (select count(difference)\n" +
"                                  from price_difference natural join job\n" +
"                                  where difference < 0 and status = 'filled'\n" +
"                                )\n" +
"      , total_employed(job_total) as (select count(job_code)\n" +
"                                  from job\n" +
"                                  where status = 'filled'\n" +
"                                )\n" +
"      ,years_employed(per_id, job_code, job_years) as (select per_id, job_code, (2016 - cast( substr(start_date, 7, 4) as int))\n" +
"                                        from works natural join job\n" +
"                                        where end_date = 'current'\n" +
"                                    )\n" +
"       ,wage_diff (per_id, income_change) as (select per_id, difference * 1920 \n" +
"                                 from person natural join works natural join job natural join price_difference \n" +
"                                 where pay_type = 'wage' and end_date = 'current'\n" +
"                                 --group by per_id\n" +
"                                 )\n" +
"      ,salary_diff (per_id, income_change) as (select per_id, difference \n" +
"                                          from  works natural join job natural join price_difference\n" +
"                                          where pay_type = 'salary' and end_date = 'current'\n" +
"                                          --group by per_id\n" +
"                                          )\n" +
"     ,the_total as((select * \n" +
"              from salary_diff) \n" +
"              union \n" +
"              (select * \n" +
"               from wage_diff))                       \n" +
"                                 \n" +
"      ,yearly_earning_change(per_id, job_code, yearly_change) as(select per_id, job_code, ROUND((income_change / job_years) ,-2) as yearly_change\n" +
"                                                from the_total natural join years_employed\n" +
"                                                )\n" +
"      ,sector_ppl_count (primary_sector, total_people) as (select primary_sector, count(job_code) \n" +
"                                          from job natural join company\n" +
"                                          where status = 'filled'\n" +
"                                          group by primary_sector)\n" +
"                                             \n" +
"select company_name, primary_sector, sum(yearly_change / total_people)\n" +
"from yearly_earning_change natural join job natural join company natural join sector_ppl_count\n" +
"group by company_name, primary_sector";
     
     }
    
    public String getQuery26(){
        return "with unemployed (per_id, p_name) as ((select per_id, p_name from person) minus (select per_id, p_name from person natural join works where end_date = 'current')),vacant_profiles (jp_code, vacancies) as (select jp_code, count(job_code) from job_profile natural join job where status = 'vacant' group by jp_code), qualified_people (num_qualified, jp_code) as (select count(per_id), req.jp_code from unemployed P, job_prereq req where not exists ((	select ks_code from job_prereq S where req.jp_code = S.jp_code) minus (select ks_code from has_skill where P.per_id = per_id)) group by jp_code) select title, jp_code, num_qualified, vacancies from qualified_people natural join job_profile natural join vacant_profiles";
    }
    
    public String getQuery27(){
        return "with not_qualified (jp_code, title, per_id)  as (select distinct P.jp_code, P.title, R.per_id\n" +
"                                                 from job_profile P, has_skill R, job J\n" +
"                                                 where exists ((select ks_code\n" +
"                                                                from job_prereq S\n" +
"                                                                where S.jp_code = P.jp_code) \n" +
"                                                                minus\n" +
"                                                               (select ks_code\n" +
"                                                                from has_skill\n" +
"                                                                where per_id = R.per_id))\n" +
"                                                                and J.jp_code = P.jp_code and J.status = 'vacant'\n" +
"                                                                --^this line changes everything.\n" +
"                                                                ),\n" +
"        --^ every jp_code that every person is not qualified for.\n" +
"\n" +
"num_unqualified (jp_code, num_unqualif) as (select jp_code, count(per_id)\n" +
"                                            from not_qualified\n" +
"                                            group by jp_code),\n" +
"       --^ number of people not qualified for each profile.\n" +
" --select * from num_not_qualified;\n" +
"                                            \n" +
"most_vacant_jp as (select jp_code\n" +
"                            from num_unqualified\n" +
"                            where num_unqualif = (select max(num_unqualif)\n" +
"                                                from num_unqualified)),\n" +
"                                                --,\n" +
"      --^the job profiles with the most unqualified people. 10 right now.\n" +
" --select * from most_vacant_job_profile;                                         \n" +
"                                                                                         \n" +
"skills_needed (jp_code, missing_skill) as  (select jp_code, ks_code\n" +
"                                            from  job_prereq natural join knowledge_skill\n" +
"                                            where jp_code in (select jp_code\n" +
"                                                             from most_vacant_jp)),\n" +
"   --^The skills needed to get a certain job with the less amount of qualified people.                                   \n" +
"    --select * from skills_needed;                                                                   \n" +
"courses_for_job (jp_code, c_for_job, c_title, ks_code, missing_skill) as (select jp_code, c_code, c_title, ks_code, missing_skill\n" +
"                                                       from course natural join has_course natural join skills_needed\n" +
"                                                       where ks_code = missing_skill)\n" +
"                                   \n" +
"select jp_code, num_unqualif, c_for_job, c_title, ks_code\n" +
"from courses_for_job natural join num_unqualified\n" +
"order by jp_code asc";
    }
    
    public String getQuery28(){
        return "select p_name from person";
    }
}

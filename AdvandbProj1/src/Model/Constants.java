package Model;

public class Constants {
	public static final String QUERY_TITLE1 = "query 1";
	public static final String QUERY_TITLE2 = "query 2";
	public static final String QUERY_TITLE3 = "query 3";
	public static final String QUERY_TITLE4 = "query 4";
	public static final String QUERY_TITLE5 = "query 5";
	public static final String QUERY_TITLE6 = "query 6";
	public static final String QUERY_TITLE7 = "query 7";
	
	public static final String QUERY_EXEC = "execute";
	public static final String QUERY_UPDATE = "update";
	
	public static final int TYPE_STRING = 0;
	public static final int TYPE_INT = 1;
	
	public static final String QUERY1_1 = "";
	public static final String QUERY1_2 = 
		"select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
		+ "from (select count(*) as num_hungry \n"
		+ "from hpq_hh \n"
		+ "where fshort = 1) NH, \n"
		+ "(select count(*) as total_houses \n"
		+ "from hpq_hh) TH, \n"
		+ "(select count(*) as benefited \n"
		+ "from hpq_hh \n"
		+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1) B; \n";
	public static final String QUERY1_3_1 = "CREATE INDEX HUNGER on hpq_hh(prog_fudforwrk,prog_fudforsch,fshort)";
	public static final String QUERY1_3_2 = "ALTER TABLE hpq_hh DROP INDEX hunger";
	public static final String QUERY1_3 = 
		"select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
		+ "from (select count(*) as num_hungry \n"
		+ "from hpq_hh \n"
		+ "where fshort = 1) NH, \n"
		+ "(select count(*) as total_houses \n"
		+ "from hpq_hh) TH, \n"
		+ "(select count(*) as benefited \n"
		+ "from hpq_hh \n"
		+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1) B; \n";
	public static final String QUERY1_4_1 =
		"CREATE VIEW household_num as \n"
		+ "select count(*) as total_houses \n"
		+ "from hpq_hh \n";
	public static final String QUERY1_4_2 = "DROP VIEW household_num";
	public static final String QUERY1_4 = "select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
			+ "from (select count(*) as num_hungry \n"
			+ "from hpq_hh \n"
			+ "where fshort = 1) NH, \n"
			+ "household_num, \n"
			+ "(select count(*) as benefited \n"
			+ "from hpq_hh \n"
			+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1) B ";
	public static final String QUERY1_5_1 = "";
	public static final String QUERY1_5_2 = "";
	public static final String QUERY1_5 = "";
	
	public static final String QUERY2_1 = "SELECT id \n"
			+ "FROM(SELECT min(total) as Lowest \n"
			+ "FROM (SELECT SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) + SUM(prog_phiheal_empl_nmem) AS total \n"
			+ "FROM hpq_hh \n"
			+ "GROUP BY id) SUM) MAXIMUM, (SELECT *,SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) + SUM(prog_phiheal_empl_nmem) AS total \n"
			+ "FROM hpq_hh \n"
			+ "GROUP BY id) totalperhousehold \n"
			+ "WHERE totalperhousehold.total = lowest ";
	public static final String QUERY2_2 = 
		"SELECT id \n"
		+ "FROM hpq_hh \n"
		+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0 \n";
	public static final String QUERY2_3_1 = "CREATE INDEX INSURANCE on hpq_hh(id,prog_phiheal_ofw_nmem,prog_phiheal_indiv_nmem,prog_phiheal_spon_nmem,prog_phiheal_life_nmem,prog_phiheal_empl_nmem);\n";
	public static final String QUERY2_3_2 = "ALTER TABLE hpq_hh DROP INDEX insurance \n";
	public static final String QUERY2_3 = "SELECT id \n"
			+ "FROM hpq_hh \n"
			+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0; ";
	public static final String QUERY2_4_1 = "CREATE VIEW NO_INSURANCE AS "
			+ "SELECT * \n"
			+ "FROM hpq_hh \n"
			+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0; ";
	public static final String QUERY2_4_2 = "DROP VIEW NO_INSURANCE;";
	public static final String QUERY2_4 = "SELECT id \n"
			+ "FROM no_insurance ";
	public static final String QUERY2_5_1 = "";
	public static final String QUERY2_5_2 = "";
	public static final String QUERY2_5 = "create procedure no_insurance_proc (in has_disabled integer, has_pregnant integer, maximum_size integer) "
			+ "select *"
			+ "from no_insurance"
			+ "where disableind = has_disabled, hpregind = has_pregnant, phsize <= maximum_size;";
	
	public static final String QUERY3_1 = "";
	public static final String QUERY3_2 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from (select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1) D,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
	public static final String QUERY3_3_1 = "CREATE INDEX BIRTH on hpq_mem(age); \n"
			+ "CREATE INDEX DEATH on hpq_death(mdeadage);";
	public static final String QUERY3_3_2 = "ALTER TABLE hpq_mem DROP INDEX birth; \n"
			+ "ALTER TABLE hpq_death DROP INDEX death;";
	public static final String QUERY3_3 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from (select count(*) birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1) D,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L;";
	public static final String QUERY3_4_1 = "create table MORTALITY select * FROM (select count(*) as livebirths "
			+ "from hpq_mem"
			+ "where age < 1) A"
			+ "cross join"
			+ "(select count(*) as birthdeaths"
			+ "from hpq_death"
			+ "where mdeadage < 1) B ";
	public static final String QUERY3_4_2 = "";
	public static final String QUERY3_4 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' from MORTALITY";
	public static final String QUERY3_5_1 = "";
	public static final String QUERY3_5_2 = "";
	public static final String QUERY3_5 = "create procedure infant_mortality (in sex integer, per_x_births integer) \n"
			+ " select birthdeaths/livebirths*per_x_births as 'Infant Mortality Rate' \n"
			+ "from (select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1 and mdeadsx = sex) D,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
	
	public static final String QUERY4_1 = "";
	public static final String QUERY4_2 = "select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end \n"
			+ "as 'Cause of Death', count(*)/Population*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from (select count(*) as Population \n"
			+ "from hpq_hh) P, hpq_death \n"
			+ "group by mdeady ";
	public static final String QUERY4_3_1 = "CREATE INDEX DC on hpq_death(mdeady);";
	public static final String QUERY4_3_2 = "ALTER TABLE hpq_death DROP INDEX dc;";
	public static final String QUERY4_3 = "select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end \n"
			+ "as 'Cause of Death', count(*)/Population*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from (select count(*) as Population \n"
			+ "from hpq_hh) P, hpq_death \n"
			+ "group by mdeady ";
	public static final String QUERY4_4_1 = "CREATE VIEW household_num as \n"
			+ "select count(*) as num_households \n"
			+ "from hpq_hh \n";
	public static final String QUERY4_4_2 = "DROP VIEW household_num";
	public static final String QUERY4_4 = "select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end \n"
			+ "as 'Cause of Death', count(*)/num_household*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from household_num, hpq_death \n"
			+ "group by mdeady ";
	public static final String QUERY4_5_1 = "";
	public static final String QUERY4_5_2 = "";
	public static final String QUERY4_5 = 
			"create procedure cause_spec_death_rate(in low_age integer, high_age integer) \n"
			+ "select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end \n"
			+ "as 'Cause of Death', count(*)/Population*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from (select count(*) as Population \n"
			+ "from hpq_hh) P, hpq_death \n"
			+ "where mdeadage <= low_age and mdeadage <= high_age \n"
			+ "group by mdeady ";
	
	public static final String QUERY5_1 = "";
	public static final String QUERY5_2 = 
			"select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end as 'Cause of death', avg(house_income) as 'Average household income of victims' \n"
			+ "from hpq_death D join \n"
			+ "(select A.id, house_income \n"
			+ "from (select id \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1) A join \n"
			+ "(select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id) B on A.id = B.id) C on D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";
	public static final String QUERY5_3_1 = 
			"CREATE INDEX INCOMEHH on hpq_hh(prevmind); \n"
			+ "CREATE INDEX SUM on hpq_mem(wagcshm); \n"
			+ "CREATE INDEX CAUSE on hpq_death(hpq_hh_id,mdeady); \n";
	public static final String QUERY5_3_2 = "ALTER TABLE hpq_hh DROP INDEX incomehh;\n"
			+ "ALTER TABLE hpq_mem DROP INDEX average;\n"
			+ "ALTER TABLE hpq_death DROP INDEX cause;\n";
	public static final String QUERY5_3 = 
			"select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end as 'Cause of death', avg(house_income) as 'Average household income of victims' \n"
			+ "from hpq_death D, \n"
			+ "(select A.id, house_income \n"
			+ "from (select id \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1) A, \n"
			+ "(select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id) B \n"
			+ "where A.id = B.id) C \n"
			+ "where D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";
	
	public static final String QUERY5_4_1 = "CREATE VIEW SUM_INCOME AS \n"
			+ "select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id \n";
	public static final String QUERY5_4_2 = "";
	public static final String QUERY5_4 = "select case(mdeady) \n"
			+ "when 1 then 'Diseases of the heart' \n"
			+ "when 2 then 'Diseases of the vascular system' \n"
			+ "when 3 then 'Pneumonia' \n"
			+ "when 4 then 'Tubercolosis' \n"
			+ "when 5 then 'Cancer' \n"
			+ "when 6 then 'Diarrhea' \n"
			+ "when 7 then 'Measles' \n"
			+ "when 8 then 'Complication during pregnancy or childbirth' \n"
			+ "when 9 then 'Accident' \n"
			+ "when 10 then 'Diabetes' \n"
			+ "when 11 then 'Disease of the lungs' \n"
			+ "when 12 then 'Disease of the kidney' \n"
			+ "when 13 then 'Drowned from flood' \n"
			+ "when 14 then 'Victim of landslide' \n"
			+ "when 15 then 'Electrocuted during typhoon' \n"
			+ "when 16 then 'Murder' \n"
			+ "else 'Others' \n"
			+ "end as 'Cause of death', avg(house_income) as 'Average household income of victims' \n"
			+ "from hpq_death D, \n"
			+ "(select A.id, house_income \n"
			+ "from (select id \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1) A, \n"
			+ "sum_income \n"
			+ "where A.id = sum_income.id) C \n"
			+ "where D.hpq_hh_id = C.id \n"
			+ "group by mdeady "
;
	public static final String QUERY5_5_1 = "";
	public static final String QUERY5_5_2 = "";
	public static final String QUERY5_5 = "";
	
	public static final String QUERY6_1 = "";
	public static final String QUERY6_2 = "select case (pwd_type) \n"
			+ "when 1 then 'Total blindness' \n"
			+ "when 2 then 'Partial blindness' \n"
			+ "when 3 then 'Low vision' \n"
			+ "when 4 then 'Totally deaf' \n"
			+ "when 5 then 'Partially deaf' \n"
			+ "when 6 then 'Oral defect' \n"
			+ "when 7 then 'One hand' \n"
			+ "when 8 then 'No hands' \n"
			+ "when 9 then 'One leg' \n"
			+ "when 10 then 'No legs' \n"
			+ "when 11 then 'Mild cerebral palsy' \n"
			+ "when 12 then 'Severe cerebral palsy' \n"
			+ "when 13 then 'Retarded' \n"
			+ "when 14 then 'Mentally ill' \n"
			+ "when 15 then 'Mental retardation' \n"
			+ "when 16 then 'Multiple impairment' \n"
			+ "else 'Others' \n"
			+ "end  \n"
			+ "as 'Disability', count(*) as 'Number of disabled', count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as 'Percentage among disabled households affected',  \n"
			+ "count(distinct phiheal_spon_mem_refno)/count(*)*100 as 'Percentage who are PHIHEAL sponsored members' \n"
			+ "from (select M.id, M.memno, M.pwd_ind, M.pwd_type, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from hpq_mem M left  \n"
			+ "join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = M.id and M.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
	public static final String QUERY6_3_1 = "CREATE INDEX DISABLED on hpq_hh(disabledind);"
			+ "CREATE INDEX SPON on hpq_phiheal_spon_mem(hpq_hh_id,phiheal_spon_mem_refno);"
			+ "CREATE INDEX MEMBER on hpq_mem(id, memno);";
	public static final String QUERY6_3_2 = "ALTER TABLE hpq_hh DROP INDEX disabledind;"
			+ "ALTER TABLE hpq_phiheal_spon_mem DROP INDEX SPON;"
			+ "ALTER TABLE hpq_mem DROP INDEX MEMBER;";
	public static final String QUERY6_3 = "select case (pwd_type) \n"
			+ "when 1 then 'Total blindness' \n"
			+ "when 2 then 'Partial blindness' \n"
			+ "when 3 then 'Low vision' \n"
			+ "when 4 then 'Totally deaf' \n"
			+ "when 5 then 'Partially deaf' \n"
			+ "when 6 then 'Oral defect' \n"
			+ "when 7 then 'One hand' \n"
			+ "when 8 then 'No hands' \n"
			+ "when 9 then 'One leg' \n"
			+ "when 10 then 'No legs' \n"
			+ "when 11 then 'Mild cerebral palsy' \n"
			+ "when 12 then 'Severe cerebral palsy' \n"
			+ "when 13 then 'Retarded' \n"
			+ "when 14 then 'Mentally ill' \n"
			+ "when 15 then 'Mental retardation' \n"
			+ "when 16 then 'Multiple impairment' \n"
			+ "else 'Others' \n"
			+ "end  \n"
			+ "as 'Disability', count(*) as 'Number of disabled', count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as 'Percentage among disabled households affected',  \n"
			+ "count(distinct phiheal_spon_mem_refno)/count(*)*100 as 'Percentage who are PHIHEAL sponsored members' \n"
			+ "from (select M.id, M.memno, M.pwd_ind, M.pwd_type, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from hpq_mem M left  \n"
			+ "join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = M.id and M.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
	public static final String QUERY6_4_1 = "CREATE VIEW spo as"
			+ "select M.id, M.memno, M.pwd_ind, M.pwd_type"
			+ "from hpq_mem M"
			+ "select case (pwd_type) \n"
			+ "when 1 then 'Total blindness' \n"
			+ "when 2 then 'Partial blindness' \n"
			+ "when 3 then 'Low vision' \n"
			+ "when 4 then 'Totally deaf' \n"
			+ "when 5 then 'Partially deaf' \n"
			+ "when 6 then 'Oral defect' \n"
			+ "when 7 then 'One hand' \n"
			+ "when 8 then 'No hands' \n"
			+ "when 9 then 'One leg' \n"
			+ "when 10 then 'No legs' \n"
			+ "when 11 then 'Mild cerebral palsy' \n"
			+ "when 12 then 'Severe cerebral palsy' \n"
			+ "when 13 then 'Retarded' \n"
			+ "when 14 then 'Mentally ill' \n"
			+ "when 15 then 'Mental retardation' \n"
			+ "when 16 then 'Multiple impairment' \n"
			+ "else 'Others' \n"
			+ "end  \n"
			+ "as 'Disability', count(*) as 'Number of disabled', count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as 'Percentage among disabled households affected',  \n"
			+ "count(distinct phiheal_spon_mem_refno)/count(*)*100 as 'Percentage who are PHIHEAL sponsored members' \n"
			+ "from (select spo.*, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from spo left join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = spo.id and spo.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";

	public static final String QUERY6_4_2 = "";
	public static final String QUERY6_4 = "";
	public static final String QUERY6_5_1 = "";
	public static final String QUERY6_5_2 = "";
	public static final String QUERY6_5 = "";
	
	public static final String QUERY7_1 = "";
	public static final String QUERY7_2 = 
		"select H.id, H.memno, OFW.OFW_members, Employed_members, Individually_paying_members, Sponsored_members, Lifetime_members \n"+
		" from hpq_mem H  \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_ofw_mem_refno, count(*) as OFW_members \n"+
		"  from hpq_phiheal_ofw_mem \n"+
		"  group by hpq_hh_id,phiheal_ofw_mem_refno) OFW on H.id = OFW.hpq_hh_id AND H.memno = phiheal_ofw_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id,phiheal_empl_mem_refno, count(*) as Employed_members \n"+
		"  from hpq_phiheal_empl_mem \n"+
		"  group by hpq_hh_id, phiheal_empl_mem_refno) EMP on H.id = EMP.hpq_hh_id AND H.memno = phiheal_empl_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_indiv_mem_refno, count(*) as Individually_paying_members \n"+
		"  from hpq_phiheal_indiv_mem \n"+
		"  group by hpq_hh_id, phiheal_indiv_mem_refno) IND on H.id = IND.hpq_hh_id AND H.memno = phiheal_indiv_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_life_mem_refno, count(*) as Lifetime_members \n"+
		"  from hpq_phiheal_life_mem \n"+
		"  group by hpq_hh_id,phiheal_life_mem_refno) LIF on H.id = LIF.hpq_hh_id AND H.memno = phiheal_life_mem_refno \n"+
        "  left join \n"+
        "  (select hpq_hh_id,phiheal_spon_mem_refno, count(*) as Sponsored_members \n"+
		"  from hpq_phiheal_spon_mem \n"+
		"  group by hpq_hh_id,phiheal_spon_mem_refno) SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno \n"+
		" where not(OFW.OFW_members is null and Employed_members is null and Individually_paying_members is null and Sponsored_members is null and Lifetime_members is null) ";
	public static final String QUERY7_3_1 = 
		"CREATE INDEX OFW on hpq_phiheal_ofw_mem(hpq_hh_id,phiheal_ofw_mem_refno); "+
		"CREATE INDEX EMP on hpq_phiheal_empl_mem(hpq_hh_id,phiheal_empl_mem_refno); "+
		"CREATE INDEX IND on hpq_phiheal_indiv_mem(hpq_hh_id,phiheal_indiv_mem_refno); "+
		"CREATE INDEX LIF on hpq_phiheal_life_mem(hpq_hh_id,phiheal_life_mem_refno); "+
		"CREATE INDEX SPO on hpq_phiheal_spon_mem(hpq_hh_id,phiheal_spon_mem_refno); "+
		"CREATE INDEX HM on hpq_mem(id, memno);";
	public static final String QUERY7_3_2 = 
		"ALTER TABLE hpq_phiheal_ofw_mem DROP INDEX ofw; \n"+
	    "ALTER TABLE hpq_phiheal_empl_mem DROP INDEX emp; \n"+
	    "ALTER TABLE hpq_phiheal_indiv_mem DROP INDEX ind; \n"+
	    "ALTER TABLE hpq_phiheal_life_mem DROP INDEX lif; \n"+
	    "ALTER TABLE hpq_phiheal_spon_mem DROP INDEX spo; \n"+
	    "ALTER TABLE hpq_mem DROP INDEX hm;";
	public static final String QUERY7_3 = 
		"select H.id, H.memno, OFW.OFW_members, Employed_members, Individually_paying_members, Sponsored_members, Lifetime_members \n"+
		" from hpq_mem H  \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_ofw_mem_refno, count(*) as OFW_members \n"+
		"  from hpq_phiheal_ofw_mem \n"+
		"  group by hpq_hh_id,phiheal_ofw_mem_refno) OFW on H.id = OFW.hpq_hh_id AND H.memno = phiheal_ofw_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id,phiheal_empl_mem_refno, count(*) as Employed_members \n"+
		"  from hpq_phiheal_empl_mem \n"+
		"  group by hpq_hh_id, phiheal_empl_mem_refno) EMP on H.id = EMP.hpq_hh_id AND H.memno = phiheal_empl_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_indiv_mem_refno, count(*) as Individually_paying_members \n"+
		"  from hpq_phiheal_indiv_mem \n"+
		"  group by hpq_hh_id, phiheal_indiv_mem_refno) IND on H.id = IND.hpq_hh_id AND H.memno = phiheal_indiv_mem_refno \n"+
		"  left join \n"+
		"  (select hpq_hh_id, phiheal_life_mem_refno, count(*) as Lifetime_members \n"+
		"  from hpq_phiheal_life_mem \n"+
		"  group by hpq_hh_id,phiheal_life_mem_refno) LIF on H.id = LIF.hpq_hh_id AND H.memno = phiheal_life_mem_refno \n"+
        "  left join \n"+
        "  (select hpq_hh_id,phiheal_spon_mem_refno, count(*) as Sponsored_members \n"+
		"  from hpq_phiheal_spon_mem \n"+
		"  group by hpq_hh_id,phiheal_spon_mem_refno) SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno \n"+
		" where not(OFW.OFW_members is null and Employed_members is null and Individually_paying_members is null and Sponsored_members is null and Lifetime_members is null) ";
	public static final String QUERY7_4_1 = "";
	public static final String QUERY7_4_2 = "";
	public static final String QUERY7_4 = "";
	public static final String QUERY7_5_1 = "";
	public static final String QUERY7_5_2 = "";
	public static final String QUERY7_5 = "";
}

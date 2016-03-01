package Model;

public class Constants {
	public static final String QUERY_TITLE1 = "Household percentage with food shortage despite food benefits";
	public static final String QUERY_TITLE2 = "Household with no health insurance";
	public static final String QUERY_TITLE3 = "Infant mortality rate";
	public static final String QUERY_TITLE4 = "Cause specific death rate";
	public static final String QUERY_TITLE5 = "Cause of death of people based on average income";
	public static final String QUERY_TITLE6 = "Disability types and percentage of affected individuals with sponsors";
	public static final String QUERY_TITLE7 = "Insurance availed per individual";
	
	public static final String QUERY_EXEC = "execute";
	public static final String QUERY_UPDATE = "update";
	
	public static final int TYPE_STRING = 0;
	public static final int TYPE_INT = 1;
	
	/*QUERY 1*/
	
	//1st Implementation
	public static final String QUERY1_1_w1 = "select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
			+ " from (select count(*) as num_hungry \n"
			+ " from hpq_hh \n"
			+ " where fshort = 1 ";
	public static final String QUERY1_1_w2 = ") NH join \n"
			+ " (select count(*) as total_houses \n"
			+ " from hpq_hh) TH join \n"
			+ " (select count(*) as benefited \n"
			+ " from hpq_hh \n"
			+ " where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1 ";
	public static final String QUERY1_1_w3 = " ) B ";
	
	//2nd Implementation
	public static final String QUERY1_2_w1 = 
		"select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
		+ "from (select count(*) as num_hungry \n"
		+ "from hpq_hh \n"
		+ "where fshort = 1 ";
	public static final String QUERY1_2_w2 = 
			" ) NH, \n"
			+ "(select count(*) as total_houses \n"
			+ "from hpq_hh) TH, \n"
			+ "(select count(*) as benefited \n"
			+ "from hpq_hh \n"
			+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1 ";
	public static final String QUERY1_2_w3 = " ) B; \n";
	
	//3rd Implementation
	public static final String QUERY1_3_1 = "CREATE INDEX HUNGER on hpq_hh(prog_fudforwrk,prog_fudforsch,fshort,zone, mun, brgy)";
	public static final String QUERY1_3_w1 = 
			"select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
			+ "from (select count(*) as num_hungry \n"
			+ "from hpq_hh \n"
			+ "where fshort = 1 ";
	public static final String QUERY1_3_w2 = 
			" ) NH, \n"
			+ "(select count(*) as total_houses \n"
			+ "from hpq_hh) TH, \n"
			+ "(select count(*) as benefited \n"
			+ "from hpq_hh \n"
			+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1 ";
	public static final String QUERY1_3_w3 = " ) B; \n";
	public static final String QUERY1_3_2 = "ALTER TABLE hpq_hh DROP INDEX hunger";
	
	//4th Implementation
	public static final String QUERY1_4_1 =
		"CREATE VIEW household_num as \n"
		+ "select count(*) as total_houses \n"
		+ "from hpq_hh \n";
	public static final String QUERY1_4_w1 = "select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
			+ "from (select count(*) as num_hungry \n"
			+ "from hpq_hh \n"
			+ "where fshort = 1 ";
	public static final String QUERY1_4_w2 = " ) NH, \n"
			+ "household_num, \n"
			+ "(select count(*) as benefited \n"
			+ "from hpq_hh \n"
			+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1 "; 
	public static final String QUERY1_4_w3 = " ) B ";
	
	public static final String QUERY1_4_2 = "DROP VIEW household_num";
			
	
	//5th Implementation
	public static final String QUERY1_5_1 = "CREATE VIEW household_num as \n"
			+ "select count(*) as total_houses \n"
			+ "from hpq_hh \n";
	public static final String QUERY1_5_2_w1 = "create procedure food_shortage_households("
		;
	public static final String QUERY1_5_2_w2 =  ") \n"
			+ "select num_hungry as 'Households that have experienced food shortage', num_hungry/total_houses*100 as 'Percentage among total households', benefited/num_hungry*100 as 'Percentage of hungry households that have benefited from food for work/food for school' \n"
			+ "from (select count(*) as num_hungry \n"
			+ "from hpq_hh \n"
			+ "where fshort = 1 ";
	public static final String QUERY1_5_2_w3 = " ) NH, \n"
			+ "household_num, \n"
			+ "(select count(*) as benefited \n"
			+ "from hpq_hh \n"
			+ "where prog_fudforwrk = 1 or prog_fudforsch = 1 and fshort = 1 "; 
	public static final String QUERY1_5_2_w4 = " ) B ";
	public static final String QUERY1_5_w1 = "call food_shortage_households( ";
	public static final String QUERY1_5_w2 = " )";
	public static final String QUERY1_5_3 = "DROP PROCEDURE IF EXISTS food_shortage_households";
	public static final String QUERY1_5_4 = "DROP VIEW household_num";
	
	
	/*QUERY 2*/
	
	//1st Implementation
	public static final String QUERY2_1_w1 = "SELECT id \n"
			+ "FROM(SELECT min(total) as Lowest \n"
			+ "FROM (SELECT SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) + SUM(prog_phiheal_empl_nmem) AS total \n"
			+ "FROM hpq_hh \n";
	public static final String QUERY2_1_w2 = "GROUP BY id) SUM) MAXIMUM, (SELECT *,SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) + SUM(prog_phiheal_empl_nmem) AS total \n"
			+ "FROM hpq_hh \n";
	public static final String QUERY2_1_w3 = "GROUP BY id) totalperhousehold \n"
			+ "WHERE totalperhousehold.total = lowest ";
			
	//2nd Implementation
	public static final String QUERY2_2 = 
		"SELECT id \n"
		+ "FROM hpq_hh \n"
		+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0 \n";
	
	
	//3rd Implementation
	public static final String QUERY2_3_1 = "CREATE INDEX INSURANCE on hpq_hh(id,prog_phiheal_ofw_nmem,prog_phiheal_indiv_nmem,prog_phiheal_spon_nmem,prog_phiheal_life_nmem,prog_phiheal_empl_nmem,disableind, hpregind, phsize) \n";
	
	public static final String QUERY2_3 = "SELECT id \n"
			+ "FROM hpq_hh \n"
			+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0 ";
	
	public static final String QUERY2_3_2 = "ALTER TABLE hpq_hh DROP INDEX insurance \n";
	
	//4th Implementation
	public static final String QUERY2_4_1 = "CREATE VIEW NO_INSURANCE AS "
			+ "SELECT * \n"
			+ "FROM hpq_hh \n"
			+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0 ";
	
	public static final String QUERY2_4 = "SELECT id \n"
			+ "FROM no_insurance ";
	
	public static final String QUERY2_4_2 = "DROP VIEW NO_INSURANCE;";
			
	//5th Implementation
	public static final String QUERY2_5_1 = "CREATE VIEW NO_INSURANCE AS "
			+ "SELECT * \n"
			+ "FROM hpq_hh \n"
			+ "WHERE prog_phiheal_spon_nmem = 0 AND prog_phiheal_indiv_nmem = 0 AND prog_phiheal_ofw_nmem = 0 AND prog_phiheal_life_nmem = 0 AND prog_phiheal_empl_nmem = 0 ";
	public static final String QUERY2_5_2 = 
			"create procedure no_insurance_proc() "
			+ "select *"
			+ "from no_insurance ";
	public static final String QUERY2_5 = "call no_insurance_proc()";
	public static final String QUERY2_5_3 = "DROP PROCEDURE IF EXISTS no_insurance_proc";
	public static final String QUERY2_5_4 = "DROP VIEW NO_INSURANCE;";
	
	
	
	/*QUERY 3*/
	
	//1st Implementation
	public static final String QUERY3_1_w1 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate \n'"
			+ " from (select count(*) as birthdeaths \n"
			+ " from hpq_death \n"
			+ " where mdeadage < 1 ";
	public static final String QUERY3_1_w2 = " ) A join \n"
			+ " (select count(*) as livebirths \n"
			+ " from hpq_mem \n"
			+ " where age < 1) B";
	
	//2nd Implementation
	public static final String QUERY3_2_w1 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from (select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1 ";
	public static final String QUERY3_2_w2 = ") D,  \n" 
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
			
	//3rd Implementation
	public static final String QUERY3_3_1 = "CREATE INDEX BIRTH on hpq_mem(age); \n"
			+ "CREATE INDEX DEATH on hpq_death(mdeadage, mdeadsx);";
	
	public static final String QUERY3_3_w1 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from (select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1";
	public static final String QUERY3_3_w2 = ") D,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
	
	public static final String QUERY3_3_2 = "ALTER TABLE hpq_mem DROP INDEX birth; \n"
			+ "ALTER TABLE hpq_death DROP INDEX death;";
			
	//4th Implementation
	public static final String QUERY3_4_1 = "create view DEATH as \n"
			+ "select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1 \n";// sa end nito
	
	public static final String QUERY3_4 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from DEATH,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
	
	public static final String QUERY3_4_2 = " DROP VIEW DEATH;";
	
	//5th Implementation
	public static final String QUERY3_5_1 = "create view DEATH as \n"
			+ "select count(*) as birthdeaths \n"
			+ "from hpq_death \n"
			+ "where mdeadage < 1 \n"; //sa end nito
	
	public static final String QUERY3_5_2 = "create procedure infant_mortality () \n"
			+ "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' \n"
			+ "from DEATH,  \n"
			+ "(select count(*) as livebirths \n"
			+ "from hpq_mem \n"
			+ "where age < 1) L \n";
	public static final String QUERY3_5 = "CALL infant_mortality()"; 
	public static final String QUERY3_5_3 = "DROP PROCEDURE IF EXISTS no_insurance_proc;";
	public static final String QUERY3_5_4 = "DROP VIEW DEATH;";
	
	/*QUERY 4*/
	
	//1st Implementation
	public static final String QUERY4_1_w1 = "select case(mdeady) \n"
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
			+ "as 'Cause of Death', dead/Population*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from (select mdeady, count(*) as dead \n"
			+ "from hpq_death \n";
	public static final String QUERY4_1_w2 = " group by mdeady) A \n"
			+ "join \n" 
			+ "(select count(*) as population \n"
			+ "from hpq_hh) B \n";
			
	//2nd Implementation
	public static final String QUERY4_2_w1 = "select case(mdeady) \n"
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
			+ "from hpq_hh) P, hpq_death ";
	public static final String QUERY4_2_w2 = "group by mdeady; ";
	
	//3rd Implementation
	public static final String QUERY4_3_1 = "CREATE INDEX DC on hpq_death(mdeady, mdeadsx, mdeadage);";
	public static final String QUERY4_3_w1 = "select case(mdeady) \n"
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
			+ "from hpq_hh) P, hpq_death ";
	public static final String QUERY4_3_w2 = "group by mdeady; ";
	public static final String QUERY4_3_2 = "ALTER TABLE hpq_death DROP INDEX dc;";
	
	//4th Implementation
	public static final String QUERY4_4_1 = "CREATE VIEW household as \n"
			+ "select count(*) as num_households \n"
			+ "from hpq_hh \n";
	
	public static final String QUERY4_4_w1 = "select case(mdeady) \n"
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
			+ "as 'Cause of Death', count(*)/num_households*100000 as 'Cause-specific death rate per 100,000 people' \n"
			+ "from household, hpq_death ";
	public static final String QUERY4_4_w2 = "group by mdeady; ";
	public static final String QUERY4_4_2 = "DROP VIEW household";
	
	//5th Implementation
	public static final String QUERY4_5_1 = "CREATE VIEW household as \n"
			+ "select count(*) as num_households \n"
			+ "from hpq_hh \n";
	public static final String QUERY4_5_2_w1 = 
			"create procedure cause_spec_death_rate( ";
	
	public static final String QUERY4_5_2_w2 = " ) \n"
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
					+ "as 'Cause of Death', count(*)/num_households*100000 as 'Cause-specific death rate per 100,000 people' \n"
					+ "from household, hpq_death ";
	public static final String QUERY4_5_2_w3 = "group by mdeady; ";
	public static final String QUERY4_5_w1 = "CALL cause_spec_death_rate( ";
	public static final String QUERY4_5_w2 = " )";
	public static final String QUERY4_5_3 = "DROP PROCEDURE cause_spec_death_rate";
	public static final String QUERY4_5_4 = "DROP VIEW household";
			
	
	
	/*QUERY 5*/
	
	//1st Implementation
	public static final String QUERY5_1_w1 = "select case(mdeady) \n"
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
			+ "end as 'Cause of Death', avg(house_income) as 'Average household income of victims' \n"
			+ "from (select H.id, sum(wagcshm) as house_income,zone,mun,brgy \n"
			+ "from hpq_mem M, hpq_hh H \n"
			+ "where prevmind = 1 and wagcshm != 0 and H.id = M.id ";
	public static final String QUERY5_1_w2 = " group by id) A, hpq_death D \n"
			+ "where A.id = D.hpq_hh_id \n"
			+ "group by mdeady ";
	//2nd Implementation
	public static final String QUERY5_2_w1 = 
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
			+ "from (select id, zone, mun,brgy \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1 ";
	public static final String QUERY5_2_w2= " ) A join \n"
			+ "(select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id) B on A.id = B.id) C on D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";

	//3rd Implementation
	public static final String QUERY5_3_1 = 
			"CREATE INDEX INCOMEHH on hpq_hh(prevmind,mun,zone,brgy); \n"
			+ "CREATE INDEX SUM on hpq_mem(wagcshm); \n"
			+ "CREATE INDEX CAUSE on hpq_death(hpq_hh_id,mdeady); \n";
	
	public static final String QUERY5_3_w1 = 
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
			+ "from (select id, zone, mun,brgy \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1 ";
	public static final String QUERY5_3_w2 = " ) A join \n"
			+ "(select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id) B on A.id = B.id) C on D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";

	
	public static final String QUERY5_3_2 = "ALTER TABLE hpq_hh DROP INDEX incomehh;\n"
			+ "ALTER TABLE hpq_mem DROP INDEX average;\n"
			+ "ALTER TABLE hpq_death DROP INDEX cause;\n";
			
	//4th Implementation
	public static final String QUERY5_4_1 = "CREATE VIEW SUM_INCOME AS \n"
			+ "select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id \n";
	
	public static final String QUERY5_4_w1 = "select case(mdeady) \n"
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
			+ "from (select id, zone,mun,brgy \n"
			+ "from hpq_hh \n"
			+ "where prevmind = 1 ";
			
	public static final String QUERY5_4_w2 =" ) A, \n"
			+ "sum_income \n"
			+ "where A.id = sum_income.id) C \n"
			+ "where D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";
			
	public static final String QUERY5_4_2 = "DROP VIEW SUM_INCOME";
	
	//5th Implementation	
	public static final String QUERY5_5_1 = "CREATE VIEW SUM_INCOME AS \n"
			+ "select id, sum(wagcshm) as house_income \n"
			+ "from hpq_mem \n"
			+ "where wagcshm != 0 \n"
			+ "group by id \n";
	
	public static final String QUERY5_5_2_w1 = "create procedure cause_of_death( ";
    public static final String QUERY5_5_2_w2 = ") \n" 
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
					+ "end as 'Cause of death', avg(house_income) as 'Average household income of victims' \n"
					+ "from hpq_death D, \n"
					+ "(select A.id, house_income \n"
					+ "from (select id, zone,mun,brgy \n"
					+ "from hpq_hh \n"
					+ "where prevmind = 1 ";
    public static final String QUERY5_5_2_w3 = " group by mdeady) A, \n"
			+ "sum_income \n"
			+ "where A.id = sum_income.id) C \n"
			+ "where D.hpq_hh_id = C.id \n"
			+ "group by mdeady ";
	public static final String QUERY5_5_w1 = "call cause_of_death( ";
	public static final String QUERY5_5_w2 = " )";
	public static final String QUERY5_5_3 = "DROP PROCEDURE cause_of_death";
	public static final String QUERY5_5_4 = "DROP VIEW SUM_INCOME";
	
	/*QUERY 6*/
	
	//1st Implementation
	public static final String QUERY6_1 = "select case (A.pwd_type) \n"
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
			+ "as 'Disability', num_disabled as Number_of_disabled, num_disabled_house/num_houses*100 as Percentage_among_disabled_households_affected, \n"
			+ "num_phiheal/num_disabled*100 as Percentage_who_are_PHILHEALTH_sponsored_members \n"
			+ "from (select pwd_type, count(*) as num_disabled \n"
			+ "from hpq_mem \n"
			+ "where pwd_ind = 1 \n"
		    + "group by pwd_type) A, \n" 
			+ "(select pwd_type, count(*) as num_phiheal \n"
			+ "from hpq_mem, hpq_phiheal_spon_mem \n"
			+ "where pwd_ind = 1 and hpq_mem.id = hpq_phiheal_spon_mem.hpq_hh_id and hpq_mem.memno = hpq_phiheal_spon_mem.phiheal_spon_mem_refno \n"
		    + "group by pwd_type) B, \n"
			+ "(select count(*) as num_houses \n"
			+ "from hpq_hh \n"
			+ "where disableind = 1) C, \n"
		    + "(select pwd_type, count(distinct hpq_hh.id) as num_disabled_house \n"
		    + "from hpq_mem, hpq_hh \n"
		    + "where pwd_ind = 1 and hpq_mem.id = hpq_hh.id \n"
		    + "group by pwd_type) D \n"
			+ "where A.pwd_type = B.pwd_type and B.pwd_type = D.pwd_type \n"
			+ "group by A.pwd_type \n";
	
	//2nd Implementation
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
			+ "as 'Disability', count(*) as Number_of_disabled, count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as Percentage_among_disabled_households_affected,  \n"
			+ "count( phiheal_spon_mem_refno)/count(*)*100 as Percentage_who_are_PHILHEALTH_sponsored_members \n"
			+ "from (select M.id, M.memno, M.pwd_ind, M.pwd_type, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from hpq_mem M left  \n"
			+ "join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = M.id and M.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
			
	//3rd Implementation
	public static final String QUERY6_3_1 = "CREATE INDEX DISABLED on hpq_hh(disabledind);"
			+ "CREATE INDEX SPON on hpq_phiheal_spon_mem(hpq_hh_id,phiheal_spon_mem_refno);"
			+ "CREATE INDEX MEMBER on hpq_mem(id, memno);";
	
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
			+ "as 'Disability', count(*) as Number_of_disabled, count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as Percentage_among_disabled_households_affected,  \n"
			+ "count( phiheal_spon_mem_refno)/count(*)*100 as Percentage_who_are_PHILHEALTH_sponsored_members \n"
			+ "from (select M.id, M.memno, M.pwd_ind, M.pwd_type, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from hpq_mem M left  \n"
			+ "join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = M.id and M.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
	public static final String QUERY6_3_2 = "ALTER TABLE hpq_hh DROP INDEX disabledind;"
			+ "ALTER TABLE hpq_phiheal_spon_mem DROP INDEX SPON;"
			+ "ALTER TABLE hpq_mem DROP INDEX MEMBER;";
			
	//4th Implementation
	public static final String QUERY6_4_1 = "CREATE VIEW spo as \n"
			+ "select M.id, M.memno, M.pwd_ind, M.pwd_type \n"
			+ "from hpq_mem M";
	
	public static final String QUERY6_4 = "select case (pwd_type) \n"
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
			+ "as 'Disability', count(*) as Number_of_disabled, count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as Percentage_among_disabled_households_affected,  \n"
			+ "count( phiheal_spon_mem_refno)/count(*)*100 as Percentage_who_are_PHILHEALTH_sponsored_members \n"
			+ "from (select spo.*, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from spo left join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = spo.id and spo.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
	public static final String QUERY6_4_2 = "DROP VIEW spo";
			
	//5ht Implementation
	public static final String QUERY6_5_1 = "CREATE VIEW spo as \n"
			+ "select M.id, M.memno, M.pwd_ind, M.pwd_type \n"
			+ "from hpq_mem M";
	public static final String QUERY6_5_2_w1 = "create procedure disabilities_procedure( ";
	public static final String QUERY6_5_2_w2 = ") \n"
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
			+ "as 'Disability', count(*) as Number_of_disabled, count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as Percentage_among_disabled_households_affected,  \n"
			+ "count(phiheal_spon_mem_refno)/count(*)*100 as Percentage_who_are_PHILHEALTH_sponsored_members \n"
			+ "from (select spo.*, PH.hpq_hh_id, PH.phiheal_spon_mem_refno \n"
			+ "from spo left join  \n"
			+ "(select * \n"
			+ "from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = spo.id and spo.memno = PH.phiheal_spon_mem_refno) A \n"
			+ "where pwd_ind = 1 \n"
			+ "group by pwd_type";
	public static final String QUERY6_5_w1 = "call disabilities_procedure( ";
	public static final String QUERY6_5_w2 = " )";
	public static final String QUERY6_5_3 = "DROP PROCEDURE IF EXISTS disabilities_procedure";
	public static final String QUERY6_5_4 = "DROP VIEW spo";
	
	
	/*QUERY 7*/
	
	//1st Implementation
	public static final String QUERY7_1 = "select H.id, H.memno, OFW.OFW_members, Employed_members, Individually_paying_members, Sponsored_members, Lifetime_members \n"
			+ " from hpq_mem H \n"
			+ " left join \n"
			+ " (select *, count(*) as OFW_members \n"
			+ " from hpq_phiheal_ofw_mem \n"
			+ " group by hpq_hh_id,phiheal_ofw_mem_refno) OFW on H.id = OFW.hpq_hh_id AND H.memno = phiheal_ofw_mem_refno \n"
			+ " left join \n"
			+ " (select *, count(*) as Employed_members \n"
			+ " from hpq_phiheal_empl_mem \n"
			+ " group by hpq_hh_id, phiheal_empl_mem_refno) EMP on H.id = EMP.hpq_hh_id AND H.memno = phiheal_empl_mem_refno \n"
			+ " left join \n"
			+ " (select *, count(*) as Individually_paying_members \n"
			+ " from hpq_phiheal_indiv_mem \n"
			+ " group by hpq_hh_id, phiheal_indiv_mem_refno) IND on H.id = IND.hpq_hh_id AND H.memno = phiheal_indiv_mem_refno \n"
			+ " left join \n"
			+ " (select *, count(*) as Lifetime_members \n"
			+ " from hpq_phiheal_life_mem \n"
			+ " group by hpq_hh_id,phiheal_life_mem_refno) LIF on H.id = LIF.hpq_hh_id AND H.memno = phiheal_life_mem_refno \n"
			+ " left join \n"
			+ " (select *, count(*) as Sponsored_members \n"
			+ " from hpq_phiheal_spon_mem \n"
			+ " group by hpq_hh_id,phiheal_spon_mem_refno) SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno \n"
			+ " where not(OFW.OFW_members is null and Employed_members is null and Individually_paying_members is null and Sponsored_members is null and Lifetime_members is null) \n";
	
	//2nd Implementation
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
	
	//3rd Implementation
	public static final String QUERY7_3_1 = 
		"CREATE INDEX OFW on hpq_phiheal_ofw_mem(hpq_hh_id,phiheal_ofw_mem_refno); "+
		"CREATE INDEX EMP on hpq_phiheal_empl_mem(hpq_hh_id,phiheal_empl_mem_refno); "+
		"CREATE INDEX IND on hpq_phiheal_indiv_mem(hpq_hh_id,phiheal_indiv_mem_refno); "+
		"CREATE INDEX LIF on hpq_phiheal_life_mem(hpq_hh_id,phiheal_life_mem_refno); "+
		"CREATE INDEX SPO on hpq_phiheal_spon_mem(hpq_hh_id,phiheal_spon_mem_refno); "+
		"CREATE INDEX HM on hpq_mem(id, memno,sex);";
	
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
	public static final String QUERY7_3_2 = 
		"ALTER TABLE hpq_phiheal_ofw_mem DROP INDEX ofw; \n"+
	    "ALTER TABLE hpq_phiheal_empl_mem DROP INDEX emp; \n"+
	    "ALTER TABLE hpq_phiheal_indiv_mem DROP INDEX ind; \n"+
	    "ALTER TABLE hpq_phiheal_life_mem DROP INDEX lif; \n"+
	    "ALTER TABLE hpq_phiheal_spon_mem DROP INDEX spo; \n"+
	    "ALTER TABLE hpq_mem DROP INDEX hm;";
		
	//4ht Implementation
	public static final String QUERY7_4_1 = "CREATE VIEW SPO AS select hpq_hh_id,phiheal_spon_mem_refno, count(*) as Sponsored_members \n"
			+ "from hpq_phiheal_spon_mem \n"
			+ "group by hpq_hh_id,phiheal_spon_mem_refno \n";
	
	public static final String QUERY7_4 = "select H.id, H.memno, OFW.OFW_members, Employed_members, Individually_paying_members, Sponsored_members, Lifetime_members \n"+
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
	        "  SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno \n"+
			" where not(OFW.OFW_members is null and Employed_members is null and Individually_paying_members is null and Sponsored_members is null and Lifetime_members is null) ";
	public static final String QUERY7_4_2 = "DROP VIEW SPO";
	
	//5th Implementation
	public static final String QUERY7_5_1 = "CREATE VIEW SPO AS select hpq_hh_id,phiheal_spon_mem_refno, count(*) as Sponsored_members \n"
			+ "from hpq_phiheal_spon_mem \n"
			+ "group by hpq_hh_id,phiheal_spon_mem_refno \n";
	public static final String QUERY7_5_2_w1 = "create procedure health_insurance_procedure( ";
	public static final String QUERY7_5_2_w2 = " )"
					+ " select H.id, H.memno, OFW.OFW_members, Employed_members, Individually_paying_members, Sponsored_members, Lifetime_members \n"+
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
			        "  SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno \n"+
					" where not(OFW.OFW_members is null and Employed_members is null and Individually_paying_members is null and Sponsored_members is null and Lifetime_members is null) ";
	public static final String QUERY7_5_w1 = "call procedure health_insurance_procedure( ";
	public static final String QUERY7_5_w2 = " )";
	public static final String QUERY7_5_3 = "DROP PROCEDURE IF EXISTS health_insurance_procedure";
	public static final String QUERY7_5_4 = "DROP VIEW SPO";
}

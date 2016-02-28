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
	public static final String QUERY1_3_1 = "";
	public static final String QUERY1_3_2 = "";
	public static final String QUERY1_3 = "";
	public static final String QUERY1_4_1 ="";
	public static final String QUERY1_4_2 = "";
	public static final String QUERY1_4 = "";
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
	public static final String QUERY2_5 = "";
	
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
	public static final String QUERY3_4_1 = "";
	public static final String QUERY3_4_2 = "";
	public static final String QUERY3_4 = "";
	public static final String QUERY3_5_1 = "";
	public static final String QUERY3_5_2 = "";
	public static final String QUERY3_5 = "";
	
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
	public static final String QUERY4_5 = "";
	
	public static final String QUERY5_1 = "";
	public static final String QUERY5_2 = "select case(mdeady) when 1 then 'Diseases of the heart' when 2 then 'Diseases of the vascular system' when 3 then 'Pneumonia' when 4 then 'Tubercolosis' when 5 then 'Cancer' when 6 then 'Diarrhea' when 7 then 'Measles' when 8 then 'Complication during pregnancy or childbirth' when 9 then 'Accident' when 10 then 'Diabetes' when 11 then 'Disease of the lungs' when 12 then 'Disease of the kidney' when 13 then 'Drowned from flood' when 14 then 'Victim of landslide' when 15 then 'Electrocuted during typhoon' when 16 then 'Murder' else 'Others' end as 'Cause of death', avg(avg_house_income) as 'Average household income of victims' from hpq_death D, (select A.id, avg_house_income from (select id from hpq_hh where prevmind = 1) A, (select id, avg(wagcshm) as avg_house_income from hpq_mem where wagcshm != 0 group by id) B where A.id = B.id) C where D.hpq_hh_id = C.id group by mdeady";
	public static final String QUERY5_3_1 = "";
	public static final String QUERY5_3_2 = "";
	public static final String QUERY5_3 = "";
	public static final String QUERY5_4_1 = "";
	public static final String QUERY5_4_2 = "";
	public static final String QUERY5_4 = "";
	public static final String QUERY5_5_1 = "";
	public static final String QUERY5_5_2 = "";
	public static final String QUERY5_5 = "";
	
	public static final String QUERY6_1 = "";
	public static final String QUERY6_2 = "";
	public static final String QUERY6_3_1 = "";
	public static final String QUERY6_3_2 = "";
	public static final String QUERY6_3 = "";
	public static final String QUERY6_4_1 = "";
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

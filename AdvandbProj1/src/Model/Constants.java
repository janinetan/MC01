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
	public static final String QUERY1_2 = "select num_hungry/num_households*100 as 'Proportion of households that experienced food shortage over the past 3 months', days_hungry as 'Average days on food shortage' from (select count(*) as num_hungry, avg((fsdays_1+fsdays_2+fsdays_3)) as days_hungry from hpq_hh where fshort = 1) A, (select count(*) as num_households from hpq_hh) B;";
	public static final String QUERY1_3 = "";
	public static final String QUERY1_4 = "";
	public static final String QUERY1_5 = "";
	
	public static final String QUERY2_1 = "";
	public static final String QUERY2_2 = "SELECT id, totalperhousehold.total, highest FROM(SELECT max(total) as highest FROM (SELECT SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) AS total FROM hpq_hh GROUP BY id) SUM) MAXIMUM, (SELECT *,SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) AS total FROM hpq_hh GROUP BY id) totalperhousehold WHERE totalperhousehold.total = highest";
	public static final String QUERY2_3 = "";
	public static final String QUERY2_4 = "";
	public static final String QUERY2_5 = "";
	
	public static final String QUERY3_1 = "";
	public static final String QUERY3_2 = "select birthdeaths/livebirths*1000 as 'Infant Mortality Rate' from (select count(*) birthdeaths from hpq_death where mdeadage < 1) D, (select count(*) as livebirths from hpq_mem where age < 1) L;";
	public static final String QUERY3_3 = "";
	public static final String QUERY3_4 = "";
	public static final String QUERY3_5 = "";
	
	public static final String QUERY4_1 = "";
	public static final String QUERY4_2 = "select case(mdeady) when 1 then 'Diseases of the heart' when 2 then 'Diseases of the vascular system' when 3 then 'Pneumonia' when 4 then 'Tubercolosis' when 5 then 'Cancer' when 6 then 'Diarrhea' when 7 then 'Measles' when 8 then 'Complication during pregnancy or childbirth' when 9 then 'Accident' when 10 then 'Diabetes' when 11 then 'Disease of the lungs' when 12 then 'Disease of the kidney' when 13 then 'Drowned from flood' when 14 then 'Victim of landslide' when 15 then 'Electrocuted during typhoon' when 16 then 'Murder' else 'Others' end as 'Cause of death', avg(avg_house_income) as 'Average household income of victims' from hpq_death D, (select A.id, avg_house_income from (select id from hpq_hh where prevmind = 1) A, (select id, avg(wagcshm) as avg_house_income from hpq_mem where wagcshm != 0 group by id) B where A.id = B.id) C where D.hpq_hh_id = C.id group by mdeady";
	public static final String QUERY4_3 = "";
	public static final String QUERY4_4 = "";
	public static final String QUERY4_5 = "";
	
	public static final String QUERY5_1 = "";
	public static final String QUERY5_2 = "";
	public static final String QUERY5_3 = "";
	public static final String QUERY5_4 = "";
	public static final String QUERY5_5 = "";
	
	public static final String QUERY6_1 = "";
	public static final String QUERY6_2 = "";
	public static final String QUERY6_3 = "";
	public static final String QUERY6_4 = "";
	public static final String QUERY6_5 = "";
	
	public static final String QUERY7_1 = "";
	public static final String QUERY7_2 = "";
	public static final String QUERY7_3 = "";
	public static final String QUERY7_4 = "";
	public static final String QUERY7_5 = "";
}

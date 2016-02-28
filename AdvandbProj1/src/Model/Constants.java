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
			"select num_hungry/num_households*100 as 'Proportion of households that experienced food shortage over the past 3 months', \n" +
			"  days_hungry as 'Average days on food shortage' \n" +
			"  from (select count(*) as num_hungry, \n " +
			"       avg((fsdays_1+fsdays_2+fsdays_3)) as days_hungry \n" +
			"  from hpq_hh \n" +
			"  where fshort = 1) A, \n" + 
			"    (select count(*) as num_households \n" +
			"    from hpq_hh) B;" ;
	public static final String QUERY1_3_index1 = "CREATE INDEX HUNGER on hpq_hh(fsdays_1,fsdays_2,fsdays_3,fshort)";
	public static final String QUERY1_3_index2 = "ALTER TABLE hpq_hh DROP INDEX hunger";
	public static final String QUERY1_3 = "select num_hungry/num_households*100 as 'Proportion of households that experienced food shortage over the past 3 months', days_hungry as 'Average days on food shortage' from (select count(*) as num_hungry, avg((fsdays_1+fsdays_2+fsdays_3)) as days_hungry from hpq_hh where fshort = 1) A,  (select count(*) as num_households from hpq_hh) B;";
	public static final String QUERY1_4 = "";
	public static final String QUERY1_5 = "";
	
	public static final String QUERY2_1 = "";
	public static final String QUERY2_2 = 
			"SELECT id, totalperhousehold.total, highest \n" +
			"FROM(SELECT max(total) as highest \n" +
			"  FROM (SELECT SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) AS total \n" +
			"    FROM hpq_hh \n" +
			"    GROUP BY id) SUM) MAXIMUM, \n" + 
			"  (SELECT *,SUM(prog_phiheal_spon_nmem) + SUM(prog_phiheal_indiv_nmem) + SUM(prog_phiheal_ofw_nmem) + SUM(prog_phiheal_life_nmem) AS total \n" +
			"   FROM hpq_hh \n" +
			"   GROUP BY id) totalperhousehold \n" +
			"WHERE totalperhousehold.total = highest";
	public static final String QUERY2_3_index1 = "";
	public static final String QUERY2_3_index2 = "";
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
	public static final String QUERY5_2 = "select case(mdeady) when 1 then 'Diseases of the heart' when 2 then 'Diseases of the vascular system' when 3 then 'Pneumonia' when 4 then 'Tubercolosis' when 5 then 'Cancer' when 6 then 'Diarrhea' when 7 then 'Measles' when 8 then 'Complication during pregnancy or childbirth' when 9 then 'Accident' when 10 then 'Diabetes' when 11 then 'Disease of the lungs' when 12 then 'Disease of the kidney' when 13 then 'Drowned from flood' when 14 then 'Victim of landslide' when 15 then 'Electrocuted during typhoon' when 16 then 'Murder' else 'Others' end as 'Cause of death', avg(avg_house_income) as 'Average household income of victims' from hpq_death D, (select A.id, avg_house_income from (select id from hpq_hh where prevmind = 1) A, (select id, avg(wagcshm) as avg_house_income from hpq_mem where wagcshm != 0 group by id) B where A.id = B.id) C where D.hpq_hh_id = C.id group by mdeady";
	public static final String QUERY5_3 = "";
	public static final String QUERY5_4 = "";
	public static final String QUERY5_5 = "";
	
	public static final String QUERY6_1 = "";
	public static final String QUERY6_2 = "select case (pwd_type) when 1 then 'Total blindness' when 2 then 'Partial blindness' when 3 then 'Low vision' when 4 then 'Totally deaf' when 5 then 'Partially deaf' when 6 then 'Oral defect' when 7 then 'One hand' when 8 then 'No hands' when 9 then 'One leg' when 10 then 'No legs' when 11 then 'Mild cerebral palsy' when 12 then 'Severe cerebral palsy' when 13 then 'Retarded' when 14 then 'Mentally ill' when 15 then 'Mental retardation' when 16 then 'Multiple impairment' else 'Others' end as 'Disability', count(*) as 'Number of disabled', count(distinct id)/(select count(*) from hpq_hh where disableind = 1)*100 as 'Percentage among disabled households affected', count(distinct phiheal_spon_mem_refno)/count(*)*100 as 'Percentage who are PHIHEAL sponsored members' from (select M.id, M.memno, M.pwd_ind, M.pwd_type, PH.hpq_hh_id, PH.phiheal_spon_mem_refno from hpq_mem M left join (select * from hpq_phiheal_spon_mem) PH on PH.hpq_hh_id = M.id and M.memno = PH.phiheal_spon_mem_refno) A where pwd_ind = 1 group by pwd_type";
	public static final String QUERY6_3 = "";
	public static final String QUERY6_4 = "";
	public static final String QUERY6_5 = "";
	
	public static final String QUERY7_1 = "";
	public static final String QUERY7_2 = "select H.id, H.memno, OFW.ofw as 'OFW members', emp as 'Employed members', ind as 'Individually paying members', spo as 'Sponsored members', lif as 'Lifetime members' from hpq_mem H left join (select *, count(*) as ofw from hpq_phiheal_ofw_mem group by hpq_hh_id,phiheal_ofw_mem_refno) OFW on H.id = OFW.hpq_hh_id AND H.memno = phiheal_ofw_mem_refno left join (select *, count(*) as emp from hpq_phiheal_empl_mem group by hpq_hh_id, phiheal_empl_mem_refno) EMP on H.id = EMP.hpq_hh_id AND H.memno = phiheal_empl_mem_refno left join (select *, count(*) as ind from hpq_phiheal_indiv_mem group by hpq_hh_id, phiheal_indiv_mem_refno) IND on H.id = IND.hpq_hh_id AND H.memno = phiheal_indiv_mem_refno left join (select *, count(*) as lif from hpq_phiheal_life_mem group by hpq_hh_id,phiheal_life_mem_refno) LIF on H.id = LIF.hpq_hh_id AND H.memno = phiheal_life_mem_refno left join (select *, count(*) as spo from hpq_phiheal_spon_mem group by hpq_hh_id,phiheal_spon_mem_refno) SPO on H.id = SPO.hpq_hh_id AND H.memno = phiheal_spon_mem_refno where not(OFW.ofw is null and emp is null and ind is null and spo is null and lif is null)";
	public static final String QUERY7_3 = "";
	public static final String QUERY7_4 = "";
	public static final String QUERY7_5 = "";
}

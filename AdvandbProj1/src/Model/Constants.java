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
	public static final String QUERY1_3 = "";

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
	public static final String QUERY2_3 = "";
	public static final String QUERY2_4 = "";
	public static final String QUERY2_5 = "";
	
	public static final String QUERY3_1 = "";
	public static final String QUERY3_2 = "";
	public static final String QUERY3_3 = "";
	public static final String QUERY3_4 = "";
	public static final String QUERY3_5 = "";
	
	public static final String QUERY4_1 = "";
	public static final String QUERY4_2 = "";
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

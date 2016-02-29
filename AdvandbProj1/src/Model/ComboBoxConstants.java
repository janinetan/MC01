package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class ComboBoxConstants {

	public static ArrayList<Column> OPTIONS_QUERY1 = new ArrayList<Column>(Arrays.asList(
			new Column("zone", "Zone", Constants.TYPE_INT),
			new Column("mun", "Municipality", Constants.TYPE_INT),
			new Column("brgy", "Barangay", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY2 = new ArrayList<Column>(Arrays.asList(
			new Column("disableind", "Disabled", Constants.TYPE_INT),
			new Column("hpregind", "Pregnant", Constants.TYPE_INT),
			new Column("phsize", "OFW Members", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY3 = new ArrayList<Column>(Arrays.asList(
			new Column("mdeadsx", "Gender", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY4 = new ArrayList<Column>(Arrays.asList(
			new Column("mdeadsx", "Gender", Constants.TYPE_INT),
			new Column("mdeadage", "Age", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY5 = new ArrayList<Column>(Arrays.asList(
			new Column("zone", "Zone", Constants.TYPE_INT),
			new Column("mun", "Municipality", Constants.TYPE_INT),
			new Column("brgy", "Barangay", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY6 = new ArrayList<Column>(Arrays.asList(
			new Column("Number_of_disabled", "Number of Disabled", Constants.TYPE_INT),
			new Column("Percentage_among_disabled_households_affected", "% of disabled households affected", Constants.TYPE_INT),
			new Column("Percentage_who_are_PHILHEALTH_sponsored_members", "% of sponsored PHILHEALTH", Constants.TYPE_INT)));
	
	public static ArrayList<Column> OPTIONS_QUERY7 = new ArrayList<Column>(Arrays.asList(
			new Column("sex", "Gender", Constants.TYPE_INT),
			new Column("id", "Household Number", Constants.TYPE_INT),
			new Column("memno", "Member Number", Constants.TYPE_INT)));
	
	public static Column findColumn(ArrayList<Column> cols, String name){
		for (Column c: cols){
			if (name.equals(c.getColName()) || name.equals(c.getName())){
				return c;
			}
		}
		return null;
	}
}
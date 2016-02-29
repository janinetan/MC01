package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class ComboBoxConstants {

	public static ArrayList<Column> OPTIONS_QUERY1 = new ArrayList<Column>(Arrays.asList(
			new Column("zone", "Zone", Constants.TYPE_INT),
			new Column("mun", "Municipality", Constants.TYPE_INT),
			new Column("brgy", "Barangay", Constants.TYPE_STRING)));
	
	public static Column findColumn(ArrayList<Column> cols, String name){
		for (Column c: cols){
			if (name.equals(c.getColName()) || name.equals(c.getName())){
				return c;
			}
		}
		return null;
	}
}

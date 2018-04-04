package HRPS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class RoomData extends StoreData {
	
	public final String SEPARATOR = "|"; //can delete later
	
	public void readClass(String filename, Object[] o) throws IOException{
		
		ArrayList stringArray = (ArrayList)read(filename);
		
		for(int i = 0; i < stringArray.size(); i++) {
			o[i] = new Room();
			String st = (String)stringArray.get[i];
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			((Room)o[i]).guest = star.nextToken().trim();
			((Room)o[i]).roomType = star.nextToken().trim();
			((Room)o[i]).bedType = star.nextToken().trim();
			//etc
		}
	}
	
	public void saveClass(String filename, Object[] o) throws IOException{
		List alw = new ArrayList();
		
		for(int i=0; i<o.length; i++) {
			if(o[i]!=null) {
				StringBuilder st = new StringBuilder();
				st.append(((Room)o[i]).guest.trim());
				st.append(SEPARATOR);
				st.append(((Room)o[i]).roomType.trim());
				st.append(SEPARATOR);
				st.append(((Room)o[i]).bedType.trim());
				st.append(SEPARATOR);
				st.append(((Room)o[i]).status.trim());
				alw.add(st.toString());
			}
		}
		write(filename, alw);
	}
	
	public void startup() {
		
	}

}

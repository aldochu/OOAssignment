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
	
	public void readClass(String filename, ArrayList RoomArray) throws IOException{
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		ArrayList stringArray = (ArrayList)read(filename);
		
		for(int i = 0; i < stringArray.size(); i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			
			Room temp = new Room();

			temp.roomId = star.nextToken().trim();
			temp.levelNo = Integer.parseInt(star.nextToken());
			temp.roomNo = Integer.parseInt(star.nextToken());
			temp.status = star.nextToken().trim();	
			temp.guestIc = star.nextToken().trim();	
			if(!temp.guestIc.equals(null)) {
				temp.roomType = star.nextToken().trim();
				temp.bedType = star.nextToken().trim();	
				temp.breakfast = Boolean.parseBoolean(star.nextToken().trim());
				temp.wifi = Boolean.parseBoolean(star.nextToken().trim());
				temp.cityView = Boolean.parseBoolean(star.nextToken().trim());
				temp.smoking = Boolean.parseBoolean(star.nextToken().trim());
				temp.rate = Double.parseDouble(star.nextToken().trim());
			}
			else {
				//do nothing
			}
			RoomArray.add(temp);
		}
	}
	
	public void saveClass(String filename, ArrayList RoomArray) throws IOException{
		List alw = new ArrayList();
		Room temp = new Room();
		
		for(int i=0; i<RoomArray.size(); i++) {
				StringBuilder st = new StringBuilder();
				temp = (Room) RoomArray.get(i);
				st.append(temp.roomId.trim());
				st.append(SEPARATOR);
				st.append(temp.levelNo);
				st.append(SEPARATOR);
				st.append(temp.roomNo);
				st.append(SEPARATOR);
				st.append(temp.status.trim());
				st.append(SEPARATOR);
				st.append(temp.guestIc.trim());
				st.append(SEPARATOR);
				st.append(temp.roomType.trim());
				st.append(SEPARATOR);
				st.append(temp.bedType.trim());
				st.append(SEPARATOR);
				st.append(temp.breakfast);
				st.append(SEPARATOR);
				st.append(temp.wifi);
				st.append(SEPARATOR);
				st.append(temp.cityView);
				st.append(SEPARATOR);
				st.append(temp.smoking);
				st.append(SEPARATOR);
				st.append(temp.rate);
				st.append(SEPARATOR);
				
				alw.add(st.toString());
		}
		write(filename, alw);
	}
	
	public void startup() {
		
	}

}

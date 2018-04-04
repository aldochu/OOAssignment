	
	package HRPS;

	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Locale;
	import java.util.StringTokenizer;


	public class ChildData extends StoreData {

		public void readClass(String filename, ArrayList ChildArray) throws IOException {
			// TODO Auto-generated method stub
	
			// read String from text file
					ArrayList stringArray = (ArrayList)read(filename); 
					
					


			        for (int i = 0 ; i < stringArray.size() ; i++) {
			        	
			        	
			        	
							String st = (String)stringArray.get(i);
							// get individual 'fields' of the string separated by SEPARATOR
							StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
							
							Child temp = new Child();

							temp.myname = star.nextToken().trim();	
							temp.race = star.nextToken().trim();	
							

							ChildArray.add(temp);
			        }	
			
		}

		@Override
		public void saveClass(String filename, ArrayList ChildArray) throws IOException {
			// TODO Auto-generated method stub
			List alw = new ArrayList() ;// to store Professors data
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); //for date variable
			Child temp = new Child();

	        for (int i = 0 ; i < ChildArray.size(); i++) {
					StringBuilder st =  new StringBuilder() ;
					temp = (Child) ChildArray.get(i);
					st.append(temp.myname.trim());
					st.append(SEPARATOR);
					st.append(temp.race.trim());
					alw.add(st.toString()) ;
	        }
				write(filename,alw);
			
		}

		@Override
		public void startup() {
			// TODO Auto-generated method stub
			
		}


	}

	
	
	


	package HRPS;

	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Locale;
	import java.util.StringTokenizer;


	public class ParentData extends StoreData {

		public void readClass2(String filename, ArrayList ParentArray, ArrayList ChildArray) throws IOException {
			// TODO Auto-generated method stub
	
			// read String from text file
			
			String ChildSeparator = "#";
			
					ArrayList stringArray = (ArrayList)read(filename); 


			        for (int i = 0 ; i < stringArray.size() ; i++) {
			        	
			        	
			        	
							String st = (String)stringArray.get(i);
							// get individual 'fields' of the string separated by SEPARATOR
							StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
							
							Parent temp = new Parent();

							temp.name = star.nextToken().trim();	
							temp.gender = star.nextToken().trim();
							
							String mychilds = star.nextToken().trim();
							
							StringTokenizer star2 = new StringTokenizer(mychilds, ChildSeparator);
							
							while(star2.hasMoreTokens())
							temp.mychild.add((Child) ChildArray.get(Integer.parseInt(star2.nextToken().trim())));

							ParentArray.add(temp);
			        }	
			
		}

		public void saveClass2(String filename, ArrayList ParentArray, ArrayList ChildArray) throws IOException {
			// TODO Auto-generated method stub
			List alw = new ArrayList() ;// to store Professors data
			Parent temp = new Parent();

	        for (int i = 0 ; i < ParentArray.size(); i++) {
					StringBuilder st =  new StringBuilder() ;
					temp = (Parent) ParentArray.get(i);
					st.append(temp.name.trim());
					st.append(SEPARATOR);
					st.append(temp.gender.trim());
					st.append(SEPARATOR);
					
					String ParentChild;
					String childchild;
					for(int k=0;k< temp.mychild.size();k++)
					{
						ParentChild = temp.mychild.get(k).myname;
						for(int g=0;g<ChildArray.size();g++)
						{
							childchild = ((Child)ChildArray.get(g)).myname;
					
							if(ParentChild.equals(childchild))
							{
								System.out.println(ParentChild + " and " + childchild);
								st.append(g);
								st.append("#");
							}
						}
					}
					alw.add(st.toString()) ;
	        }
	        
				write(filename,alw);
			
		}

		@Override
		public void startup() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void readClass(String filename, ArrayList o) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveClass(String filename, ArrayList o) throws IOException {
			// TODO Auto-generated method stub
			
		}


	}


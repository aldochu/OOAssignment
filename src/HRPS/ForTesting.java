package HRPS;

public class ForTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChildApp ap = new ChildApp();
		
		ParentApp pa = new ParentApp();
		
		pa.LoadParent(ap.getchild());
		pa.createparent();
		pa.SaveParent(ap.getchild());
	}

}

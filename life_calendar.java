import java.util.Calendar;

public class life_calendar{

	public static void main(String[] args){
		Calendar life = Calendar.getInstance();

		System.out.println("Current Year is: "+life.get(Calendar.YEAR) );
		System.out.println("Current Month is: "+life.get(Calendar.MONTH) +1);
		System.out.println("Current Date is: "+life.get(Calendar.DATE) );
	}
}



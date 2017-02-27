
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.regex.Matcher;	//decimal
import java.util.regex.Pattern;  //decimal

import org.apache.commons.validator.routines.EmailValidator;

public class NameValidationFinal {
	Scanner sc = new Scanner(System.in);
	public static void main(String[] args){

		new NameValidationFinal();

	}//Main
	
	NameValidationFinal(){ //Constructor
		
		
		System.out.println("Enter your text: isWhiteSpace isAlpha isstrNameValid isCheckEmail ");
		String username = sc.nextLine();
		
		
		boolean whitespace = isWhiteSpace(username);
		System.out.println("whiteSpace (!space, enter, tab)= "+whitespace+"\n ----------------");//Output
		
		boolean testing = isAlpha(username);
		System.out.println("isAlpha ( a-z, 0-9, !char)= "+testing+"\n ----------------");
		
		boolean strName = isstrNameValid(username);
		System.out.println("isstrNameValid (O'hara) = "+strName+"\n ----------------");
		
		boolean email = isCheckEmail(username);
		System.out.println("CheckEmail (@yahoo.com , @yahoo.com.ph) = "+email+"\n ----------------\n\n");
		
		
		
		
		
		System.out.println("Enter your no: isNumber priceFormat isCheckPhone isCheckAlpha ");
		String no = sc.nextLine();
		boolean number = isNumber(no);
		System.out.println("numbers (1234) = "+number+"\n ----------------");
		
		boolean format = priceFormat(no);
		System.out.println("price format (100.00) = "+format+"\n ----------------");
		
		boolean phoneno = isCheckPhone(no);
		System.out.println("phone no (+6390 , 09 , 123-12-12 , 123-1212 , 1231212 = "+phoneno+"\n ----------------");
		
		
		boolean alphanumeric = isCheckAlpha(no);

		System.out.println("alphanumeric (letters & no) = "+alphanumeric+"\n ----------------");
		
		
		
		
		
				
		
	} //Constructor
	
	//Mary Start
	public boolean isWhiteSpace(String name) { //enter and space

		boolean containsWhitespace = true;
		if (name.isEmpty()) {
			containsWhitespace = false;
		}else if(name != null) {
			for (int i = 0; i < name.length() && containsWhitespace; i++) {
			    if (Character.isWhitespace(name.charAt(i))) {
			        containsWhitespace = false;
			    }
			}
		}
		return containsWhitespace;
	}
	
	
	public boolean isNumber(String no) {   //no only
		String regex = "\\d+";
		try {
		if(no.matches(regex)) {  
		    System.out.println("Valid number");
		    return true;
		}else {
			System.out.println("Invalid number");
			return false;
		}
		}catch(Exception e){
			return false;
		}
	}
	//Mary End
	
	
	
	public boolean isAlpha(String name) {  //Example O'hara
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public boolean isstrNameValid(String strname){ //Names Ex. O'hara , jan-jan
		if (strname.matches("[a-zA-Z '-]+")){
			return true;
						
		}else {
			return false;
		}
	}//Method
	
	
	public boolean priceFormat(String no){    //mali pa ngoutput di nalalaman kapag 100.10.10 nagiging true 
												//pero nacocorrect nya pag mali input mo
		String pattern = "[0-9]+([,.][0-9]{1,2})?";
	      
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(no); 
	      if (m.find()) {
	         System.out.println("Found value: " + m.group(0));
	         return true;
	      }else {
	         System.out.println("INAPPROPRIATE DIGITS!");
	         return false;
	      }
		
	}
	public boolean isCheckAlpha(String name){ //Method for checking alphanumeric
		if(name.matches("^[a-zA-Z0-9]*$")){
			return true;			
		}else {
			return false;
		}
	}//Method

	public boolean isCheckEmail(String mail){ //Method for checking email
		if(EmailValidator.getInstance().isValid(mail)){
			return true;			
		}else {
			return false;
		}
	}//Method
	


	public boolean isCheckPhone(String phone){ //Method for checking phone number
		boolean found = false;
		if(phone.matches("^[09]{1,2}[0-9]{1,9}$")){
			      
				found = true;
				//System.out.println("normal");
		}
		
		else if(phone.matches("^[+][63]{1,2}[0-9]{1,10}")){
				found = true;
				//System.out.println("special");
		}
		else if(phone.matches("^[0-9]{1,3}[-][0-9]{1,4}$") || phone.matches("^[0-9]{1,3}[-][0-9]{1,4}[-][0-9]{1,4}$") 
				|| phone.matches("^[0-9]{1,7}$")) {
			found = true;
		}
		return found;
		
	 }//Method
	

	public boolean isstrNameValid1(String strname){ //names O'hara
		if (strname.matches("[a-zA-Z '-]+")){
		        strname = strname.substring(0,1).toUpperCase()+strname.substring(1).toLowerCase();
			System.out.println(strname);
			return true;
			
		
		}else {
			
			return false;
		}
	 }//Method
}

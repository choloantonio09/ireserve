/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capstone.Option;

/**
 *
 * @author trey
 */



    public class StringUtil {
    public static boolean isAlphabetic(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }//end isAlphabetic
    public static boolean isAlphabeticWithSpace(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }//end isAlphabeticWithSpace
    public static boolean isAlphaNumericWithSpace(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isDigit(c) && !Character.isSpaceChar(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }//end isAlphabeticWithSpace
    public static boolean isAlphabeticWithSpaceApostropheOrHyphen(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isSpaceChar(c) &&
                    c != '\'' && c != '-' && c!='\'') {
                b = false;
                break;
            }
        }
        
        return b;
    }//end isAlphabeticWithSpaceApostropheOrHyphen
    public static boolean isAlphanumericWithSpaceApostropheOrHyphen(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isSpaceChar(c) &&
                    c != '\'' && c != '-' && !Character.isDigit(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }
    public static boolean isAlphanumericWithPeriodSpaceApostropheOrHyphen(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isSpaceChar(c) &&
                    c != '\'' && c != '-' && c !='.' && !Character.isDigit(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }
    public static boolean isApostropheHyphenAndSpaceFollowedByLetter(String s) {
        boolean b = true;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if( (s.charAt(i) == '\'' || s.charAt(i) == '-' || s.charAt(i) == ' ') && 
                        !Character.isLetter(s.charAt(i+1))) {
                    
                    b = false;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    }//end isApostropheOrHyphenFollowedByApostropheOrHyphen
     public static boolean isApostropheHyphenAndSpacePeirodFollowedByLetter(String s) {
        boolean b = true;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if( (s.charAt(i) == '\'' || s.charAt(i) == '-' || s.charAt(i) == ' ' || s.charAt(i) == '.')&& 
                        !Character.isLetter(s.charAt(i+1))) {
                    b = false;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    }
    public static boolean doesStartsAndEndsWithLetter(String s) {
        boolean b = false;
        
        try {
            if(Character.isLetter(s.charAt(0)) &&
                Character.isLetter(s.charAt(s.length()-1)))
                b = true;
        } catch(StringIndexOutOfBoundsException e) {}
        
        return b;
    }
    public static boolean doesStartsAndEndsWithNumber(String s) {
        boolean b = false;
        
        try {
            if(Character.isDigit(s.charAt(0)) &&
                Character.isDigit(s.charAt(s.length()-1)))
                b = true;
        } catch(StringIndexOutOfBoundsException e) {}
        
        return b;
    }
    public static boolean isNumberPassword(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isDigit(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }
    public static boolean isAlphaNumeric(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isDigit(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }//end isAlphaNumeric
    public static boolean doesContain2ConsecutiveSpace(String s) {
        boolean b = false;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if(s.charAt(i) == ' ' && s.charAt(i + 1) == ' ') {
                    b = true;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    }//end doesContain2ConsecutiveSpace
    public static boolean doesContainSpace(String s) {
        boolean b = false;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if(s.charAt(i) == ' ') {
                    b = true;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    }
        public static boolean isNumber(String s) {
        //boolean b = true;
        
        try {
            Double.parseDouble( s );
            
            return true;
        } catch(Exception e) {}
        try{
        Integer.parseInt(s);
        }catch(Exception e){
        
        }
        return false;
    }
         public static boolean isApostropheHyphenAndSpaceFollowedByLetterORNumber(String s) {
        boolean b = true;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if( (s.charAt(i) == '\'' || s.charAt(i) == '-' || s.charAt(i) == ' ') && 
                     (!Character.isLetter(s.charAt(i+1))) && !Character.isDigit(s.charAt(i+1))) {
                    
                    b = false;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    } 
              public static boolean isApostropheHyphenAndSpacePeriodFollowedByLetterORNumber(String s) {
        boolean b = true;
        
        try {
            for(int i = 0 ; i<s.length() ; i++) {
                if( (s.charAt(i) == '\'' || s.charAt(i) == '-' || s.charAt(i) == ' ' || s.charAt(i) == '.') && 
                     (!Character.isLetter(s.charAt(i+1))) && !Character.isDigit(s.charAt(i+1))) {
                    
                    b = false;
                    break;
                }
            }
        } catch(StringIndexOutOfBoundsException e){}
        
        return b;
    }
     public static boolean doesStartsAndEndsWithNumberandLetter(String s) {
        boolean b = false;
        
        try {
            if((Character.isDigit(s.charAt(0)) || Character.isLetter(s.charAt(0))) &&
               (Character.isDigit(s.charAt(s.length()-1)) || Character.isLetter(s.charAt(s.length()-1))))
                b = true;
        } catch(StringIndexOutOfBoundsException e) {}
        
        return b;
    }
     public static boolean isAlphanumericWithSpaceApostropheOrHyphenOrPeriod(String s) {
        boolean b = true;
        
        if(s.trim().length() < 1)
            b = false;
        
        for(char c : s.toCharArray()) {
            if(!Character.isLetter(c) && !Character.isSpaceChar(c) &&
                    c != '\'' && c != '-' && c != '.' && !Character.isDigit(c)) {
                b = false;
                break;
            }
        }
        
        return b;
    }

     public static boolean isDecimal(String s){
    	 int l = 0;
    	 int limit = 0;
    	 boolean b = true;
    	 
    	 l = s.length();
    	 System.out.println(l);
    	 for (int i = 0; i < l; i++) {
    		 System.out.println(s.charAt(i));
			if(!(Character.isDigit(s.charAt(i))) && s.charAt(i) == '.'){
				limit ++;
				b = true;
				if(limit >= 2)
					b = false;;
					break;
			}else{
				b = false;
			}
    	 }
    	 return b;
     }
}//end StringUtil



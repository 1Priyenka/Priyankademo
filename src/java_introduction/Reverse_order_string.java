package java_introduction;

public class Reverse_order_string {
public static void main(String[] args) {
	
	String s = "priyanka chirag gandhi";

	String[] splittedString = s.split("chirag");

	System.out.println(splittedString[0]);

	System.out.println(splittedString[1]);

	System.out.println(splittedString[1].trim());//remove black space before Academy

	
	// TO PRINT REVERSE ORDER STRING
	
	for(int i =s.length()-1;i>=0;i--)

	{
       System.out.println(s.charAt(i));
    }

	}
}

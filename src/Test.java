import java.lang.reflect.*;
//import java.reflect.*;

public class Test {

	
	public static void main(String[] args)
	{
		Object object = null;
		Class classObject = null;
	
		try {
			
			//Load the class 
			classObject = Class.forName(args[0]);
			
			//Create an instance of the class
			object = classObject.newInstance();
		} catch(Exception e)
		{
			System.out.println(e);
			return;
		}
		
		
		
		try {
			Method m = classObject.getMethod(args[1], null);
			
			m.invoke(object,null);
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
}

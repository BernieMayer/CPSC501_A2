import java.lang.reflect.*;
//import java.reflect.*;

import utilities.NumberUltility;

public class Test {

	
	public static void printMethodsInfo(Class classObject)
	{
		Method[] methods = classObject.getDeclaredMethods();
		for (Method method:methods)
		{
			System.out.println("Method name: " + method.getName());
			
			
			System.out.println("Return type of method: " + method.getReturnType());
			
			//Get and print access modifier of each method
			int modifiers = method.getModifiers();
		    System.out.println("method modifier: " + Modifier.toString(modifiers));
		    
		    Class[] parameterList = method.getParameterTypes();
		    System.out.println("method parameter types");
		    for (Class paramater:parameterList)
		    {
		    	System.out.println(paramater.getName() + " ");
		    }
		    System.out.println();
		    
		    //Get and print exception throws by methods
		    Class[] exceptionList = method.getExceptionTypes();
		    System.out.println("Exception thrown by method: ");
		    for (Class exception:exceptionList)
		    {
		    	System.out.println(exception.getName() + " ");
		    }
		    
		    System.out.println();
		    
		    
		    
		    
		    System.out.println("***************");
		    System.out.println("Method toString()");
		    System.out.println(method);
		    System.out.println("***************");
		}
		
		
	}
	
	public static void printConstructors(Class classObject)
	{
		Constructor[] constructors = classObject.getConstructors();
		
		for (Constructor constructor : constructors)
		{
			//print the name of each constructor
			System.out.println("Constructor name: " + constructor.getName());
			
			//Get and print access modifier of each constructor
			int modifiers = constructor.getModifiers();
			System.out.println("Constructor modidifier: " + Modifier.toString(modifiers));
			
			
			//Get and print parameter types
			Class[] parameterList = constructor.getParameterTypes();
			for (Class parameter: parameterList)
			{
				System.out.println(parameter.getName() + " ");
			}
			
			System.out.println();
			
			//Get and print exception throws by consctructors
			
			Class[] exceptionList = constructor.getExceptionTypes();
			System.out.println("Exception thrown by constructors:  ");
			for (Class exception: exceptionList)
			{
				System.out.println(exception.getName() + " ");
			}
			
			System.out.println();
			
			System.out.println("**************************");
			
			
			
			
		}
	}
	
	public static void main(String[] args)
	{
		Object object = null;
		Class classObject = null;
	
		try {
			
			//Load the class 
			classObject = Class.forName(args[0]);
			
			//Create an instance of the class
			object = classObject.newInstance();
			
			String className = classObject.getName();
			System.out.println("The full class name is: " + className);
			
			
			// Get simple class name
			String simpleClassName = classObject.getSimpleName();
			System.out.println("The simple class name is: " + simpleClassName);
			
			int modifiers = classObject.getModifiers();
			System.out.println("The class modifier is " + Modifier.toString(modifiers));
			
			printConstructors(classObject);
			printMethodsInfo(classObject);
			
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

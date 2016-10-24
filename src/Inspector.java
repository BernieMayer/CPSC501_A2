
import java.lang.reflect.*;

public class Inspector {
	
	public Inspector()
	{
		
	}

	public void inspect(Object obj, boolean recursive)
	{
		if (obj.getClass().isArray())
		{
			handleArray(obj, recursive);
		} else 
		{
			handleInspection(obj, recursive);
		}
		
	}
	
	
	public void handleInspection(Object obj, boolean recursive)
	{
		
	}
	
	public void handleArray(Object array, boolean recursive)
	{
		int length = Array.getLength(array);
		for (int i = 0; i < length; i++)
		{
			Object obj = Array.get(array, i);
			handleInspection(obj, recursive);
		}
		
	}
	/*
	public String queryArrayInfo(Object array)
	{
		String arrayInfoAsString = "";
		
		arrayInfoAsString = arrayInfoAsString + "Name: " + array.
	}*/
	
	
	
	
	/**
	 * 
	 * @param o
	 */
		
	public void printFieldNames(Object o) {
		Class c = o.getClass();
		Field[] publicFields = c.getFields();
		
		for (int i = 0; i < publicFields.length; i++){
			String fieldName = publicFields[i].getName();
			Class typeClass = publicFields[i].getType();
			String fieldType = typeClass.getName();
		}
		
  }
	 
	

	  public String getDeclaringClassName(Class classObject)
	  {
	          String className = classObject.getName();
	          return className;
	  }
	  
	  public String getSuperClassName(Class classObject)
	  {
	          String superName = classObject.getSuperclass().getName();
	          return superName;
	  }
	  
	  public String queryInterfaces(Class classObject)
	  {
	          Class[] interfaces = classObject.getInterfaces();
	          String interfaceAsString = "";
	          
	          for (Class a_interface:interfaces)
	          {
	                  interfaceAsString = interfaceAsString + a_interface.getName();
	                  interfaceAsString = interfaceAsString + "\n";

	          }
				
	          return interfaceAsString;
	  }
	  
	  public String queryFields(Class classObject)
	  {
		  Field[] fields = classObject.getDeclaredFields();
		  String fieldsAsString = "";
		  
		  for (Field aField:fields)
		  {
			  aField.setAccessible(true); //just in case
			  fieldsAsString = fieldsAsString + "Name: " + aField.getName() + "\n";
			  fieldsAsString = fieldsAsString + "Type: " + aField.getType().getSimpleName() + "\n";
			  int modifiers = aField.getModifiers();
			  fieldsAsString = fieldsAsString + "Modifiers: " + Modifier.toString(modifiers) + "\n";
			  fieldsAsString = fieldsAsString + "****************" + "\n";
			  
		  }
		  return fieldsAsString;
	  }
	  
	  
	  public String queryFieldValue(Field aField)
	  {
		  aField.setAccessible(true);
		  String fieldValueAsString = "";
		  
		  if (aField.getClass().isPrimitive())
		  {
			  Class<?> aPrimitiveClass = aField.getType();
			  try {
				fieldValueAsString =  aField.get(aPrimitiveClass).toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  } else 
			{
				fieldValueAsString = aField.getClass().getSimpleName() + " : " + aField.hashCode();
			}
		  
		  
		  return fieldValueAsString;
	  }
	  
		public void printMethodsInfo(Class classObject)
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
		
		public void printConstructors(Class classObject)
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

	  
	  

	
}

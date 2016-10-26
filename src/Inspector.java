
import java.lang.reflect.*;
import java.util.Hashtable;
import java.util.ArrayList;

public class Inspector {
	
	private boolean recursive;
	private Object main_Obj;
	private Hashtable table;
	
	public Inspector()
	{
		table = new Hashtable();
		//objectsToProcess = new ArrayList<Object>();
	}

	public void inspect(Object obj, boolean recursive)
	{
		
		this.recursive = recursive;
		
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
		if (obj == null)
		{
			System.out.println("The object is null");
			return;
		}
		if (table.contains(obj) && recursive == false)
		{
			return;
		} else if (table.contains(obj) && recursive == true)
		{
			System.out.println("The object " + obj.getClass().getSimpleName() + " : " + obj.hashCode() + " has already been inspected");
			return;
		}
		
		
		
		String name = getDeclaringClassName(obj.getClass());
		System.out.println("The class name is: " + name);
	
		
		String superClassName = getSuperClassName(obj.getClass());
		System.out.println("The Super Class is :" + superClassName);
		
		String interfaceNames = queryInterfaces(obj.getClass());
		System.out.println("The interfaces this class implements are: ");
		System.out.println(interfaceNames);
		
		
		String fieldsInfo = queryFields(obj);
		System.out.println("FIELDS:");
		System.out.println(fieldsInfo);
		
		printMethodsInfo(obj.getClass());
		
		printConstructors(obj.getClass());
		
		table.put(obj, obj);
		

	}
	
	
	
	public void handleArray(Object array, boolean recursive)
	{
		String arrayInfo = queryArrayInfo(array);
		System.out.println(arrayInfo);
		int length = Array.getLength(array);
		for (int i = 0; i < length; i++)
		{
			Object obj = Array.get(array, i);
			
			System.out.println("Now showing the object at index : " + i);
			handleInspection(obj, recursive);
		}
		
	}

	public String queryArrayInfo(Object array)
	{
		String arrayInfoAsString = "";
		
		arrayInfoAsString += "This object is an array \n";
		
		//arrayInfoAsString = arrayInfoAsString + "Name: " + "\n";
		arrayInfoAsString = arrayInfoAsString + "Length: " + Array.getLength(array) + "\n";
		arrayInfoAsString = arrayInfoAsString + "Componenet type: " +array.getClass().getSimpleName() + "\n";
	    
	    return arrayInfoAsString;
	}
	
	
	
	
	
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
	  
	  
	  public String queryFields(Object obj)
	  {
		  Class classObject = obj.getClass();
		  Field[] fields = classObject.getDeclaredFields();
		  String fieldsAsString = "";
		  
		  for (Field aField:fields)
		  {
			  aField.setAccessible(true);
			  if ( aField.getType().isArray())
			  {
				  Object arrayObject = null;
				  int length = 0;
				try {
					arrayObject = aField.get(obj);
					length = Array.getLength(arrayObject);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			    fieldsAsString += arrayObject.getClass().getSimpleName() + " Is an array of length " + length + "\n";
			    //show the array modifiers
				for (int i = 0; i < length; i++)
				{
					fieldsAsString += "Now showing the item at index: " + i + "\n";
					Object arrayItem = Array.get(arrayObject, i);
					fieldsAsString += "Value: " + queryObjectValue(arrayItem);
					fieldsAsString += "*****************" + "\n";
				}
				  
			  }
			  fieldsAsString = fieldsAsString + "Name: " + aField.getName() + "\n";
			  fieldsAsString = fieldsAsString + "Type: " + aField.getType().getSimpleName() + "\n";
			  int modifiers = aField.getModifiers();
			  fieldsAsString = fieldsAsString + "Modifiers: " + Modifier.toString(modifiers) + "\n";
			  fieldsAsString = fieldsAsString + "Value: " + queryFieldValue(aField, obj);
			  fieldsAsString = fieldsAsString + "****************" + "\n";
			  
		  }
		  return fieldsAsString;
	  }
	  
	  public String queryObjectValue(Object obj)
	  {
		  if (obj == null)
		  {
			  return "The object is null \n";
		  }
		  String objValueAsString = "";
		  if (obj.getClass().isPrimitive())
		  {
			  objValueAsString += obj.toString() + "\n";
		  } else
		  {
			  objValueAsString += obj.getClass().getSimpleName() + " : " + obj.hashCode() + "\n";
		  }
		  
		  return objValueAsString;
	  }
	  
	  public String queryFieldValue(Field aField, Object obj)
	  {
		  aField.setAccessible(true);
		  String fieldValueAsString = "";
		  
		  if (aField.getType().isPrimitive())
		  {
			  Class<?> aPrimitiveClass = aField.getType();
			  try {
				fieldValueAsString =  aField.get(obj).toString() + "\n";
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  } else 
			{
			   
				fieldValueAsString = obj.getClass().getSimpleName() + " : " + obj.hashCode() + "\n";
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
			    
			    
			    
			    /*
			    System.out.println("***************");
			    System.out.println("Method toString()");
			    System.out.println(method);
			    System.out.println("***************");
			    */
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

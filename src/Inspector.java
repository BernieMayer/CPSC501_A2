
import java.lang.reflect.*;
import java.util.Hashtable;
import java.util.ArrayList;

public class Inspector extends QueryObjects {
	
	private boolean recursive;
	private Object main_Obj;
	private Hashtable table;
	private ArrayList<Object> objectsToProcess;
	
	public Inspector()
	{
		table = new Hashtable();
		objectsToProcess = new ArrayList<Object>();
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
		
		if (recursive)
		{
			processObjects();
		}
		
	}
	
	private void processObjects()
	{
		QueryObjects i = new Inspector();
		Object[] array = this.objectsToProcess.toArray();
		this.objectsToProcess.clear();
		for (Object obj : array)
		{
			inspect(obj, false);
		}
	}
	
	
	public void handleInspection(Object obj, boolean recursive)
	{
		if (obj == null)
		{
			System.out.println("The object is null");
			return;
		}
		if (table.contains(obj.hashCode()) && recursive == false)
		{
			return;
		} else if (table.contains(obj.hashCode()) && recursive == true)
		{
			System.out.println("The object " + obj.getClass().getSimpleName() + " : " + obj.hashCode() + " has already been inspected");
			return;
		}
		
		table.put(obj.hashCode(), obj);
		
		printObjectInspection(obj.getClass(), obj);
		
		Class objClass = obj.getClass();
		
		while (objClass.getSuperclass() != null)
		{
			Class superClass = obj.getClass().getSuperclass();			
			System.out.println("Now display " + obj.getClass().getSimpleName() + " superClass");
			printObjectInspection(superClass, obj);
			
			objClass = objClass.getSuperclass();
		}
	}





	/**
	 * @param obj the object to inspect
	 */
	private void printObjectInspection(Class aClass, Object obj) {
		String name = getDeclaringClassName(aClass);
		System.out.println("The class name is: " + name);
	
		
		String superClassName = getSuperClassName(aClass);
		System.out.println("The Super Class is :" + superClassName);
		
		String interfaceNames = queryInterfaces(aClass);
		System.out.println("The interfaces this class implements are: ");
		System.out.println(interfaceNames);
		
		
		String fieldsInfo = queryFields(obj, aClass);
		System.out.println("FIELDS:");
		System.out.println(fieldsInfo);
		
		System.out.println("CONSTRUCTORS");
		printConstructors(aClass);
		
		
		System.out.println("METHODS:");
		printMethodsInfo(aClass);
		
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
		      if (classObject.getSuperclass() != null)
		      {
	          String superName = classObject.getSuperclass().getName();
	          return superName;
		      } else {
		    	  return "<none>";
		      }
	  }
	  
	  public String queryFields(Object obj, Class classObject)
	  {
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
					if (arrayObject != null)
						length = Array.getLength(arrayObject);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (arrayObject == null)
				{
					fieldsAsString += "This field is null" + "\n";
				} else {
			    fieldsAsString += arrayObject.getClass().getSimpleName() + " Is an array of length " + length + "\n";
				}
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
			  	if (recursive)
			  	{
			  		Object objectToInspect = null;
			  		try {
			  			objectToInspect = aField.get(obj);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  		if (objectToInspect == null)
			  		{
			  			fieldValueAsString += "The field is null " + "\n";
			  			return fieldValueAsString;
			  		} else {
			  			if ( ! this.objectsToProcess.contains(objectToInspect))
			  			{
			  				fieldValueAsString += "This object will be inspected later " 
			  												+ objectToInspect.getClass().getSimpleName() + " : "
			  												+ objectToInspect.hashCode() + "\n";
			  				this.objectsToProcess.add(objectToInspect);
			  			}
			  		}
			  		
			  	} else {
			  	
			  
			    Object fieldObject = null;
				try {
					fieldObject = aField.get(obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (fieldObject == null)
				{
					fieldValueAsString += "The field is null" + "\n";
				} else {
				fieldValueAsString = fieldObject.getClass().getSimpleName() + " : " + fieldObject.hashCode() + "\n";
				}
				}
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

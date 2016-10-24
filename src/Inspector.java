
import java.lang.reflect.*;

public class Inspector {
	
	public Inspector()
	{
		
	}
	
	public void inspect(Object obj, boolean recursive)
	{
		
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
  
  
	
}

import java.lang.reflect.Array;

public class QueryObjects {

	public String queryArrayInfo(Object array) {
		String arrayInfoAsString = "";
		
		arrayInfoAsString += "This object is an array \n";
		
		//arrayInfoAsString = arrayInfoAsString + "Name: " + "\n";
		arrayInfoAsString = arrayInfoAsString + "Length: " + Array.getLength(array) + "\n";
		arrayInfoAsString = arrayInfoAsString + "Componenet type: " +array.getClass().getSimpleName() + "\n";
	    
	    return arrayInfoAsString;
	}

	public String queryInterfaces(Class classObject) {
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


import java.lang.reflect.*;

public class Inspector {

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
	
}

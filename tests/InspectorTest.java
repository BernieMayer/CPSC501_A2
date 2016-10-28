import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Test;

public class InspectorTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testQueryArrayInfo()
	{
		int[] array = new int[] {1, 2, 3, 4};
		
		Inspector i = new Inspector();
		
	    String output = i.queryArrayInfo(array);
	    
	    assertTrue(output.contains("Length: 4"));
	    assertTrue(output.contains("int"));
	}

	
	@Test
	public void testQueryInterfacesUsingFileClass()
	{
		
		Inspector i = new Inspector();
		
		File f = new File("Inspector.java");
		
		String s = i.queryInterfaces(f.getClass());
		
		assertTrue((s.contains("Serializable") && s.contains("Comparable")));
		
	}
	
	@Test
	public void testQueryInterfacesObjectClass()
	{
		Inspector i = new Inspector();
		Object o = new Object();
		
		assertTrue(i.queryInterfaces(o.getClass()).equals(""));
	}
	
	@Test
	public void testQueryFieldsWithTwoModifiers()
	{
		Inspector i = new Inspector();
		MockClassA a = new MockClassA();
		
		String output = i.queryFields(a, a.getClass());
		
		
		assertTrue(output.contains("volatile"));
		assertTrue(output.contains("public"));
		assertTrue(output.contains("String"));
		
		
	}
	
	@Test 
	public void testQueryFieldWithClass()
	{
		MockClassB b = new MockClassB();
		MockClassA a = new MockClassA();
		
		b.aClass = a;
		
		Field f = null;
		try 
		{
			f = b.getClass().getDeclaredField("aClass");
			
		}  catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Inspector i = new Inspector();
		String output = i.queryFieldValue(f, b);
		
		assertTrue(output.contains("MockClassA"));
		
	}
	
	
	@Test
	public void testInfiniteRecursion()
	{
		MockClassC c = new MockClassC();
		Inspector i = new Inspector();
		
		i.inspect(c, true);
	}
	
	@Test
	public void testQueryFieldValue()
	{
		MockClassB b = new MockClassB();
		b.a = 1;
		
		
		Field f = null;
		try {
			f = b.getClass().getDeclaredField("a");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Inspector i = new Inspector();
		
		assertTrue(i.queryFieldValue(f, b).contains("1"));
	}
	
}

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
		
		QueryObjects i = new Inspector();
		
	    String output = i.queryArrayInfo(array);
	    
	    assertTrue(output.contains("Length: 4"));
	    assertTrue(output.contains("int"));
	}

	
	@Test
	public void testQueryInterfacesUsingFileClass()
	{
		
		QueryObjects i = new Inspector();
		
		File f = new File("Inspector.java");
		
		String s = i.queryInterfaces(f.getClass());
		
		assertTrue((s.contains("Serializable") && s.contains("Comparable")));
		
	}
	
	@Test
	public void testQueryInterfacesObjectClass()
	{
		QueryObjects i = new Inspector();
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
		
		System.out.println("Running test infiniteRecursion()");
		//This tests will pass if this test case terminates
		MockClassC c = new MockClassC();
		Inspector i = new Inspector();
		
		i.inspect(c, true);
		
		System.out.println("***************");
	}
	
	@Test
	public void testPrintingMethodWithArrayParam()
	{
		//To test this requires looking into the console
		
		
		System.out.println("Running test testPrintingMethodWithArrayParam()");
		
		Inspector i = new Inspector();
		MockClassC c = new MockClassC();
		
		System.out.println("This method contains an array of Strings as a paramater");
		
		i.printMethodsInfo(c.getClass());
		
		System.out.println("***************************************");
		
		
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

package utilities;

import java.io.FileNotFoundException;

public class NumberUltility {

	public String message;
	public boolean i_flag = false;
	public boolean b_flag = false;

	
	
	
	public NumberUltility() {
		super();
		this.message = "Basic";
	}




	public NumberUltility(String message, boolean i_flag, boolean b_flag) {
		super();
		this.message = message;
		this.i_flag = i_flag;
		this.b_flag = b_flag;
	}
	
	


	public NumberUltility(String message) {
		super();
		this.message = message;
	}




	public void Test() 
	{
		System.out.println("Teeeest");
	}
	
	private int calculateMax(int a, int b)
	{
		//Bad code
		return a;
	}
	
	public float calculateE()
	{
		return (float) 2.71;
	}
	
	
	public int readFromComplexFile() throws FileNotFoundException
	{
		return 0;
	}
	
	protected String aStringMethod()
	{
		return "abc";
	}
	
}

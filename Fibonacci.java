import java.io.Serializable;
import java.util.ArrayList;


//Fibonacci class implements Task interface and Serializable
public class Fibonacci implements Task,Serializable
{

	private int num;
	private String result;
	
	
	public Fibonacci() {}
	
	public Fibonacci(int num) {
		super();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	//implement the method from the interface
	//Display results of Computation - Fibonacci series up to n'th range
	@Override
	public String getResults() 
	{
		return result;
	}

	
	//implement the method from the interface
	//Compute Fibonacci series for the entered range
	@Override
	public void executeTask() 
	{
		  int number1 = 0;
	      int number2 = 1;
	      int sum;
	      result = number1 + " + ";
	        for(int i=1;i<num;++i){
	          
	          sum = number1 + number2;
	          result = result + number2 + " + ";
	          number1 = number2;
	          number2= sum;   

	        }
	        
	 }
	
}


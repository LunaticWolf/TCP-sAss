import java.io.Serializable;

//Factorial class implements Task interface and Serializable
public class Factorial implements Task,Serializable
{

	
	private int n;
	private String result;
	
	
	public Factorial() {}

	public Factorial(int n) {
		super();
		this.n = n;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}

	
	
	//implement the method from the interface
	//Display results of Computation - Factorial of n
	@Override
	public String getResults() {
		return "Factorial of " + n + " = " + result + "\n";
	}

	//implement the method from the interface
	//Compute Factorial for the entered range
	@Override
	public void executeTask() 
	{
		int fac = 1;
		for(int i=1;i<=n;i++) {
			fac = fac * i;
		}
		
		result = String.valueOf(fac);
	}
		
	
}

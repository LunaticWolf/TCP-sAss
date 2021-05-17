import java.io.Serializable;

//Gcd class implements Task interface and Serializable
public class GCD implements Task,Serializable
{

	private int n1;
	private int n2;
	private String result;
	
	public GCD() {}

	public GCD(int n1, int n2) {
		super();
		this.n1 = n1;
		this.n2 = n2;
	}


	public int getN1() {
		return n1;
	}

	
	public void setN1(int n1) {
		this.n1 = n1;
	}

	
	public int getN2() {
		return n2;
	}

	
	public void setN2(int n2) {
		this.n2 = n2;
	}

	
	public String getResult() {
		return result;
	}

	
	public void setResult(String result) {
		this.result = result;
	}

	
	
	//implement the method from the interface
	//Display results of Computation - GCD between n1 & n2
	@Override
	public String getResults() 
	{
		return "GCD of given number is:" + result + "\n";
	}

	
	//implement the method from the interface
	//Compute GCD for the entered numbers - n1 & n2
	@Override
	public void executeTask() 
	{
	
		int gcd = 1;
		for(int i=1;i<=n1 && i<=n2;++i)
			if(n1%i==0 && n2%i==0)
				gcd=i;
	
		this.result = String.valueOf(gcd);
		
	}
	
}

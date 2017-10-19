
public class Account {
	private double balance;
	
	public Account(){
		balance = 0.00;
	}
	
	public void deposit(double amount){
		balance += amount;
	}
	
	public void withdraw(double amount){
		balance-= amount;
	}
	public double getBalance(){
		return balance;
	}
}

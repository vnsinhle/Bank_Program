import java.math.BigDecimal;
import java.util.Scanner;


public class Main {
	
	Scanner userinput = new Scanner(System.in);	//keyboard input
	Account user = new Account();
	String choice;
	boolean running = true;						//boolean to put initial menu in loop when given an invalid option
	boolean decimal, valid;						//boolean to put deposit/withdraw menu in loop when given an invalid amount
	double amount;								//user-provided amount
	
	public void run(){
		while(running){
			System.out.print("Please enter in a command (Deposit, Withdraw, Balance, Exit): ");
			getChoice();
		}
	}
	
	public void getChoice(){ 					//runs option selected, if option is invalid, do nothing and return to while loop in run()
		String choice = userinput.nextLine();
		if(choice.equalsIgnoreCase("Deposit") || choice.equalsIgnoreCase("Withdraw") || choice.equalsIgnoreCase("Balance") || choice.equalsIgnoreCase("Exit")){
			if(choice.equalsIgnoreCase("Deposit")){runDeposit();}
			if(choice.equalsIgnoreCase("Withdraw")){runWithdraw();}
			if(choice.equalsIgnoreCase("Balance")){runBalance();}
			if(choice.equalsIgnoreCase("Exit")){runExit();}
		}
		else{
			System.out.println("Invalid option.\n");
		}
	}
	
	private void runDeposit() {
		valid = false;
		while(!valid){
			System.out.printf("Please enter an amount to deposit: ");
			try{
				amount = Double.parseDouble(userinput.nextLine());
				decimal = BigDecimal.valueOf(amount).scale() > 2;	//checks if input has more than 2 decimal places
				if(amount < 0 || decimal == true){
					System.out.println("Invalid amount.");				
				}
				else if(amount == 0){
					System.out.println("You did not make a deposit.\n");
					valid = true;
				}
				else{
					user.deposit(amount);
					System.out.println("You have made a successful deposit.\n");
					valid = true;
				}
			}
			catch(NumberFormatException e){							//throws exception error if input contains letters and symbols
				System.out.println("Invalid amount.");
			}
		}
	}

	private void runWithdraw() {
		valid = false;
		while(!valid){
			try{
				System.out.printf("Please enter an amount to withdraw: ");
				amount = Double.parseDouble(userinput.nextLine());
				decimal = BigDecimal.valueOf(amount).scale() > 2;
				if(amount < 0 || decimal == true){
					System.out.println("Invalid amount.");				
				}
				else if(amount > user.getBalance()){
					System.out.println("Insufficienct funds in account.");
				}
				else if(amount == 0){
					System.out.println("You did not make a withdraw.\n");
					valid = true;
				}
				else{
					user.withdraw(amount);
					System.out.println("You have made a successful withdrawal.\n");
					valid = true;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Invalid amount.");
			}
		}
		
		
	}

	private void runBalance() {
		System.out.printf("The current balance is: $");
		System.out.printf("%.2f", user.getBalance());			//displays 2 decimal places
		System.out.println(".\n");
	}

	private void runExit() {
		System.out.println("Exiting Program.");
		running = false;
		System.exit(0);
	}

	public static void main(String[] args){
		Main main = new Main();
		main.run();
	}
}

import java.util.Scanner;

// This is the account class.
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // A method/function to deposit money.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount of rupees " + amount + "deposited successfully! New Balance: " + balance);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    // A method/function to withdraw money.
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully! New Balance: " + balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    // A method/function to display Account Details.
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

    // A method to access private members.
    public int getAccountNumber(){
        return accountNumber;
    }

    // A method/function to update Account Details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully!");
    }
}

// This is the UserInterface Class which has been kept same as file name, i.e., Main.
public class Main {
    private Account[] accounts;
    private int accountCount;
    private Scanner sc;

    public Main(int size) {
        accounts = new Account[size];
        accountCount = 0;
        sc = new Scanner(System.in);
    }

    // This is the method to create new account, that takes account details from and stores in the array.
    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        int accountNumber = 1001 + accountCount;
        accounts[accountCount] = new Account(accountNumber, name, amount, email, phone);
        accountCount++;

        System.out.println("Account created successfully with Account Number: " + accountNumber);
    }

    // This is the method/function to handle deposit operation
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // This is the method/function to perform withdraw operation
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // This is the method/function to show Account Details.
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // This the method/function to update contact details
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();

            System.out.print("Enter new phone number: ");
            String phone = sc.nextLine();

            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Find account helper
    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    // Main menu that handle user choice
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Thank you for using the Banking Application!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    // Main method
    public static void main(String[] args) {
        Main ui = new Main(100); // maximum 100 accounts
        ui.mainMenu();
    }
}

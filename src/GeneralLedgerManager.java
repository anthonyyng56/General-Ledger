// Anthony Ng, 112833134, R07
/**
 * The <code>GeneralLedgerManager</code> class represents a General Ledger.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 **/
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralLedgerManager {
    /**
     * The main driver for the GeneralLedgerManager class
     * which is a test program for a general ledger,
     * allowing the user to manage transactions and
     * their financial data.
     *
     * @param args
     *      Command line arguments.
     **/
    public static void main(String [] args) {
        Scanner stdin = new Scanner(System.in);
        GeneralLedger generalLedger = new GeneralLedger();
        GeneralLedger generalLedgerCopy = new GeneralLedger();
        boolean continueTransactions = true;
        do {
            printMenu();
            System.out.print("\nEnter a selection: ");
            String userChoice = stdin.next();
            switch (userChoice.toUpperCase()) {
                case "A":
                    try {
                        System.out.print("\nEnter Date (yyyy/mm/dd): ");
                        stdin.nextLine();
                        String enteredDate = stdin.nextLine();
                        System.out.print("Enter Amount ($): ");
                        double enteredAmount = stdin.nextDouble();
                        System.out.print("Enter Description: ");
                        stdin.nextLine();
                        String enteredDescription = stdin.nextLine();
                        Transaction addedTransaction = new Transaction(
                                enteredDate, enteredAmount, enteredDescription);
                        generalLedger.addTransaction(addedTransaction);
                        System.out.println("\nTransaction successfully added"
                                + " to the general ledger.\n");
                    } catch (InputMismatchException ex) {
                        System.out.println("\nInput Mismatch!"
                                + " Please try again.\n");
                        stdin.nextLine();
                    } catch (FullGeneralLedgerException |
                            TransactionAlreadyExistsException |
                            InvalidTransactionException ex) {
                        System.out.println("\n" + ex.getMessage() + "\n");
                    }
                    break;
                case "G":
                    System.out.print("\nEnter position: ");
                    stdin.nextLine();
                    try {
                        int enteredPosition = stdin.nextInt();
                        Transaction retrievedTransaction =
                                generalLedger.getTransaction(enteredPosition);
                        GeneralLedger.printHeader();
                        generalLedger.printSingleTransaction(
                                retrievedTransaction, enteredPosition - 1);
                        System.out.println("\n");
                    } catch (InputMismatchException ex) {
                        System.out.println("\nInput Mismatch!"
                                + " Please try again.\n");
                        stdin.nextLine();
                    } catch (InvalidLedgerPositionException ex) {
                        System.out.println("\n" + ex.getMessage() + "\n");
                    }
                    break;
                case "R":
                    System.out.print("\nEnter position:");
                    stdin.nextLine();
                    try {
                        int removePosition = stdin.nextInt();
                        generalLedger.removeTransaction(removePosition);
                        System.out.println("\nTransaction has been"
                                + " successfully removed from the"
                                + " general ledger.\n");
                    } catch (InputMismatchException ex) {
                        System.out.println("\nInput Mismatch!" +
                                " Please try again.\n");
                        stdin.nextLine();
                    } catch (InvalidLedgerPositionException ex) {
                        System.out.println("\nTransaction not removed: No"
                                + " such transaction in the general ledger.\n");
                    }
                    break;
                case "P":
                    if (generalLedger.getTotalTransactions() == 0) {
                        System.out.println("\nNo transactions currently in"
                                + " the general ledger.\n");
                    } else {
                        generalLedger.printALLTransactions();
                    }
                    break;
                case "F":
                    System.out.print("\nEnter Date (yyyy/mm/dd): ");
                    stdin.nextLine();
                    String enteredDate = stdin.nextLine();
                    GeneralLedger.filter(generalLedger, enteredDate);
                    break;
                case "L":
                    System.out.print("\nEnter Date (yyyy/mm/dd): ");
                    stdin.nextLine();
                    enteredDate = stdin.nextLine();
                    System.out.print("Enter Amount ($): ");
                    double enteredAmount = stdin.nextDouble();
                    System.out.print("Enter Description: ");
                    stdin.nextLine();
                    String enteredDescription = stdin.nextLine();
                    Transaction enteredTransaction =
                            new Transaction(enteredDate, enteredAmount,
                                    enteredDescription);
                    boolean exists = false;
                    for (int i = 0; i < generalLedger.getTotalTransactions();
                         i++) {
                        if (generalLedger.getLedger()[i].equals(
                                enteredTransaction)) {
                            exists = true;
                            GeneralLedger.printHeader();
                            generalLedger.printSingleTransaction(
                                    enteredTransaction, i);

                            System.out.println("\n");
                        }
                    }
                    if (!exists) {
                        System.out.println("\nSuch transaction does "
                                + "not exist.\n");
                    }
                    break;
                case "S":
                    if (generalLedger.size() == 1) {
                        System.out.println("\nThere is " + generalLedger.size()
                                + " transaction currently in the general "
                                + "ledger.\n");
                    } else {
                        System.out.println("\nThere are " + generalLedger.size()
                                + " transactions currently in the general "
                                + "ledger.\n");
                    }
                    break;
                case "B":
                    generalLedgerCopy = (GeneralLedger)generalLedger.clone();
                    System.out.println("\nCreated a backup of the current "
                            + "general ledger.\n");
                    break;
                case "PB":
                    if (generalLedgerCopy.getTotalTransactions() == 0) {
                        System.out.println("\nNo transactions currently in"
                                + " the general ledger backup.\n");
                    } else {
                        generalLedgerCopy.printALLTransactions();
                    }
                    break;
                case "RB":
                    generalLedger = (GeneralLedger)generalLedgerCopy.clone();
                    System.out.println("\nGeneral ledger successfully "
                            + "reverted to the backup copy.\n");
                    break;
                case "CB":
                    if (generalLedger.equals(generalLedgerCopy)) {
                        System.out.println("\nThe current general ledger is "
                                + "the same as the backup copy.\n");
                    } else {
                        System.out.println("\nThe current general ledger is "
                                + "NOT the same as the backup copy.\n");
                    }
                    break;
                case "PF":
                    //complete in O(1) time
                    System.out.println("\nFinancial Data for your Account");
                    System.out.println("------------------------------------"
                            + "-----------------------------------------------"
                            + "--------------");
                    System.out.printf("     Assets: $%.2f%n",
                            generalLedger.getTotalDebitAmount());
                    System.out.printf("Liabilities: $%.2f%n",
                            generalLedger.getTotalCreditAmount());
                    System.out.printf("  Net Worth: $%.2f%n",
                            (generalLedger.getTotalDebitAmount() -
                                    generalLedger.getTotalCreditAmount()));
                    System.out.println("\n");
                    break;
                case "Q":
                    System.out.println("\nProgram terminating successfully...");
                    continueTransactions = false;
                    break;
                default:
                    System.out.println("\nCommand not recognized."
                            + " Please try again.\n");
            }

        } while (continueTransactions);
    }
    /**
     * Prints the selection menu in the console.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The selection menu is printed to the console.</dd>
     * </dl>
     **/
    public static void printMenu() {
        System.out.println("(A) Add Transaction \n"
                + "(G) Get Transaction\n"
                + "(R) Remove Transaction\n"
                + "(P) Print Transactions in General Ledger\n"
                + "(F) Filter by Date\n"
                + "(L) Look for Transaction\n"
                + "(S) Size\n"
                + "(B) Backup\n"
                + "(PB) Print Transactions in Backup\n"
                + "(RB) Revert to Backup\n"
                + "(CB) Compare Backup with Current\n"
                + "(PF) Print Financial Information\n"
                + "(Q) Quit");
    }
}
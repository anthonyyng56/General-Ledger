// Anthony Ng, 112833134, R07
/**
 * The <code>GeneralLedger</code> class represents a General Ledger.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 * Data members: int MAX_TRANSACTIONS
 *               Transaction[] ledger
 *               int totalTransactions
 *               double totalDebitAmount
 *               double totalCreditAmount
 **/
public class GeneralLedger {
    private static final int MAX_TRANSACTIONS = 50;
    private Transaction[] ledger = new Transaction[MAX_TRANSACTIONS];
    private int totalTransactions = 0;
    private double totalDebitAmount = 0.0;
    private double totalCreditAmount = 0.0;

    /**
     * Constructs an instance of the GeneralLedger with no Transaction objects
     * in it.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The GeneralLedger has been initialized to an empty list of
     * Transactions.</dd>
     * </dl>
     **/
    public GeneralLedger() {
    }
    /**
     * Returns ledger the array of Transaction objects of this GeneralLedger
     *
     * @return
     *      ledger array of Transaction objects of this GeneralLedger.
     **/
    public Transaction[] getLedger() {
        return this.ledger;
    }

    /**
     * Returns the total transactions in this GeneralLedger.
     *
     * @return
     *      The total transactions in this GeneralLedger.
     **/
    public int getTotalTransactions() {
        return this.totalTransactions;
    }

    /**
     * Returns the total debit amount in this GeneralLedger.
     *
     * @return
     *      The total debit amount in this GeneralLedger.
     **/
    public double getTotalDebitAmount() {
        return this.totalDebitAmount;
    }

    /**
     * Returns the total credit amount in this GeneralLedger.
     *
     * @return
     *      The total credit amount in this GeneralLedger.
     **/
    public double getTotalCreditAmount() {
        return this.totalCreditAmount;
    }

    /**
     * Sets the ledger of this GeneralLedger.
     *
     * @param ledger
     *      An array of Transaction objects.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd><code>ledger</code> is set to the supplied Transaction
     * array.</dd>
     * </dl>
     **/
    public void setLedger(Transaction[] ledger) {
        this.ledger = ledger;
    }

    /**
     * Sets the total transactions of this GeneralLedger.
     *
     * @param totalTransactions
     *      An int containing the total number of transactions in this
     *      GeneralLedger.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd><code>totalTransactions</code> is set to the supplied value.</dd>
     * </dl>
     **/
    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    /**
     * Sets the total debit amount of this GeneralLedger.
     *
     * @param totalDebitAmount
     *      A double containing the total amount of debit in this GeneralLedger.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd><code>totalDebitAmount</code> is set to the supplied value.</dd>
     * </dl>
     **/
    public void setTotalDebitAmount(double totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }

    /**
     * Sets the total credit amount in this GeneralLedger.
     *
     * @param totalCreditAmount
     *      A double containing the total amount of credit in this
     *      GeneralLedger.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd><code>totalCreditAmount</code> is set to the supplied value.</dd>
     * </dl>
     **/
    public void setTotalCreditAmount(double totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    /**
     * Adds newTransaction into this GeneralLedger and puts all transactions in
     * date order.
     *
     * @param newTransaction
     *      The new Transaction object to be added to the ledger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>The Transaction object has been instantiated.</dd>
     * <dd>The number of Transaction objects in this GeneralLedger is less
     * than MAX_TRANSACTIONS.</dd>
     * </dl>
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd><code>newTransaction</code> has been added to the list</dd>
     * <dd>The list is in correct order by date.</dd>
     * </dl>
     *
     * @throws FullGeneralLedgerException
     *      Thrown if there is no more room in this GeneralLedger to add
     *      more transactions.
     * @throws InvalidTransactionException
     *      Thrown if the transaction amount is 0 or the date is invalid.
     * @throws TransactionAlreadyExistsException
     *      Thrown if the transaction trying to be added already exists
     *      in this Generaledger.
     **/
    public void addTransaction(Transaction newTransaction)
            throws  FullGeneralLedgerException, InvalidTransactionException,
            TransactionAlreadyExistsException{
        //check to see if ledger is full
        if (this.totalTransactions == MAX_TRANSACTIONS) {
            throw new FullGeneralLedgerException();
        }
        //check if transaction exists or is invalid
        try {
            boolean exists = exists(newTransaction);
        } catch (IllegalArgumentException ex) {
            throw new InvalidTransactionException();
        }
        if (exists(newTransaction)) {
            throw new TransactionAlreadyExistsException();
        }
        //add newTransaction to this GeneralLedger
        this.ledger[totalTransactions] = (Transaction)newTransaction.clone();
        //increment totalTransactions by 1
        totalTransactions++;
        //increase totalCreditAmount or totalDebitAmount
        if (newTransaction.getAmount() < 0) {
            totalCreditAmount += (-1 * newTransaction.getAmount());
        } else {
            totalDebitAmount += newTransaction.getAmount();
        }
        //sort transactions in this GeneralLedger by date by bubble sort
        for (int i = 0; i < totalTransactions; i++) {
            for (int j = 0; j < totalTransactions - 1; j++) {
                int[] dateArray1 = splitDate(this.ledger[j].getDate());
                int[] dateArray2 = splitDate(this.ledger[j + 1].getDate());
                if (dateArray1[0] > dateArray2[0]) {
                    Transaction tempTransaction =
                            (Transaction)this.ledger[j].clone();
                    ledger[j] = (Transaction)this.ledger[j + 1].clone();
                    ledger[j + 1] = (Transaction)tempTransaction.clone();
                } else if (dateArray1[0] == dateArray2[0]) {
                    if (dateArray1[1] > dateArray2[1]) {
                        Transaction tempTransaction =
                                (Transaction)this.ledger[j].clone();
                        ledger[j] = (Transaction)this.ledger[j + 1].clone();
                        ledger[j + 1] = (Transaction)tempTransaction.clone();
                    } else if (dateArray1[1] == dateArray2[1]) {
                        if (dateArray1[2] > dateArray2[2]) {
                            Transaction tempTransaction =
                                    (Transaction)this.ledger[j].clone();
                            ledger[j] = (Transaction)this.ledger[j + 1].clone();
                            ledger[j + 1] =
                                    (Transaction)tempTransaction.clone();
                        }
                    }
                }
            }
        }
    }

    /**
     * Removes transaction located at position from this GeneralLedger
     *
     * @param position
     *      An int containing the position of a transaction the user wants to
     *      remove from this GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This generalLedger has been instantiated.</dd>
     * <dd><code>position</code> is greater than or equal to 1 and less than
     * or equal to the total transactions currently in this GeneralLedger.</dd>
     * </dl>
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The transaction at the desired <code>position</code> is removed.</dd>
     * <dd>Transactions in this GeneralLedger move up to fill the void space
     * created by the removal of the transaction at the desired position if
     * there is an empty space.</dd>
     * </dl>
     *
     * @throws InvalidLedgerPositionException
     *      Thrown if the position is greater than the total number of
     *      transactions in this GeneralLedger or less than 1.
     **/
    public void removeTransaction(int position)
            throws InvalidLedgerPositionException {
        //check if position is valid
        if (position < 1 || position > this.totalTransactions) {
            throw new InvalidLedgerPositionException();
        }
        //adjust totalDebitAmount or totalCreditAmount based on removal
        if (this.ledger[position - 1].getAmount() < 0) {
            this.totalCreditAmount -=
                    (-1 * this.ledger[position - 1].getAmount());
        } else {
            this.totalDebitAmount -= this.ledger[position - 1].getAmount();
        }
        //fill in gap from removed transaction
        for (int i = position - 1; i < this.totalTransactions - 1; i++) {
            if (position == this.totalTransactions) {
                Transaction tempTransaction = new Transaction();
                this.ledger[i] = (Transaction)tempTransaction.clone();
                break;
            } else {
                Transaction tempTransaction = new Transaction();
                this.ledger[i] = (Transaction)this.ledger[i + 1].clone();
                this.ledger[i + 1] = (Transaction) tempTransaction.clone();
            }
        }
        //decrement totalTransactions after removal
        totalTransactions--;
    }

    /**
     * Returns a reference to the Transaction object located at position.
     *
     * @param position
     *      An int that refers to the position of a transaction in the
     *      GeneralLedger for this method to retrieve.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>The GeneralLedger has been instantiated</dd>
     * <dd><code>position</code> is greater than or equal to 1 and less than
     * or equal to the total number of transactions in this GeneralLedger.</dd>
     * </dl>
     *
     * @return
     *      The Transaction object at the desired position in this
     *      GeneralLedger.
     *
     * @throws InvalidLedgerPositionException
     *      Thrown if the position is greater than the total number of
     *      transactions in this GeneralLedger or less than 1.
     **/
    public Transaction getTransaction(int position)
            throws InvalidLedgerPositionException{
        //check if position is valid
        if (position < 1 || position > totalTransactions) {
            throw new InvalidLedgerPositionException();
        }
        return ledger[position - 1];
    }

    /**
     * Prints all transactions that were made on a specified date.
     *
     * @param generalLedger
     *      A GeneralLedger object to be filtered by the specified date.
     *
     * @param date
     *      A String that specifies the date of transactions to search for.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger has been instantiated.</dd>
     * </dl>
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>Prints a formatted table of the transactions that have taken place
     * on the specified date.</dd>
     * </dl>
     **/
    public static void filter(GeneralLedger generalLedger, String date) {
        Transaction[] ledger = generalLedger.getLedger();
        GeneralLedger.printHeader();
        //loop through all elements to print all transactions on specified date
        for (int i = 0; i < generalLedger.getTotalTransactions(); i++) {
            if (ledger[i].getDate().equals(date)) {
                generalLedger.printSingleTransaction(ledger[i], i);
            }
        }
        System.out.println("\n");
    }

    /**
     * Creates a copy of this GeneralLedger which is distinct and unaffected
     * by changes in the original GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger has been instantiated.</dd>
     * </dl>
     *
     * @return
     *      A backup copy of this GeneralLedger.
     **/
    public Object clone() {
        Object cloneObject = new GeneralLedger();
        Transaction[] tempTransactionArray = new Transaction[MAX_TRANSACTIONS];
        for (int i = 0; i < totalTransactions; i++) {
            tempTransactionArray[i] = (Transaction)this.ledger[i].clone();
        }
        //set elements of the clone equal to the original
        ((GeneralLedger)cloneObject).setLedger(tempTransactionArray);
        ((GeneralLedger)cloneObject).setTotalTransactions(totalTransactions);
        ((GeneralLedger)cloneObject).setTotalCreditAmount(totalCreditAmount);
        ((GeneralLedger)cloneObject).setTotalDebitAmount(totalDebitAmount);
        return cloneObject;
    }

    /**
     * Returns a boolean to tell whether a specified transaction exists in
     * this GeneralLedger.
     *
     * @param transaction
     *      The specified transaction to check to see if it exists.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger and the specified transaction has been
     * instantiated.</dd>
     * </dl>
     *
     * @return
     *      true if this GeneralLedger contains transaction and false otherwise.
     *
     * @throws  IllegalArgumentException
     *      Thrown if the transaction is not a valid Transaction object due to
     *      the amount being 0, an invalid date, or date entered in an invalid
     *      format.
     **/
    public boolean exists(Transaction transaction)
            throws IllegalArgumentException{
        try {
            String date = transaction.getDate();
            int[] dateArray = splitDate(date);
            //check if date and amount is valid
            if (transaction.getAmount() == 0 || dateArray[0] < 1900 ||
                    dateArray[0] >  2050 || dateArray[1] < 1 ||
                    dateArray[1] > 12 || dateArray[2] < 1 ||
                    dateArray[2] > 30) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException
                | StringIndexOutOfBoundsException ex) {
            throw new IllegalArgumentException();
        }
        for (int k = 0; k < this.totalTransactions; k++) {
            if (transaction.equals(ledger[k])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the total number of transactions in this GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger has been instantiated.</dd>
     * </dl>
     *
     * @return
     *      An int which represents the total number of transactions in this
     *      GeneralLedger.
     **/
    public int size() {
        //return size in O(1) time
        return this.totalTransactions;
    }

    /**
     * Prints a table of all transactions in this GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger has been instantiated.</dd>
     * </dl>
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>All transactions in this GeneralLedger are printed in the table.
     * </dd>
     * </dl>
     **/
    public void printALLTransactions() {
        System.out.print(this.toString());
        System.out.println();
    }

    /**
     * Returns a String representation of a this GeneralLedger.
     *
     * @return
     *      A String representation of GeneralLedger in a neat formatted
     *      table.
     **/
    public String toString() {
        String table = "";
        table += String.format("\n%-7s %-16s %-13s %-10s %-10s%n", "No.",
                "Date", "Debit", "Credit", "Description");
        table += "------------------------------------------------------"
                + "-------------------------------------------\n";
        for (int i = 0; i < this.totalTransactions; i++) {
            //return String based on credit or debit
            if (this.getLedger()[i].getAmount() < 0) {
                table += String.format("%-7d %-10s %11s %14.2f %3s %-20s%n",
                        (i + 1), this.getLedger()[i].getDate(), "",
                        (-1 * this.getLedger()[i].getAmount()),
                        "", this.getLedger()[i].getDescription());
            } else {
                table += String.format("%-7d %-10s %11.2f %14s %3s %-20s%n",
                        (i + 1), this.getLedger()[i].getDate(),
                        this.getLedger()[i].getAmount(), "", "",
                        this.getLedger()[i].getDescription());
            }
        }
        return table;
    }

    /**
     * Prints the header of the table printed in other methods
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The header used in other methods is printed.</dd>
     * </dl>
     **/
    public static void printHeader() {
        System.out.printf("\n%-7s %-16s %-13s %-10s %-10s%n",
                "No.", "Date", "Debit", "Credit", "Description");
        System.out.println("----------------------------------------------"
                + "---------------------------------------------------");
    }

    /**
     * Prints a single transaction which is neat and formatted like the table
     * arrangement.
     *
     * @param transaction
     *      A Transaction object to be printed out in a String format.
     *
     * @param position
     *      An int representing the position of the transaction in this
     *      GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>transaction has to be instantiated.</dd>
     * </dl>
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The transaction is printed in a single line and is formatted for
     * the table and header.</dd>
     * </dl>
     **/
    public void printSingleTransaction(Transaction transaction, int position) {
        //print format different based on credit or debit
        if (transaction.getAmount() < 0) {
            System.out.printf("%-7d %-10s %11s %14.2f %3s %-20s%n",
                    (position + 1), transaction.getDate(), "",
                    (-1 * transaction.getAmount()), "",
                    transaction.getDescription());
        } else {
            System.out.printf("%-7d %-10s %11.2f %14s %3s %-20s%n",
                    (position + 1), transaction.getDate(),
                    (transaction.getAmount()), "", "",
                    transaction.getDescription());
        }
    }

    /**
     * Compares two GeneralLedger objects and returns a boolean values based
     * on if the two objects are identical.
     *
     * @param obj
     *      An object which is compared to this GeneralLedger. This object for
     *      comparison should be an instance of GeneralLedger.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This GeneralLedger and <code>obj</code> have been instantiated.</dd>
     * </dl>
     *
     * @return
     *      true if this GeneralLedger and obj are identical, false otherwise.
     **/
    public boolean equals(Object obj) {
        if (obj instanceof GeneralLedger) {
            if (this.getTotalTransactions() !=
                    ((GeneralLedger)obj).getTotalTransactions() ||
                    this.getTotalCreditAmount() !=
                            ((GeneralLedger)obj).getTotalCreditAmount() ||
                    this.getTotalDebitAmount() !=
                            ((GeneralLedger)obj).getTotalDebitAmount()) {
                return false;
            }
            //loop through both Transaction arrays to see if they are equal
            for (int i = 0; i < this.getTotalTransactions(); i++) {
                if (!(this.getLedger()[i].equals(
                        ((GeneralLedger)obj).getLedger()[i]))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Splits a String representing a date into an int array for convenient use
     * in other methods to validate or compare dates.
     *
     * @param date
     *      A String that represents a date.
     *
     * @return
     *      An int array with a size of 3 which holds the year, month, and day.
     *
     * @throws  NumberFormatException
     *      Thrown if the date String was not written in the form
     *      "yyyy/mm/dd".
     **/
    public int[] splitDate(String date) throws NumberFormatException{
        int [] dateArray = new int[3];
        try {
            int firstBackSlash = date.indexOf('/');
            int secondBackSlash =
                    date.indexOf('/', firstBackSlash + 1);
            dateArray[0] = Integer.parseInt(date.substring(0, firstBackSlash));
            dateArray[1] =
                    Integer.parseInt((date.substring(firstBackSlash + 1,
                            secondBackSlash)));
            dateArray[2] =
                    Integer.parseInt(date.substring(secondBackSlash + 1));
            return dateArray;
        } catch (NumberFormatException | StringIndexOutOfBoundsException ex) {
            throw new NumberFormatException();
        }
    }
}
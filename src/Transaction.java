// Anthony Ng, 112833134, R07
/**
 * The <code>Transaction</code> class represents a Transaction.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 * Data members: String date
 *               double amount
 *               String description
 **/
public class Transaction {
    private String date;
    private double amount;
    private String description;

    /**
     * Constructs a Transaction object.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The Transaction object has been instantiated.</dd>
     * </dl>
     **/
    public Transaction() {
    }

    /**
     * Constructs a Transaction object with a specified date, amount, and
     * description.
     *
     * @param date
     *      A String that represents a date in yyyy/mm/dd format.
     * @param amount
     *      A double that represents the amount of money earned or spent.
     * @param description
     *      A String that describes details of the transaction.
     *
     * <dl>
     * <dt><b>Postcondition:</b></dt>
     * <dd>The Transaction has been initialized to the specified date, amount,
     * and description.</dd>
     * </dl>
     **/
    public Transaction(String date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Returns the date of this Transaction.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This Transaction has been initialized.</dd>
     * </dl>
     *
     * @return
     *      The date this Transaction was made.
     **/
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the amount of money of this Transaction.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This Transaction has been initialized.</dd>
     * </dl>
     *
     * @return
     *      The amount of money of this Transaction.
     **/
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the description of this Transaction.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This Transaction has been initialized.</dd>
     * </dl>
     *
     * @return
     *      The description of this Transaction.
     **/
    public String getDescription() {
        return this.description;
    }

    /**
     * Makes an exact independent copy of this Transaction which is unaffected
     * by changes made in the original Transaction object.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This Transaction has been initialized.</dd>
     * </dl>
     *
     * @return
     *      The clone object of this Transaction.
     **/
    public Object clone() {
        Object cloneObject = new Transaction(
                this.getDate(), this.getAmount(), this.getDescription());
        return cloneObject;
    }

    /**
     * Compares this Transaction to obj and checks whether they are identical.
     *
     * @param obj
     *      An Object being compared to this Transaction.
     *
     * <dl>
     * <dt><b>Precondition:</b></dt>
     * <dd>This Transaction and <code>obj</code> have been initialized.</dd>
     * </dl>
     *
     * @return
     *      true if this Transaction is identical to obj, otherwise false.
     **/
    public boolean equals(Object obj) {
        if (obj instanceof Transaction) {
            if (this.getDate().equals(((Transaction)obj).getDate()) &&
                    this.getAmount() == ((Transaction)obj).getAmount() &&
                    this.getDescription().equals(
                            ((Transaction)obj).getDescription())) {
                return true;
            }
        }
        return false;
    }
}
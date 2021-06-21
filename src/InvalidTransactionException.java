// Anthony Ng, 112833134, R07
/**
 * The <code>InvalidTransactionException</code> class represents an exception
 * thrown when the specified transaction's amount is 0 or when the date is
 * invalid or written in the wrong format.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 **/
public class InvalidTransactionException extends IllegalArgumentException {
    public InvalidTransactionException() {
        super("Transaction not added:"
                + " Transaction is Invalid.");
    }
}
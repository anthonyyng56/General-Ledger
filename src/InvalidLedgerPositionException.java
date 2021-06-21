// Anthony Ng, 112833134, R07
/**
 * The <code>InvalidLedgerPositionException</code> class represents an exception
 * thrown when the specified ledger position is less than 1 or greater than the
 * total number of transactions.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 **/
public class InvalidLedgerPositionException extends IllegalArgumentException {
    public InvalidLedgerPositionException() {
        super("No such transaction.");
    }
}
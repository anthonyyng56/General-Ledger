// Anthony Ng, 112833134, R07
/**
 * The <code>TransactionAlreadyExistsException</code> class represents an
 * exception thrown when the specified transaction was attempted to be added
 * but already exists in this GeneralLedger.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 **/
public class TransactionAlreadyExistsException
        extends IllegalArgumentException {
    public TransactionAlreadyExistsException() {
        super("Transaction not added: "
                + "Transaction already exists in the general ledger.");
    }
}
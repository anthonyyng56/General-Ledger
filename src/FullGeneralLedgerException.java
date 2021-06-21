// Anthony Ng, 112833134, R07
/**
 * The <code>FullGeneralLedgerException</code> class represents an exception
 * thrown when the GeneralLedger cannot add anymore transactions.
 *
 *
 * @author Anthony Ng
 *      e-mail: anthony.ng@stonybrook.edu
 *
 **/
public class FullGeneralLedgerException extends IndexOutOfBoundsException {
    public FullGeneralLedgerException() {
        super("Transaction not added: "
                + "General Ledger is completely full.");
    }
}


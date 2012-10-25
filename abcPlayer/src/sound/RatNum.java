package sound;

/**
 * Represents a reduced fraction of positive integers. 
 * @author Arjun
 *
 */
public class RatNum 
{
    private final int numer; 
    private final int denom; 
    
    /**
     * Creates a new immutable Rational Number object from both a numerator and a denomator
     * @param numer  must be greater than or equal to 0
     * @param denom  must be greater than 0
     */
    public RatNum(int numer, int denom)
    {
        if (denom == 0)
        {
            throw new IllegalArgumentException("Denominator of a rational number cannot be zero");
        }
        int gcd = Utilities.GCD(numer, denom); 
        this.numer = numer/gcd; 
        this.denom = denom/gcd; 
    }
    
    /**
     * Creates a new immutable Rational Number object from an integer
     * @param numer  must be greater than or equal to 0
     */
    public RatNum(int numer)
    {
        this(numer, 1);
    }
    
    //Note: The following methods are trivial and do not need to be specced. 
    
    public int getNumer()
    {
        return numer;
    }
    
    public int getDenom()
    {
        return denom; 
    }
    
    @Override
    public String toString() {
    	return numer + "/" + denom;
    }
}

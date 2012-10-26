package sound;

/**
 * Represents a class providing helpful math utility functions.
 * @author Arjun
 *
 */
public class Utilities 
{
    /**
     * Computes the greatest common divisor between two integers.
     * @param a - must not be >= 0
     * @param b must be > 0
     * @return the greatest common divisor
     */
    public static int GCD(int a, int b)
    {
        //Use Euclid's method. 
       if (a < 0 || b < 0)
       {
           throw new IllegalArgumentException("Parameters must be nonnegative integers");
       }
       
       if (b == 0)
       {
           return a;
        
       }    
       else 
       {
           return GCD(b, a  % b);
       }
    }
    
    /**
     * Computes the least common multiple of two integers. 
     * @param a - must not be >= 0
     * @param b must be > 0
     * @return the least common multiple. 
     */
    public static int LCM(int a, int b)
    {
        if (a < 0 || b < 0)
        {
            throw new IllegalArgumentException("Parameters must be nonnegative integers");
        }
        
        int gcd = GCD(a,b); 
        return (a * b)/(gcd);
    }
}

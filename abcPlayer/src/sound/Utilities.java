package sound;

public class Utilities 
{
    public static int GCD(int a, int b)
    {
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

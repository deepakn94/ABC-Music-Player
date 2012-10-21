package sound;

public class RatNum 
{
    private final int numer; 
    private final int denom; 
    
    public RatNum(int numer, int denom)
    {
        if (denom == 0)
        {
            throw new IllegalArgumentException("Denominator of a rational number cannot be zero");
        }
        int gcd = gcd(numer, denom); 
        this.numer = numer/gcd; 
        this.denom = denom/gcd; 
    }
    
    public RatNum(int numer)
    {
        this.numer = numer; 
        this.denom = 1;
    }
    
    public int getNumer()
    {
        return numer;
    }
    
    public int getDenom()
    {
        return denom; 
    }
    
    private int gcd(int a, int b)
    {  
       if (b == 0)
       {
           return a;
        
       }    
       else 
       {
           return gcd(b, a  % b);
       }
    }
    
    @Override
    public String toString() {
    	return numer + "/" + denom;
    }
}

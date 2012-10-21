package sound;

public class Rest implements Playable
{
    private final RatNum length; 
    
    public Rest(RatNum length)
    {
        this.length = length; 
    }
    
    public RatNum getLength()
    {
        return length; 
    }
    
    @Override
    public String toString() {
    	String rest = "Rest(" + (length.toString()) + ")";
    	return rest;
    }
}

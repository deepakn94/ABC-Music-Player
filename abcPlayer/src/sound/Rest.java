package sound;

public class Rest implements Playable
{
    private RatNum length; 
    
    public Rest(RatNum length)
    {
        this.length = length; 
    }
    
    public RatNum getLength()
    {
        return length; 
    }
}

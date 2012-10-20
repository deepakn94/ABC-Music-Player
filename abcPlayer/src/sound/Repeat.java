package sound;

import java.util.ArrayList;
import java.util.List;

public class Repeat implements Playable
{
    private final List<Playable> toRepeat; 
    
    public Repeat(List<Playable> toRepeat)
    {
        this.toRepeat = new ArrayList<Playable>(toRepeat); 
    }
    
    public List<Playable> getItemsToRepeat()
    {
        return new ArrayList<Playable>(this.toRepeat);
    }
}

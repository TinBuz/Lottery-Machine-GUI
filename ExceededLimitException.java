
/**
 * This is the exception if integer bounds are not met.
 * 
 * @author (Tin Buzancic) 
 * @version (5/4/2015)
 */
public class ExceededLimitException extends Exception
{			
    private int key;
    
    public ExceededLimitException(int key)
    {					// formal parameters
        this.key = key;
    }
    public int getKey()		// accessor method for
    {					//  key with details
        return key;
    } 
    public String toString()	// overridden method for
    {					//  diagnostic string
        return "No details matching '" + key +
               "' were found.";
    }
}

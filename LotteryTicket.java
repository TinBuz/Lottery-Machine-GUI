import java.util.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the LotteryTicket super class which holds all shared methods between the 3 sub classes and all necessary methods to check 
 * if the lotto numbers are valid and sorted.
 * 
 * @author (Tin Buzancic) 
 * @version (5/4/2014)
 */
public abstract class LotteryTicket
{
    String ticketName;
    int number;
    private ArrayList<Integer> pickedNumbers = new ArrayList<Integer>();
    DateFormat dateFormat = new SimpleDateFormat("EEE MMM d, YYYY");
    public static final int MAX_LOTTO_NUMBER = 60;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int EXCEEDED_VALUE = Integer.MIN_VALUE;
    Random rand = new Random();

    public LotteryTicket(int num)
    {
        ticketName = "PICK " + num;
        number = num;
        pickedNumbers = new ArrayList<Integer>();
    }         

    /**
     * Generates a random integer between 0 and 60.
     * @return
     * int the random the number you created.
     */
    public void quickPick()
    {       
        for(int i = 0; i < number; i++)
        {
            int randomInt;
            do
            {
                randomInt = rand.nextInt(MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
            }
            while(pickedNumbers.contains(randomInt));
            pickedNumbers.add(randomInt);            
        }
        pickedNumbers = sortLottoNumbers(pickedNumbers);
    }

    /**
     * Sorts the user created lottery numbers from least to greatest
     * @param
     * ArrayList<Integer> the array list you wish to iterate and sort through.
     * @return
     * ArrayList<Integer> the array list of the newly sorted out lotto numbers.
     */
    public ArrayList<Integer> sortLottoNumbers(ArrayList<Integer> unsortedNumbers)
    {
        for(int i = 0; i < number; i++)
        {
            int min = i;
            for(int j = i; j < number; j++)
            {
                if(unsortedNumbers.get(min) > unsortedNumbers.get(j))
                {
                    min = j;
                }
                int tmp = unsortedNumbers.get(i);
                unsortedNumbers.set(i, unsortedNumbers.get(min));
                unsortedNumbers.set(min, tmp);
            }
        }
        return unsortedNumbers;
    }
    
    /**
     * checks to see if the manual input is valid
     * @param
     * ArrayList<Integer> an arraylist of manually inserted lotto numbers.
     * @throws
     * ExceededLimitException if the number is less/greater than MIN/MAX or if number already exists
     */
    public void manualEntry(ArrayList<Integer> manualNumbers)
    throws ExceededLimitException
    {
        for(int i = 0; i < number; i++)
        {
            if(manualNumbers.get(i) > MAX_LOTTO_NUMBER || manualNumbers.get(i) < MIN_LOTTO_NUMBER)
            {
                throw new ExceededLimitException(EXCEEDED_VALUE);
            }
            else
            {
                for(int j = (i + 1); j < number; j++)
                {
                    if(manualNumbers.get(i) == manualNumbers.get(j))
                    {
                        throw new ExceededLimitException(EXCEEDED_VALUE);
                    }
                }
            }
        }
        pickedNumbers = sortLottoNumbers(manualNumbers);
    }

    //GETTERS/SETTERS

    /**
     * Gets arraylist of picked numbers.
     * @return
     * picked numbers array list.
     */
    public ArrayList<Integer> getPickedNumbers()
    {
        return pickedNumbers;
    }

    /**
     * get the date format in week, month, day, and year format
     * @return
     * String Date representation.
     */
    public String getDateFormat()
    {
        return dateFormat.format(new Date());
    }
    
    /**
     * Gets the number instance variable.
     * @return
     * int the number.
     */
    public int getRandomNumber()
    {
        return number;
    }
    
    /**
     * Returns the name instance variable.
     * @return
     * String the ticket name
     */
    public String getName()
    {
        return ticketName;
    }
    
    /**
     * Prints out the lottery ticket information.
     * @return
     * String representation of the lottery info.
     */
    public String listLotteryInfo()
    {
        String info = "\nNAME:\n" + getName() + " \n" + "DATE: \n" + getDateFormat() + " \nNUMBERS: \n";
        for(int number : pickedNumbers)
        {
            if(number < 10)
            {
                info = info + "0" + number + "   ";
            }
            else
            {
                info = info +  number + "    ";
            }
        }
        return info;
    }
}

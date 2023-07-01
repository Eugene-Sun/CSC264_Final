import java.util.ArrayList;
/**********************************************************
 * Program Name   : HangmanEntity
 * Author         : Eugene Sun
 * Date           : 2023.05.07
 * Course/Section : CSC 264-301
 * Program Description: 
 *
 * Methods:
 * -------
 * HangmanEntity - constructor class
 * getGuessCount - 
 * setGuessCount - 
 * getGameOverFlag - 
 * setGameOverFlag - 
 * getWord - 
 * setWord - 
 * getMaskWord - 
 * setMaskWord - 
 * setMaskWord - 
 * getUserInput -
 * setUserInput - 
 * getLiveCount -
 * setLiveCount - 
 * getUserInputList -
 * appendUserInput -
 **********************************************************/
public class HangmanEntity
 {
    //Local Constants

    //Local Variables
    private String word;            //  the word the user has to guess
    private String maskWord;        //  the word with all characters not in letters replaced by _'s to display in the UI
    private String userInput;       //  comma separated string for storing all the characters the user guessed
    private int liveCount;          //  the number of lives the user has remaining
    private int guessCount;         //  the number of guesses the user has made
    private boolean gameOverFlag;   //  for storing whether the game is over

    /**********************************************************
    * Method Name : Hangman
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Constructor method, does not do anything
    *   BEGIN HangmanEntity
    *   END HangmanEntity
    *
    **********************************************************/
    public HangmanEntity() 
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
    }// END HangmanEntity

    /**********************************************************
    * Method Name : getGuessCount
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for guessCount
    *   BEGIN guessCount
    *       return guessCount
    *   END guessCount
    **********************************************************/
    public int getGuessCount() 
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return guessCount
        return guessCount;
    }//END getGuessCount

    /**********************************************************
    * Method Name : setGuessCount
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for guessCount
    *   BEGIN setGuessCount
    *       set guessCount as guessCount
    *   END setGuessCount
    **********************************************************/
    public void setGuessCount(int guessCount)
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //set guessCount as guessCount
        this.guessCount = guessCount;
    }//END getGuessCount

    /**********************************************************
    * Method Name : getGameOverFlag
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for getGameOverFlag
    *   BEGIN getGameOverFlag
    *       return getGameOverFlag
    *   END getGameOverFlag
    **********************************************************/
    public boolean getGameOverFlag()
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return guessCount
        return gameOverFlag;
    }//End getGameOverFlag

    /**********************************************************
    * Method Name : setGameOverFlag
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for gameOverFlag
    *   BEGIN setGameOverFlag
    *       set gameOverFlag as gameOverFlag
    *   END setGameOverFlag
    **********************************************************/
    public void setGameOverFlag(boolean gameOverFlag)
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        this.gameOverFlag = gameOverFlag;
    }//End setGameOverFlag

    /**********************************************************
    * Method Name : getWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for gameOverFlag
    *   BEGIN getWord
    *       return getWord
    *   END getWord
    **********************************************************/
    public String getWord()
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return word
        return word;
    }

    /**********************************************************
    * Method Name : setWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for word
    *   BEGIN setWord
    *       set word as word
    *   END setWord
    **********************************************************/
    public void setWord(String word)
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //set word as word
        this.word = word;
    }

    /**********************************************************
    * Method Name : getMaskWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for maskWord
    *   BEGIN getMaskWord
    *       return getMaskWord
    *   END getMaskWord
    **********************************************************/
    public String getMaskWord()
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return maskWord
        return maskWord;
    }

    /**********************************************************
    * Method Name : setMaskWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for maskWord
    *   BEGIN setMaskWord
    *       set maskWord as maskWord
    *   END setMaskWord
    **********************************************************/
    public void setMaskWord(String maskWord)
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //set maskWord as maskWord
        this.maskWord = maskWord;
    }

    /**********************************************************
    * Method Name : getUserInput
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for userInput
    *   BEGIN getUserInput
    *       return getUserInput
    *   END getUserInput
    **********************************************************/
    public String getUserInput()
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return getUserInput
        return userInput;
    }

    /**********************************************************
    * Method Name : setUserInput
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for userInput
    *   BEGIN setUserInput
    *       set userInput as userInput
    *   END setUserInput
    **********************************************************/
    public void setUserInput(String userInputStr)
     {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //set userInput as userInput
        this.userInput = userInputStr;
    }

    /**********************************************************
    * Method Name : getLiveCount
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Getter method for liveCount
    *   BEGIN getLiveCount
    *       return liveCount
    *   END getLiveCount
    **********************************************************/
    public int getLiveCount()
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //return liveCount
        return liveCount;
    }

    /**********************************************************
    * Method Name : setLiveCount
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: setter method for liveCount
    *   BEGIN setLiveCount
    *       set liveCount as liveCount
    *   END setLiveCount
    **********************************************************/
    public void setLiveCount(int liveCount)
     {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        this.liveCount = liveCount;
    }

    /**********************************************************
    * Method Name : getUserInputList
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: convenience method for converting userInput into an ArrayList
    *   BEGIN getUserInputList
    *       IF userInput is not empty
    *           split userInput
    *           FOR
    *               add letter to lettersList
    *           END FOR
    *       END IF
    *   END getUserInputList
    **********************************************************/
    public ArrayList<Character> getUserInputList()
     {
        //Local Constants
        final String COMMA = ",";                                   //regex for splitting the userInput

        //Local Variables
        String userInput = getUserInput();                          //comma separated value contain characters the user guessed
        ArrayList<Character> letters = new ArrayList<Character>();  //list to store the user input
      
        /********************     *****************/

        //if userInput is not empty
        if (!userInput.isEmpty())
         {
            String[] temp = userInput.split(COMMA);
            //loop through temp list
            for (String letter : temp)
             {
                //add each letter 
                letters.add(letter.charAt(0));
            }
        }
        //return letters
        return letters;
    }

    /**********************************************************
    * Method Name : appendUserInput
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: convenience method updating the userInput
    *   BEGIN appendUserInput
    *       IF userInput is not empty
    *           append input
    *       ELSE
    *           append input followed by comma
    *       END IF
    *   END appendUserInput
    **********************************************************/
    public void appendUserInput(char input)
    {
        //Local Constants
        final String COMMA = ",";                                   //regex for splitting the userInput

        //Local Variables
        String userInput = getUserInput();                          //comma separated value contain characters the user guessed
        
        /********************     *****************/

        // if userInput is currently blank just append the character to the end of the string
        // otherwise append the character followed by a comma
        userInput = userInput.isBlank() ? String.valueOf(input)
                : userInput + COMMA + String.valueOf(input);

        //store new userInput in Entity
        setUserInput(userInput);
    }
}

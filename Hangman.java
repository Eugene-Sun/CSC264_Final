import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**********************************************************
 * Program Name   : Hangman
 * Author         : Eugene Sun
 * Date           : 2023.05.07
 * Course/Section : CSC 264-301
 * Program Description: Hangman abstract class
 *
 * Methods:
 * -------
 * Hangman - constructor class
 * displayWelcomeMessage - 
 * displayGoodByeMessage - 
 * getPlayMode - determines if a quiz grade is valid or not
 * playGame - creates version of Hangman Class given the provided
 *
 **********************************************************/
public abstract class  Hangman 
{
    //Local Constants
    private final String DICTIONARY_FILE = "data/gsl.txt";          // text file the hangman game uses to select a word for the user to guess (http://jbauman.com/aboutgsl.html)
    
    // private final String DICTIONARY_FILE = "dictionary.txt";     // for a ridiculously large dictionary use "dictionary.txt"
    
    //Local Variables
    private final int MIN_WORD_LENGTH = 3;                          // minimum length of a word to consider
    private ArrayList<String> wordList = new ArrayList<String>();   // list to store words from gls.txt
    protected HangmanEntity he;                                     //entity for storing game data

    /**********************************************************
    * Method Name : Hangman
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Constructor method, 
    *    This method initializes the Hangman entity and calls the loadDictionary method

    * BEGIN Hangman
    *   initialize HangmanEntity
    *   call loadDictionary method
    * END HangmaninitGame
    **********************************************************/
    protected Hangman() 
    {
        //Local Constants
      
        //Local Variables
      
        /********************     *****************/
        //set local Hangman entity to new instance
        he = new HangmanEntity();

        //call loadDictionary method
        loadDictionary();
    }//End Hangman

    /**********************************************************
    * Method Name : initGame
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method initializes the Hangman entity
    * BEGIN initGame
    *   set liveCount
    *   set tryCount
    *   set userInput
    *   set gameOverFlag
    *   set word
    * END initGame
    **********************************************************/
    protected void initGame(char level) 
    {
        //Local Constants
        final int DEFAULT_LIVECOUNT = 8;                //default number of lives the user has to guess word
        final int DEFAULT_TRY_COUNT = 0;                //initial value for try count
        final boolean DEFAULT_GAME_OVER_FLAG = false;   //initial boolean value for gameOverFlag

        //Local Variables
        String nextWord = getNextWord(level);           //String for storing the next user has to guess
      
        /********************     *****************/
        //set liveCount
        he.setLiveCount(DEFAULT_LIVECOUNT);

        //set tryCount
        he.setGuessCount(DEFAULT_TRY_COUNT);

        //set userInput
        he.setUserInput("");

        //set gameOverFlag
        he.setGameOverFlag(DEFAULT_GAME_OVER_FLAG);

        //set word
        he.setWord(nextWord);
    }//End initGame

    /**********************************************************
    * Method Name : getLevelList
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for the convenience of providing level information to methods
    *   in the Hangman program
    * BEGIN getLevelList
    *   set easy label, value and regex
    *   set medium label, value and regex
    *   set hard label, value and regex
    *   add easy HashMap to temp ArrayList
    *   add medium HashMap to temp ArrayList
    *   add hard HashMap to temp ArrayList
    *   return temp ArrayList
    * END getLevelList
    **********************************************************/
    protected static ArrayList<HashMap<String, String>> getLevelList() 
    {
        //Local Constants

        //Local Variables
        ArrayList<HashMap<String, String>> temp = new ArrayList<>();        //temp arrayList for holding HashMaps
        HashMap<String, String> easy = new HashMap<>();                     //temp HashMap for holding easy level information
        HashMap<String, String> medium = new HashMap<>();                   //temp HashMap for holding medium level information
        HashMap<String, String> hard = new HashMap<>();                     //temp HashMap for holding hard level information
      
        /********************     *****************/

        //set label for easy level
        easy.put("label", "Easy");
        //set value for easy level value
        easy.put("value", "E");
        //set regex for easy level value
        easy.put("regex", "^[a-z]{3,5}$");
        //set label for medium level
        medium.put("label", "Medium");
        //set value for medium level
        medium.put("value", "M");
        //set regex for medium level
        medium.put("regex", "^[a-z]{6,8}$");
        //set label for hard level
        hard.put("label", "Hard");
        //set value for hard level
        hard.put("value", "H");
        //set regex for hard level
        hard.put("regex", "^[a-z]{8,}$");
        //add easy HashMap to temp ArrayList
        temp.add(easy);
        //add medium HashMap to temp ArrayList
        temp.add(medium);
        //add hard HashMap to temp ArrayList
        temp.add(hard);
        //return temp ArrayList
        return temp;
    }//END getLevelList

    /**********************************************************
    * Method Name : getLevelList
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for the convenience of providing the index of the given level in levelList
    * BEGIN getLevelIndex
    *   set easy label, value and regex
    *   set medium label, value and regex
    *   set hard label, value and regex
    *   add easy HashMap to temp ArrayList
    *   add medium HashMap to temp ArrayList
    *   add hard HashMap to temp ArrayList
    *   return temp ArrayList
    * END getLevelList
    **********************************************************/
    private int getLevelIndex(char level) 
    {
        //Local Constants

        //Local Variables
        ArrayList<HashMap<String, String>> tempList = getLevelList();   //ArrayList containing level information
        int index = 0;                                                  //for storing which index in the levelList the given level is
        /********************     *****************/
        
        //FOR (size of level ArrayList)
        for (int i = 0; i < tempList.size(); i++) {
            //get HashMap from current iteration in tempList
            HashMap<String, String> map = tempList.get(i);

            //get value from HashMap
            String value = map.get("value"); 

            //IF (level matches value in HashMap)
            if (String.valueOf(level).equals(value)) 
            {
                //set index to value in HashMap
                index = i;
            }
        }// End For loop

        //return index
        return index;
    }//END getLevelIndex

    /**********************************************************
    * Method Name : getNextWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method gets the next word for the user to guess given the level (E, M, H)
    * BEGIN getNextWord
    *   get words from word list whose length is between the minLength and maxLength for each level
    *   select a random word from the tempWordsList
    *   set word in Hangman entity
    *   return the selected word
    * END getNextWord
    **********************************************************/
    private String getNextWord(char level) 
    {
        //Local Constants

        //Local Variables
        ArrayList<HashMap<String, String>> tempList = getLevelList();
        int index = getLevelIndex(level);
        String regex = tempList.get(index).get("regex");
        List<String> tempWordList = new ArrayList<String>(); //
        Random random = new Random();

        /******************************** */

        //get words from word list whose length is between the minLength and maxLength for each level
        for (String wordToCheck: wordList) 
        {
            //if the word matches the regex condition
            if (wordToCheck.matches(regex)) {
                //add word to tempWord list
                tempWordList.add(wordToCheck);
            }
        }

        // pick the next word from the tempWordsList
        // note: random.nextInt(n) returns an int between 0 and n-1
        String theWord = tempWordList.get(random.nextInt(tempWordList.size()));
        // set word in Hangman entity
        he.setWord(theWord);
        //return the selected word
        return theWord;
    }// END getNextWord

    /**********************************************************
    * Method Name : maskWord
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: return a String with the characters not already guessed by the user
    * replaced with _ characters.
    * BEGIN maskWord
    *   FOR
    *       IF character in word matches character in letters list
    *           append character to maskWord
    *       ELSE
    *           append "_" character
    *   END FOR
    *   return maskWord
    * END maskWord
    **********************************************************/
    private String maskWord() 
    {
        //Local Constants

        //Local Variables
        String word = he.getWord();                             //the word to be masked
        ArrayList<Character> letters = he.getUserInputList();   //list of characters the user guessed to be revealed
        String maskWord = "";                                   //the word with all characters not in letters replaced by _'s to display in the UI

        /******************************** */

        //LOOP through each character in the word
        for (int i = 0; i<word.length(); i++) 
        {
            //IF a character in the word matches a character in the userInput list
            if (letters.contains(word.charAt(i))) 
            {
                //append the matching character to anser
                maskWord = maskWord + word.charAt(i);
            }
            //ELSE
            else
            {
                //append a "_" character
                maskWord = maskWord + "_";
            }
        }//END for
        //return answer
        return maskWord;
    }// END maskWord

    /**********************************************************
    * Method Name : loadDictionary
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method loads list of words from gsl.txt file
    *   BEGIN loadDictionary
    *       read word file
    *       LOOP through words in word file
    *           IF (word is greater than the min word length)
    *               add word to words list
    *           END IF
    *       END LOOP
    *       store the words array in Hangman
    *   END loadDictionary
    **********************************************************/
    private void loadDictionary() 
    {
        //Local Constants

        //Local Variables
        ArrayList<String> words = new ArrayList<String>();  //read in the data from the dictionary to an ArrayList
        Scanner wordScan = null;                            //open the file and create a Scanner to read it in

        /******************************** */
        
        //try for catching when word file is not found in directory
        try {
            //read word file
            wordScan = new Scanner(new File(DICTIONARY_FILE));
        }//End try
        catch (FileNotFoundException e) {
            //throw error if word file is not found
            System.err.println(e);
            //exit program
            System.exit(1);
        }//END catch
        
        //WHILE word file has words left
        while (wordScan.hasNext()) {

            String nextWord = wordScan.next();
            // read the contents of the file one word at a time, skipping
            // over words that are too short for use by hangman.
            if (nextWord.length() >= MIN_WORD_LENGTH) {
                //add word to words list
                words.add(nextWord);
            }
        }

        // store the words array in Hangman
        this.wordList = words;
    } // End loadDictionary

    /**********************************************************
    * Method Name : guess
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method checks the following conditions:
    *   1. whether the user input is a matching character in the word
    *   2. whether the user input is a non-matching character in the word
    *
    *   BEGIN guess
    *       add character to the userInputString
    *       increment guessCount
    *       store new guessCount value in HangmanEntity
    *       get new maskWord
    *       store new maskWord in HangmanEntity
    *       IF (guessed character is in the word)
    *           decrease liveCount by 1
    *           store new liveCount value in HangmanEntity
    *           IF (if liveCount is zero)
    *               set gameOverFlag in HangmanEntity to true
    *           END IF
    *       ELSE
    *           IF (all characters in maskWord have been revealed)
    *               set gameOverFlag in HangmanEntity to true
    *           END IF
    *       END IF
    *   END guess
    **********************************************************/
    protected void guess(char input) 
    {
        //Local Constants

        //Local Variables
        String theWord = he.getWord();          //the word to guess
        int liveCount = he.getLiveCount();      //number of lives the user has left
        int guessCount = he.getGuessCount();    //number of guesses the user already made
        String maskWord = new String();         //the maskWord displayed on the UI

        /******************************** */
        
        //add character to the userInputString
        he.appendUserInput(input);
        //increment guessCount
        guessCount++;
        //store new guessCount value in HangmanEntity
        he.setGuessCount(guessCount);
        //get new maskWord
        maskWord = maskWord();
        //store new maskWord in HangmanEntity
        he.setMaskWord(maskWord);

        // if the guessed character is in the word?
        if (theWord.indexOf(input) == -1) 
        {
            // decrease liveCount by 1
            liveCount--;
            //store new liveCount value in HangmanEntity
            he.setLiveCount(liveCount);            

            // if liveCount is zero
            if(liveCount == 0) 
            {
                //set gameOverFlag in HangmanEntity to true
                he.setGameOverFlag(true);
            }
        }
        else 
        {
            //  if all characters in maskWord have been revealed
            if (theWord.equals(maskWord)) 
            {
                //set gameOverFlag in HangmanEntity to true
                he.setGameOverFlag(true);
            }
        } //END IF
    }//END guess

    /**********************************************************
    * Method Name : isRepeatInput
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method checks whether the user input is a previously inputted character
    *
    *   BEGIN isRepeatInput
    *       check if input is in the ArrayList of userInputs
    *       return true or false 
    *   END isRepeatInput
    **********************************************************/
    protected boolean isRepeatInput(char input) 
    {
        //Local Constants

        //Local Variables
        ArrayList<Character> inputLetters = he.getUserInputList();
        boolean isRepeatInput;
        /******************************** */
        //check whether the user input is a previously inputted character
        isRepeatInput = inputLetters.contains(input);
        //return isRepeatInput (true or false)
        return isRepeatInput;
    }//END isRepeatInput
  
}


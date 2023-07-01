import java.util.ArrayList;
import java.util.Scanner;

/**********************************************************
 * Program Name   : Hangman game (Final Project)
 * Author         : Eugene Sun
 * Date           : 2023.05.07
 * Course/Section : CSC 264-301
 * Program Description: 
 *
 * Methods:
 * -------
 * main - calculates the average of 2 quiz grades
 * displayWelcomeMessage - 
 * displayGoodByeMessage - 
 * getPlayMode - determines if a quiz grade is valid or not
 * playGame - creates version of Hangman Class given the provided
 *
 **********************************************************/
public class Main
{
   /**********************************************************
    * Method Name : Main
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Constructor method, 
    *    This method first displays a welcome message and then prompts user for which version of hangman (T for Textmode, G for GuiMode) or QUIT (Q)
    *    on quit, system will display goodbye message
    * BEGIN main
    *    call displayWelcomeMessage
    *    call method playGame
    //NOTE: the following section code related to this pseudoCode has been commented out
    *    WHILE gameMode does not equal
    *       call method getPlayMode for gameMode
    *       IF (gameMode equals QUIT)
    *          set doneFlag to true
    *       ELSE
    *          call method playGame
    *       END IF
    *    END WHILE
    *    call displayGoodByeMessage
    //NOTE: the above section code related to this pseudoCode has been commented out
    * END Main
    **********************************************************/
   public static void main(String[] args)
   {
      //Local Constants
      final String QUIT = "X";
      final String HELP = "H";
      //Local Variables
      boolean doneFlag = false;      //boolean value for leaving while loop
      /********************     *****************/

      //call displayWelcomeMessage method
      displayWelcomeMessage();

      String gameMode = "G";
      playGame(gameMode);

      //WHILE doneFlag is not true
      // while (!doneFlag)
      // {

      //    //call getPlayMode method to get which version of hangman to play
      //    String gameMode = getPlayMode(); 

      //    // IF user inputted X back in getPlayMode
      //    if (gameMode.equalsIgnoreCase(QUIT))
      //    {
      //       //set doneFlag as true
      //       doneFlag = true;
      //    }
      //    else if (gameMode.equalsIgnoreCase(HELP)) {
            
      //    }
      //    else
      //    {

      //       //else call playMethod
      //       playGame(gameMode);
      //    }
      // }// End WHILE

      //call displayGoodByeMessage method
      displayGoodByeMessage();      
   }//End main

   /**********************************************************
    * Method Name : displayWelcomeMessage
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method prints a welcome message into the terminal
    * BEGIN displayWelcomeMessage
    *    print message
    * END displayWelcomeMessage
    **********************************************************/
   private static void displayWelcomeMessage() 
   {   
      //Local Constants
      //Local Variables
      /********************     *****************/ 
      //print message
      System.out.println("Welcome to the Hangman Game Room developed by Eugene Sun.  Let's get started!");
   }//End displayWelcomeMessage

   /**********************************************************
    * Method Name : displayGoodByeMessage
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method prints a goodbye message into the terminal
    * BEGIN displayGoodByeMessage
    *    print message
    * END displayWelcomeMessage
    **********************************************************/
   private static void displayGoodByeMessage() 
   {     
      //Local Constants
      //Local Variables
      /********************     *****************/ 
      //print message
      System.out.println("Thanks for playing.");
   }//End displayGoodByeMessage
   
   /**********************************************************
    * Method Name : getPlayMode
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description:
    *    
    * BEGIN getPlayMode
    *    WHILE doneFlag is false
    *       Prompt for user to input T, G or X
    *       wait for user input
    *       IF (user input is not T, G or X)
    *          set doneFlag to true
    *       END IF
    *    END WHILE
    * return userSelection
    * END displayWelcomeMessage
    **********************************************************/
   private static String getPlayMode() 
   { 
      //Local Constants
      final String VALID_SELECTION_CHARSET = "TGX";   //String to compare the user's selection to
      //Local Variables
      boolean doneFlag = false;
      Scanner keyboard = new Scanner(System.in);      //create instance of scanner
      String userSelection = new String();            //String for storing the user's input
      /********************     *****************/

      //WHILE doneFlag is false
      while (!doneFlag)
      {

         //Prompt user to input T, G or X
         System.out.println("Please select play mode. (if selecting GUI mode please ALT + TAB to see level dialog)" +
         "\nT:Text Mode; G: GUI Mode; X: End Game!");

         //wait for user input
         userSelection = keyboard.next();

         //if user input is T, G or X
         if (VALID_SELECTION_CHARSET.indexOf(userSelection) > -1)
         {
            //set doneFlag to true
            doneFlag = true;
         }// End if
      }//End WHILE

      //Return userSelection
      return userSelection;
   }//End getPlayMode
 
   /**********************************************************
    * Method Name : getPlayMode
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Constructor method, 
    * This method instantiates either textMode or guiMode given the 
    *    provided gameMode input
    * BEGIN playGame
    *    IF (gameMode equals T)
    *       create instance of textModeGame class 
    *       playGame
    *    ELSE IF (gameMode equals T)
    *       create instance of guiModeGame class
    *       playGame
    *    ELSE
    *       Display error message
    *    END IF
    * END playGame
    **********************************************************/
    private static void playGame(String gameMode)
    {
      //Local Constants
      //Local Variables
      /********************     *****************/

      //IF (gameMode equals T)
      if (gameMode.equalsIgnoreCase("T"))
      {
         //create instances of textModeGame class
         TextModeGame textMode = new TextModeGame();

         //playGame
         textMode.playGame();

      //ELSE IF (gameMode equals T)
      } 
      else if (gameMode.equalsIgnoreCase("G"))
      {
         //create instances of GuiModeGame class
         GuiModeGame guiMode = new GuiModeGame();

         //playGame
         guiMode.playGame();

      //ELSE
      } 
      else
      {
         System.out.println("Sorry, I do not recognize this game mode. You selected " + gameMode);
      }//END IF
    }//END playGame
}
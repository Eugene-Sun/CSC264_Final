import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**********************************************************
 * Program Name : TextModeGame
 * Author : Eugene Sun
 * Date : 2023.04.05
 * Course/Section : CSC 264-301
 * Program Description: 
 * 
 * Methods:
 * -------
 * 
 **********************************************************/
public class TextModeGame extends Hangman {
   
   
   /**
    * playGame - runs single game then returns to the main, purpose is to simplify the control 
    * single game ends when:
    * 1. user has not yet guesses the entire word, and the user has used all the guess count.
    * 2. user has guessed word
    * 3. time expired!!! future feature 
    */
    // Game initialization 
    // 1. Create entity to contain game information 
    // 2. Create HangmanService to start game
    // 3. Display guess hint -- number of letters in the word, number of guesses that user can make, and number guesses left 
    // 4. Loop until end 
   protected void playGame() {
      char level = getLevel();        //first prompt user for level
      super.initGame(level);
      char userInput;
      
      displayInitialMessage();   
      while (true){
         // Prompt user to enter letter
         
         userInput = getUserInput();
         int curnLiveCount = he.getLiveCount();  // record liveCount before next guess
         guess(userInput);
         displayResult();

         if (curnLiveCount == he.getLiveCount()) {  
            // correct guess
            if (he.getGameOverFlag() == true){
               System.out.println("Congrats - you guessed the whole word!.");
               System.out.println("You got it!  The word was: " + he.getWord());
               return;
            }
         } else if (curnLiveCount > he.getLiveCount()) {  
            // wrong guess
            System.out.println("The letter " + userInput + " is not in the word.");
            if(he.getLiveCount() == 0){
               // live count has exhausted, display message and return game to main 
               displayGameOverMessage(he);
               return;
            } else{
               continue;
            }
         } else{
            // NOP this should never happen
         }
      }
   } // runTextMode

   private char getLevel() {
      ArrayList<HashMap<String, String>> temp = getLevelList();
      Scanner keyboard = new Scanner(System.in);
      String userInput = new String();
      String validSelectCharSet = new String();
      boolean isValid = false;
      while(!isValid) {
         
         System.out.println("------------------------------------------ ");
         String printString = "Please input letter of the level you wish to play: ";
         for (int i = 0; i < temp.size(); i++) {
            HashMap<String, String> map = temp.get(i);
            validSelectCharSet = validSelectCharSet + map.get("value");
            printString = printString + map.get("value") + ": " + map.get("label") + " ";
         }
         System.out.println(printString);

         userInput = keyboard.next();
         if (validSelectCharSet.indexOf(userInput) > -1){
            isValid = true;   
         }
      }

      return userInput.charAt(0);
   }

   private char getUserInput(){
      // create a Scanner to read keyboard input
      Scanner keyboard = new Scanner(System.in);
      String userInput = new String();
      while (true){
         System.out.print("Your guess? ");
         userInput = keyboard.next().toLowerCase();   // get entire string 

         if (userInput.length() == 0) {
            System.out.println("Please a letter!");
         } else if (isRepeatInput(userInput.charAt(0))){
            System.out.println("You already guessed that!");
         } else{
            return userInput.charAt(0);
         }
      }
   }

   /**
    * displayResult: 
    * 1. Number of letters in the word (___), including user correctly guess letters
    * 2. Number of guesses that user can make, and number guesses left 
   */
   private void displayResult() {
      System.out.println("------------------------------------------ ");
      System.out.print("Number of tries: " + he.getGuessCount() + ",");
      System.out.println(" you have " + he.getLiveCount() + " tries left");
      System.out.println("Letters you have entered are: " + he.getUserInput());
      System.out.println("Word to guess (" + he.getMaskWord().length() + " letters): " + he.getMaskWord());
   }

   private void displayInitialMessage() {
      System.out.println("------------------------------------------ ");
      System.out.println("You have " + he.getLiveCount() + " total tries, good luck!");
      System.out.println("The word to guess is " + he.getWord().length() + " letters long");
   }

   /**
    * displayLieveExhausedMessage: 
    * 1. Number of letters in the word (___), including user correctly guess letters
    * 2. Number of guesses that user can make, and number guesses left 
   */
   private void displayGameOverMessage(HangmanEntity gameEntity){
      System.out.println("Sorry, you have used up your live count, exiting to main.");
      System.out.println("The word was: " + gameEntity.getWord());
   }
}
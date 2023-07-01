import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
public class GuiModeGame extends Hangman {

   // local constants
   private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";  //alphabet string
   private final int WINDOW_HEIGHT = 800;                         //window height
   private final int WINDOW_WIDTH = 800;                          //window width

   // local variables
   private JFrame gameWindow;                                     //JFrame
   private JButton[] keyboardArray;                               //List for storing JButton components
   private JLabel hangmanLabel;                                        //Label for displaying the hangman image
   private JTextArea progressField;                               //JTextArea that displays the user's progress

   // Game initialization
   // 1. Create entity to contain game information
   // 2. Create HangmanService to start game
   // 3. Display guess hint -- number of letters in the word, number of guesses
   // that user can make, and number guesses left
   // 4. Loop until end

   /**********************************************************
    * Method Name : Hangman
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: Constructor method, 
    *    This method prompts the user to select a level, then given the level initializes the hangman game
    *       afterwards creates the GUI window
    * BEGIN playGame
    *   prompt user for getting level
    *   call initGame in super
    *    call loadWindow
    * END playGame
    **********************************************************/
   protected void playGame() {
      //Local Constants
      
      //Local Variables
      char level = getLevel();   //level of difficulty
      
      /********************     *****************/
      super.initGame(level);
      loadWindow();
   }

   /**********************************************************
    * Method Name : loadWindow
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method creates the hangman GUI window
    * BEGIN loadWindow
    *   initialize gameWindow
    *   call initWindowLayout
    * END loadWindow
    **********************************************************/
   private void loadWindow() {
      //Local Constants
      
      //Local Variables
      
      /********************     *****************/

      // initialize gameWindow
      gameWindow = new JFrame("Hangman Game");
      gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      gameWindow.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      gameWindow.pack();
      gameWindow.setVisible(true);

      // initialize window layout
      initWindowLayout();
   }

   /**********************************************************
    * Method Name : restartGame
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for restarting the hangman game
    * BEGIN restartGame
    *   prompt for user to select level
    *   call initGame in super
    *   remove all items in gameWindow's contentPane
    *   readd items via initWindowLayout method
    * END restartGame
    **********************************************************/
   private void restartGame() {
      //Local Constants
      
      //Local Variables
      char level = getLevel();   //level of difficulty
      
      /********************     *****************/

      //call super to initialize game
      super.initGame(level);

      //remove all items in gameWindow's contentPane
      gameWindow.getContentPane().removeAll();

      //readd items via initWindowLayout method
      initWindowLayout();
   }

   /**********************************************************
    * Method Name : getLevel
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method prompt for the user to select a level via a popup dialog
    * BEGIN getLevel
    *    prompt for user to select level
    *    call initGame in super
    *    display dialog
    *    get level input from dialog
    *    return level
    * END getLevel
    **********************************************************/
   private char getLevel() {
      //Local Constants
      
      //Local Variables
      char level = "E".charAt(0);
      ArrayList<HashMap<String, String>> levelList = Hangman.getLevelList();
      Object[] options = new Object[levelList.size()];
      /********************     *****************/

      //FOR
      for (int i = 0; i < levelList.size(); i++) {
         //get HashMap from list at index i
         HashMap<String, String> map = levelList.get(i);
         //pass label value to option array
         options[i] = map.get("label");
      }

      //create and display instant of dialog
      int result = JOptionPane.showOptionDialog(
            null, // parent Component
            "Please select level of difficulty", // message to display in dialog
            "Select Level", // title for dialog
            JOptionPane.YES_NO_CANCEL_OPTION, // yes no buttons
            JOptionPane.QUESTION_MESSAGE,
            null, // no icon
            options, // button titles
            options[0]);

      //get the user input from dialog
      HashMap<String, String> levelObj = levelList.get(result);

      //get level value from HashMap
      level = levelObj.get("value").charAt(0);

      //return level
      return level;
   }

   /**********************************************************
    * Method Name : initWindowLayout
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method initializes the content of the Hangman Game GUI
    * BEGIN initWindowLayout
    *    setGamePanelLayout
    *    initialize hangmanLabel
    *    initialize progressField
    *    set centerPanel layout
    *    add hangmanLabel to centerPanel
    *    add progressField to centerPanel
    *    set layout for northPanel
    *    add helpText to northPanel
    *    set southPanel layout to grid layout
    *    set border for centerPanel
    *    set border for southPanel
    *    add northPanel to gamePanel
    *    add centerPanel to gamePanel
    *    add southPanel to gamePanel
    *    add gamePanel to gameWindow
    *    revalidate content of gameWindow
    *    call initKeyboard
    *    call updateHangmanImage
    *    call displayInitialMessage
    * END initWindowLayout
    **********************************************************/
   private void initWindowLayout() {
      //Local Constants
      
      //Local Variables
      JPanel gamePanel = new JPanel();    // panel that contains entire layout
      JPanel centerPanel = new JPanel();  // center panel for hangman image and progressField
      JPanel southPanel = new JPanel();   // sourth panel for keyboard
      JPanel northPanel = new JPanel();   // sourth panel for keyboard

      JTextArea helpText = new JTextArea(getHelpText());
      /********************     *****************/
      // set gamepanel layout to border
      gamePanel.setLayout(new BorderLayout());

      //initialize hangmanLabel
      hangmanLabel = new JLabel();
      hangmanLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      hangmanLabel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT - 200);

      //textarea for progress
      progressField = new JTextArea();
      progressField.setEditable(false);
      progressField.setFont(new Font("Serif", Font.PLAIN, 18));
      progressField.setLineWrap(true);
      progressField.setSize(WINDOW_WIDTH, 200);

      //set font for helpText
      helpText.setFont(new Font("Serif", Font.PLAIN, 18));

      //initiate center panel layout
      // centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
      centerPanel.setBackground(Color.WHITE);

      //add hangman image to center panel
      centerPanel.add(hangmanLabel);

      //add progressField to center panel
      centerPanel.add(progressField);

      //set layout for northPanel
      northPanel.setLayout(new GridLayout(1, 3));
      northPanel.setSize(WINDOW_WIDTH, 200);
      
      //add helpText to northPanel
      northPanel.add(helpText);

      // set layout for south panel
      southPanel.setLayout(new GridLayout(3, 9));

      // set border for each region of the GUI
      centerPanel.setBorder(BorderFactory.createEtchedBorder());
      southPanel.setBorder(BorderFactory.createEtchedBorder());
      
      //add center panel to game panel
      gamePanel.add(northPanel, BorderLayout.NORTH);

      //add center panel to game panel
      gamePanel.add(centerPanel, BorderLayout.CENTER);

      //add center panel to game panel
      gamePanel.add(southPanel, BorderLayout.SOUTH);

      //add game panel to gameWindow
      gameWindow.getContentPane().add(gamePanel);

      //add center panel to game panel
      gameWindow.revalidate();

      //init keyboard
      initKeyboard(southPanel);

      //update the hangman image
      updateHangmanImage();

      //display initial message in progress field
      displayInitialMessage();
   }//END init

   /**********************************************************
    * Method Name : getHelpText
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method returns instructions for playing the Hangman game
    * BEGIN getHelpText
    *    return helpText
    * END getHelpText
    **********************************************************/
   private String getHelpText() 
   {

      //Local Constants
      
      //Local Variables
      String helpText = " Instructions: Guess the hidden word by selecing a letter from the keyboard below." +
      "\n If guessed correctly the selected character will be revealed in the hidden word, incorrect guesses will " + 
      "\n reduce the number of lives you have left by one. Using up all your lives will result in a game over";
      
      /********************     *****************/
      
      //return helpText
      return helpText;
   }//END getHelpText



   /**********************************************************
    * Method Name : updateHangmanImage
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method update the displayed hangman image
    * BEGIN updateHangmanImage
    *    setGamePanelLayout
    *    initialize hangmanLabel
    *    initialize progressField
    *    set centerPanel layout
    *    add hangmanLabel to centerPanel
    *    add progressField to centerPanel
    *    set southPanel layout to grid layout
    * END updateHangmanImage
    **********************************************************/
   private void updateHangmanImage() {

      //Local Constants
      
      //Local Variables
      int liveCount = he.getLiveCount();  //the number of lives the user has left
      BufferedImage bufferedImage = null; //Image class for creating hangman image
      /********************     *****************/
      //get hangman image from local directory
      try {
         bufferedImage = ImageIO.read(new File("images/hangman" + liveCount + ".jpg"));
      } catch (Exception e) {
         // TODO: handle exception
      }

      //instantiate hangman image in GUI and set sizing
      Image image = bufferedImage.getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT - 300, Image.SCALE_DEFAULT);
      ImageIcon imageIcon = new ImageIcon(image);

      //add new hangman image to hangmanLabel
      hangmanLabel.setIcon(imageIcon);
   }

   /**********************************************************
    * Method Name : updateHangmanImage
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method updates the displayed image in the progressField
    * BEGIN updateHangmanImage
    *    build message
    *    set text in progressField with message
    *    IF gameOver
    *       call gameOver method
    *    END IF
    * END updateHangmanImage
    **********************************************************/
   private void displayResult() {
      //Local Constants
      
      //Local Variables
      String hint = ""; //resulting message to display in progressField
      /********************     *****************/

      //building the displayed message
      hint = hint + "  Number of tries: " + he.getGuessCount() + ",";
      hint = hint + " you have " + he.getLiveCount() + " tries left";
      hint = hint + "\n  Letters you have entered are: \n" + he.getUserInput();
      hint = hint + "\n  Word to guess (" + he.getMaskWord().length() + " letters): " + he.getMaskWord();

      //update text in progressField
      progressField.setText(hint);

      //if gameOver call gameOver method
      if (he.getGameOverFlag()) {
         gameOver();
      }
   }

   /**********************************************************
    * Method Name : displayInitialMessage
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method updates the displayed image in the progressField at game start
    * BEGIN displayInitialMessage
    *    build message
    *    set text in progressField
    * END displayInitialMessage
    **********************************************************/
   private void displayInitialMessage() {
      //Local Constants
      
      //Local Variables
      String hint = ""; //resulting message to display in progressField
      /********************     *****************/
      //build the initial message to be displayed in progressField
      hint = hint + "  You have " + he.getLiveCount() + " total tries, good luck!";
      hint = hint + "\n  The word to guess is " + he.getWord().length() + " letters long";

      //update text in progressField
      progressField.setText(hint);
   }

   /**********************************************************
    * Method Name : gameOver
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method displays a game over message followed by the actual
    *    word the user was guessing, then provides user with the option to quit
    *    or start a new game
    * BEGIN gameOver
    *    build message
    *    set text in progressField
    * END gameOver
    **********************************************************/
   private void gameOver() {
      // Local Constants
      final String TITLE = "Game Over";

      // Local Variables
      String message = new String();               //message to display in window
      Object[] options = { "Play again", "Quit" }; //options to display in window

      /******************************** */

      //if user's liveCount is 0
      if (he.getLiveCount() == 0) {
         //add lose message
         message = "Game over! The word was " + "\"" + he.getWord() + "\"";
      } else {

         //add victory message
         message = "Congrats - you guessed the whole word!.";
      }

      //create and display instant of dialog
      int result = JOptionPane.showOptionDialog(
            gameWindow, // parent Component
            message, // message to display in dialog
            TITLE, // title for dialog
            JOptionPane.YES_NO_OPTION, // yes no buttons
            JOptionPane.QUESTION_MESSAGE,
            null, // no icon
            options, // button titles
            options[0]);

      //IF 
      if (result == JOptionPane.YES_OPTION) {
         restartGame();
      } else {
         closeWindow();
      }
   }

   /**********************************************************
    * Method Name : closeWindow
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for closing the Hangman GUI window
    * BEGIN closeWindow
    *    close gameWindow
    * END closeWindow
    **********************************************************/
   private void closeWindow() {
      // Local Constants

      // Local Variables

      /******************************** */
      gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
   }

   /**********************************************************
    * Method Name : initKeyboard
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for initializing the kayboard displayed in the Hangman game
    * BEGIN initKeyboard
    *    
    * END initKeyboard
    **********************************************************/
   private void initKeyboard(JPanel southPanel) {
      // Local Constants

      // Local Variables

      /******************************** */
      //initialize keyboard array
      keyboardArray = new JButton[26];

      for (int i = 0; i < ALPHABET.length(); i++) {
         //get character from alphabet string
         String temp = Character.toString(ALPHABET.charAt(i));

         //create button
         JButton button = new JButton(temp);

         //add 
         button.addActionListener(new KeyboardListener());
         keyboardArray[i] = button;
         southPanel.add(button);
      }
   }

   /**********************************************************
    * Method Name : initKeyboard
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for initializing the kayboard displayed in the Hangman game
    * BEGIN initKeyboard
    *    
    * END initKeyboard
    **********************************************************/
   private JButton getButton(Object object) {
      // Local Constants

      // Local Variables
      JButton button = null;

      /******************************** */
      for (JButton btn : keyboardArray) {
         if (btn == object) {
            button = btn;
         }
      }
      return button;
   }

   private class KeyboardListener implements ActionListener 
   {
      /**********************************************************
    * Method Name : initKeyboard
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for initializing the kayboard displayed in the Hangman game
    * BEGIN initKeyboard
    *    
    * END initKeyboard
    **********************************************************/
      public void actionPerformed(ActionEvent event) {
         // Local Constants

         // Local Variables

         /******************************** */
         if (!he.getGameOverFlag()) 
         {
            JButton button = getButton(event.getSource());
            char letter = button.getText().charAt(0);

            guess(letter);
            button.setEnabled(false);
            updateHangmanImage();
            displayResult();
         }
      }
   }

   private class HelpButtonListener implements ActionListener 
   {
      /**********************************************************
    * Method Name : initKeyboard
    * Author : Eugene Sun
    * Date : 2023.04.04
    * Course/Section : CSC 264-301
    * Method Description: This method is for initializing the kayboard displayed in the Hangman game
    * BEGIN initKeyboard
    *    
    * END initKeyboard
    **********************************************************/
      public void actionPerformed(ActionEvent event) {
         // Local Constants

         // Local Variables

         /******************************** */

      }
   }
}
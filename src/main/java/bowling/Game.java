
package bowling;

import java.util.Arrays;
import java.util.List;

public class Game {

   private static final String STRIKE = "X";
   private static final String SPARE = "/";

   /**
    * @param frame is the frame in which we want the first throw
    * @return the value of the first throw of the frame.
    */
   static int getFirstThrow( String frame ) {
      String [] throwz = frame.split( "" );
      return Integer.valueOf( throwz[0] ); 
   }

   /**
    * @param tenthFrame is the tenth frame of the game.
    * @param frames are the game frames. We will need these if the player
    *        gets a spare or a strike in the frame.
    * @return the score from the tenth frame. The tenth frame is different 
    *         as outlined by the game rules, so we need a new method to manage the case.
    */
   static int calculateTenthFrame( String tenthFrame, List<String> frames ) {

      int score = 0;

      if ( tenthFrame.equals( STRIKE ) ) {

         score = 10;

         String extraThrows = frames.get( 10 );
         for ( String extraThrow : extraThrows.split( "" ) ) {
            if ( extraThrow.equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( extraThrow );
            }
         }
      } else {
         String [] throwz = tenthFrame.split( "" );
         if ( throwz[1].equals( SPARE ) ) {
            score = score + 10;
            String extraThrow = frames.get( 10 );
            if ( extraThrow.equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( extraThrow );
            }
         } else {
            score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
         }
      }

      return score;
   }

   /**
    * @param ninthFrame is the ninth frame of the game.
    * @param tenthFrame is the tenth frame of the game. We will need this one if there 
    *        is a spare or strike in the ninth frame.
    * @param frames is all the frames of the game.. We will need this if there is a strike
    *        in the ninth and tenth frame.
    * @return the score from the ninth frame.
    */
   static int calculateFrame( int index, String frame, List<String> frames ) {
      
      int score = 0;

      if ( frame.equals( STRIKE ) ) {

         score = 10;
         int tfIndex = index + 2;
         String nextFrame = frames.get( index + 1 );

         if ( nextFrame.equals( STRIKE ) ) {
            score = score + 10;
            String [] extraThrows = frames.get( tfIndex ).split( "" );
            if ( extraThrows[0].equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( extraThrows[0] );
            }
         } else {
            String [] throwz = nextFrame.split( "" );
            if ( throwz[1].equals( SPARE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
            }
         }
      } else {
         String [] throwz = frame.split( "" );
         if ( throwz[1].equals( SPARE ) ) {
            
            score = 10;
            String nextFrame = frames.get( index + 1 );

            if ( nextFrame.equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + getFirstThrow( nextFrame );
            }
         } else {
            score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
         }
      }

      return score;
   }

   /**
    * The string the represents the game.
    */
   private String game;

   /**
    * getFrames() breaks a hyphen delimited string up into a List for
    * processing.
    */
   private List<String> getFrames() {
      List<String> frames = Arrays.asList( this.game.split( "-" ) );
      return frames;
   }

   /**
    * Constructor that sets the state for the instance.
    */
   public Game( String game ) {
      this.game = game;
   }

   /**
    * @return the score from the bowling game.
    */
   public int getFinalScore() {

      int score = 0;
      boolean spare = false;
      List<String> frames = getFrames();
      for ( int i=0; i<9; i++ ) {
         String frame = frames.get( i );
         score = score + calculateFrame( i, frame, frames );
      }

      String tenthFrame = frames.get( 9 );
      int tenthFrameTotal = calculateTenthFrame( tenthFrame, frames );
      score = score + tenthFrameTotal;

      return score;
   }
}

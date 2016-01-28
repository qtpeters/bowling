
package bowling;

import java.util.Arrays;
import java.util.List;

public class Game {

   private String game;

   private List<String> getFrames() {
      List<String> frames = Arrays.asList( this.game.split( "-" ) );
      return frames;
   }

   public Game( String game ) {
      this.game = game;
   }

   public int getFinalScore() {

      int score = 0;
      int strike = 0;
      boolean spare = false;
      for ( String frame : getFrames() ) {
         if ( frame.equals( "X" ) ) {
               //System.out.println( "\t" + frame );
               score += 10;
               if ( strike != 0 ) {
                  strike--;
               } else {
                  strike = 2;
               }

         } else {
            String [] thrs = frame.split( "" );
            for ( String thr : thrs ) {
               //System.out.println( "\t" + thr );
               if ( strike != 0 ) {
                  strike--;
                  total = 0; 
               } else {
                  strike = 2;
               }

            }
         }
      }

      return 0;
   }
}

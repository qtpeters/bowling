
package bowling;

import java.util.Arrays;
import java.util.List;

public class Game {

   private static final String STRIKE = "X";
   private static final String SPARE = "/";

   private String game;

   private List<String> getFrames() {
      List<String> frames = Arrays.asList( this.game.split( "-" ) );
      return frames;
   }

   private int getNextFrameTotal( String frame, String framep1 ) {
      
      int total = 0;

      String [] throwz = frame.split( "" );
      if ( throwz[1].equals( SPARE ) ) {
         String [] throwzp1 = framep1.split( "" );
         total = Integer.valueOf( throwz[0] ) + 10;
      } else {
         total = Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
      }

      return total;
   }

   private int getFirstThrow( String framep2 ) {
      String [] throwz = framep2.split( "" );
      return Integer.valueOf( throwz[0] ); 
   }

   private int getFrameTotal( String frame, String framep1, String framep2 ) {

      int total = 0;

      if ( frame.equals( STRIKE ) ) {
         if ( framep1.equals( STRIKE ) ) {
            if ( framep2.equals( STRIKE ) ) {
               total = 10 + 10 + 10;
            } else {
               total = 10 + 10 + getFirstThrow( framep2 );
            }
         } else {
            total = 10 + getNextFrameTotal( frame, framep1 );
         }
      } else {
         total = getNextFrameTotal( frame, framep1 );
      }

      return total;
   }

   static int doTenthFrameStrike( String extraThrows ) {
     
      int score = 0;

      for ( String extraThrow : extraThrows.split( "" ) ) {
         if ( extraThrow.equals( STRIKE ) ) {
            score = score + 10;
         } else {
            score = score + Integer.valueOf( extraThrow );
         }
      }

      return score * 2 + 10;
   }

   static int doTenthFrameNoStrike( String tenthFrame, List<String> frames  ) {
      
      int score = 0;

      String [] throwz = tenthFrame.split( "" );
      if ( throwz[1].equals( SPARE ) ) {
         String extraThrow = frames.get( 10 );
         if ( extraThrow.equals( STRIKE ) ) {
            score = score + 10;
         } else {
            score = score + Integer.valueOf( extraThrow );
         }
      } else {
         score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
      }

      return score;
   }

   static int calculateTenthFrame( String tenthFrame, List<String> frames ) {

      int score = 0;

      if ( tenthFrame.equals( STRIKE ) ) {
         score = score + 10;
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

   static int calculateNinthFrame( String ninthFrame, String tenthFrame, List<String> frames ) {
      
      int score = 0;
      
      if ( ninthFrame.equals( STRIKE ) ) {
         score = score + 10;
         if ( tenthFrame.equals( STRIKE ) ) {
            score = score + 10;
            String [] extraThrows = frames.get( 10 ).split( "" );
            if ( extraThrows[0].equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( extraThrows[0] );
            }
         } else {
            String [] throwz = tenthFrame.split( "" );
            if ( throwz[1].equals( SPARE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
            }
         }
      } else {
         String [] throwz = ninthFrame.split( "" );
         if ( throwz[1].equals( SPARE ) ) {
            
            score = 10;

            if ( tenthFrame.equals( STRIKE ) ) {
               score = score + 10;
            } else {
               score = score + Integer.valueOf( tenthFrame.split( "" )[0] );
            }
         } else {
            score = score + Integer.valueOf( throwz[0] ) + Integer.valueOf( throwz[1] );
         }
      }

      return score;
   }

   public Game( String game ) {
      this.game = game;
   }

   public int getFinalScore() {

      int score = 0;
      boolean spare = false;
      List<String> frames = getFrames();
      for ( int i=0; i<8; i++ ) {
         String frame = frames.get( i );
         String framep1 = frames.get( i+1 );
         String framep2 = frames.get( i+2 );

         int frameTotal = getFrameTotal( frame, framep1, framep2 );

         score = score + frameTotal;
      }

      String ninthFrame = frames.get( 8 );
      String tenthFrame = frames.get( 9 );
      int ninthFrameTotal = calculateNinthFrame( ninthFrame, tenthFrame, frames );
      int tenthFrameTotal = calculateTenthFrame( tenthFrame, frames );
      score = score + ninthFrameTotal + tenthFrameTotal;

      return score;
   }
}

package bowling;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Solution {

   public static void main( String [] args ) {

      try ( BufferedReader reader = new BufferedReader(
               new InputStreamReader( System.in ) ) )  {

         String line = null;
         while ( ( line = reader.readLine() ) != null ) {
            System.out.println( "TEST CASE ->" + line + "<-" );
            Game game = new Game( line );
            System.out.println( "FINAL SCORE: " + game.getFinalScore() );
            System.out.println();
         }

         reader.close();

      } catch ( Exception e ) {
         e.printStackTrace();
      }   
   }
}  

package bowling;

import static org.junit.Assert.*;
import static java.util.Collections.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class GameTest {

   @Test
   public void testCalculateTenthFrame() {
      
      int result = 0;
      String tenthFrame = "";
      List<String> frames = new ArrayList<String>();
      for ( int i=0; i<20; i++ ) {
         frames.add( i, new String( "" ) );
      }

      // Two numbers
      tenthFrame = "45";
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 9, result );

      // Number with a spare, extra throw strike
      tenthFrame = "6/";
      frames.add( 10, "X" );
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 20, result );

      // Number with a spare, extra throw number
      tenthFrame = "4/";
      frames.add( 10, "6" );
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 16, result );

      // Strike, two numbers for extra throws
      tenthFrame = "X";
      frames.add( 10, "56" );
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 21, result );
      
      // Strike, strike and number for extra throw
      tenthFrame = "X";
      frames.add( 10, "X6" );
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 26, result );
      
      // Strike, two strikes for extra throws 
      tenthFrame = "X";
      frames.add( 10, "XX" );
      result = Game.calculateTenthFrame( tenthFrame, frames );
      assertEquals( 30, result );

   }

   @Test
   public void testCalculateNinthFrame() {
      
      int result = 0;
      String ninthFrame = "";
      String tenthFrame = "";
      List<String> frames = new ArrayList<String>();      
      for ( int i=0; i<20; i++ ) {
         frames.add( i, new String( "" ) );
      }

      // Two numbers
      ninthFrame = "45";
      result = Game.calculateNinthFrame( ninthFrame, tenthFrame, frames );
      assertEquals( 9, result );

      // With a spare
      ninthFrame = "2/";
      tenthFrame = "63";
      result = Game.calculateNinthFrame( ninthFrame, tenthFrame, frames );
      assertEquals( 16, result );

   }
}


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
   public void testCalculateFrame() {
      
      int result = 0;
      int index = 0;
      String frame = "";
      String tenthFrame = "";
      List<String> frames = new ArrayList<String>();      
      frames.add( 0, "" );

      // Two numbers
      frame = "45";
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 9, result );

      // With a spare
      frame = "2/";
      frames.add( 1, "63" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 16, result );

      // With a spare and a strike in the next frame
      frame = "2/";
      frames.add( 1, "X" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 20, result );

      // With a strike and numbers in the next frame
      frame = "X";
      frames.add( 1, "45" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 19, result );

      // With a strike and a spares in the next frame
      frame = "X";
      frames.add( 1, "4/" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 20, result );

      // With a strike and a strike in the next frame
      frame = "X";
      frames.add( 1, "X" );
      frames.add( 2, "45" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 24, result );

      // With a strike and a strike in the tenth frame and the extra throw
      frame = "X";
      frames.add( 1, "X" );
      frames.add( 2, "XX" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 30, result );

      // With a strike and a strike in the next frame and the frame after
      frame = "X";
      frames.add( 1, "X" );
      frames.add( 2, "X" );
      result = Game.calculateFrame( index, frame, frames );
      assertEquals( 30, result );

   }

   @Test
   public void testBowling() {

      Game game = new Game( "X-X-X-X-X-X-X-X-X-X-XX" );
      assertEquals( 300, game.getFinalScore() );

      game = new Game( "45-54-36-27-09-63-81-18-90-72" );
      assertEquals( 90, game.getFinalScore() );

      game = new Game( "5/-5/-5/-5/-5/-5/-5/-5/-5/-5/-5" );
      assertEquals( 150, game.getFinalScore() );
   }
}


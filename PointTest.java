package Entity;

	import java.util.*;
	import static org.junit.Assert.assertEquals;

	import org.junit.Assert;
	import org.junit.Test;

	public class PointTest {

	  @Test
	    public void constructor_Test(){
	    Point testPoint = new Point(0,0);
	    int expectedX = 0;
	    int expectedY = 0;
	    assertEquals("Expected initial x value to be 0", expectedX,  testPoint.getX());
	    assertEquals("Expected initial y value to be 0", expectedY,  testPoint.getY());
	  }


	  @Test
	    public void setX_Test(){
	    Point actual = new Point(5,0);
	    int expectedX = 5;
	    assertEquals("Expected 5", expectedX, actual.getX());
	  }


	  @Test
	    public void setY_Test(){
	    Point actual = new Point(0,10);
	    int expectedY = 10 ;
	  assertEquals("Expected 10", expectedY, actual.getY());
	  }




	}



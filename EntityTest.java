
import java.util.*;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class EntityTest {


@Test
  public void constructor_Test(){
  Entity testEntity = new Entity("aEntity", 5.0, 10.0);
  String expectedName = "aEntity";
  double expectedX = 5.0;
  double expectedY = 10.0;
  assertEquals("Expected id to be aEntity", expectedName, testEntity.getId());
  assertEquals("Expected initial x value to be 5", expectedX,  testEntity.getX(), 0);
  assertEquals("Expected initial y value to be 10", expectedY,  testEntity.getY(), 0);
}

@Test
  public void setId_Test(){
  Entity actual = new Entity("aEntity", 0, 0);
  String expectedName = "newID";
  actual.setId("newID");
  Assert.assertTrue(actual.getId().equals(expectedName));
}

@Test
  public void setX_Test(){
  Entity actual = new Entity("aEntity", 0, 0);
  double expectedX = 5.0;
  actual.setX(expectedX);
  assertEquals("Expected 5", expectedX, actual.getX(), 0);
  
}

@Test
  public void setY_Test(){
  Entity actual = new Entity("aEntity", 0, 0);
  double expectedY = 10.0;
  actual.setY(expectedY);
  assertEquals("Expected initial y value to be 10", expectedY, actual.getY(), 0);
}

@Test
  public void move_Test(){
  Entity actual = new Entity("aEntity", 0, 0);
  double expectedDisX = 1;
  double expectedDisY = 2;
  actual.move(expectedDisX, expectedDisY);
  assertEquals("Expected initial x value to be 1", expectedDisX,  actual.getX(), 0);
  assertEquals("Expected initial y value to be 2", expectedDisY,  actual.getY(), 0);
}








}





























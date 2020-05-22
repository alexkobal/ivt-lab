package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore storeOneMock;
  private TorpedoStore storeTwoMock;

  @BeforeEach
  public void init(){
    storeOneMock = mock(TorpedoStore.class);
    storeTwoMock = mock(TorpedoStore.class);
    this.ship = new GT4500(storeOneMock, storeTwoMock);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(storeOneMock.isEmpty()).thenReturn(false);
    when(storeOneMock.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(storeOneMock, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(storeOneMock.isEmpty()).thenReturn(false);
    when(storeTwoMock.isEmpty()).thenReturn(false);
    when(storeOneMock.fire(1)).thenReturn(true);
    when(storeTwoMock.fire(2)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(storeOneMock, times(1)).fire(1);
    verify(storeTwoMock, times(1)).fire(2);
  }

  @Test
  public void FireTorpedoAlternateTvice(){
    //Arrange
    when(storeOneMock.isEmpty()).thenReturn(false);
    when(storeTwoMock.isEmpty()).thenReturn(false);
    when(storeOneMock.fire(1)).thenReturn(true);
    when(storeTwoMock.fire(1)).thenReturn(true);

    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(true, result);
    verify(storeOneMock, times(1)).fire(1);
    verify(storeTwoMock, times(1)).fire(1);
  }

  @Test
  public void FireTorpedoAlternateThreeTimes(){
        //Arrange
        when(storeOneMock.isEmpty()).thenReturn(false);
        when(storeTwoMock.isEmpty()).thenReturn(false);
        when(storeOneMock.fire(1)).thenReturn(true);
        when(storeTwoMock.fire(1)).thenReturn(true);
    
        //Act
        ship.fireTorpedo(FiringMode.SINGLE);
        ship.fireTorpedo(FiringMode.SINGLE);
        boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    
        //Assert
        assertEquals(true, result);
        verify(storeOneMock, times(2)).fire(1);
        verify(storeTwoMock, times(1)).fire(1);
  }

  @Test
  public void FireTorpedoFirstTwiceSecondEmpty(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(true);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(1)).thenReturn(true);		
		//Act
		ship.fireTorpedo(FiringMode.SINGLE);
		boolean result = ship.fireTorpedo(FiringMode.SINGLE);		
		//Assert
		assertEquals(true, result);
		verify(storeOneMock, times(2)).fire(1);
		verify(storeTwoMock, times(0)).fire(1);
  }

  @Test
  public void FireTorpedoFirstEmptySecondOnce(){
	  	//Arrange
		when(storeOneMock.isEmpty()).thenReturn(true);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(1)).thenReturn(true);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.SINGLE);		
		//Assert
		assertEquals(true, result);
		verify(storeOneMock, times(0)).fire(1);
		verify(storeTwoMock, times(1)).fire(1);
  }

  @Test
  public void FireTorpedoTwiceFirstFails(){
	  	//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(false);
		when(storeTwoMock.fire(1)).thenReturn(true);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.SINGLE);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(1)).fire(1);
		verify(storeTwoMock, times(0)).fire(1);
  }

  @Test
  public void FireBothTorpedsFirstIsEmpty(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(true);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(2)).thenReturn(true);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.ALL);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(0)).fire(1);
		verify(storeTwoMock, times(0)).fire(2);
  }

  @Test
  public void FirstLastTimeBothEmpty(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(true);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(2)).thenReturn(true);		
		//Act
		ship.fireTorpedo(FiringMode.SINGLE);
		when(storeOneMock.isEmpty()).thenReturn(true);
		boolean result = ship.fireTorpedo(FiringMode.SINGLE);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(1)).fire(1);
		verify(storeTwoMock, times(0)).fire(1);
  }

  @Test
  public void SecondLastTimeBothEmpty(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(true);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(2)).thenReturn(true);		
		//Act
		ship.fireTorpedo(FiringMode.SINGLE);
		when(storeTwoMock.isEmpty()).thenReturn(true);
		boolean result = ship.fireTorpedo(FiringMode.SINGLE);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(0)).fire(1);
		verify(storeTwoMock, times(1)).fire(1);
  }

  @Test
  public void FireBothTorpedsSecondIsEmpty(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(true);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(2)).thenReturn(true);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.ALL);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(0)).fire(1);
		verify(storeTwoMock, times(0)).fire(2);
  }

  @Test
  public void BadFiringMode(){
	  //Arrange

	  //Act
	  boolean result = ship.fireTorpedo(FiringMode.ELSE);

	  //Assert
	  assertEquals(false, result);
  }

  @Test
  public void FireBothTorpedsSecondFails(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(true);
		when(storeTwoMock.fire(2)).thenReturn(false);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.ALL);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(1)).fire(1);
		verify(storeTwoMock, times(1)).fire(2);
  }

  @Test
  public void FireBothTorpedsFirstFails(){
		//Arrange
		when(storeOneMock.isEmpty()).thenReturn(false);
		when(storeTwoMock.isEmpty()).thenReturn(false);
		when(storeOneMock.fire(1)).thenReturn(false);
		when(storeTwoMock.fire(2)).thenReturn(true);		
		//Act
		boolean result = ship.fireTorpedo(FiringMode.ALL);		
		//Assert
		assertEquals(false, result);
		verify(storeOneMock, times(1)).fire(1);
		verify(storeTwoMock, times(0)).fire(2);
  }
}

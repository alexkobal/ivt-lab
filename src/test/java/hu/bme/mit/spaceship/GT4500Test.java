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

}

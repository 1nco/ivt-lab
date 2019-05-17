package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockPrimary;
  private TorpedoStore mockSecondary;
  @BeforeEach
  public void init(){
    mockPrimary = mock(TorpedoStore.class);
    mockSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).fire(1);
    // Assert
    assertEquals(true, result);
  }


  @Test
  public void fireTorpedo_Single_Alternating(){
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).fire(1);
    // Assert
    assertEquals(true, res);
  }
  @Test
  public void fireTorpedo_Single_Primary_Empty(){
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(1)).fire(1);
    assertEquals(true, result);
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(2)).fire(1);
    // Assert
    assertEquals(true, res);
  }
  @Test
  public void fireTorpedo_Single_Secondary_Empty(){
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    assertEquals(true, result);
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(2)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    // Assert
    assertEquals(true, res);
    }
  
  @Test
  public void fireTorpedo_Single_Both_Empty(){
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    assertEquals(false, result);
    boolean res = ship.fireTorpedo(FiringMode.SINGLE);
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    // Assert
    assertEquals(false, res);
    }
  @Test
  public void fireTorpedo_All_Both_Empty(){
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.fire(1)).thenReturn(true);
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    verify(mockPrimary, times(0)).fire(1);
    verify(mockSecondary, times(0)).fire(1);
    // Assert
    assertEquals(false, result);
  }
  @Test
  public void fireTorpedo_Single_failure(){
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    // Assert
    assertEquals(false, result);
    }
}


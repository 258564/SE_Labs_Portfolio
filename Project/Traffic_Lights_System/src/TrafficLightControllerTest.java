import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrafficLightControllerTest {

    private TrafficLightController controller;

    @Before
    public void setUp() {
        controller = new TrafficLightController();
    }

    @Test
    public void testInitialStateIsNorthSouthGreen() {
        assertEquals(TrafficLightController.State.NS_GREEN, controller.getState());
    }

    @Test
    public void testNsGreenAdvancesToNsYellow() {
        controller.advanceState();
        assertEquals(TrafficLightController.State.NS_YELLOW, controller.getState());
    }

    @Test
    public void testNsYellowAdvancesToEwGreen() {
        advanceTimes(2);
        assertEquals(TrafficLightController.State.EW_GREEN, controller.getState());
    }

    @Test
    public void testEwGreenAdvancesToEwYellow() {
        advanceTimes(3);
        assertEquals(TrafficLightController.State.EW_YELLOW, controller.getState());
    }

    private void advanceTimes(int n) {
        for (int i = 0; i < n; i++) {
            controller.advanceState();
        }
    }

    @Test
    public void testNsAndEwAreNeverBothGreen() {
        for (int i = 0; i < 8; i++) {
            assertFalse("Both axes are green at step " + i, controller.areBothAxesGreenSimultaneously());
            controller.advanceState();
        }
    }
}
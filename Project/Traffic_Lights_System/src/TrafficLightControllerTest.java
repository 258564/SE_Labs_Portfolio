import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrafficLightControllerTest {

    private TrafficLightController controller;

    @Before
    public void setUp() {
        controller = new TrafficLightController();
    }

    // Initial state tests

    @Test
    public void testInitialNsStateIsGreen() {
        assertEquals(TrafficLightController.LightState.GREEN, controller.getNsState());
    }

    @Test
    public void testInitialEwStateIsRed() {
        assertEquals(TrafficLightController.LightState.RED, controller.getEwState());
    }

    @Test
    public void testInitialArrowStateIsEwOn() {
        assertEquals(TrafficLightController.ArrowState.EW_ON, controller.getArrowState());
    }

    // NsGreen to NSYellow

    @Test
    public void testNsGreenAdvancesToNsYellow() {
        controller.advanceState();
        assertEquals(TrafficLightController.LightState.YELLOW, controller.getNsState());
    }

    @Test
    public void testEwStaysRedWhenNsIsYellow() {
        controller.advanceState();
        assertEquals(TrafficLightController.LightState.RED, controller.getEwState());
    }

    @Test
    public void testArrowIsOffWhenNsIsYellow() {
        controller.advanceState();
        assertEquals(TrafficLightController.ArrowState.BOTH_OFF, controller.getArrowState());
    }

    private void advanceTimes(int n) {
        for (int i = 0; i < n; i++) {
            controller.advanceState();
        }
    }

    // Ns Yellow to NsRed && EwGreen

    @Test
    public void testNsYellowAdvancesToNsRed() {
        advanceTimes(2);
        assertEquals(TrafficLightController.LightState.RED, controller.getNsState());
    }

    @Test
    public void testEwGoesYellowWhenNsBecomesRed() {
        advanceTimes(2);
        assertEquals(TrafficLightController.LightState.YELLOW, controller.getEwState());
    }

    @Test
    public void testNsArrowOnWhenEwIsGreen() {
        advanceTimes(3);
        assertEquals(TrafficLightController.ArrowState.NS_ON, controller.getArrowState());
    }


    // EwGreen to EwYellow
    @Test
    public void testEwGreenAdvancesToEwYellow() {
        advanceTimes(4);
        assertEquals(TrafficLightController.LightState.YELLOW, controller.getEwState());
    }

    @Test
    public void testNsStaysRedWhenEwIsYellow() {
        advanceTimes(4);
        assertEquals(TrafficLightController.LightState.RED, controller.getNsState());
    }

    @Test
    public void testArrowIsOffWhenEwIsYellow() {
        advanceTimes(4);
        assertEquals(TrafficLightController.ArrowState.BOTH_OFF, controller.getArrowState());
    }


    // EwYellow to EwRed && NsGreen
    @Test
    public void testEwYellowAdvancesToEwRed() {
        advanceTimes(5);
        assertEquals(TrafficLightController.LightState.RED, controller.getEwState());
        assertEquals(TrafficLightController.LightState.YELLOW, controller.getNsState());
    }

    @Test
    public void testNsGoesYellowWhenEwBecomesRed() {
        advanceTimes(5);
        assertEquals(TrafficLightController.LightState.YELLOW, controller.getNsState());
    }

    @Test
    public void testArrowStaysOffWhenNsIsYellowAgain() {
        advanceTimes(5);
        assertEquals(TrafficLightController.ArrowState.BOTH_OFF, controller.getArrowState());
    }


    @Test
    public void testNsAndEwAreNeverBothGreen() {
        for (int i = 0; i < 8; i++) {
            assertFalse("Both axes are green at step " + i, controller.areBothAxesGreenSimultaneously());controller.advanceState();
        }
    }

}
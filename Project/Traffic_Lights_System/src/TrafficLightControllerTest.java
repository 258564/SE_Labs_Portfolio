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
    public void testInitialState() {
        assertState(
                TrafficLightController.LightState.GREEN,
                TrafficLightController.LightState.RED,
                TrafficLightController.ArrowState.EW_ON);
    }

    @Test
    public void testAfterNsGreen_NsBecomesYellow() {
        advanceTimes(1);
        assertState(
                TrafficLightController.LightState.YELLOW,
                TrafficLightController.LightState.RED,
                TrafficLightController.ArrowState.BOTH_OFF);
    }

    @Test
    public void testAfterNsYellow_EwBecomesYellow() {
        advanceTimes(2);
        assertState(
                TrafficLightController.LightState.RED,
                TrafficLightController.LightState.YELLOW,
                TrafficLightController.ArrowState.BOTH_OFF);
    }

    @Test
    public void testAfterEwYellowOpen_EwBecomesGreen() {
        advanceTimes(3);
        assertState(
                TrafficLightController.LightState.RED,
                TrafficLightController.LightState.GREEN,
                TrafficLightController.ArrowState.NS_ON);
    }

    @Test
    public void testAfterEwGreen_EwBecomesYellow() {
        advanceTimes(4);
        assertState(
                TrafficLightController.LightState.RED,
                TrafficLightController.LightState.YELLOW,
                TrafficLightController.ArrowState.BOTH_OFF);
    }

    @Test
    public void testAfterEwYellowClose_NsBecomesYellow() {
        advanceTimes(5);
        assertState(
                TrafficLightController.LightState.YELLOW,
                TrafficLightController.LightState.RED,
                TrafficLightController.ArrowState.BOTH_OFF);
    }

    @Test
    public void testAfterFullCycle_NsReturnsToGreen() {
        advanceTimes(6);
        assertState(
                TrafficLightController.LightState.GREEN,
                TrafficLightController.LightState.RED,
                TrafficLightController.ArrowState.EW_ON);
    }

    // Safety tests

    @Test
    public void testNsAndEwAreNeverBothGreen() {
        for (int i = 0; i < 6; i++) {
            boolean bothGreen = controller.getNsState() == TrafficLightController.LightState.GREEN &&
                            controller.getEwState() == TrafficLightController.LightState.GREEN;
            assertFalse("Both lights are green " + i, bothGreen);
            controller.advanceState();
        }
    }

    @Test
    public void testArrowEwOnOnlyWhenNsIsGreen() {
        for (int i = 0; i < 6; i++) {
            if (controller.getArrowState() == TrafficLightController.ArrowState.EW_ON) {
                assertEquals("EW arrow is on but NS is not green " + i,
                        TrafficLightController.LightState.GREEN,
                        controller.getNsState());
            }
            controller.advanceState();
        }
    }

    @Test
    public void testArrowNsOnOnlyWhenEwIsGreen() {
        for (int i = 0; i < 6; i++) {
            if (controller.getArrowState() == TrafficLightController.ArrowState.NS_ON) {
                assertEquals("NS arrow is on but EW is not green " + i,
                        TrafficLightController.LightState.GREEN,
                        controller.getEwState());
            }
            controller.advanceState();
        }
    }

    // Helpers

    private void advanceTimes(int n) {
        for (int i = 0; i < n; i++) {
            controller.advanceState();
        }
    }

    private void assertState(
            TrafficLightController.LightState expectedNs,
            TrafficLightController.LightState expectedEw,
            TrafficLightController.ArrowState expectedArrow
    ) {
        assertEquals(expectedNs, controller.getNsState());
        assertEquals(expectedEw, controller.getEwState());
        assertEquals(expectedArrow, controller.getArrowState());
    }
}
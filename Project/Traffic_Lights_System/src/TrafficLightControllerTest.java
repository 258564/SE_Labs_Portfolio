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
    public void test() {
        controller.advanceState();
        assertEquals(TrafficLightController.State.NS_YELLOW, controller.getState());
    }
}
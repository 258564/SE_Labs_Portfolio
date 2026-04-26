public class TrafficLightController {

    public enum LightState {
        GREEN,
        YELLOW,
        RED
    }

    public enum ArrowState {
        NS_ON,
        EW_ON,
        BOTH_OFF
    }

    private LightState nsState;
    private LightState ewState;
    private ArrowState arrowState;

    public TrafficLightController() {
        this.nsState    = LightState.GREEN;
        this.ewState    = LightState.RED;
        this.arrowState = ArrowState.EW_ON;
    }

    public LightState getNsState() {
        return nsState;
    }

    public LightState getEwState() {
        return ewState;
    }

    public ArrowState getArrowState() {
        return arrowState;
    }

    public void advanceState() {
        boolean prev_ns_green = true;
        boolean prev_ew_on = false;
        if (nsState == LightState.GREEN && ewState == LightState.RED) {
            nsState    = LightState.YELLOW;
            arrowState = ArrowState.BOTH_OFF;
        } else if (nsState == LightState.YELLOW && ewState == LightState.RED && prev_ns_green == true) {
            nsState    = LightState.RED;
            ewState    = LightState.YELLOW;
            prev_ns_green = false;
        } else if (nsState == LightState.RED && ewState == LightState.YELLOW && prev_ew_on == false) {
            ewState = LightState.GREEN;
            arrowState = ArrowState.NS_ON;
            prev_ew_on = true;
        } else if (nsState == LightState.RED && ewState == LightState.GREEN) {
            ewState = LightState.YELLOW;
            arrowState = ArrowState.BOTH_OFF;
        } else if (nsState == LightState.RED && ewState == LightState.YELLOW && prev_ew_on == true) {
            ewState    = LightState.RED;
            nsState    = LightState.YELLOW;
            prev_ew_on = false;
        } else if (nsState == LightState.YELLOW && ewState == LightState.RED && prev_ns_green == false) {
            nsState    = LightState.GREEN;
            arrowState = ArrowState.EW_ON;
            prev_ns_green = true;
        }
    }

    public boolean isNorthSouthGreen() {
        return nsState == LightState.GREEN;
    }

    public boolean isEastWestGreen() {
        return ewState == LightState.GREEN;
    }

    public boolean areBothAxesGreenSimultaneously() {
        return isNorthSouthGreen() && isEastWestGreen();
    }
}
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
        if (nsState == LightState.GREEN) {
            nsState    = LightState.YELLOW;
            arrowState = ArrowState.BOTH_OFF;

        } else if (nsState == LightState.YELLOW) {
            nsState    = LightState.RED;
            ewState    = LightState.GREEN;
            arrowState = ArrowState.NS_ON;

        } else if (nsState == LightState.RED && ewState == LightState.GREEN) {
            ewState    = LightState.YELLOW;
            arrowState = ArrowState.BOTH_OFF;

        } else if (nsState == LightState.RED
                && ewState == LightState.YELLOW) {
            nsState    = LightState.GREEN;
            ewState    = LightState.RED;
            arrowState = ArrowState.EW_ON;
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
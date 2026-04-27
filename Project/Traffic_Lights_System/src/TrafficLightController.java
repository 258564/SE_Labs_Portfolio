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

    private enum CyclePhase {
        NS_GREEN,
        NS_YELLOW_TO_EW,
        EW_YELLOW_OPEN,
        EW_GREEN,
        EW_YELLOW_CLOSE,
        NS_YELLOW_TO_NS
    }

    private LightState nsState;
    private LightState ewState;
    private ArrowState arrowState;

    private CyclePhase phase = CyclePhase.NS_GREEN;

    public TrafficLightController() {
        this.nsState = LightState.GREEN;
        this.ewState = LightState.RED;
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
        switch (phase) {
            case NS_GREEN:
                nsState = LightState.YELLOW;
                arrowState = ArrowState.BOTH_OFF;
                phase = CyclePhase.NS_YELLOW_TO_EW;
                break;
            case NS_YELLOW_TO_EW:
                nsState = LightState.RED;
                ewState = LightState.YELLOW;
                phase = CyclePhase.EW_YELLOW_OPEN;
                break;
            case EW_YELLOW_OPEN:
                ewState = LightState.GREEN;
                arrowState = ArrowState.NS_ON;
                phase = CyclePhase.EW_GREEN;
                break;
            case EW_GREEN:
                ewState = LightState.YELLOW;
                arrowState = ArrowState.BOTH_OFF;
                phase = CyclePhase.EW_YELLOW_CLOSE;
                break;
            case EW_YELLOW_CLOSE:
                ewState = LightState.RED;
                nsState = LightState.YELLOW;
                phase = CyclePhase.NS_YELLOW_TO_NS;
                break;
            case NS_YELLOW_TO_NS:
                nsState = LightState.GREEN;
                arrowState = ArrowState.EW_ON;
                phase = CyclePhase.NS_GREEN;
                break;
            default:
                throw new IllegalStateException("Unexpected cycle phase: " + phase);
        }
    }
}
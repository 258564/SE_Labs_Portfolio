public class TrafficLightController {
    public enum State {
        NS_GREEN,
        NS_YELLOW,
        EW_GREEN,
        EW_YELLOW
    }
    private State current_state;

    public TrafficLightController() {
        this.current_state = TrafficLightController.State.NS_GREEN;
    }

    public State getState() {
        return current_state;
    }
    public void setState(State current_state) {
        this.current_state = current_state;
    }

    public void advanceState() {
        switch (current_state){
            case NS_GREEN:
                current_state = State.NS_YELLOW;
                break;
            case NS_YELLOW:
                current_state = State.EW_GREEN;
                break;
            case EW_GREEN:
                current_state = State.EW_YELLOW;
                break;
            case EW_YELLOW:
                current_state = State.NS_GREEN;
                break;
        }
    }

    public boolean isNorthSouthGreen() {
        return current_state == State.NS_GREEN;
    }

    public boolean isEastWestGreen() {
        return current_state == State.EW_GREEN;
    }

    public boolean areBothAxesGreenSimultaneously() {
        return isNorthSouthGreen() && isEastWestGreen();
    }
}
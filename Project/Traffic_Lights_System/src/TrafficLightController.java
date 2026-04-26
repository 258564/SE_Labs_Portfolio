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
}
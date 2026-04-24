public class Game {
    private static final int ALL_PINS = 10;
    private int[] pins = new int[21];
    private int rollCount = 0;

    public void roll (int knocked) {
        pins[rollCount++] = knocked;
    }

    public int score() {
        int total = 0;
        int ball = 0;

        for (int frame = 0; frame < ALL_PINS; frame++) {
            if (firstRollKnockedAllPins(ball)) {
                total += ALL_PINS + strikeBonus(ball);
                ball  += 1;
            } else if (twoRollsKnockedAllPins(ball)) {
                total += ALL_PINS + pins[ball + 2];
                ball  += 2;
            } else {
                total += pins[ball] + pins[ball + 1];
                ball  += 2;
            }
        }
        return total;
    }

    private boolean firstRollKnockedAllPins(int ball) {
        return pins[ball] == ALL_PINS;
    }

    private boolean twoRollsKnockedAllPins(int ball) {
        return pins[ball] + pins[ball + 1] == ALL_PINS;
    }

    private int strikeBonus(int ball) {
        return pins[ball + 1] + pins[ball + 2];
    }
}

public class Game {
    private int[] pins = new int[21];
    private int rollCount = 0;
    public void roll (int knocked) {
        pins[rollCount++] = knocked;
    }

    public int score() {
        int total = 0;
        int ball = 0;
        int frame = 0;

        while (frame < 10) {
            boolean spare = (pins[ball] + pins[ball + 1] == 10);
            if (spare) {
                total += 10 + pins[ball + 2];
                ball += 2;
            } else {
                total += pins[ball] + pins[ball + 1];
                ball += 2;
            }
            frame++;
        }
        return total;
    }
}

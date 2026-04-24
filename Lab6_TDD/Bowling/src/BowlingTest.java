import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTest {

    private Game g;

    @BeforeEach
    public void setUp() {
        g = new Game();
    }

    private void rollMany(int times, int pins) {
        for (int i = 0; i < times; i++) {
            g.roll(pins);
        }
    }

    @Test
    public void testGutterGame() {
        rollMany(20, 0);
        assertEquals(0, g.score());
    }

    @Test
    public void testForAllOnes() {
        rollMany(20, 1);
        assertEquals(20, g.score());
    }

    @Test
    public void testForSpare() {
        g.roll(4);
        g.roll(6);
        g.roll(3);
        rollMany(17, 0);
        assertEquals(16, g.score());
    }

    
}


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTest {
    @Test
    public void testGutterGame() {
        Game g = new Game();
        for (int i = 0; i < 20; i++){
            g.roll(0);
        }
        assertEquals(0, g.score());
    }
}

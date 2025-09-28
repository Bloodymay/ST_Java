import org.junit.jupiter.api.Test;

public class MathTests {
    @Test
    public void testDivideByZero() {
        var a = 1;
        var b = 1;
        var c = a / b;
        System.out.println(c);
    }
}

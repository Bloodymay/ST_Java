import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseChecks {
    @Test
    public void testSqrt() {
        var input = 5.0;
        var result = Math.sqrt(input);
        var reverse = result*result;
        Assertions.assertEquals(input, reverse, 0.000001);
    }
    @Test
    public void testSort() {
        var input = new ArrayList<>(List.of(3,7,4,9,0,10));
        input.sort(Integer::compareTo);
        for (var i = 0; i < input.size()-1; i++) {
            Assertions.assertTrue(input.get(i) <= input.get(i+1));
        }
    }
}

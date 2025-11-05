package collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionTests {
    @Test
    void arrayTests(){
        //var array = new String[]{"a", "b", "c"};
        var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "Hello";
        Assertions.assertEquals("Hello", array[0]);
        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }
    @Test
    void listTests(){

        var list = new ArrayList<>(List.of("a","b","c","d"));
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("a", list.get(0));
        list.set(0, "Pupupu");
        Assertions.assertEquals("Pupupu", list.get(0));
    }

    @Test
    void setTests(){
        var set = new HashSet<>(List.of("a","b","c", "a")) ;
        Assertions.assertEquals(3, set.size());
        //var element = set.stream().findAny().get(); получение элемента из множества
        set.add("d");
        Assertions.assertEquals(4, set.size());
    }
    @Test
    void mapTests(){
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");
        digits.put('5', "five");
        Assertions.assertEquals("one", digits.get('1'));
        digits.put('1', "four");
        Assertions.assertEquals("four", digits.get('1'));
    }
}

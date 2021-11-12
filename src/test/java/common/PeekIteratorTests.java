package common;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeekIteratorTests {

    @Test
    public void test_char(){
        var source = "abcdefg王";
        var it = source.chars();
        Iterator<Integer> iter = it.iterator();
        while(iter.hasNext()){
            int i = iter.next();
            System.out.println((char)i);
        }
        /*
        a
        b
        c
        d
        e
        f
        g
        鐜
        ?
         */
//        System.out.println(c);
//        var it = new

    }

    @Test
    public void test_peek(){
        var source = "abcdefg";
        var it = new PeekIterator<Character>(source.chars().mapToObj(c -> (char)c));

        assertEquals('a',it.next());
        assertEquals('b',it.next());
        it.next();
        it.next();
        assertEquals('e',it.next());
        assertEquals('f',it.peek());
        assertEquals('f',it.peek());
    }

    @Test
    public void test_putBack(){
        var source = "abcdefg";
        var it = new PeekIterator<Character>(source.chars().mapToObj(c -> (char)c));

        assertEquals('a',it.next());
        assertEquals('b',it.next());
        it.next();
        it.next();
        it.putBack();
        it.putBack();
        assertEquals('c',it.peek());
        assertEquals('c',it.peek());
    }

    @Test
    public void test_endToken(){
        var source = "abcdefg";
        var it = new PeekIterator<Character>(source.chars().mapToObj(c -> (char)c),'0');
        var i = 0;
        while(it.hasNext()){
            if(i == 7){
                assertEquals('0',it.next());
            }else{
                assertEquals(source.charAt(i++),it.next());
            }
        }
    }

}

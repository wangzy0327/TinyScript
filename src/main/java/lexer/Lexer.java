package lexer;

import java.util.List;
import java.util.stream.Stream;

public class Lexer {

    public List<Token> analyse(Stream source){
        var s = "abcdefg";
        var stream = s.chars().mapToObj(c -> (char)c );
        return null;
    }
}

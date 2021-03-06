package slpl.syntax.lexical;

import slpl.err.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {

    public static List<Token> lex(String programText, boolean skipLayout) {
        StringBuilder captureGroups = new StringBuilder();
        for (TokenType tokenType : TokenType.values()) {
            captureGroups.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern()));
        }
        Pattern pattern = Pattern.compile(captureGroups.substring(1));
        Matcher matcher = pattern.matcher(programText);
        ArrayList<Token> tokens = new ArrayList<>();
        int row = 1, col = 1;
        while (matcher.find()) {
            for (TokenType tokenType : TokenType.values()) {
                String capturedSubsequence = matcher.group(tokenType.name());
                if (capturedSubsequence != null) {
                    Token t = new Token(tokenType, capturedSubsequence, row, col);
                    if(tokenType == TokenType.EOL) {
                        ++row;
                        col = 1;
                    } else {
                        col += capturedSubsequence.length();
                    }
                    if (!tokenType.instanceOf(TokenTypeClass.LAYOUT) || !skipLayout) {
                        tokens.add(t);
                        break;
                    }
                }
            }
        }
        return tokens;
    }

    public static void main(String[] args) throws ParseException {

    }

}

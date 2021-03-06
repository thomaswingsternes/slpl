package slpl.syntax;

import slpl.ast.Print;
import slpl.ast.Str;
import slpl.err.ParseException;
import slpl.syntax.lexical.TokenType;
import slpl.util.TokenStream;

public class PrintParser {

    public static Print parsePrint(TokenStream ts) throws ParseException {
        ts.expect(TokenType.PRINT, TokenType.PRINTLN);
        boolean nl = ts.consume().type() == TokenType.PRINTLN;
        if(ts.hasNext(TokenType.STRING)) {
            return new Print(new Str(ts.consume().content()), nl);
        } else {
            return new Print(ExpressionParser.parseExpression(ts), nl);
        }
    }

}

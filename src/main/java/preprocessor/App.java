package preprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }


    public String insertinator(final String line) {
        String[] tokens = line.trim().split("\\s+");
        if (lastToken(tokens).equals("{")
                && isAtom(tokens[0])
                && missingParen(tokens[1])) {

            String stepName = tokens[0];
            String oldSnippet = line.substring(line.indexOf(stepName), line.lastIndexOf("{"));
            String args = line.substring(line.indexOf(stepName) + stepName.length(), line.lastIndexOf("{")).trim();
            if (args.equals("")) {
                return line;
            } else {
                String newSnippet = tokens[0] + "(" + args + ") ";
                return line.replace(oldSnippet, newSnippet);
            }


        } else {
            return line;
        }


    }

    public String preprocessPipeline(String pipeline) throws IOException {
        BufferedReader bufReader = new BufferedReader(new StringReader(pipeline));
        StringBuffer out = new StringBuffer();
        String line=null;
        while( (line=bufReader.readLine()) != null ) {
            String newLine = insertinator(line);
            out.append(newLine);
            out.append("\n");
        }
        return out.toString();
    }

    private boolean missingParen(String token) {
        return !token.startsWith("(");
    }

    private String lastToken(String[] tokens) {
        return tokens[tokens.length - 1];
    }

    private boolean isAtom(String token) {
        return token.matches("[a-zA-Z]+");
    }
}

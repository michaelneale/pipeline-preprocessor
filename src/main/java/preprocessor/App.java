package preprocessor;

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

    private boolean missingParen(String token) {
        return !token.startsWith("(");
    }

    private String lastToken(String[] tokens) {
        return tokens[tokens.length - 1];
    }


    private boolean isAtom(String token) {
        if (token.contains("(") && Character.isLetter(token.charAt(0))) {
            return false;
        } else {
            return true;
        }
    }
}

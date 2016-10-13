package seedu.address.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    public static boolean containsIgnoreCase(String source, String query) {
        List<String> strings = listifyString(source);
        return strings.stream().filter(s -> s.equals(query.toLowerCase())).count() > 0;
    }

    public static boolean containsSubstringIgnoreCase(String source, String query) {
        List<String> strings = listifyString(source);
        return strings.stream().filter(s -> s.contains(query.toLowerCase())).count() > 0;
    }

    private static List<String> listifyString(String source) {
        return Arrays.asList(source.toLowerCase().split("\\s+"));
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t){
        assert t != null;
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if s represents an unsigned integer e.g. 1, 2, 3, ... <br>
     *   Will return false for null, empty string, "-1", "0", "+1", and " 2 " (untrimmed) "3 0" (contains whitespace).
     * @param s Should be trimmed.
     */
    public static boolean isUnsignedInteger(String s){
        return s != null && s.matches("^0*[1-9]\\d*$");
    }
}

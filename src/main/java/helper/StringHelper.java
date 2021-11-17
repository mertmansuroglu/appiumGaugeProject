package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringHelper {
    Logger log = LogManager.getLogger(StringHelper.class);


    /**
     * o,u
     *
     * @param originalText Original text to be replaced
     * @param oldPart      The part of the text to be replaced
     * @param replacement  New part of text to replace
     * @param onlyFirst    Will all equal part of the text be change?
     * @return The new text after replacement
     */
    protected String replace(String originalText, String oldPart, String replacement, boolean... onlyFirst) {
        if (oldPart.length() == 1) {
            //loves my body= replace(o,e)   mesquito cellar
            //(o,u)=== luves my budy
            return originalText.replace(oldPart.charAt(0), replacement.charAt(0));
//            Params:
//regex – the regular expression to which this string is to be matched
//replacement – the string to be substituted for the first match
//            str.replaceFirst(regex, repl)
        } else if (onlyFirst.length > 0 && onlyFirst[0]) {
            return originalText.replaceFirst(oldPart, replacement);
        } else if (isItRegex(oldPart)) {
            return originalText.replaceAll(oldPart, replacement);
        } else {
            return originalText.replace(oldPart, replacement);
        }
    }

    private boolean isItRegex(String input) {
        try {
            Pattern.compile(input);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    protected String subString(String text, int firstIndex, int... lastIndex) {

        if (lastIndex != null && lastIndex.length == 1) {
            return text.substring(firstIndex, lastIndex[0]);
        } else if (lastIndex.length == 0) {
            return text.substring(firstIndex);
        } else {
            log.warn("You send lastIndex param more then once. Online first could be use");
            return text.substring(firstIndex, lastIndex[0]);
        }

    }
}

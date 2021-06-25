package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserIdParser {

    private static final String DESCRIPTION_STRUCTURE_REGEX = "The user with id '([0-9]+)' .*";
    private static final Pattern PATTERN = Pattern.compile(DESCRIPTION_STRUCTURE_REGEX);

    public static String deriveUserIdFromEventDescription(String description) {
        Matcher matcher = PATTERN.matcher(description);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Cannot derive user ID.");
    }
}

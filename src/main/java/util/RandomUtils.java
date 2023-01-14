package util;

import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    private static final String[] roles = new String[]{"DRIVER", "LIFEGUARD", "GUIDE", "GUARDIAN", "TRANSLATOR", "ANIMATOR", "RESIDENT", "MANAGER"};
    private static final String[] languages = new String[]{"POLISH", "ENGLISH", "FRENCH", "SPANISH", "GERMAN", "RUSSIAN", "CHINESE", "ITALIAN", "GREEK", "PORTUGUESE"};
    private static final String[] levels = new String[]{"A1", "A2", "B1", "B2", "C1", "C2"};
    private static final String[] transportTypes = new String[]{"PLANE", "BUS", "SHIP", "BIKE", "ON FOOT", "ROCKET", "TRAIN", "SKATEBOARD", "SUBMARINE"};

    public static String randomLang() {
        return languages[randomInt(languages.length)];
    }

    public static String randomLevel() {
        return levels[randomInt(levels.length)];
    }

    public static String randomTransportType() {
        return transportTypes[randomInt(transportTypes.length)];
    }

    public static String randomRole() {
        return roles[randomInt(roles.length)];
    }

    public static boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static Integer randomInt(int max) {
        return new Random().nextInt(max);
    }
}

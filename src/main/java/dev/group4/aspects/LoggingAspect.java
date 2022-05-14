package dev.group4.aspects;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LoggingAspect {

    public static void LogError(Throwable e) {
        String path = System.getProperty("user.dir") + "//potluck.log";
        File file = new File(path);
        try {
            file.createNewFile(); // attempt to create the file if not found

            Files.write(Paths.get(path),
                    e.getMessage().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println("Can't connect to logger");
        }
    }
}
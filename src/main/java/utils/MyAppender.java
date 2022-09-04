package utils;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class MyAppender {

    public static Appender getFileAppender(String file) {
        Layout layout = PatternLayout.newBuilder().withPattern("%m%n").build();
        FileAppender fileAppender = FileAppender.newBuilder().setName("test")
                .setLayout(layout)
                .withFileName(file)
                .withAppend(true)
                .build();
        return fileAppender;
    }
}

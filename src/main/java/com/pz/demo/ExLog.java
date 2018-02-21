package com.pz.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class ExLog {
    private static ExLog ourInstance = new ExLog();
    private static Logger logger;

    public static ExLog getInstance() {
        return ourInstance;
    }

    private ExLog() {

    }
    public void start(){
        logger = Logger.getLogger("Log");
        FileHandler fh;
        try {
            fh = new FileHandler("log.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addError(String message){
        logger.info(message);
    }
}

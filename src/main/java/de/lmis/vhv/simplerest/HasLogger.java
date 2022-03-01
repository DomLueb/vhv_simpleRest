package de.lmis.vhv.simplerest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

public interface HasLogger {

    /**
     * Gets the logger for the current class. Identifies the user class if the class is wrapped by a proxy.
     *
     * @return Logger for the current user class.
     */
    default Logger logger() {
        Class<?> userClass = ClassUtils.getUserClass(getClass());
        return LoggerFactory.getLogger(userClass);
    }
}
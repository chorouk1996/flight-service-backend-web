package com.service.backend.web.services.helper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class UtilHelper {

    private UtilHelper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }


    public static <T> void updateIfNotNull(Consumer<T> setAircraftType, Supplier<T> getAircraftType) {
        if(getAircraftType.get()!= null){
            setAircraftType.accept(getAircraftType.get());
        }
    }
}

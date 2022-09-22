package com.cortes.p2p.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TimeHelper {
    /**
     * All chrono unit set in minutes.
     */
    private static final int USER_AUTH_TOKEN_OFFSET = 10;
    public static Instant getUserAuthTokenExpireAt() {
        Instant currentInstant = Instant.now();
        return currentInstant.plus(USER_AUTH_TOKEN_OFFSET, ChronoUnit.MINUTES);
    }

    public static boolean isTokenValid(Instant expireAt) {
//        if this instant is before the specified instant
        return Instant.now().isBefore(expireAt);
    }
}

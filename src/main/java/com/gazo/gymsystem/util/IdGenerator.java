package com.gazo.gymsystem.util;

import java.util.UUID;

public class IdGenerator {

    public static String generarIdCliente() {

        return "GAZO-" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 6);

    }


}
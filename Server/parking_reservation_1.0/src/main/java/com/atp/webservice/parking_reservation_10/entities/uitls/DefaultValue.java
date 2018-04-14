package com.atp.webservice.parking_reservation_10.entities.uitls;

import java.sql.Time;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.*;

public class DefaultValue {

    public static final String STRING = "DEFAULT";

    public static final int INT = 0;

    public static final double DOUBLE = 0;

    public static final boolean BOOLEAN = false;

    public static  final Timestamp TIMESTAMP = new Timestamp(10000);

    public static final Time TIME = new Time(1000);

    public static final UUID UUID = java.util.UUID.randomUUID();

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

}

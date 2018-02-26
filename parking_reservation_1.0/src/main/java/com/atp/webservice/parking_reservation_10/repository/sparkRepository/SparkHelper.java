package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import org.apache.spark.sql.*;

import java.util.List;

public class SparkHelper {

    private static SparkSession sparkSession = null;

    private SparkHelper() {
        sparkSession = SparkSession
                        .builder()
                        .appName("Parking Reservation")
                        .getOrCreate();
    }

    /**
     * Get Spark Session
     * @return @{@link SparkSession}
     */
    public static SparkSession GetSession(){
        if(sparkSession == null)
            new SparkHelper();
        return  sparkSession;
    }

    /**
     * Close Session
     */
    public static void CloseSession(){
        if(sparkSession!=null)
            sparkSession.close();
    }

    /**
     * Convert from Spark RDD to List
     * @param dataset Spark RDD
     * @param type Class type map with entity table in database
     * @param <T> Entity
     * @return List Entities
     */
    public static <T> List<T> ConvertFromRDDToList(Dataset<Row> dataset, Class<T> type){
        Encoder<T> encoder = Encoders.bean(type);
        return dataset.as(encoder).collectAsList();
    }
}

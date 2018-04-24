package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import org.apache.avro.reflect.AvroEncode;
import org.apache.spark.sql.*;

import java.util.List;

public class SparkHelper {

    private static SparkSession sparkSession = null;

    private SparkHelper() {
        sparkSession = SparkSession
                        .builder()
                        .appName("StationOverview Reservation")
                        .master("local")
                        .getOrCreate();
    }

    /**
     * Get Spark Session
     * @return @{@link SparkSession}
     */
    public static SparkSession getSession(){
        if(sparkSession == null)
            new SparkHelper();
        return  sparkSession;
    }

    /**
     * Close Session
     */
    public static void closeSession(){
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
    public static <T> List<T> convertFromRDDToList(Dataset<Row> dataset, Class<T> type){
        Encoder<T> encoder = Encoders.bean(type);
        return dataset.as(encoder).collectAsList();
    }

    public static <T>  Dataset<T> GetRRDFromTable(String tableName, Class<T> type){
        Dataset<Row> dataset = getSession().read()
                .format("org.apache.spark.sql.execution.datasources.jdbc.JdbcRelationProvider")
                .option("url","jdbc:mysql://localhost:3306/parkingmanager")
                .option("dbtable",tableName)
                .option("user","root")
                .option("password","root")
                .load();
        Encoder<T> encoder = Encoders.bean((type));
        return  dataset.as((encoder));
    }
}

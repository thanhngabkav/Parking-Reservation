package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationColumns;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.algorithms.MapHelper;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationRepositoryImp implements StationRepository {

    Logger logger = Logger.getLogger(StationRepositoryImp.class);

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    public static Dataset<Row> parkingDataSet;

    @Override
    public List<StationPresenter> findAll() {
        return parkingDataSet.as(Encoders.bean(StationPresenter.class)).collectAsList();
    }

    @Override
    public StationPresenter findOne(Integer parkingID) {
        Encoder<StationPresenter> encoder = Encoders.bean(StationPresenter.class);
        Dataset<StationPresenter> filterDF = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).equalTo(parkingID)).as(Encoders.bean(StationPresenter.class));
        if(filterDF.count() > 0) {
            filterDF.show();
            String json = filterDF.toJSON().first();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                StationPresenter stationPresenter = objectMapper.readValue(json, StationPresenter.class);
                return stationPresenter;
            } catch (IOException e) {
                logger.error("Missing result! Cannot convert to StationPresenter");
                e.printStackTrace();
            }
        }
        //not found
        return null ;
    }

    int sum(int a, int b){
        return a+b;
    }

    @Override
    public boolean exist(Integer parkingID) {
        Dataset<StationPresenter> filterDF = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).cast("integer").equalTo(parkingID)).as(Encoders.bean(StationPresenter.class));
        return  filterDF.count()>=0;
    }

    @Override
    public void delete(Integer parkingID) {
//        this.stationCRUDRepository.delete(parkingID);
        parkingDataSet = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).notEqual(parkingID.toString()));
    }

    @Override
    public long count() {
        return parkingDataSet.count();
    }

    @Override
    public StationPresenter save(StationPresenter stationPresenter) {
        if(exist(stationPresenter.getStation_id())){
            parkingDataSet = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).cast("integer").notEqual(stationPresenter.getStation_id()));
        }
        List<StationPresenter> presenterList = new ArrayList<StationPresenter>();
        presenterList.add(stationPresenter);
        Dataset<StationPresenter> newDataset = SparkHelper.getSession().createDataset(presenterList, Encoders.bean(StationPresenter.class));
        parkingDataSet = parkingDataSet.union(newDataset.select("station_id", "application_id", "close_time", "coordinate", "created_date", "holding_slots", "image_link", "name",
                "open_time", "owner_id", "parking_map_link", "station_id", "status", "total_slots", "used_slots"));
        return stationPresenter;

    }

    @Override
    public void unpersist() {
        if(parkingDataSet!=null) {
            parkingDataSet.unpersist();
            parkingDataSet = null;
        }
    }


    @Override
    public List<StationPresenter> findNearByStations(StationLocationModel stationLocationModel, double radius, char distanceUnit) {

        MapHelper mapHelper = new MapHelper();
        double lat2 = stationLocationModel.getLat();
        double lng2 = stationLocationModel.getLng();

        ObjectMapper objectMapper = new ObjectMapper();
        Encoder<StationPresenter> encoder = Encoders.bean(StationPresenter.class);
        //define filter function
        FilterFunction<String> filterFunction = x-> mapHelper.distance(Double.parseDouble(objectMapper.readValue(x, StationPresenter.class).getCoordinate().split(",")[0])
                ,Double.parseDouble(objectMapper.readValue(x, StationPresenter.class).getCoordinate().split(",")[1]),lat2,lng2,distanceUnit) <= radius;
        Dataset<String> filterData = parkingDataSet.toJSON().filter(filterFunction);
        List<String> stringList = filterData.collectAsList();

        //Convert results to Model
        List<StationPresenter> mStationPresenters = new ArrayList<StationPresenter>();
        for(String json : stringList){
            try {
                StationPresenter stationPresenter = objectMapper.readValue(json, StationPresenter.class);
                mStationPresenters.add(stationPresenter);
            } catch (IOException e) {
                logger.error("Missing result! Cannot convert to StationPresenter");
                e.printStackTrace();
            }
        }
        return mStationPresenters;
    }
}

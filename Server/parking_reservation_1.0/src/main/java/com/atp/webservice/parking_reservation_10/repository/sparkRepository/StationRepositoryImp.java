package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationColumns;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StationRepositoryImp implements StationRepository {

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    private static Dataset<StationPresenter> parkingDataSet;

//    @PostConstruct
//    public void InitParkingDataSet(){
//        parkingDataSet = SparkHelper.GetRRDFromTable(TableName.STATION, StationPresenter.class);
//        parkingDataSet.persist(StorageLevel.MEMORY_ONLY());
//        //parkingDataSet.show();
//    }

    @Override
    public List<StationPresenter> findAll() {
        return parkingDataSet.collectAsList();
    }

    @Override
    public StationPresenter findOne(Integer parkingID) {
        Dataset<StationPresenter> filterDF = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).equalTo(parkingID));
        if(filterDF.count() > 0)
            return filterDF.collectAsList().get(0);
        //not found
        return null ;
    }

    @Override
    public boolean exist(Integer parkingID) {
        Dataset<StationPresenter> filterDF = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).equalTo(parkingID));
        return  filterDF.count()>=0;
    }

    @Override
    public void delete(Integer parkingID) {
        this.stationCRUDRepository.delete(parkingID);
        parkingDataSet = parkingDataSet.filter(parkingDataSet.col(StationColumns.ID).notEqual(parkingID.toString()));
    }

    @Override
    public long count() {
        return parkingDataSet.count();
    }

    @Override
    public StationPresenter save(StationPresenter stationPresenter) {
        Station station = stationPresenter.convertToParking();
        this.stationCRUDRepository.save(station);
        List<StationPresenter> presenterList = new ArrayList<StationPresenter>();
        presenterList.add(stationPresenter);
        Dataset<StationPresenter> newDataset = SparkHelper.getSession().createDataset(presenterList, Encoders.bean(StationPresenter.class));
       parkingDataSet = parkingDataSet.union(newDataset);
       return stationPresenter;
    }

    @Override
    public void unpersist() {
        if(parkingDataSet!=null) {
            parkingDataSet.unpersist();
            parkingDataSet = null;
        }
    }

}

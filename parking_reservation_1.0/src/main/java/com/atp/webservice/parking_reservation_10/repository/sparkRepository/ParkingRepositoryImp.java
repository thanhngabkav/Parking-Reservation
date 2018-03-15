//package com.atp.webservice.parking_reservation_10.repository.sparkRepository;
//
//import com.atp.webservice.parking_reservation_10.entities.Parking;
//import com.atp.webservice.parking_reservation_10.entities.uitls.ParkingCols;
//import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
//import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.ParkingCRUDRepository;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.storage.StorageLevel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Repository
//public class ParkingRepositoryImp implements ParkingRepository{
//
//    @Autowired
//    private ParkingCRUDRepository parkingCRUDRepository;
//
//    private static Dataset<Parking> parkingDataSet;
//
//    @PostConstruct
//    public void InitParkingDataSet(){
//        parkingDataSet = SparkHelper.GetRRDFromTable(TableName.PARKING, Parking.class);
//        parkingDataSet.persist(StorageLevel.MEMORY_ONLY());
//    }
//
//    @Override
//    public List<Parking> findAll() {
//        return parkingDataSet.collectAsList();
//    }
//
//    @Override
//    public Parking findOne(Integer parkingID) {
//        Dataset<Parking> filterDF = parkingDataSet.filter(parkingDataSet.col(ParkingCols.ID).equalTo(parkingID));
//        if(filterDF.count() > 0)
//            return filterDF.collectAsList().get(0);
//        //not found
//        return null ;
//    }
//
//    @Override
//    public boolean exist(Integer parkingID) {
//        Dataset<Parking> filterDF = parkingDataSet.filter(parkingDataSet.col(ParkingCols.ID).equalTo(parkingID));
//        return  filterDF.count()>=0;
//    }
//
//    @Override
//    public void delete(Integer integer) {
//        //this.
//    }
//
//    @Override
//    public long count() {
//        return parkingDataSet.count();
//    }
//
//    @Override
//    public Parking save(Parking entity) {
//        return null;
//    }
//
//    @Override
//    public void unpersist() {
//        if(parkingDataSet!=null) {
//            parkingDataSet.unpersist();
//            parkingDataSet = null;
//        }
//    }
//
//}

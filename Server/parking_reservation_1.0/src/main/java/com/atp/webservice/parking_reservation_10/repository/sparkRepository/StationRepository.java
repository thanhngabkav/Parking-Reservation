package com.atp.webservice.parking_reservation_10.repository.sparkRepository;


import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;

import java.util.List;

public interface StationRepository extends SparkSQLCRUDRepository<StationPresenter, Integer>{

    /**
     * Find near by stations by {@link StationLocationModel} in a range
     * @param stationLocationModel {@link StationLocationModel}
     * @param radius filter range
     * @param distanceUnit Distance unit
     * @return List {@link StationPresenter}
     */
    List<StationPresenter> findNearByStations(StationLocationModel stationLocationModel,double radius, char distanceUnit);

}

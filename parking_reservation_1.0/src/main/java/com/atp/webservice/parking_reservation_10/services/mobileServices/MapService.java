package com.atp.webservice.parking_reservation_10.services.mobileServices;

import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.StationLocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

    /**
     * Get list Station Locations near a determined stationLocation
     * @param stationLocation @{@link StationLocation}
     * @return {@link List< StationLocation >}
     */
    List<StationLocation> getNearByParking(StationLocation stationLocation, double radius);

}

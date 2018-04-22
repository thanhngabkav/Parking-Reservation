package com.atp.webservice.parking_reservation_10.services.mobileServices.mapService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationLocationModel;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface MapService extends Serializable {

    /**
     * Get list StationOverview Locations near a determined stationLocationModel
     * @param stationLocationModel @{@link StationLocationModel}
     * @return {@link List<  StationLocationModel  >}
     */
    List<StationLocationModel> getNearByParking(StationLocationModel stationLocationModel, double radius);

}

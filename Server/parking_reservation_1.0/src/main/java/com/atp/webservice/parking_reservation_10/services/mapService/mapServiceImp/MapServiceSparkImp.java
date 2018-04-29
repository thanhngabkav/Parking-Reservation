package com.atp.webservice.parking_reservation_10.services.mapService.mapServiceImp;

import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.ServiceCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mapService.MapService;
import com.atp.webservice.parking_reservation_10.services.mapService.MapService;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a implement of {@link MapService} using Spark repository
 */

@Service
public class MapServiceSparkImp implements MapService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Override
    public List<StationLocationModel> getNearByParking(StationLocationModel stationLocationModel, double radius) {
        List<StationPresenter> stationPresenterList = stationRepository.findNearByStations(stationLocationModel, radius, 'K');
        List<StationLocationModel> result = new ArrayList<StationLocationModel>();
        for (StationPresenter stationPresenter : stationPresenterList) {
            result.add(convertFromStationPresenter(stationPresenter));
        }
        return result;
    }

    @Override
    public List<StationLocationModel> getNearByStationsService(StationLocationModel stationLocationModel, double radius, int serviceID) {
        List<StationLocationModel> nearByStations = getNearByParking(stationLocationModel, radius);
        List<StationLocationModel> results = new ArrayList<StationLocationModel>();
        for(StationLocationModel station : nearByStations){
            List<com.atp.webservice.parking_reservation_10.entities.Service> stationServices
                    = stationCRUDRepository.findOne(station.getStationID()).getServices();
            for(com.atp.webservice.parking_reservation_10.entities.Service service : stationServices){
                if(service.getServiceID() == serviceID){
                    results.add(station);
                }
            }
        }
        return results;
    }

    /**
     * Convert from {@link StationPresenter} to {@link StationLocationModel}
     *
     * @param stationPresenter StationPresenter
     * @return StationLocationModel
     */
    private StationLocationModel convertFromStationPresenter(StationPresenter stationPresenter) {
        StationLocationModel mStationLocationModel = new StationLocationModel();
        mStationLocationModel.setTotalSlot(Integer.parseInt(stationPresenter.getTotal_slots()));
        mStationLocationModel.setUsedSlot(Integer.parseInt(stationPresenter.getUsed_slots()));
        mStationLocationModel.setFreeSlot(mStationLocationModel.getTotalSlot() - mStationLocationModel.getUsedSlot());
        mStationLocationModel.setLat(Double.parseDouble(stationPresenter.getCoordinate().split(",")[0]));
        mStationLocationModel.setLng(Double.parseDouble(stationPresenter.getCoordinate().split(",")[1]));
        mStationLocationModel.setStationID(stationPresenter.getStation_id());

        return mStationLocationModel;
    }

}

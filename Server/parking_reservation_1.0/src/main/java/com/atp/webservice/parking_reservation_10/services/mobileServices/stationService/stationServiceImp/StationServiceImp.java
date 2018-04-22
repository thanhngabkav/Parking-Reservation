    package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.stationServiceImp;

    import com.atp.webservice.parking_reservation_10.entities.Station;
    import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
    import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
    import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationConverter;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class StationServiceImp implements StationService {

        @Autowired
        private StationCRUDRepository stationCRUDRepository;

        @Autowired
        private StationRepository stationRepository;

        @Override
        public List<StationModel> getStationByName(String name) {
           List<com.atp.webservice.parking_reservation_10.entities.Station> stations = stationCRUDRepository.findStationsByName(name);
           List<StationModel> listStationModelPresenter = new ArrayList<StationModel>();
           for(com.atp.webservice.parking_reservation_10.entities.Station station : stations){
               StationModel stationModelPresenter = StationConverter.convertFromEntities(station);
                listStationModelPresenter.add(stationModelPresenter);
           }

           return listStationModelPresenter;
        }

        @Override
        public StationModel getStationByID(int stationID) {
            StationModel stationModel =  StationConverter.convertFromEntities(stationCRUDRepository.findOne(stationID));
    //        StationPresenter stationPresenter  = stationRepository.findOne(stationID);
    //
    //        StationModel stationModel =  StationConverter.convertFromEntities()
    //        System.out.println(stationModel.toString());
            return stationModel;
        }

        @Override
        public void addNewStation(Station station) {
            StationPresenter stationPresenter = new StationPresenter();
            stationPresenter.convertFromEntities( stationCRUDRepository.save(station));
            stationRepository.save(stationPresenter);
        }

    }

    package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.stationServiceImp;

    import com.atp.webservice.parking_reservation_10.entities.Station;
    import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
    import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
    import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
    import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationConverter;
    import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.sql.Timestamp;
    import java.time.LocalDateTime;
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
            return stationModel;
        }

        @Override
        public StationModel addNewStation(StationModel station) {

            StationPresenter stationPresenter = new StationPresenter();
            station.setID(-1);
            station.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            station.setUsedSlots(0);
            station.setHoldingSlots(0);
            station.setStar(3);
            station.setStatus(StationStatus.WAITING_FOR_ACTIVE);
            //save to database
            Station m_station =  stationCRUDRepository.save(StationConverter.convertToEntity(station));
            stationPresenter.convertFromEntities(m_station);
            //save to spark data set
            stationRepository.save(stationPresenter);
            StationModel newStation =  StationConverter.convertFromEntities(m_station);

            return newStation;
        }

        @Override
        public StationModel updateHoldingSlots(int stationID, int num) {
            Station station = stationCRUDRepository.findOne(stationID);
            if(station == null || station.getStatus()!= StationStatus.ACTIVE) // station not found or UnActive
                return null;
            station.setHoldingSlots(station.getHoldingSlots() + num >0 ? station.getHoldingSlots() + num : 0 );
            StationModel updatedStation = StationConverter.convertFromEntities(stationCRUDRepository.save(station));
            return updatedStation;
        }

        @Override
        public StationModel updateUsedSlots(int stationID, int num) {
            Station station = stationCRUDRepository.findOne(stationID);
            if(station == null) // station not found
                return null;
            station.setUsedSlots(station.getUsedSlots() + num >0 ? station.getHoldingSlots() + num : 0 );
            //update station
            Station updatedStation = stationCRUDRepository.save(station);
            //update spark data set
            StationPresenter stationPresenter = new StationPresenter();
            stationPresenter.convertFromEntities(updatedStation);
            stationRepository.save(stationPresenter);
            return StationConverter.convertFromEntities(updatedStation);
        }

        @Override
        public StationModel updateStation(StationModel stationModel) {
            Station m_station = stationCRUDRepository.findOne(stationModel.getID());
            if(m_station == null){ // station not found
                return null;
            }
            //update station
            Station updatedStation = stationCRUDRepository.save(StationConverter.convertToEntity(stationModel));
            //update spark data set
            StationPresenter stationPresenter = new StationPresenter();
            stationPresenter.convertFromEntities(updatedStation);
            stationRepository.save(stationPresenter);
            return StationConverter.convertFromEntities(updatedStation);
        }
    }

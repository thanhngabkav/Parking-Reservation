package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService.ticketTypeServiceImp;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService.TicketTypeConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketTypeServiceImp implements TicketTypeService{


    @Autowired
    private TicketTypeCRUDRepository ticketTypeCRUDRepository;

    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;

    @Autowired
    private TicketTypeConverter ticketTypeConverter;

    @Override
    public List<TicketTypeModel> getAllTicketTypesByServiceIDAndStationID (int serviceID, int stationID) {
        List<StationVehicleType> m_stationVehicleTypes = stationVehicleTypeCRUDRepository.findByStationID(stationID);
        List<TicketTypeModel> m_TicketTypeModels = new ArrayList<TicketTypeModel>();
        for(StationVehicleType vehicleType : m_stationVehicleTypes){
            List<TicketType> m_ticketTypes =ticketTypeCRUDRepository.findByServiceIDAndStationVehicleTypeID(serviceID, vehicleType.getId());
            for(TicketType ticketType : m_ticketTypes){
                m_TicketTypeModels.add(ticketTypeConverter.convertFromEntity(ticketType));
            }
        }
        return m_TicketTypeModels;
    }

    @Override
    public List<TicketTypeModel> getTicketTypesByServiceIDAndStationIDAndVehicleTypeID(int serviceID, int stationID, int vehicleTypeID) {
        StationVehicleType m_stationVehicleType = stationVehicleTypeCRUDRepository.findFirstByStationIDAndAndVehicleTypeId(stationID, vehicleTypeID);
        List<TicketTypeModel> m_TicketTypeModels = new ArrayList<TicketTypeModel>();
        List<TicketType> m_ticketTypes =ticketTypeCRUDRepository.findByServiceIDAndStationVehicleTypeID(serviceID, m_stationVehicleType.getId());
        for(TicketType ticketType : m_ticketTypes){
            m_TicketTypeModels.add(ticketTypeConverter.convertFromEntity(ticketType));
        }
        return m_TicketTypeModels;
    }

    @Override
    public TicketTypeModel addNewTicketType(TicketTypeModel ticketTypeModel) {
        //check is exited
        if(ticketTypeCRUDRepository.findOne(ticketTypeModel.getTicketTypeID())!= null){
            return null;
        }

        TicketType m_ticketType = ticketTypeConverter.convertFromModel(ticketTypeModel);

        return ticketTypeConverter.convertFromEntity(ticketTypeCRUDRepository.save(m_ticketType));
    }

    @Override
    public TicketTypeModel updateTicketType(TicketTypeModel ticketTypeModel) {
        //check is exited
        if(ticketTypeCRUDRepository.findOne(ticketTypeModel.getTicketTypeID())== null){
            return null;
        }
        TicketType m_ticketType = ticketTypeConverter.convertFromModel(ticketTypeModel);

        return ticketTypeConverter.convertFromEntity(ticketTypeCRUDRepository.save(m_ticketType));
    }
}

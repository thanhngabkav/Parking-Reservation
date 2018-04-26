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

    @Override
    public List<TicketTypeModel> getAllTicketTypesByServiceIDAndStationID (int serviceID, int stationID) {
        List<StationVehicleType> m_stationVehicleTypes = stationVehicleTypeCRUDRepository.findByStationID(stationID);
        List<TicketTypeModel> m_TicketTypeModels = new ArrayList<TicketTypeModel>();
        TicketTypeConverter m_converter = new TicketTypeConverter();
        for(StationVehicleType vehicleType : m_stationVehicleTypes){
            TicketType m_tickettType = ticketTypeCRUDRepository.findByServiceIDAndStationVehicleTypeID(serviceID, vehicleType.getId());
            m_TicketTypeModels.add(m_converter.convertFromEntity(m_tickettType));
        }
        return m_TicketTypeModels;
    }

    @Override
    public TicketTypeModel addNewTicketType(TicketTypeModel ticketTypeModel) {
        //check is exited
        if(ticketTypeCRUDRepository.findOne(ticketTypeModel.getTicketTypeID())!= null){
            return null;
        }

        TicketTypeConverter m_converter =  new TicketTypeConverter();
        TicketType m_ticketType = m_converter.convertFromModel(ticketTypeModel);

        return m_converter.convertFromEntity(ticketTypeCRUDRepository.save(m_ticketType));
    }

    @Override
    public TicketTypeModel updateTicketType(TicketTypeModel ticketTypeModel) {
        //check is exited
        if(ticketTypeCRUDRepository.findOne(ticketTypeModel.getTicketTypeID())== null){
            return null;
        }
        TicketTypeConverter m_converter =  new TicketTypeConverter();
        TicketType m_ticketType = m_converter.convertFromModel(ticketTypeModel);

        return m_converter.convertFromEntity(ticketTypeCRUDRepository.save(m_ticketType));
    }
}

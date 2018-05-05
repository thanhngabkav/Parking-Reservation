package com.atp.webservice.parking_reservation_10.services.ownerService;


import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/owners")
public class OwnerController {

    @Autowired
    private OwnerCRUDRepository parkingOwnerRepository;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerConverter ownerConverter;


    /**
     * Get All StationOverview Owner
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerModel>> GetAll() {
        List<OwnerModel> ownerList = ownerService.getAllOwnerModel();
        return new ResponseEntity<List<OwnerModel>>(ownerList, HttpStatus.OK);
    }

    /**
     * Get Owner {@link Owner} by ID
     *
     * @param ownerID owner ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerModel> GetOwnerByID(@PathVariable("id") String ownerID) {
        Owner owner = this.parkingOwnerRepository.findOne(ownerID);
        return new ResponseEntity<OwnerModel>(ownerConverter.convertFromEntity(owner), HttpStatus.OK);

    }

    /**
     * Find owner by phone number or email
     * @param emailOrPhone
     * @return Owner
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Owner> getByUserName(@RequestParam("userName") String emailOrPhone){
        Owner owner = ownerService.findByPhoneNumberOrEmail(emailOrPhone);
        return new ResponseEntity<Owner>(owner,HttpStatus.OK);
    }

    /**
     * Create new {@link Owner}
     * @param ownerModel {@link OwnerModel} - presenter of {@link Owner}
     * @return OwnerModel
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerModel> createNewOwner(@RequestBody OwnerModel ownerModel) throws NoSuchAlgorithmException {
        OwnerModel saveModel = ownerService.createOwner(ownerModel);

        return new ResponseEntity<OwnerModel>(saveModel, HttpStatus.OK);
    }

    /**
     * Get page list Owners
     * @param page
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OwnerModel>> getPageListOwner(@RequestParam("page") int page){
        Page<OwnerModel> ownerModelList = ownerService.getPageListOwners(page);

        return new ResponseEntity<Page<OwnerModel>>(ownerModelList, HttpStatus.OK);
    }
}

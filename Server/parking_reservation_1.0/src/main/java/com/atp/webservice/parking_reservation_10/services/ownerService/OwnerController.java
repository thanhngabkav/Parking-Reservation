package com.atp.webservice.parking_reservation_10.services.ownerService;


import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * Get All StationOverview Owner
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Owner>> GetAll() {
        List<Owner> ownerList = new ArrayList<Owner>();
        this.parkingOwnerRepository.findAll().forEach(ownerList::add);
        return new ResponseEntity<List<Owner>>(ownerList, HttpStatus.OK);
    }

    /**
     * Get Owner {@link Owner} by ID
     *
     * @param ownerID owner ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Owner> GetOwnerByID(@PathVariable("id") String ownerID) {
        Owner owner = this.parkingOwnerRepository.findOne(ownerID);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);

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
        OwnerModel ownerModel1 = ownerService.createOwner(ownerModel);

        return new ResponseEntity<OwnerModel>(ownerModel, HttpStatus.OK);
    }

    /**
     * Get page list Owners
     * @param page
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OwnerModel>> getPageListOwner(@RequestParam("page") int page){
        List<OwnerModel> ownerModelList = ownerService.getPageListOwners(page);

        return new ResponseEntity<List<OwnerModel>>(ownerModelList, HttpStatus.OK);
    }
}

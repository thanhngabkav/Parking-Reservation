package com.atp.webservice.parking_reservation_10.services.ownerService;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.services.models.OwnerModel;

import java.util.*;
import java.security.NoSuchAlgorithmException;

public interface OwnerService {

    /**
     * Find {@link Owner} by phone number or email
     * @param phoneOrEmail phone number or email as string
     * @return Owner
     */
    Owner findByPhoneNumberOrEmail(String phoneOrEmail);

    /**
     * Create new owner
     * @param ownerModel
     * @return OwnerModel
     * @throws NoSuchAlgorithmException
     */
    OwnerModel createOwner(OwnerModel ownerModel) throws NoSuchAlgorithmException;

    /**
     * get list {@link OwnerModel} by page number
     * @param page page number
     * @return List {@link OwnerModel}
     */
    List<OwnerModel> getPageListOwners(int page);
}

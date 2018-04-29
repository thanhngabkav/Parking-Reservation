package com.atp.webservice.parking_reservation_10.services.ownerService;

import com.atp.webservice.parking_reservation_10.entities.Owner;

public interface OwnerService {

    /**
     * Find {@link Owner} by phone number or email
     * @param phoneOrEmail phone number or email as string
     * @return Owner
     */
    Owner findByPhoneNumberOrEmail(String phoneOrEmail);
}

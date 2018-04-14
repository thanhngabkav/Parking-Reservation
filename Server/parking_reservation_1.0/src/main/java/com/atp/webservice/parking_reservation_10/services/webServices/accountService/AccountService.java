package com.atp.webservice.parking_reservation_10.services.webServices.accountService;

import java.io.IOException;

public interface AccountService {

    /**
     //     * Save user Image
     //     * @param image @{@link java.util.Arrays<Byte>}
     //     * @param userID user ID
     //     */
    void SaveUserImage(byte[] image, Integer userID, String fileName) throws IOException;

    /**
     * Get User File
     * @param userID  User ID
     * @param fileName File name
     * @return File as byte array
     */
    byte[] GetUserImage(Integer userID, String fileName) throws IOException;

}

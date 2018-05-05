package com.atp.webservice.parking_reservation_10.services.userService;


import com.atp.webservice.parking_reservation_10.services.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * Find user by phone number or email
     * @param emailOrPhone
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getByUserName(@RequestParam("userName") String emailOrPhone){
        UserModel userModel = userService.getUserByEmailOrPhoneNumber(emailOrPhone);
        return new ResponseEntity<UserModel>(userModel,HttpStatus.OK);
    }


}

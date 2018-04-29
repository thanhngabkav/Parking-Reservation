package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CommentCRUDRepository extends JpaRepository<Comment,String> {

    List<Comment> findAllByStationIDOrderByCreatedTimeDesc(int stationID, Pageable pageable);

    long  countByStationID(int stationID);
}

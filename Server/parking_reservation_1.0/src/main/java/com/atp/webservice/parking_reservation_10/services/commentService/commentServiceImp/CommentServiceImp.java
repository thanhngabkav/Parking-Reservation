package com.atp.webservice.parking_reservation_10.services.commentService.commentServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Comment;
import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.CommentCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.commentService.CommentService;
import com.atp.webservice.parking_reservation_10.services.commentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentCRUDRepository commentCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    private static final int PAGE_SIZE = 3;

    @Override
    public Comment addNewComment(Comment comment) {
        Station station = stationCRUDRepository.findOne(comment.getStationID());
        if(station == null)
            return null;
        comment.setId(UUID.randomUUID().toString());
        comment.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        long numComment = commentCRUDRepository.countByStationID(comment.getStationID());
        station.setStar((station.getStar()*(numComment+10) + comment.getStar())/(numComment +11));
        stationCRUDRepository.save(station);
        return commentCRUDRepository.save(comment);
    }

    @Override
    public List<Comment> getPageListCommentByStation(int stationID, int page) {
        PageRequest pageRequest = new PageRequest(page-1, PAGE_SIZE);
        List<Comment> commentList = commentCRUDRepository.findAllByStationIDOrderByCreatedTimeDesc(stationID, pageRequest);
        return commentList;
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment oldComment = commentCRUDRepository.findOne(comment.getId());
        if(oldComment == null)
            return null;
        return addNewComment(comment);
    }


}

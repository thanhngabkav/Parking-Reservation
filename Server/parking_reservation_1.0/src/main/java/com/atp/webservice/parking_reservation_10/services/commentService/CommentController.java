package com.atp.webservice.parking_reservation_10.services.commentService;


import com.atp.webservice.parking_reservation_10.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Add new comment for a station
     * @param comment
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){

        return new ResponseEntity<Comment>(commentService.addNewComment(comment), HttpStatus.OK);
    }

    /**
     * Update comment
     * @param comment
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> update(@RequestBody Comment comment){
        return new ResponseEntity<Comment>(commentService.updateComment(comment), HttpStatus.OK);
    }

    /**
     * Get page list comments of a station
     * @param stationID
     * @param page
     * @return
     */
    @RequestMapping(value = "/station/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getPageListStationComments(@PathVariable(value = "id") int stationID, @RequestParam("page") int page){
        List<Comment> result = commentService.getPageListCommentByStation(stationID, page);
        return new ResponseEntity<List<Comment>>(result, HttpStatus.OK);
    }
}

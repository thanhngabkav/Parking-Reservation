package com.atp.webservice.parking_reservation_10.services.commentService;

import com.atp.webservice.parking_reservation_10.entities.Comment;

import java.util.List;

public interface CommentService {

    /**
     * Add new {@link Comment} for a station
     * @param comment
     * @return Comment
     */
    Comment addNewComment(Comment comment);

    /**
     * Get page list {@link Comment} of a station order DESC by created time
     * @param stationID
     * @param page
     * @return Comment
     */
    List<Comment> getPageListCommentByStation(int stationID, int page);

    /**
     * Update {@link Comment}
     * @param comment
     * @return Comment
     */
    Comment updateComment(Comment comment);
}

DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteSurveyResponse $$

CREATE PROCEDURE spDeleteSurveyResponse(
IN courseID BIGINT,
IN bannerID VARCHAR(40)
)
BEGIN
	SET @surveyID = (SELECT SurveyCourse.id
					FROM SurveyCourse
					WHERE SurveyCourse.courseID = courseID);
    
	SET @userID = (SELECT User.id
				FROM User
				WHERE User.bannerID = bannerID);
		
	SET @responseIDs = (SELECT GROUP_CONCAT(Response.id)
						FROM Response
						JOIN SurveyResponse ON Response.id = SurveyResponse.responseID
						WHERE SurveyResponse.surveyID = @surveyID AND Response.userID = @userID);

	DELETE FROM SurveyResponse
	WHERE FIND_IN_SET(SurveyResponse.responseID, @responseIDs);
    
	DELETE FROM Response
	WHERE FIND_IN_SET(Response.id, @responseIDs);
END$$
DELIMITER ;
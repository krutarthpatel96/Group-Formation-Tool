DELIMITER $$

DROP PROCEDURE IF EXISTS spFetchStudentRespondents $$

CREATE PROCEDURE `spFetchStudentRespondents`(
IN courseID BIGINT
)
BEGIN
	SET @surveyID = (SELECT id
				FROM SurveyCourse
				WHERE SurveyCourse.courseID = courseID);

	SELECT DISTINCT User.bannerID
	FROM Response
	JOIN User ON Response.userID = User.id
	WHERE surveyID = @surveyID;
END$$
DELIMITER ;
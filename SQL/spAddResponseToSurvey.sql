DELIMITER $$

DROP PROCEDURE IF EXISTS spAddResponseToSurvey $$

CREATE PROCEDURE spAddResponseToSurvey(
IN courseID BIGINT,
IN questionID BIGINT,
IN bannerID VARCHAR(20),
IN response VARCHAR(500)
)
BEGIN
	SET @userID = (SELECT id
				FROM User
				WHERE User.bannerID = bannerID);

	SET @surveyID = (SELECT id
				FROM SurveyCourse
				WHERE SurveyCourse.courseID = courseID);

	INSERT INTO Response (surveyID, questionID, userID, response) VALUES (@surveyID, questionID, @userID, response);

	SET @responseID = LAST_INSERT_ID();
                        
	INSERT INTO SurveyResponse (surveyID, responseID) VALUES (@surveyID, @responseID);
END$$
DELIMITER ;
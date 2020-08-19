DELIMITER $$

DROP PROCEDURE IF EXISTS spAddQuestionToSurvey $$

CREATE PROCEDURE spAddQuestionToSurvey(
IN courseID BIGINT,
IN questionID BIGINT
)
BEGIN
	DECLARE surveyID BIGINT;
	SET @surveyID = (SELECT SurveyCourse.id
				FROM SurveyCourse 
				WHERE SurveyCourse.courseID = courseID);
                    
	INSERT INTO SurveyQuestion (id,questionID) VALUES (@surveyID, questionID);
END$$
DELIMITER ;
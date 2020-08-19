DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionFromSurvey $$

CREATE PROCEDURE spDeleteQuestionFromSurvey(
IN courseID BIGINT,
IN questionID BIGINT
)
BEGIN
	DECLARE surveyID BIGINT;
	SET @surveyID = (SELECT SurveyCourse.id
						FROM SurveyCourse
						WHERE SurveyCourse.courseID = courseID);

	DELETE FROM SurveyQuestion
	WHERE SurveyQuestion.questionID = questionID
			AND SurveyQuestion.id = @surveyID;
END$$
DELIMITER ;

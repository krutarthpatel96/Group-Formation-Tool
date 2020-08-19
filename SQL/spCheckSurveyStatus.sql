DELIMITER $$

DROP PROCEDURE IF EXISTS spCheckSurveyStatus $$

CREATE PROCEDURE spCheckSurveyStatus(
IN courseID BIGINT
)
BEGIN
	SELECT SurveyCourse.isEnabled
		FROM SurveyCourse
		WHERE SurveyCourse.courseID = courseID;
END$$
DELIMITER ;

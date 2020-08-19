DELIMITER $$

DROP PROCEDURE IF EXISTS spToggleSurvey $$

CREATE PROCEDURE spToggleSurvey(
IN courseID BIGINT,
IN status TINYINT
)
BEGIN
	UPDATE SurveyCourse
	SET isEnabled = status
	WHERE SurveyCourse.courseID = courseID;
END$$
DELIMITER ;

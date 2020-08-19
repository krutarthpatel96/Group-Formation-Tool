DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateCourse $$

CREATE PROCEDURE spCreateCourse (
	IN title VARCHAR(200),
    OUT id BIGINT
)
BEGIN
	INSERT INTO Course(title)
	VALUES (title);
	SET id = LAST_INSERT_ID();
    
	SET @courseID = LAST_INSERT_ID();
	INSERT INTO SurveyCourse(courseID, isEnabled)
	VALUES (@courseID, 0);
END $$

DELIMITER ;
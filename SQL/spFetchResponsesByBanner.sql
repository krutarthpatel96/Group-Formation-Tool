DELIMITER $$

DROP PROCEDURE IF EXISTS spFetchResponsesByBanner $$

CREATE PROCEDURE `spFetchResponsesByBanner`(
IN bannerID VARCHAR(20),
IN courseID BIGINT(20)
)
BEGIN
	SET @userID = (SELECT id
				FROM User
				WHERE User.bannerID = bannerID);
                    
	SET @surveyID = (SELECT id
				FROM SurveyCourse
				WHERE SurveyCourse.courseID = courseID);
                            
	SELECT Question.id, 
		QuestionTitle.title, 
		QuestionText.text, 
		QuestionType.type, 
		Response.response
	FROM User
	JOIN QuestionBank ON User.id = QuestionBank.userID
	JOIN Question ON Question.id = QuestionBank.questionID
	JOIN QuestionTitle ON Question.title = QuestionTitle.id
	JOIN QuestionText ON Question.text = QuestionText.id
    JOIN QuestionType ON Question.type = QuestionType.id
    JOIN Response ON Question.id = Response.questionID
	WHERE Response.userID = @userID AND Response.surveyID = @surveyID;
END$$
DELIMITER ;
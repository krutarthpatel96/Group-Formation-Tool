DELIMITER $$

DROP PROCEDURE IF EXISTS spFindInstructorAvailableQuestions $$

CREATE PROCEDURE spFindInstructorAvailableQuestions(
IN bannerID VARCHAR(40),
IN courseID BIGINT
)
BEGIN
	SET @questions = (SELECT GROUP_CONCAT(SurveyQuestion.questionID)
						FROM SurveyCourse
						JOIN SurveyQuestion ON SurveyCourse.id = SurveyQuestion.id
						WHERE SurveyCourse.courseID = courseID
						);

	SELECT Question.id, 
		QuestionTitle.title, 
		QuestionText.text, 
		QuestionType.type, 
		Question.timestamp
	FROM User
	JOIN QuestionBank ON User.id = QuestionBank.userID
	JOIN Question ON Question.id = QuestionBank.questionID
	JOIN QuestionType ON Question.type = QuestionType.id
	JOIN QuestionTitle ON Question.title = QuestionTitle.id
	JOIN QuestionText ON Question.text = QuestionText.id
	WHERE User.bannerID = bannerID
	AND NOT FIND_IN_SET(Question.id, IFNULL(@questions,'-1'))
	ORDER BY Question.timestamp;	
END$$
DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS spFindInstructorSurveyQuestionsByCourseID $$

CREATE PROCEDURE spFindInstructorSurveyQuestionsByCourseID(
IN courseID BIGINT(20)
)
BEGIN
	SELECT Question.id, 
		QuestionTitle.title, 
		QuestionText.text, 
		QuestionType.type, 
		Question.timestamp
	FROM SurveyCourse
	JOIN SurveyQuestion ON SurveyCourse.id = SurveyQuestion.id
	JOIN Question ON SurveyQuestion.questionID = Question.id
	JOIN QuestionTitle ON QuestionTitle.id = Question.title
	JOIN QuestionText ON QuestionText.id = Question.text
	JOIN QuestionType ON QuestionType.id = Question.type
	WHERE SurveyCourse.courseID = courseID;
END$$
DELIMITER ;

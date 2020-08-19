DELIMITER $$

DROP PROCEDURE IF EXISTS spFindInstructorSurveyQuestionOptions $$

CREATE PROCEDURE spFindInstructorSurveyQuestionOptions(
IN questionID BIGINT
)
BEGIN
	SELECT QuestionChoice.id,
		QuestionChoice.displayText,
		QuestionChoice.storedAs
	FROM QuestionChoice
	JOIN QuestionOption ON QuestionChoice.id = QuestionOption.choiceID
	WHERE QuestionOption.questionID = questionID;
END$$
DELIMITER ;

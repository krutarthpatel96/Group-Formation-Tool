package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

@Controller
public class SurveyController {

	private static final String CourseID = "courseID";
	private static final String BannerID = "bannerID";

	@RequestMapping("/survey/questions")
	public String surveyQuestions(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId) {

		ISurveyPersistence surveyDB = SurveyFactory.instance().makeSurveyDB();
		ISurveyInstructorRelationshipPersistence surveyInstructorRelationshipDB = SurveyFactory.instance()
				.makeSurveyInstructorRelationshipDB();
		int surveyStatus = surveyDB.checkSurveyStatus(courseId);
		List<IQuestion> surveyQuestionList = surveyInstructorRelationshipDB.loadInstructorSurveyQuestions(courseId);
		List<IQuestion> instructorQuestionList = surveyInstructorRelationshipDB
				.loadAvailabeInstructorSurveyQuestions(bannerId, courseId);
		model.addAttribute("courseID", courseId);
		model.addAttribute("surveyStatus", surveyStatus);
		model.addAttribute("surveyQuestionList", surveyQuestionList);
		model.addAttribute("instructorQuestionList", instructorQuestionList);
		return "survey/surveyquestions";
	}
}

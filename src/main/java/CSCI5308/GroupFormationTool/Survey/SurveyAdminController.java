package CSCI5308.GroupFormationTool.Survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyAdminController {

	private static final String CourseID = "courseID";
	private static final String BannerID = "bannerID";
	private static final String QuestionID = "questionID";
	private static final String Status = "status";

	@RequestMapping("/survey/question/add")
	public ModelAndView addQuestion(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId, @RequestParam(name = QuestionID) long questionId) {

		ISurveyPersistence surveyDB = SurveyFactory.instance().makeSurveyDB();
		boolean status = surveyDB.addQuestionToSurvey(courseId, questionId);
		ModelAndView mav = new ModelAndView(
				"redirect:/survey/questions?courseID=" + courseId + "&bannerID=" + bannerId);
		mav.addObject("addStatus", status);
		return mav;
	}

	@RequestMapping("/survey/question/delete")
	public ModelAndView deleteQuestion(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId, @RequestParam(name = QuestionID) long questionId) {

		ISurveyPersistence surveyDB = SurveyFactory.instance().makeSurveyDB();
		boolean status = surveyDB.deleteQuestionFromSurvey(courseId, questionId);
		ModelAndView mav = new ModelAndView(
				"redirect:/survey/questions?courseID=" + courseId + "&bannerID=" + bannerId);
		mav.addObject("addStatus", status);
		return mav;
	}

	@RequestMapping("/survey/question/toggle")
	public ModelAndView toggleSurvey(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId, @RequestParam(name = Status) int surveyStatus) {

		ISurveyPersistence surveyDB = SurveyFactory.instance().makeSurveyDB();
		surveyDB.toggleSurvey(courseId, surveyStatus);
		ModelAndView mav = new ModelAndView(
				"redirect:/survey/questions?courseID=" + courseId + "&bannerID=" + bannerId);
		return mav;
	}
}

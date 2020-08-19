package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

@Controller
public class GroupFormationController {

	private static final String CourseID = "courseID";
	private static final String BannerID = "bannerID";

	@RequestMapping("/survey/group/criteria")
	public String setCriteria(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId) {

		ISurveyInstructorRelationshipPersistence surveyInstructorRelationshipDB = SurveyFactory.instance()
				.makeSurveyInstructorRelationshipDB();
		List<IQuestion> surveyQuestionList = surveyInstructorRelationshipDB.loadInstructorSurveyQuestions(courseId);
		Criteria criteria = new Criteria();
		model.addAttribute("courseID", courseId);
		model.addAttribute("surveyQuestionList", surveyQuestionList);
		model.addAttribute("criteria", criteria);
		return "survey/groupcriteria";
	}

	@RequestMapping("/survey/group/submit")
	public String submitSurveyResponse(Model model, @RequestParam(name = CourseID) long courseId,
			@ModelAttribute Criteria criteria) {

		IResponsePersistence responseDB = SurveyFactory.instance().makeResponseDB();
		HashMap<Integer, List<String>> groups = criteria.formGroups(courseId, responseDB);
		model.addAttribute("groups", groups);
		return "survey/groups";
	}

}

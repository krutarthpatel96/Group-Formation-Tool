package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@Controller
public class ResponseController {

	private static final String CourseID = "courseID";
	private static final String BannerID = "bannerID";

	@RequestMapping("/survey/response/start")
	public String startSurvey(Model model, @RequestParam(name = CourseID) long courseId,
			@RequestParam(name = BannerID) String bannerId) {

		ISurveyInstructorRelationshipPersistence surveyInstructorRelationshipDB = SurveyFactory.instance()
				.makeSurveyInstructorRelationshipDB();
		IQuestionBank questions = SurveyFactory.instance().makeQuestionBank();
		IResponseWrapper responses = SurveyFactory.instance().makeResponseWrapper();
		questions.loadAllSurveyQuestions(surveyInstructorRelationshipDB, bannerId, courseId);
		responses.addResponses(bannerId, courseId, questions);
		model.addAttribute("questions", questions);
		model.addAttribute("responses", responses);
		return "survey/survey";
	}
	
	@RequestMapping("/survey/response/submit")
	public String submitSurveyResponse(Model model, @ModelAttribute ResponseWrapper responses) {

		IResponsePersistence responseDB = SurveyFactory.instance().makeResponseDB();
		boolean status = responses.saveResponse(responseDB);

		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		List<ICourse> allCourses = courseDB.loadAllCourses();
		model.addAttribute("courses", allCourses);
		model.addAttribute("surveystatus", status);
		return "index";
	}

}

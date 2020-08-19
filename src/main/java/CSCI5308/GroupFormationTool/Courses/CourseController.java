package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyFactory;

@Controller
public class CourseController
{
	private static final String ID = "id";
	
	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		ISurveyPersistence surveyDB = SurveyFactory.instance().makeSurveyDB();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		// This is likely something I would repeat elsewhere, I should come up with a generic solution
		// for this in milestone 2.
		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		if (null == userRoles)
		{
			// Default is user is a guest.
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);
		}
		else
		{
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
		}
		model.addAttribute("surveyStatus", surveyDB.checkSurveyStatus(courseID));
		return "course/course";
	}
}

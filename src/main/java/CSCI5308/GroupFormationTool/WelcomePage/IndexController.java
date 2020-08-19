package CSCI5308.GroupFormationTool.WelcomePage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.Courses.CoursesFactory;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

import java.util.List;


@Controller
public class IndexController
{
	@GetMapping("/")
	public String greeting(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated())
		{
			ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
			List<ICourse> allCourses = courseDB.loadAllCourses();
			model.addAttribute("courses", allCourses);
		}
		return "index";
	}
}
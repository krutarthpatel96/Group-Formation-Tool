package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstructorAdminController
{
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";
	
	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
			 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(
			Model model,
			@RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults)
	{
		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
			 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			return "course/instructoradmin";
		}
		else
		{
			return "logout";
		}
	}

	
	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID)
	{
		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		courseDB.loadCourseByID(courseID, course);
		model.addAttribute("course", course);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR) ||
			 course.isCurrentUserEnrolledAsRoleInCourse(Role.TA))
		{
			return "course/enrollta";
		}
		else
		{
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
   public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID)
   {
		ICoursePersistence courseDB = CoursesFactory.instance().makeCourseDB();
		ICourse course = CoursesFactory.instance().makeCourse();
		courseDB.loadCourseByID(courseID, course);
		IStudentFileParser parser = CoursesFactory.instance().makeStudentCSVParser(file);
		IStudentFileImport importer = CoursesFactory.instance().makeStudentFileImport(parser, course);
		ModelAndView mav = new ModelAndView("redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		mav.addObject("successful", importer.getSuccessResults());
		mav.addObject("failures", importer.getFailureResults());
		mav.addObject("displayresults", true);
		
		return mav;
   }
}

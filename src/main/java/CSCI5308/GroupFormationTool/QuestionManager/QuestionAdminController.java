package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionAdminController {
	private static final String ID = "id";
	private static final String BannerID = "bannerID";

	@RequestMapping("/question/delete")
	public ModelAndView deleteQuestion(Model model, @RequestParam(name = ID) long questionId,
			@RequestParam(name = BannerID) String bannerId) {
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		questionDB.deleteQuestionByQuestionId(questionId);
		ModelAndView mav = new ModelAndView("redirect:/question/questionmanager/title?bannerID=" + bannerId);
		return mav;
	}

	@RequestMapping("/question/add")
	public String addQuestion(Model model) {
		IQuestion question = QuestionManagerFactory.instance().makeQuestion();
		List<QuestionType> questionType = new ArrayList<QuestionType>();
		questionType = Arrays.asList(QuestionType.values());
		model.addAttribute("question", question);
		model.addAttribute("questionTypes", questionType);
		return "/question/addquestion";
	}

	@RequestMapping("/question/reviewQuestion")
	public ModelAndView addOptions(Model model, @RequestParam(name = BannerID) String bannerId,
			@ModelAttribute Question question) {
		IOptions options = QuestionManagerFactory.instance().makeOptions();
		options.addOption();
		ModelAndView mav = new ModelAndView();
		mav.addObject("question", question);
		mav.addObject("options", options);
		mav.setViewName("/question/reviewquestion");
		return mav;
	}

	@RequestMapping("/question/submit")
	public ModelAndView saveQuestion(Model model, @ModelAttribute Question question, @ModelAttribute Options options,
			@RequestParam(name = BannerID) String bannerId) {
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		long questionID = question.createQuestion(questionDB, bannerId);
		options.saveOptions(questionDB, questionID);
		ModelAndView mav = new ModelAndView("redirect:/question/questionmanager/title?bannerID=" + bannerId);
		return mav;
	}

	@RequestMapping(value = "/question/submit", params = { "addOptionRow" })
	public ModelAndView addOptionRow(@ModelAttribute Question question, @ModelAttribute Options options,
			final BindingResult bindingResult) {
		options.addOption();
		ModelAndView mav = new ModelAndView();
		mav.addObject("question", question);
		mav.addObject("options", options);
		mav.setViewName("/question/reviewquestion");
		return mav;
	}

}

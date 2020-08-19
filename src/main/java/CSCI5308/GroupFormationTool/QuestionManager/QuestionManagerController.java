package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionManagerController {
	private static final String BannerID = "bannerID";

	@RequestMapping("/question/questionmanager/title")
	public String questionsByTitle(Model model, @RequestParam(name = BannerID) String bannerID) {
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		List<IQuestion> questionList = questionDB.loadQuestionsSortedByTitle(bannerID);
		model.addAttribute("questionList", questionList);
		return "question/questions";
	}

	@RequestMapping("/question/questionmanager/date")
	public String questionsByDate(Model model, @RequestParam(name = BannerID) String bannerID) {
		IQuestionPersistence questionDB = QuestionManagerFactory.instance().makeQuestionDB();
		List<IQuestion> questionList = questionDB.loadSortedQuestionsSortedByDate(bannerID);
		model.addAttribute("questionList", questionList);
		return "question/questions";
	}

}

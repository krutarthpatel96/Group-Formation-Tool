package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.List;

public interface ICriteria {
	public int getGroupSize();

	public void setGroupSize(int groupSize);

	public List<String> getQuestionId();

	public void setQuestionId(List<String> questionId);
	
	public List<String> getWeight();

	public void setWeight(List<String> weight);

	public List<String> getCriteria();

	public void setCriteria(List<String> type);

	public HashMap<Integer, List<String>> formGroups(long courseId, IResponsePersistence responseDB);

}

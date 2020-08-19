package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.List;

public interface IStrategyContext {
	public void setStrategy(IStrategy strategy);

	public HashMap<Integer, List<String>> executeStrategy(ICriteria criteria, List<IResponseWrapper> responses);

}

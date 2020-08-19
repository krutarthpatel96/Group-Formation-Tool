package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.List;

public class StrategyContext implements IStrategyContext {
	private IStrategy strategy;

	@Override
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public HashMap<Integer, List<String>> executeStrategy(ICriteria criteria, List<IResponseWrapper> responses) {
		return strategy.formGroups(criteria, responses);
	}
}
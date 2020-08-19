package CSCI5308.GroupFormationTool.Survey;

import java.util.HashMap;
import java.util.List;

public interface IStrategy {

	public HashMap<Integer, List<String>> formGroups(ICriteria criteria, List<IResponseWrapper> responses);

}

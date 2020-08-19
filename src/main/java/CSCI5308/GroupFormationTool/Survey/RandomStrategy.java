package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.List;

public class RandomStrategy implements IStrategy {

	private HashMap<Integer, List<String>> groups;
	private List<String> banner;

	@Override
	public HashMap<Integer, List<String>> formGroups(ICriteria criteria, List<IResponseWrapper> responses) {
		groups = new HashMap<Integer, List<String>>();
		int groupIndex = 1;
		int cnt = 0;
		
		while (true) {
			banner = new ArrayList<String>();

			while (responses.size() > 0 && cnt < criteria.getGroupSize()) {
				int index = generateRandom(responses.size());
				banner.add(responses.get(index).getBannerId());
				responses.remove(index);
				cnt++;
			}

			groups.put(groupIndex++, banner);
			cnt = 0;
			if (responses.size() == 0) {
				break;
			}
		}

		return groups;
	}

	private int generateRandom(int range) {
		Random random = new Random();
		int student = random.nextInt(range);
		return student;
	}

}

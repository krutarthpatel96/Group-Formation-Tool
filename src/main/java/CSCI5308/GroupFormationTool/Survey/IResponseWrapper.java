package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

public interface IResponseWrapper {

	public long getCourseId();

	public void setCourseId(long courseId);

	public String getBannerId();

	public void setBannerId(String bannerId);

	public List<Response> getResponses();

	public void setResponses(List<Response> responses);

	public void addResponses(String bannerId, long courseId, IQuestionBank questions);

	public boolean saveResponse(IResponsePersistence responseDB);
}

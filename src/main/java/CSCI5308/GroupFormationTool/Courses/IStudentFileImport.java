package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

public interface IStudentFileImport {

	public void enrollStudentFromRecord();
	
	public List<String> getSuccessResults();

	public List<String> getFailureResults();

}

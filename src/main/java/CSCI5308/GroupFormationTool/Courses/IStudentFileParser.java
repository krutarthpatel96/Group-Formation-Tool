package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IStudentFileParser 
{

	public List<IUser> parseFile(List<String> failureResults);
	
}

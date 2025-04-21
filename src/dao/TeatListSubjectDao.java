package dao;

import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TeatListSubjectDao extends Dao{
	private String baseSql;

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception{

	}

	public List<TestListSubject> filter(int entYear,Subject subject,School school)throws Exception {

	}
}

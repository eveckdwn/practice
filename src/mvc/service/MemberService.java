package mvc.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	SqlSessionTemplate template;
	
	public MemberService(SqlSessionTemplate template) {
		this.template = template;
	}
	
	public boolean confirmId(String id) {
		return template.selectOne("member.checkId", id) == null;
	}
	
	public boolean addNewOne(Map map) {
		return template.insert("member.addNewOne", map)==1;
	}
	
	public int readId(Map map) {
		Map member = template.selectOne("member.checkId", map.get("id"));
		if(member == null) {
			return 1;
		}else if(map.get("pass").equals(member.get("PASS"))) {
			return 0;
		}else {
			return 2;
		}
	}

}

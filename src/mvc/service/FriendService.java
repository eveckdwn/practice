package mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
	@Autowired
	SqlSessionTemplate template;
	
	public FriendService(SqlSessionTemplate template) {
		this.template = template;
	}
	
	public List checkFriends(String one) {
		//	ģ�� ��ȸ
		Map map = new HashMap();
		map.put("one", one);
		return template.selectList("friend.checkFriends", map); 
	}
	
	public boolean followFriend(String one, String other) {
		//	ģ�� ��û
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.insert("friend.followFriend", map) == 1;
	}
	
	public List requestFriend(String other) {
		//	ģ�� ��û ��� ��ȸ
		Map map = new HashMap();
		map.put("other", other);
		return template.selectList("friend.requestFriend", map);
	}
	
	public boolean acceptFriend(String one, String other) {
		//	ģ�� ����
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.update("friend.acceptFriend", map) == 1;
	}
	
	public boolean refuseFriend(String one, String other) {
		//	ģ�� ����
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.delete("friend.refuseFriend", map) == 1;
	}
	
	public boolean deleteFriend(String one, String other) {
		//	ģ�� ����
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.delete("friend.deleteFriend", map) == 1;
	}

}

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
		//	模备 炼雀
		Map map = new HashMap();
		map.put("one", one);
		return template.selectList("friend.checkFriends", map); 
	}
	
	public boolean followFriend(String one, String other) {
		//	模备 夸没
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.insert("friend.followFriend", map) == 1;
	}
	
	public List requestFriend(String other) {
		//	模备 夸没 格废 炼雀
		Map map = new HashMap();
		map.put("other", other);
		return template.selectList("friend.requestFriend", map);
	}
	
	public boolean acceptFriend(String one, String other) {
		//	模备 荐遏
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.update("friend.acceptFriend", map) == 1;
	}
	
	public boolean refuseFriend(String one, String other) {
		//	模备 芭例
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.delete("friend.refuseFriend", map) == 1;
	}
	
	public boolean deleteFriend(String one, String other) {
		//	模备 昏力
		Map map = new HashMap();
		map.put("one", one);
		map.put("other", other);
		return template.delete("friend.deleteFriend", map) == 1;
	}

}

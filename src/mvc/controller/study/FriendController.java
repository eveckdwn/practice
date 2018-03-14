package mvc.controller.study;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.service.FriendService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	@Autowired
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.GET)	//	친구 목록 조회
	public String listHandle(@RequestParam String one, Model model) {
		List friends = friendService.checkFriends(one);
		model.addAttribute("friends", friends);
		return "friend/friends";
	}
	
	@RequestMapping("/follow")
	public String followHandle() {
		return "";
	}
	
	@RequestMapping("/request")
	public String requestHandle() {
		return "";
	}

}

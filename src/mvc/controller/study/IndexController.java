package mvc.controller.study;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.service.GreetService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	GreetService greetService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String Alpha01Handler(Model model) {
		model.addAttribute("ment", greetService.make());
		return "index";
	}

}

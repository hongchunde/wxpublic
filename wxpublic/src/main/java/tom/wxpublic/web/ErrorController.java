package tom.wxpublic.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorController {

	@RequestMapping(value = "/error", 
			method = {RequestMethod.GET,RequestMethod.POST})
	private String list(String model) {
		
		return "error";
	}
	
}

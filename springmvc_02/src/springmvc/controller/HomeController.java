package springmvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.domain.Person;
@Controller
@RequestMapping(value="/home") //根路径
public class HomeController {
	
    @RequestMapping(value="/a")//子路径
	public String method1(HttpServletRequest request,String name,@RequestParam(value="id")Long uid,Person person,Map<String,Object> model){
		System.out.println(request.getParameter("id"));
		System.out.println(uid);
		request.setAttribute("name", name);
		model.put("key", person);
		return "home";
	}
    
    @RequestMapping(value="/b")
    public String method2(){
    	
    	return "";
    }
}

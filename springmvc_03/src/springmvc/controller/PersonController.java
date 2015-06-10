package springmvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.domain.Person;
import springmvc.service.PersonService;

@Controller
@RequestMapping(value="/person")
public class PersonController {
     @Resource(name="personService")
     private PersonService personService;
     @RequestMapping(value="/save")
     public String save(Person person){
    	 this.personService.save(person);
    	 return "success";
     }
     @RequestMapping(value="/personlist")
     public String getPersonList(HttpServletRequest request){
    	 List<Person> list=this.personService.getPersonList();
    	 request.setAttribute("list", list);
    	 return "personlist";
     }
     @RequestMapping(value="/deleteById")
     public String deleteByIdt(Person person){
    	 this.personService.delete(person);
    	 return "redirect:/person/personlist";
     }
     
     @RequestMapping(value="/addUI")
     public String addUI(){
    	
    	 return "addUI";
     }
     @RequestMapping(value="/add")
     public String add(Person person){
    	 this.personService.save(person);
    	 return "redirect:/person/personlist";
     }
     @RequestMapping(value="/updateUI")
     public String updateUI(String id,HttpServletRequest req){
    	 Person person=this.personService.getById(id);
    	 req.setAttribute("person", person);
    	 return "updateUI";
     }
     @RequestMapping(value="/update")
     public String updateUI(Person person){
    	 this.personService.update(person);
    	 return "redirect:/person/personlist";
     }
     @RequestMapping(value="/batchDelete")
     public String batchDelete(String idsValue){
    	 String str=idsValue.substring(0,idsValue.length()-1);
    	 String[] strArr=str.split(",");
    	 Person person=new Person();
    	 for(String id:strArr){
    		 person.setId(id);
    		 this.personService.delete(person);
    	 }
    	 
    	 return "redirect:/person/personlist";
     }
     
}

package OAproject.Action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller("forwardAction")
@Scope("prototype")
public class ForwardAction{

	public String left(){
		return "left";
	}
	public String right(){
		return "right";
	}
	public String top(){
		return "top";
	}
	public String bottom(){
		return "bottom";
	}
	public String kynamic(){
		return "kynamic";
	}
}

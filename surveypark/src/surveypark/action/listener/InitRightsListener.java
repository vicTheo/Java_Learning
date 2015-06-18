package surveypark.action.listener;

import java.util.HashMap;
/**
 * 初始化权限监听器
 */
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import surveypark.domain.security.Right;
import surveypark.service.RightService;
@Component
public class InitRightsListener implements ApplicationListener,
		ServletContextAware {
    @Resource(name="rightService")
	private RightService rightService;
    //接受sc
    private ServletContext servletContext;

	public void onApplicationEvent(ApplicationEvent arg0) {
		
		if(arg0 instanceof ContextRefreshedEvent){
			Map<String,Right> map=new HashMap<String, Right>();
			List<Right> list=rightService.getAllEntities();
			for(Right r:list){
				map.put(r.getRightUrl(), r);
			}
			if(servletContext!=null){
				servletContext.setAttribute("all_rights_map", map);
			}
		}
	}

	//注入sc
	public void setServletContext(ServletContext arg0) {
		this.servletContext=arg0;
	}

}

package cinema.dao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Initialization....");
		
		ConnectionManager.open();
		
		System.out.println("Done!");
		
	}
	
	

}

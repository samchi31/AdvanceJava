package servletTest;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] requestDestroyed() 호출");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("[MyServletRequestListener] requestInitialized() 호출");
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println(
				"[MyServletRequestListener] attributeAdded() 호출 => " + srae.getName() + "=" + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println(
				"[MyServletRequestListener] attributeRemoved() 호출 => " + srae.getName() + "=" + srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println(
				"[MyServletRequestListener] attributeReplaced() 호출 => " + srae.getName() + "=" + srae.getValue());
	}

}

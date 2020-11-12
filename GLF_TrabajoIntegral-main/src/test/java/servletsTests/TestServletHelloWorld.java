package servletsTests;

import static org.junit.Assert.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.Mockito;

import servlets.HelloWorld;

public class TestServletHelloWorld extends Mockito{
	@Test
	public void testServlet() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
		
		new HelloWorld().doPost(request, response);
		
		writer.flush();
		assertTrue(stringWriter.toString().contains("Served at: "));
	}

}

package ch.supsi.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/item/*")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

	private final static String NAME = "name";
	private final static String DESCRIPTION = "description";
	private final static String AUTHOR = "author";

	private ObjectMapper objectMapper = new ObjectMapper();
	Server server = new Server();

	//GET /item/
	//GET /item/[0-9]
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {

		if(req.getPathInfo() == null){
			try {
				res.getWriter().println("D");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String[] pathFragments = req.getPathInfo().split("/");
		try {
			res.getWriter().println(pathFragments.length + "   " + req.getPathInfo());
			for(String s : pathFragments)
				res.getWriter().println(s);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if(pathFragments.length > 1){
			try {
				//res.sendError(400, "bad url");
				res.getWriter().println("ciaoo");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		printInfo(req, res);
		res.setContentType("application/json");
		try {
			objectMapper.writeValue(res.getWriter(), server.items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		Item item = new Item(req.getParameter(NAME), req.getParameter(DESCRIPTION), req.getParameter(AUTHOR));
		if(server.putItem(item)){
			String jsonItem = null;
			//Json
			try {
				jsonItem = objectMapper.writeValueAsString(item);
				res.setContentType("application/json");
				res.getWriter().println(jsonItem);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				res.sendError(409, "DUPLICATE!");
				res.getWriter().println();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res){
		res.setContentType("text/plain");
		try {
			res.getWriter().println("AAAA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static void printInfo(HttpServletRequest req, HttpServletResponse res){

		try {
			res.getWriter().println("=====PATH INFO======\n");

			res.getWriter().println("getContextPath(): " + req.getContextPath());
			res.getWriter().println("getPathInfo(): " + req.getPathInfo());
			res.getWriter().println("getAuthType(): " + req.getAuthType());


			res.getWriter().println("=====HEADER INFO======\n");

			Enumeration<String> headersNames = req.getHeaderNames();
			while (headersNames.hasMoreElements()){
				String headerName = headersNames.nextElement();
				res.getWriter().println(headerName + ": " + req.getHeader(headerName));
			}

			res.getWriter().println("=====PATAMETER INFO======\n");

			Enumeration<String> parametersNames = req.getParameterNames();
			while (parametersNames.hasMoreElements()){
				String parameterName = parametersNames.nextElement();
				res.getWriter().println(parameterName + ": " + req.getParameter(parameterName));
			}

			res.getWriter().println("=====END INFO======\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


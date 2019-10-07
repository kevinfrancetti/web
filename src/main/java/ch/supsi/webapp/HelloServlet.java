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

@WebServlet(value="/hello")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

	private static void printInfo(HttpServletRequest req, HttpServletResponse resp){

		try {
			resp.getWriter().println("=====HEADER INFO_======\n");

			Enumeration<String> headersNames = req.getHeaderNames();
			while (headersNames.hasMoreElements()){
			    String headerName = headersNames.nextElement();
				resp.getWriter().println(headerName + ": " + req.getHeader(headerName));
			}

			resp.getWriter().println("=====PATAMETER INFO======\n");

			Enumeration<String> parametersNames = req.getParameterNames();
			while (parametersNames.hasMoreElements()){
				String parameterName = parametersNames.nextElement();
				resp.getWriter().println(parameterName + ": " + req.getParameter(parameterName));
			}

			resp.getWriter().println("=====END INFO======\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Server server = new Server();

	private final static String NAME = "name";
	private final static String DESCRIPTION = "description";
	private final static String AUTHOR = "author";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		System.out.println("GET");
		printInfo(req, res);

		Item item = new Item();
		item.setName(req.getParameter(NAME));
		item.setDescription(req.getParameter(DESCRIPTION));
		item.setAuthor(req.getParameter(AUTHOR));

		server.putItem(item);

		ObjectMapper objectMapper = new ObjectMapper();

		String jsonItem = null;
		//Json
		try {
			jsonItem = objectMapper.writeValueAsString(server.getItem(0));
			res.getWriter().println(jsonItem);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST");
		doGet(req, resp);
	}
}

class Item {
	private String name = null;
	private String description = null;
	private String author = null;

	public String getName(){
		return name;
	}

	public void setName(String s){
		name = s;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String s){
		description = s;
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String a){
		author = a;
	}

	public boolean equals(Object o){
		Item i = (Item) o;
		if(!name.equals(i.name)) return false;
		if(!description.equals(i.description)) return false;
		if(!author.equals(i.author)) return false;
		return true;
	}

	public String toString(){
		return name + "\n" + description + "\n" + author;
	}

}

package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/items/*")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String AUTHOR = "author";

    //Error list used
    private final static Integer DUPLICATE = 409;
    private final static Integer BAD_REQUEST = 400;
    private final static Integer NOT_FOUND = 404;

    private ObjectMapper objectMapper = new ObjectMapper();
    Server server = new Server();


    String[] tokenize(String s) {
        return s.split("/");
    }



    //GET /item/
    //GET /item/[0-9]
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        // GET /items
        if (req.getPathInfo() == null) {
            res.setContentType("application/json");
            objectMapper.writeValue(res.getWriter(), server.items);
            return;
        } else {// GET /items/...
            String[] tokens = tokenize(req.getPathInfo());
            if(tokens.length == 0){
                res.setContentType("application/json");
                objectMapper.writeValue(res.getWriter(), server.items);
                return;
            }else if (tokens.length > 2) {
                res.sendError(NOT_FOUND, "NOT FOUND, bad url");
                return;
            }

            //Get the number after the slash bar es: /items/3 sets index_index = 3
            int item_index = Integer.parseInt(tokens[1]);
            if(server.items.size() <= item_index){
                res.sendError(NOT_FOUND, "No such item with index: " + item_index);
                return;
            }
            res.setContentType("application/json");
            objectMapper.writeValue(res.getWriter(), server.items.get(item_index));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        //res.getWriter().println(req.getParameterMap().containsKey("name"));
        if (req.getParameterMap().size() == 3) {
            if (!req.getParameterMap().containsKey(NAME)) {
                res.sendError(BAD_REQUEST, "wrong paramater name or more");
                return;
            } else if (!req.getParameterMap().containsKey(DESCRIPTION)) {
                res.sendError(BAD_REQUEST, "wrong paramater description or more");
                return;
            } else if (!req.getParameterMap().containsKey(AUTHOR)) {
                res.sendError(BAD_REQUEST, "wrong paramater author");
                return;
            }

            //If everything is ok add item
            Item item = new Item(req.getParameter(NAME), req.getParameter(DESCRIPTION), req.getParameter(AUTHOR));
            if (server.putItem(item)) {
                String jsonItem = null;
                //Json
                jsonItem = objectMapper.writeValueAsString(item);
                //res.setContentType("application/json");
                //res.getWriter().println(jsonItem);
            } else {
                res.sendError(DUPLICATE, "DUPLICATE ITEM!");
                res.getWriter().println();
            }

        } else {
            res.sendError(BAD_REQUEST, "wrong parameter size");
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // DELETE /items
        if (req.getPathInfo() == null) {
            res.setContentType("application/json");
            res.sendError(BAD_REQUEST, "SELECT ONE ITEM");
            return;
        } else {// DELETE /items/...
            String[] tokens = tokenize(req.getPathInfo());
            if(tokens.length == 0){
                res.setContentType("application/json");
                res.sendError(BAD_REQUEST, "SELECT ONE ITEM");
                return;
            }else if (tokens.length > 2) {
                res.sendError(NOT_FOUND, "NOT FOUND, bad url");
                return;
            }

            //Get the number after the slash bar es: /items/3 sets index_index = 3
            int item_index = Integer.parseInt(tokens[1]);
            if(server.items.size() <= item_index){
                res.sendError(NOT_FOUND, "No such item with index: " + item_index);
                return;
            }
            res.setContentType("application/json");
            objectMapper.writeValue(res.getWriter(), server.items.remove(item_index));
        }
    }

    private int getItemIndexFromRequest(HttpServletRequest req) throws IOException {
        if (req.getPathInfo() == null) {
            return -1;
        } else {
            String[] tokens = tokenize(req.getPathInfo());
            if (tokens.length == 1) {
                int item_index = Integer.parseInt(tokens[1]);
                return item_index;
            }
            return -1;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // DELETE /items
        if (req.getPathInfo() == null) {
            res.setContentType("application/json");
            res.sendError(BAD_REQUEST, "SELECT ONE ITEM");
            return;
        } else {// DELETE /items/...
            String[] tokens = tokenize(req.getPathInfo());
            if(tokens.length == 0){
                res.setContentType("application/json");
                res.sendError(BAD_REQUEST, "SELECT ONE ITEM");
                return;
            }else if (tokens.length > 2) {
                res.sendError(NOT_FOUND, "NOT FOUND, bad url");
                return;
            }

            if (req.getParameterMap().size() == 3) {
                if (!req.getParameterMap().containsKey(NAME)) {
                    res.sendError(BAD_REQUEST, "wrong paramater name or more");
                    return;
                } else if (!req.getParameterMap().containsKey(DESCRIPTION)) {
                    res.sendError(BAD_REQUEST, "wrong paramater description or more");
                    return;
                } else if (!req.getParameterMap().containsKey(AUTHOR)) {
                    res.sendError(BAD_REQUEST, "wrong paramater author");
                    return;
                }
            }

            //Get the number after the slash bar es: /items/3 sets index_index = 3
            int item_index = Integer.parseInt(tokens[1]);
            if(server.items.size() <= item_index){
                res.sendError(NOT_FOUND, "No such item with index: " + item_index);
                return;
            }
            res.setContentType("application/json");
            server.items.get(item_index).name = req.getParameter(NAME);
            server.items.get(item_index).description = req.getParameter(DESCRIPTION);
            server.items.get(item_index).author = req.getParameter(AUTHOR);
        }
    }

    //Used for debugging
    private static void printInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.getWriter().println("=====PATH INFO======\n");

        res.getWriter().println("getContextPath(): " + req.getContextPath());
        res.getWriter().println("getPathInfo(): " + req.getPathInfo());
        res.getWriter().println("getAuthType(): " + req.getAuthType());


        res.getWriter().println("=====HEADER INFO======\n");

        Enumeration<String> headersNames = req.getHeaderNames();
        while (headersNames.hasMoreElements()) {
            String headerName = headersNames.nextElement();
            res.getWriter().println(headerName + ": " + req.getHeader(headerName));
        }

        res.getWriter().println("=====PATAMETER INFO======\n");

        Enumeration<String> parametersNames = req.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String parameterName = parametersNames.nextElement();
            res.getWriter().println(parameterName + ": " + req.getParameter(parameterName));
        }

        res.getWriter().println("=====END INFO======\n\n");

    }

}


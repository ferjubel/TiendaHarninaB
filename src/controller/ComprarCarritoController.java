package controller;

import dao.GenericDao;
import dto.Login;
import org.json.simple.JSONObject;
import procedures.ProceduresProductos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/comprarCarrito")
public class ComprarCarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    JSONObject oneJson;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        Login login = new Login();

        try {
            login.setIdClient((int) session.getAttribute("idClient"));
            new GenericDao().execProcedure(ProceduresProductos.COMPRAR_CARRITO.getName(),login);
            oneJson.put("resultado",true);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
            oneJson.put("resultado",false);
        }

        response.getWriter().write(oneJson.toJSONString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}

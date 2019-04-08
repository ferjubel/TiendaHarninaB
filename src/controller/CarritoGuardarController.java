package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dao.GenericDao;
import dto.Carrito;
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
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/guardarCarrito")
public class CarritoGuardarController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    JSONObject oneJson;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        Login login = new Login((int) session.getAttribute("idClient"));
        String jsonSring = request.getParameter("json");
        final Gson gson = new Gson();
        final Type tipo = new TypeToken<List<Carrito>>() {
        }.getType();
        final List<Carrito> carro = gson.fromJson(jsonSring, tipo);
        System.out.println(jsonSring);
        Boolean guardado = true;
        try {
            new GenericDao().execProcedure(ProceduresProductos.DELETE_CARRITO.getName(), login);
            for (int i = 0; i < carro.size() && guardado; i++) {
                new GenericDao().execProcedure(ProceduresProductos.GUARDAR_CARRITO.getName(), carro.get(i));
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
        oneJson = new JSONObject();
        oneJson.put("guardado", guardado);
        response.getWriter().write(oneJson.toJSONString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
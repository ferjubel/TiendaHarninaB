package controller;

import dao.GenericDao;
import dto.Carrito;
import dto.Login;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import procedures.ProceduresProductos;
import reflection.JsonTransferObject;

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
import java.util.ArrayList;

@WebServlet("/getCarrito")
public class CarritoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    HttpSession session;
    JSONObject oneJson;
    JSONArray arrayJson = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        arrayJson = new JSONArray();
        session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        ArrayList<Carrito> listaModels = null;
        Carrito carrito = new Carrito();

        try {
            carrito.setIdCliente((int) session.getAttribute("idClient"));
            listaModels = (ArrayList<Carrito>) new GenericDao().execProcedure(ProceduresProductos.GET_CARRITO.getName(),carrito);
            System.out.println("modelos de la bbdd: "+listaModels);
            listaModels.forEach(modeloEntity -> {
                oneJson = new JSONObject();
                oneJson.put("IdModelo",modeloEntity.getIdModelo());
                oneJson.put("IdCliente",session.getAttribute("idClient").toString());
                oneJson.put("cantidadPedida",modeloEntity.getCantidadPedida());
                oneJson.put("actualPrecioModelo", modeloEntity.getActualPrecioModelo());
                arrayJson.add(oneJson);
            });
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
        /*---MOCK---
        oneJson = new JSONObject();
        oneJson.put("IdModelo",6);
        oneJson.put("IdCliente",session.getAttribute("idClient"));
        oneJson.put("cantidadPedida",1);
        oneJson.put("actualPrecioModelo",333);
        arrayJson.add(oneJson);
        oneJson = new JSONObject();
        oneJson.put("IdModelo",1);
        oneJson.put("IdCliente",session.getAttribute("idClient"));
        oneJson.put("cantidadPedida",4);
        oneJson.put("actualPrecioModelo",138.85);
        arrayJson.add(oneJson);
        //System.out.println("arrayJson a javascript");
        //System.out.println(arrayJson);
        ---MOCK--- */
        response.getWriter().write(arrayJson.toJSONString());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}

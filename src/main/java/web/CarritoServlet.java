package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

/**
 *
 * @author clopez
 */
@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        res.setContentType("text/html;chraset=UTF-8");
        
        String articuloNuevo = req.getParameter("articulo");
        
        HttpSession sesion = req.getSession();
        
        List<String>articulos = (List<String>)sesion.getAttribute("articulos");
        if(articulos == null){
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        if(articuloNuevo != null && !articuloNuevo.trim().equals("")){
            articulos.add(articuloNuevo);
        }
        
        try (PrintWriter out = res.getWriter()) {
            out.print ("<h1>Lista de articulos</h1> <br>");
            for(String articulo : articulos){
                out.print("<li>"+articulo +"</li>");
            }
            out.print("<br>");
            out.print("<a href='/EjemploListaCompras'>Regresar al inicio</a>");
        }
    }
}

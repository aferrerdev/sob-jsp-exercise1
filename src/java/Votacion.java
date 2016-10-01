/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alex
 */
@WebServlet(urlPatterns = {"/Votacion"})
public class Votacion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Votacion</title>");
            out.println("</head>");
            out.println("<body>");

            // Obtenim comptador de vots i si és null inicialitzar.
            ServletContext sc = getServletContext();
            // Guardarem el contador en el contexte del Servlet (Zona Aplicació)
            ContadorVotos contador = (ContadorVotos) sc.getAttribute("totalVotes");
            if (contador == null) {
                contador = new ContadorVotos();
                contador.setValue(0);
                out.println("<p>You are the first person!</p>");
            }
            sc.setAttribute("totalVotes", contador);

            // Inicialitzar Sessió usuari.
            HttpSession sessio = request.getSession(true);
            Persona persona = (Persona) sessio.getAttribute("persona");
            if (persona == null) {
                persona = new Persona(request);
                // Mostrem el formulari, ja que la persona no ha votat encara.
                if (persona.getDni() == null) {
                    out.println("<p>Total vots "+contador.getValue());
                    out.println("<h1>Do your votation</h1>");
                    out.println("<form action=\"#\" method=\"post\">");
                    out.println("DNI: <input type=\"text\" name=\"dni\" /><br />");
                    out.println("Nom:<input type=\"text\" name=\"nom\" /><br />");
                    out.println("1r Cognom: <input type=\"text\" name=\"cognom1\" /><br />");
                    out.println("2n cognom: <input type=\"text\" name=\"cognom2\" /><br />");
                    out.println("<input type=\"submit\" value=\"VOTAR\" /><br />");
                    out.println("</form>");
                } 
                else {
                    // Guardar les dades request de la persona i guardarles com a Variable de Sessió.
                    sessio.setAttribute("persona", persona);
                    
                    // Cookies: comprovar si ja ha votat
                    // Comprovem que aquest DNI no hagi votat
                    Cookie[] cookies = request.getCookies();
                    boolean found = false;
                    for (Cookie cookie: cookies){
                        if (cookie.getName().equals(persona.dni)) {
                            found = true;
                            break;
                        }
                    }
                    
                   if (!found) {
                        // Cookie no trobada. Generem una nova cookie amb el DNI per indicar que ja ha votat
                        Cookie c = new Cookie(persona.dni, "1"); 
                        c.setMaxAge(60*60*24*365); // fem que la cookie tardi un any a caducar.
                        response.addCookie(c);
                        contador.addVote();
                        out.println("<p>Total vots "+contador.getValue());
                        out.println("Heu votat correctament.");
                    } else {
                       out.println("<p>Voste ja ha votat</p>");
                        out.println("L'usuari amb DNI " + persona.dni + " ja havia votat abans.");
                    }                            
                    
                    // Tancar sessió
                    out.println("<a href=\"http://localhost:8080/sob-jsp-exercise1/logoutServlet\">Logout</a>");
                }
            }

        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

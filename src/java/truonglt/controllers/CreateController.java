/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package truonglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import truonglt.utils.XMLUtils;

/**
 *
 * @author datnlm
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String XMLFILE = "/WEB-INF/item.xml";
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.html";
    private boolean found = false;

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
        String url = ERROR;
        try {
            String id = request.getParameter("txtID");
            String type = request.getParameter("cboType");
            String name = request.getParameter("txtName");
            String description = request.getParameter("txtDescription");
            String point = request.getParameter("txtPoint");
            String status = request.getParameter("cboStatus");

            String realPath = getServletContext().getRealPath("/");
            String filePath = realPath + XMLFILE;

            Document doc = XMLUtils.parseFileToDOM(filePath);
            if (doc != null) {
                found = false;
                checkID(id, doc);
                if (found) {
                    url = ERROR;
                } else {
                    Element itemE = doc.createElement("item");
                    itemE.setAttribute("type", type);

                    Element idE = doc.createElement("id");
                    idE.setTextContent(id);

                    Element nameE = doc.createElement("name");
                    nameE.setTextContent(name);

                    Element descriptionE = doc.createElement("description");
                    descriptionE.setTextContent(description);

                    Element pointE = doc.createElement("point");
                    pointE.setTextContent(point);

                    Element statusE = doc.createElement("status");
                    statusE.setTextContent(status);

                    itemE.appendChild(idE);
                    itemE.appendChild(nameE);
                    itemE.appendChild(descriptionE);
                    itemE.appendChild(pointE);
                    itemE.appendChild(statusE);

                    NodeList listItem = doc.getElementsByTagName("items");
                    if (listItem != null) {
                        listItem.item(0).appendChild(itemE);
                        boolean result = XMLUtils.writeXML(doc, filePath);
                        if (result) {
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void checkID(String id, Node node) {
        if (node == null) {
            return;
        }
        if (node.getNodeName().equals("item")) {
            NodeList childrenOfStudent = node.getChildNodes();
            for (int i = 0; i < childrenOfStudent.getLength(); i++) {
                Node tmp = childrenOfStudent.item(i);
                if (tmp.getNodeName().equals("id")) {
                    if (id.equals(tmp.getTextContent().trim())) {
                        found = true;
                        return;
                    }

                }
            }
        }
        NodeList children = node.getChildNodes();
        int count = 0;

        while (count < children.getLength()) {
            checkID(id, children.item(count++));
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

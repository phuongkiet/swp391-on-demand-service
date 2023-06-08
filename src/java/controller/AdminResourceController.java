/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.BlockResourceEntity;
import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import service.ResourceService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AdminResourceController", urlPatterns = {"/admin-resource"})
public class AdminResourceController extends HttpServlet {

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
        String controller = (String) request.getAttribute("controller");
        String action = (String) request.getAttribute("action");
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user.getRoleID() == 4) {
            try {
                switch (action) {
                    case "table-resource":
                        tableResource(request, response, user);
                        break;
                    case "update-resource":
                        updateResource(request, response);
                        break;
                    case "update-resource-handler":
                        updateResourceHandler(request, response);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/home/index.do");
        }

    }

    protected void tableResource(HttpServletRequest request, HttpServletResponse response, UserEntity user) throws ServletException, IOException, SQLException {

        ResourceService rService = new ResourceService();
        String op = (String) request.getParameter("op");
        String indexPage = request.getParameter("page");
        HttpSession session = request.getSession();
        int userBlockId = user.getBID();
        if (indexPage == null) {
            indexPage = "1";
        }
        int page = Integer.parseInt(indexPage);
        List<BlockResourceEntity> list = new ArrayList<>();
        List<BlockResourceEntity> list1 = new ArrayList<>();
        int endPage;
        int numberOfEntitiesInLastPage;
        switch (op) {
            case "getAll":
                list1 = rService.getAllResource(userBlockId);
                endPage = list1.size() / 10;
                if (list1.size() % 10 != 0) {
                    endPage++;
                }
                numberOfEntitiesInLastPage = list1.size() % 10;
                if (page == endPage) {
                    for (int i = 0 + 10 * (page - 1); i < (10 * page) - (10 - numberOfEntitiesInLastPage); i++) {
                        list.add(list1.get(i));
                    }
                } else {
                    for (int i = 0 + 10 * (page - 1); i < 10 * page; i++) {
                        list.add(list1.get(i));
                    }
                }
                break;
            case "search":
                String searchValue = (String) request.getParameter("txtSearch");
                list1 = rService.getResourceBySearched(searchValue, userBlockId);
                String isSortedSearch = "";
                isSortedSearch += (String) request.getParameter("isSortedSearch");
                String searchOption = (String) request.getParameter("searchOption");
                if (isSortedSearch.equals("on")) {
                    if (!list1.isEmpty()) {
                        if (searchOption.equals("quantityAsc")) {
                            Collections.sort(list1, (e1, e2) -> {
                                return e1.getQuantity() - e2.getQuantity();
                            });
                        } else {
                            Collections.sort(list1, (e1, e2) -> {
                                return e2.getQuantity() - e1.getQuantity();
                            });
                        }
                    }
                }
                endPage = list1.size() / 10;
                if (list1.size() % 10 != 0) {
                    endPage++;
                }
                numberOfEntitiesInLastPage = list1.size() % 10;
                if (page == endPage) {
                    for (int i = 0 + 10 * (page - 1); i < (10 * page) - (10 - numberOfEntitiesInLastPage); i++) {
                        list.add(list1.get(i));
                    }
                } else {
                    for (int i = 0 + 10 * (page - 1); i < 10 * page; i++) {
                        list.add(list1.get(i));
                    }
                }

                request.setAttribute("searchValue", searchValue);
                request.setAttribute("searchOption", searchOption);
                break;
            case "filter":
                String filterOption = (String) request.getParameter("optionQuantity");
                list1 = rService.getAllResource(userBlockId);

                if (filterOption.equals("quantityAsc")) {
                    Collections.sort(list1, (e1, e2) -> {
                        return e1.getQuantity() - e2.getQuantity();
                    });
                } else {
                    Collections.sort(list1, (e1, e2) -> {
                        return e2.getQuantity() - e1.getQuantity();
                    });
                }
                endPage = list1.size() / 10;
                if (list1.size() % 10 != 0) {
                    endPage++;
                }
                numberOfEntitiesInLastPage = list1.size() % 10;
                if (page == endPage) {
                    for (int i = 0 + 10 * (page - 1); i < (10 * page) - (10 - numberOfEntitiesInLastPage); i++) {
                        list.add(list1.get(i));
                    }
                } else {
                    for (int i = 0 + 10 * (page - 1); i < 10 * page; i++) {
                        list.add(list1.get(i));
                    }
                }
                request.setAttribute("optionQuantity", filterOption);
                break;
        }
        endPage = list1.size() / 10;
        //Lay tong so luong san pham trong db
        if (list1.size() % 10 != 0) {
            endPage++;
        }

        request.setAttribute("op", op);
        request.setAttribute("page", page - 1);
        request.setAttribute("list", list);
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);

    }

    protected void updateResource(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String bId = (String) request.getParameter("blockId");
        String rId = (String) request.getParameter("resourceId");
        if (bId.isBlank() || rId.isBlank()) {
            response.sendRedirect("/admin-resource/table-resource.do?op=getAll");
        } else {
            ResourceService rService = new ResourceService();
            BlockResourceEntity br = rService.getBlockResource(Integer.parseInt(bId), Integer.parseInt(rId));
            request.setAttribute("blockResourceEntity", br);
            request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
        }

    }

    protected void updateResourceHandler(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        String bId = (String) request.getParameter("blockId");
        String bName = (String) request.getParameter("blockName");
        String rId = (String) request.getParameter("resourceId");
        String rName = (String) request.getParameter("resourceName");
        String quantity = (String) request.getParameter("quantity");
        String op = (String) request.getParameter("op");
        String message;
        switch (op) {
            case "update":
                if (bId.isBlank() || bName.isBlank() || rId.isBlank() || rName.isBlank() || quantity.isBlank()) {
                    message = "You need to select UPDATE operation before going to update page";
                } else {
                    BlockResourceEntity br = new BlockResourceEntity(Integer.parseInt(bId), bName, Integer.parseInt(rId), rName, Integer.parseInt(quantity));
                    ResourceService rService = new ResourceService();
                    rService.updateResource(br);
                    message = "Updated Successfully";
                }
                response.sendRedirect(request.getContextPath() + String.format("/admin-resource/table-resource.do?op=getAll&message=%s", message));
                break;
            case "cancel":
                response.sendRedirect(request.getContextPath() + "/admin-resource/table-resource.do?op=getAll");
                break;
        }

//        request.setAttribute("action", "table-resource");
//        request.setAttribute("op", "getAll");
//        request.getRequestDispatcher("/WEB-INF/layouts/admin.jsp").forward(request, response);
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

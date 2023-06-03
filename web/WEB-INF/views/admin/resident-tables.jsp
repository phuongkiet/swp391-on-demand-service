<%-- 
    Document   : resident-tables
    Created on : Jun 2, 2023, 9:53:26 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<div class="pagetitle">
    <h1>Resident Tables</h1>
    <nav>
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/admin/admin-dashboard.do" />">Home</a></li>
            <li class="breadcrumb-item">Tables</li>
            <li class="breadcrumb-item active">Resident Tables</li>
        </ol>
    </nav>
</div><!-- End Page Title -->
<section class="section">
    <div class="row">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Resident Tables</h6>
            </div>
            <div class="row">
                <div class="card-header py-3 col-md-6">
                    <form>
                        <div class="form-group pb-2">
                            <label  class="p-1" for="exampleInputEmail1">Search</label>
                            <input type="text" class="form-control" id="exampleInputEmail1" name = "txtSearch" value="${searchValue}" aria-describedby="emailHelp" placeholder="Enter resident name">
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" name = "op" value="search">Search</button>
                    </form>

                </div>
                <div class="card-header py-3 col-md-6">
                    <form>
                        <div class="form-group pb-2">
                            <label  class="p-1" for="exampleInputEmail1">Filter</label>
                            <select class="form-select" aria-label="Default select example" name="optionBlock">
                                <option ${optionBlock=="blockAsc"?"selected":""} value="blockAsc">Order by block ascending</option>
                                <option ${optionBlock=="blockDesc"?"selected":""} value="blockDesc">Order by block descending</option>
                            </select>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary" name = "op" value="filter">Filter</button>
                    </form>
                </div>
            </div>
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Room</th>
                                    <th>Block</th>
                                    <th>Status</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <c:forEach var="r" items="${list}" varStatus="loop">
                                <tbody>
                                    <tr>
                                        <td>${r.AID}</td>
                                        <td>${r.name}</td>
                                        <td>${r.email}</td>
                                        <td>${r.phone}</td>
                                        <td>${r.room}</td>
                                        <td>${r.BID}</td>
                                        <td>
            <!--                                <a href="<c:url value="/admin/table_update.do?id=${product.id}" />">Update</a> |
                                            <a href="<c:url value="/admin//table_delete.do?id=${product.id}"/>" id="delete" data-toggle="modal" data-target="#deleteModal">
                                                Delete
                                            </a>                         -->
                                        </td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<nav aria-label="Page navigation example" class="col-lg-12" style="display: flex; justify-content: center">
    <ul class="pagination">

        <c:if test="${currentPage > 1}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?&page=${currentPage - 1}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">Previous</a></li>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?page=${i}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                    <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?page=${i}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">${i}</a></li>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/resident-tables.do?page=${currentPage + 1}&op=${op}&txtSearch=${searchValue}&optionBlock=${optionBlock}" />">Next</a></li>
        </c:if>
    </ul>
</nav>

<%-- 
    Document   : service-list
    Created on : May 26, 2023, 8:53:55 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <section class="services-page-1">
            <div class="container">
                <div class="row">
                    <!--Services Two single Start-->
                    <div class="col-xl-4 col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="100ms">
                        <div class="services-two__single">
                            <div class="services-two__img-box">
                                <div class="services-two__img">
                                    <img src="${pageContext.request.contextPath}/assets/images/services/services-2-1.jpg" alt="">
                                </div>
                                <div class="services-two__icon">
                                    <span class="icon-plumbing"></span>
                                </div>
                            </div>
                            <div class="services-two__content">
                                <h3 class="services-two__title"><a href="plumbing-services.html">Plumbing Services</a>
                                </h3>
                                <p class="services-two__text">Lorem ipsum is simply free text dolor sit am adipi we help
                                    you ensure everyone.</p>
                                <a href="<c:url value="/service/service-detail.do" />" class="services-two__btn">read more</a>
                            </div>
                        </div>
                    </div>
                    <!--Services Two single End-->
                </div>
            </div>
        </section>
    </body>
</html>
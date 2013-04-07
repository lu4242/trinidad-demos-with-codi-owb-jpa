<!--
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
	   
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://portals.apache.org/pluto" prefix="pluto" %>

<html>
<head>
    <title>Pluto Portal</title>
    <style type="text/css" title="currentStyle" media="screen">
        @import "<c:out value="${pageContext.request.contextPath}"/>/pluto.css";
        @import "<c:out value="${pageContext.request.contextPath}"/>/portlet-spec-1.0.css";
        <c:forEach items="${org_apache_pluto_embedded_extraStyles}" var="style">
	    @import "<c:out value="${pageContext.request.contextPath}"/><c:out value="${style}"/>";
	    </c:forEach>
    </style>
    <script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}"/>/pluto.js">
    </script>
</head>

<body>

<div id="portal">

    <!-- Header block: the Apache Pluto banner image and description -->
    <div id="header">
        <h1>Apache Pluto</h1>

        <p>An Apache Portals Project</p>
    </div>

    <!-- Logout link -->
    <c:if test="${pageContext.request.remoteUser != null}">
        <div id="logout">
            <a href="<c:url value='/Logout'/>">Logout</a>
        </div>
    </c:if>
    
    <!-- Content block: portlets are divided into two columns/groups -->
    <div id="content">
        <pluto:isMaximized var="isMax"/>

        <!-- Left column -->
        <c:choose>
            <c:when test="${isMax}">
                    <c:forEach var="portlet" varStatus="status"
                               items="${org_apache_pluto_embedded_portletIds}">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                    </c:forEach>
             </c:when>

            <c:otherwise>
                <div id="portlets-left-column">
                    <c:forEach var="portlet" varStatus="status"
                               items="${org_apache_pluto_embedded_portletIds}" step="2">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                    </c:forEach>
                </div>

                <!-- Right column -->
                <div id="portlets-right-column">
                    <c:forEach var="portlet" varStatus="status"
                               items="${org_apache_pluto_embedded_portletIds}" begin="1" step="2">
                        <c:set var="portlet" value="${portlet}" scope="request"/>
                        <jsp:include page="/WEB-INF/themes/portlet-skin.jsp"/>
                    </c:forEach>
                </div>

            </c:otherwise>
        </c:choose>

    </div>



    <!-- Footer block: copyright -->
    <div id="footer">
        &copy; 2003-2005 Apache Software Foundation
    </div>

</div>

</body>

</html>



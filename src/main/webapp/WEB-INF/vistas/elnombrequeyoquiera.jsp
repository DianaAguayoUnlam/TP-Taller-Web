	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
    <!-- COPIADO DE la vista LOGIN -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h1>Hola ${nombre} de apellido ${ape} </h1>
		
		<br>
		<!-- Iterar una lista -->
									<!-- Key modelo -->			<!-- cualquier nombre -->
		<c:forEach items="${listaPalabras}" var="variable"> 
		
		<h1>
		${variable}
		
		
		
		<!--  <a href="/buscarPersona?nombre=${Jorge}">${Jorge}</a>" -->
		 </h1>
		
		
		
		 </c:forEach>
		
		
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tarea</title>
</head>
<body>

<h1> Formulario</h1>

<form modelAtribute="Persona" action="GuardarDatos" method="Post">

<input type="text" path="nombre" name="nombre" placeholder="Ingrese Nombre">
<input type="text" path="apellido" name="apellido" placeholder="Ingrese Apellido">

<input type="submit" name="Agregar" value="Agregar"></input>


</form>


<br>
<a href="/proyecto-limpio-spring/Mostrar"><input type="submit" action="Mostrar" name="Mostrar" value="Mostrar Guardados"/> </a>

<br><br>

</body>
</html>
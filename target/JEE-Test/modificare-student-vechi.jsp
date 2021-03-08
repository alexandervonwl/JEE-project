<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular student</h3>
		<jsp:useBean id="studentBean" class="beans.StudentBean" scope="request" />
        <jsp:setProperty name="studentBean" property="nume" value='<%= request.getAttribute("nume") %>'/>
        <jsp:setProperty name="studentBean" property="prenume" value='<%= request.getAttribute("prenume") %>'/>
        <jsp:setProperty name="studentBean" property="varsta" value='<%= request.getAttribute("varsta") %>'/>
        <jsp:setProperty name="studentBean" property="media" value='<%= request.getAttribute("media") %>'/>
        <jsp:setProperty name="studentBean" property="email" value='<%= request.getAttribute("email") %>'/>

        		Modificati datele despre student:

        		<form action="./modificare-student" method="post">
        			Nume: <input type="text" name="nume" value=<jsp:getProperty name="studentBean" property="nume" /> />
        			<br />
        			Prenume: <input type="text" name="prenume" value=<jsp:getProperty name="studentBean" property="prenume" /> />
        			<br />
        			Varsta: <input type="number" name="varsta" value=<jsp:getProperty name="studentBean" property="varsta" /> />
        			<br />
                    Media: <input type="number" name="media" value=<jsp:getProperty name="studentBean" property="media" /> />
        			<br />
                    Email: <input type="number" name="email" value=<jsp:getProperty name="studentBean" property="email" /> />
                    <br />
        			<br />
        			<button type="submit" name="submit">Trimite</button>
        		</form>
	</body>
</html>
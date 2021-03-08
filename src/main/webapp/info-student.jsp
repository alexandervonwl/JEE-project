<html xmlns:jsp="http://java.sun.com/JSP/Page">
    <head>
		<title>Informatii student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Informatii student</h3>

		<!-- populare bean cu informatii din cererea HTTP -->

		<!-- folosirea bean-ului pentru afisarea informatiilor -->
		<p>Urmatoarele informatii au fost introduse:</p>

		<ul type="bullet">
            <%@ page import="java.util.Vector" %>
            <%@ page import="beans.*" %>

            <%
                Vector<StudentBean> list = (Vector<StudentBean>)request.getAttribute("list");
                for(StudentBean studentB : list) {
                    out.println("<li>ID:" + studentB.getId() + "</li>");
                    out.println("<li>Nume:" + studentB.getNume() + "</li>");
                    out.println("<li>Prenume:" + studentB.getPrenume() + "</li>");
                    out.println("<li>Varsta:" + studentB.getVarsta() + "</li>");
                    out.println("<li>Medie:" + studentB.getMedia() + "</li>");
                    out.println("<li>Email:" + studentB.getEmail() + "</li>");

                    out.println("<form action=\"./delete-student\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + studentB.getId() + "\"/>");
                    out.println("<button type=\"submit\" name=\"submit\">Sterge</button></form>");
                    out.println("<form action=\"./modificare-student\" method=\"post\">");
                    out.println("<input type=\"hidden\" name=\"id\" value=\"" + studentB.getId() + "\"/>");
                    out.println("<button type=\"submit\" name=\"submit\">Modificare</button></form>");
                }
            %>
        <p>
            <a href="./index.jsp">Acasa</a>
        </p>
	</body>
</html>
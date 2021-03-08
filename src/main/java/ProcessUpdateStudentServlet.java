import beans.StudentBean;
import beans.StudentsBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ProcessUpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        File file = new File("E:\\Facultate\\An3\\Sem2\\SD\\laboratoare\\lab1\\cod exemplu\\JEE-Test\\student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();

        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        double media = Double.parseDouble(request.getParameter("media"));
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("id"));

        StudentsBean studentsBean = xmlMapper.readValue(file, StudentsBean.class);
        StudentBean bean = studentsBean.getStudents().get(id - 1);

        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setMedia(media);
        bean.setEmail(email);

        xmlMapper.writeValue(file, studentsBean);

        request.setAttribute("list", studentsBean.getStudents());

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}
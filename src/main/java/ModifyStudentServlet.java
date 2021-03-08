import beans.StudentBean;
import beans.StudentsBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;

public class ModifyStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        File file = new File("E:\\Facultate\\An3\\Sem2\\SD\\laboratoare\\lab1\\cod exemplu\\JEE-Test\\student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }

        XmlMapper xmlMapper = new XmlMapper();
        StudentsBean studentsBean = xmlMapper.readValue(file,StudentsBean.class);

        int id = Integer.parseInt(request.getParameter("id"));
        StudentBean bean = studentsBean.getStudents().get(id-1);

        request.setAttribute("id", id);
        request.setAttribute("nume", bean.getNume());
        request.setAttribute("prenume", bean.getPrenume());
        request.setAttribute("varsta", bean.getVarsta());
        request.setAttribute("media", bean.getMedia());
        request.setAttribute("email", bean.getEmail());

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./modificare-student.jsp").forward(request, response);
    }
}
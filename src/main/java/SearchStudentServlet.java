import beans.StudentBean;
import beans.StudentsBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class SearchStudentServlet extends HttpServlet {
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

        StudentsBean studentsBean = xmlMapper.readValue(file, StudentsBean.class);
        Vector<StudentBean> students = studentsBean.getStudents();

        //determinam dupa ce cautam studentul
        StudentBean needed_student=new StudentBean();
        for(StudentBean student: students)
        {
            if(student.getNume().equals(nume))
            {
                needed_student = student;
                break;
            }
        }

        request.setAttribute("nume", needed_student.getNume());
        request.setAttribute("prenume", needed_student.getPrenume());
        request.setAttribute("varsta", needed_student.getVarsta());
        request.setAttribute("media", needed_student.getMedia());
        request.setAttribute("email", needed_student.getEmail());

        // redirectionare date catre pagina de afisare a informatiilor studentului
        request.getRequestDispatcher("./info-student-vechi.jsp").forward(request, response);
    }
}
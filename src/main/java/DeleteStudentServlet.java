import beans.StudentBean;
import beans.StudentsBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Vector;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // deserializare student din fisierul XML de pe disc
        File file = new File("E:\\Facultate\\An3\\Sem2\\SD\\laboratoare\\lab1\\cod exemplu\\JEE-Test\\student.xml");

        if (!file.exists()) {
            response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
            return;
        }


        XmlMapper xmlMapper = new XmlMapper();
        StudentsBean studentsBean = xmlMapper.readValue(file, StudentsBean.class);

        Vector<StudentBean> studentBeanList = studentsBean.getStudents();
        int id = Integer.parseInt(request.getParameter("id"));

        studentBeanList.remove(id-1);

        if(studentBeanList.size() == 0){
            file.delete();
        }
        else {
            for (int i = 0; i < studentBeanList.size(); i++) {
                studentBeanList.get(i).setId(i + 1);
            }
            xmlMapper.writeValue(file, studentsBean);

            request.setAttribute("list", studentBeanList);
        }

        request.getRequestDispatcher("./info-student.jsp").forward(request, response);


    }
}

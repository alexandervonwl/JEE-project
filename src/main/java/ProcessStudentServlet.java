import beans.StudentBean;
import beans.StudentsBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.Year;

public class ProcessStudentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // se citesc parametrii din cererea de tip POST
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        double media = Double.parseDouble(request.getParameter("media"));
        String email = request.getParameter("email");


        /*
        procesarea datelor - calcularea anului nasterii
         */
        int anCurent = Year.now().getValue();
        int anNastere = anCurent - varsta;

        // initializare serializator Jackson
        File file = new File("E:\\Facultate\\An3\\Sem2\\SD\\laboratoare\\lab1\\cod exemplu\\JEE-Test\\student.xml");

        //MOD LABORATOR
        XmlMapper mapper = new XmlMapper();


        // creare bean si populare cu date
        StudentBean bean = new StudentBean();
        bean.setNume(nume);
        bean.setPrenume(prenume);
        bean.setVarsta(varsta);
        bean.setMedia(media);
        bean.setEmail(email);

        StudentsBean studentsBean = new StudentsBean();

        if(file.length() == 0) {
            bean.setId(1);
        }
        else
        {
            studentsBean = mapper.readValue(file, StudentsBean.class);
            bean.setId(studentsBean.getStudents().size()+1);
        }



        studentsBean.addStudent(bean);


        String xmlString = mapper.writeValueAsString(studentsBean);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(xmlString);
        fileWriter.close();

        //MOD NOU
        //XML output factory


        // se trimit datele primite si anul nasterii catre o alta pagina JSP pentru afisare
        /*request.setAttribute("nume", nume);
        request.setAttribute("prenume", prenume);
        request.setAttribute("varsta", varsta);
        request.setAttribute("anNastere", anNastere);
        request.setAttribute("media", media);
        request.setAttribute("email", email);*/

        request.setAttribute("list", studentsBean.getStudents());
        request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}
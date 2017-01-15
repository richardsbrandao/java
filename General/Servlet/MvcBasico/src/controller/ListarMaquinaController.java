package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MaquinaDao;
import domain.Maquina;

@WebServlet("/ListarMaquina")
public class ListarMaquinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaquinaDao maquinaDao = new MaquinaDao();
       
    public ListarMaquinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Maquina> maquinas = maquinaDao.listar();
		request.setAttribute("maquinas", maquinas);
		if( request.getParameter("criado") != null ) {
			request.setAttribute("mensagem", "Maquina criada com sucesso!");
		}
		getServletContext().getRequestDispatcher("/maquina/listar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

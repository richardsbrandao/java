package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MaquinaDao;
import domain.Maquina;

@WebServlet("/CriarMaquina")
public class CriarMaquinaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MaquinaDao maquinaDao = new MaquinaDao();
       
    public CriarMaquinaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/maquina/criar.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Maquina maquina = new Maquina();
		maquina.setNome( request.getParameter("nome") );
		
		try {
			maquinaDao.salvar(maquina);
			response.sendRedirect("ListarMaquina?criado");
		} catch (Exception e) {
			request.setAttribute("mensagem", "Erro ao salvar maquina!");
			getServletContext().getRequestDispatcher("/maquina/criar.jsp").forward(request, response);
		}
	}

}

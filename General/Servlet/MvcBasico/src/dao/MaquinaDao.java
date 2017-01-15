package dao;

import java.util.ArrayList;
import java.util.List;

import domain.Maquina;

public class MaquinaDao {

	public List<Maquina> listar() {
		List<Maquina> maquinas = new ArrayList<Maquina>();
		maquinas.add( new Maquina(1L, "Maquina1") );
		maquinas.add( new Maquina(2L, "Maquina2") );
		maquinas.add( new Maquina(3L, "Maquina3") );
		return maquinas;
	}

	public void salvar(Maquina maquina) throws Exception {
		if( "".equals( maquina.getNome() ) ) {
			throw new Exception("Maquina Nulo");
		}
	}
	
}

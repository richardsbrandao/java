package com.richard.studi.dao.impl;

import java.util.List;
import java.util.Properties;

import com.richard.studi.dominio.Cliente;
import com.richard.studi.dominio.Indirizzo;

public class IndirizzoDao extends HQLDaoAbstratto<Indirizzo> {

	public List<Indirizzo> trovareTutto() {
		return trovareTutto(Indirizzo.class);
	}
	
	public Indirizzo trovareDaCliente(Cliente cliente) {
		Properties properties = new Properties();
		properties.put("cliente", cliente);
		return super.trovareDa(Indirizzo.class, properties).get(0);
	}
	
	public Indirizzo trovareDaId(Long id) {
		return super.trovareDaId(Indirizzo.class, id);
	}

	public Long contare() {
		return super.contare( Indirizzo.class );
	}
	
}

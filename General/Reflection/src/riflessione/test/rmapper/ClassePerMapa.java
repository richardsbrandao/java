package riflessione.test.rmapper;

import java.util.Date;
import java.util.Map;

import riflessione.studi.rmapper.RMapper;
import riflessione.studi.rmapper.domain.Serie;

public class ClassePerMapa {

	public static void main(String[] args) throws Exception {
		Serie serie = new Serie("The Flash", 1, 23, new Date());
		
		Map<String, Object> classePerMapa = RMapper.classePerMapa(serie);
		
		classePerMapa.forEach((k, v) -> System.out.println(k + " -> " + v));
	}
	
}

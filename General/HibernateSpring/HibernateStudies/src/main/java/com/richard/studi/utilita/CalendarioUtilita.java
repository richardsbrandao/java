package com.richard.studi.utilita;

import java.util.Calendar;
import java.util.Date;

public class CalendarioUtilita {
	
	public Calendar toCalendar(Date data) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(data);
		return calendario;
	}
	
	public Date adDate(int giorno, int mese, int anno) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anno, mese, giorno);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		return calendario.getTime();
	}
	
	public Date adDate(int giorno, int mese, int anno, int ora, int minuto, int secondo) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anno, mese, giorno);
		calendario.set(Calendar.HOUR_OF_DAY, ora);
		calendario.set(Calendar.MINUTE, minuto);
		calendario.set(Calendar.SECOND, secondo);
		return calendario.getTime();
	}

	public int ottenereAnnoInCorso() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public int ottenereMeseInCorso() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public int ottenereGiornoDiMeseInCorso() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public int ottenereMeseVicino() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
}

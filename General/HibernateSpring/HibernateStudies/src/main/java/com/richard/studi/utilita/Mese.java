package com.richard.studi.utilita;

public enum Mese {

	GENNAIO("Janeiro", 0, 1, 31),
	FEBBRAIO("Fevereiro", 1, 2, 28),
	MARZO("Março", 2, 3, 31),
	APRILE("Abril", 3, 4, 30),
	MAGGIO("Maio", 4, 5, 31),
	GIUGNO("Junho", 5, 6, 30),
	LUGLIO("Julho", 6, 7, 31),
	AGOSTO("Agosto", 7, 8, 31),
	SETTEMBRE("Setembro", 8, 9, 30),
	OTTOBRE("Outubro", 9, 10, 31),
	NOVEMBRE("Novembro", 10, 11, 30),
	DICEMBRE("Dezembro", 11, 12, 31);
	
	public String nome;
	public int calendarId;
	public int meseId;
	public int maxGiorni;
	
	Mese(String nome, int id, int meseId, int maxGiorni) {
		this.nome = nome;
		this.meseId = meseId;
		this.calendarId = id;
		this.maxGiorni = maxGiorni;
	}

	public String getNome() {
		return nome;
	}

	public int getCalendarId() {
		return calendarId;
	}
	
	public int getMonthId() {
		return calendarId+1;
	}

	public int getMaxDays() {
		return maxGiorni;
	}

	public static Mese trovareDaCalendarId(int calendarId) {
		for( Mese mese : values() ) {
			if( mese.calendarId == calendarId ) {
				return mese;
			}
		}
		return null;
	}

	public Mese scorsoMese() {
		if( meseId == Mese.GENNAIO.meseId ) {
			return Mese.DICEMBRE;
		}
		return trovareDaCalendarId( getCalendarId()-1 );
	}

	
}

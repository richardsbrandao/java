package br.com.ideais.evaluation.helpers;

import java.util.Calendar;
import java.util.Date;


public class Filter {

	private Date beginning;
	private Date end;
	private Level level;
	
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = setDeadlineByHour(end).getTime();
	}
	private Calendar setDeadlineByHour(Date end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(end);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
}

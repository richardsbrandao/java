package br.com.ideais.evaluation.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateTypeConverter extends StrutsTypeConverter {

	public Object convertFromString(Map context, String[] values, Class toClass) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = (Date) sdf.parse(values[0]);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			return values[0];
		}
	}

	public String convertToString(Map context, Object o) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(o);
	}
}

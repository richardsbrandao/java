package com.richard.studies.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataBaseTestConfigurer {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private List<String> lines = new ArrayList<String>();
	
	public void prepareIntegrationDatabase() throws UnitTestException {
		prepareDatabase("prepareIntegrationTests");
	}
	
	private void prepareDatabase(String fileName) throws UnitTestException {
		try {
			String testPath = "database/" + fileName + ".sql";
			BufferedReader file = loadSqlFile( testPath );
			readLines(file);
			executeSqls();
		} catch (Exception e) {
			throw new UnitTestException("Erro na configuração do banco: " + e.getMessage());
		}
	}

	private void executeSqls() {
		for( String sql : lines ) {
			jdbcTemplate.execute(sql);
		}
	}

	private void readLines(BufferedReader file) throws Exception {
		lines.clear();
		String sql = null;
		while( ( sql = file.readLine() ) != null ) {
			if( StringUtils.isNotBlank(sql) ) {
				lines.add( sql );
			}
		}
	}

	private BufferedReader loadSqlFile(String testFilePath) {
		InputStream filePath = ClassLoader.getSystemResourceAsStream( testFilePath );
		InputStreamReader stream = new InputStreamReader( filePath );
		return new BufferedReader( stream );
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

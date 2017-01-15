package riflessione.test.proxy.appconfig;

import riflessione.studi.proxy.appconfig.AppConfigProxy;
import riflessione.studi.proxy.appconfig.Config;

public class AppConfigProxyTest {
	
	public static void main(String[] args) {
		System.setProperty("database.password", "aq1sw2");
		System.setProperty("csv.has.header", "true");
		System.setProperty("installment.number", "3");
		
		Config proxy = (Config) AppConfigProxy.createInstance();
		
		String format = "%s tem o valor %s e é do tipo %s";
		String password = proxy.getDatabasePassword();
		System.out.println(String.format(format, "password", password, password.getClass().getName()));
		
		Boolean csvHasHeader = proxy.getCsvHasHeader();
		System.out.println(String.format(format, "csvHasHeader", csvHasHeader, csvHasHeader.getClass().getName()));
		
		Integer installmentNumber = proxy.getInstallmentNumber();
		System.out.println(String.format(format, "installmentNumber", installmentNumber, installmentNumber.getClass().getName()));
		
		String databaseUrl = proxy.getDatabaseUrl();
		System.out.println(String.format(format, "databaseUrl", databaseUrl, "null"));
	}
	
}

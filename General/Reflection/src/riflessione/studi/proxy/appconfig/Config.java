package riflessione.studi.proxy.appconfig;

public interface Config {

	public String getDatabaseUsername();
	public String getDatabaseUrl();
	public String getDatabasePassword();
	
	public String getJavaHome();
	
	public String getCsvSeparator();
	public Boolean getCsvHasHeader();
	
	public Integer getInstallmentNumber();
}

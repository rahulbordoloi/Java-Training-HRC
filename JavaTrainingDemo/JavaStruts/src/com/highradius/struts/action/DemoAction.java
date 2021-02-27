package com.highradius.struts.action;

public class DemoAction {
	
	private String result;
	
	public String execute() {
		setResult("{\"Result\": \"Success\"}");
		return "success";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}

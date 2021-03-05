package com.highradius.struts.action;

public class DemoAction {
	
	private Integer testParameter = null;
	private String result;
	
	public String execute() {
		
		// if(testParameter.equals(null)) 
		if(testParameter == null) 
			System.out.println("No Request");
		else
			System.out.println("Incoming Request");
		
		setResult("{\"Result\": \"Success\"}");
		return "success";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getTestParameter() {
		return testParameter;
	}

	public void setTestParameter(Integer testParameter) {
		this.testParameter = testParameter;
	}
}

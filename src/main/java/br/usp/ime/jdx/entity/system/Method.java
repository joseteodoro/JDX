package br.usp.ime.jdx.entity;

import java.io.Serializable;
import java.util.List;

//TODO: getJavaDoc(), getAnnotations()
public class Method implements Serializable, SystemEntity{

	private static final long serialVersionUID = -4865185051823887456L;

	private String name;
	private List<String> parameters;
	private String returnType;
	private String body;
	private String sourceCode;
	private Type containingType;
	private boolean isConstructor = false;

	public Method(String name, List<String> parameters, String returnType, 
			String body, String sourceCode){
		
		this.name = name;
		this.parameters = parameters;
		this.returnType = returnType;
		this.body = body;
		this.sourceCode = sourceCode;	
		
	}
	
	public Method(String name, List<String> parameters, String returnType, 
			String body, String sourceCode, boolean isConstructor){
		
		this(name,parameters,returnType,body,sourceCode);
		this.isConstructor = isConstructor;
	}
	
	public String getReturnType() {
		return returnType;
	}

	public String getBody() {
		return body;
	}

	public String getName() {
		return name;
	}
	
	public String getSignature(){
		String signature = name + "(";
		
		if(!parameters.isEmpty()){
			for(int i = 0; i<parameters.size() -1; i++){
				signature += parameters.get(i) + ", ";
			}
			signature += parameters.get(parameters.size()-1);
		}
		
		signature += ")";
		return signature;
	}
	
	public String getSourceCode(){
		return sourceCode;
	}
	
	public String toString(){
		String s = new String();
		if (containingType != null) s= containingType.getFQN() + ".";
		s += getSignature();
		return s;
	}
	
	public CompUnit getContainingCompUnit(){
		return getContainingType().getCompUnit();
	}
	
	public Type getContainingType(){
		return containingType;
	}
	
	public void setContainingType(Type containingType){
		this.containingType = containingType;
	}

	public List<String> getParameters() {
		return parameters;
	}
	
	public boolean isConstructor(){
		return isConstructor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((containingType == null) ? 0 : containingType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Method other = (Method) obj;
		if (containingType == null) {
			if (other.containingType != null)
				return false;
		} else if (!containingType.equals(other.containingType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
	
	
}
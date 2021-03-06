package br.usp.ime.jdx.entity.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CompUnit implements Serializable, JavaElement{

	private static final long serialVersionUID = 5569390666734073800L;

	private Package pkg;
	private String absolutePath;
	private String relativePath;
	private String rawSourceCode;
	private SourceCode sourceCode;
	private Set<Type> types;
	
	public CompUnit(Package pkg, String absolutePath, String relativePath){
		
		this.pkg = pkg;
		this.absolutePath = absolutePath;
		this.relativePath = relativePath;
		
		this.types = new HashSet<Type>();
	}
	
	public CompUnit(Package pkg, String absolutePath, String relativePath, String rawSourceCode){
		
		this(pkg,absolutePath,relativePath);
		this.rawSourceCode = rawSourceCode;
		this.sourceCode = new SourceCode(rawSourceCode);
	}
	
	public Package getPackage(){
		return pkg;
	}
	
	public void addType(Type type){
		this.types.add(type);
	}
	
	public Set<Type> getTypes(){
		return types;
	}
	
	public boolean containsType(String typeName){
		for(Type type : types){
			if(typeName.equals(type.getFQN())){
				return true;
			}
		}
		return false;
	}
	
	public Set<Method> getMethods(){

		Set<Method> methodsFromTypes = new HashSet<>();
		for(Type type : types){
			methodsFromTypes.addAll(type.getMethods());
		}
		return methodsFromTypes;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relativePath == null) ? 0 : relativePath.hashCode());
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
		CompUnit other = (CompUnit) obj;
		if (relativePath == null) {
			if (other.relativePath != null)
				return false;
		} else if (!relativePath.equals(other.relativePath))
			return false;
		return true;
	}
	
	public String toString() {
		return relativePath;
	}
	
	public SourceCode getSourceCode() {
		return sourceCode;
	}
	
	String getRawSourceCode() {
		return rawSourceCode;
	}
}

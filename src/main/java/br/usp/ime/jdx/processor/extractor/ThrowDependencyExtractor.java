package br.usp.ime.jdx.processor.extractor;

import java.util.List;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;

import br.usp.ime.jdx.entity.relationship.dependency.DependencyReport;
import br.usp.ime.jdx.entity.system.Method;
import br.usp.ime.jdx.entity.system.Type;
import br.usp.ime.jdx.filter.StringMatcher;

public class ThrowDependencyExtractor implements MethodDeclarationProcessor{
	
	private Cache cache;
	private DependencyReport depReport;
	private StringMatcher classFilter;
		
	public ThrowDependencyExtractor(Cache cacher, DependencyReport depReport, 
			StringMatcher classFilter){
		
		this.cache = cacher;
		this.depReport = depReport;
		this.classFilter = classFilter;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void processMethodDeclaration(MethodDeclaration methodDeclaration, 
			Method clientMethod){

		List<SimpleType> thrownExceptionTypes = methodDeclaration.thrownExceptionTypes();
		
		for(SimpleType thrownExceptionType : thrownExceptionTypes){
			
			ITypeBinding iTypeBinding = thrownExceptionType.resolveBinding();
	
			if(iTypeBinding != null){
				
				Type type = BindingResolver.resolveTypeBinding(
						classFilter, cache, iTypeBinding);
			
				if(type != null){
					depReport.addThrowDependency(clientMethod, type);
				}
			}
		}		
	}	
}
package edu.illinois.mir.k.coverage;

import org.kframework.kil.ASTNode;
import org.kframework.kil.Rewrite;
import org.kframework.kil.Rule;
import org.kframework.kil.Sentence;
import org.kframework.kil.Term;
import org.kframework.kil.loader.Context;
import org.kframework.kil.visitors.BasicTransformer;
import org.kframework.kil.visitors.exceptions.TransformerException;

public class CoverageTransformer extends BasicTransformer{

	
	int id = 0;
	public CoverageTransformer(String name, Context context) {
		super(name, context);	
	}
	
	@Override
	public ASTNode transform(Rule node) throws TransformerException {
		Rule r = node.shallowCopy();
		Term body = r.getBody();
		return transform((Sentence) r);
	}

	@Override
	public ASTNode transform(Rewrite node) throws TransformerException {
		Rewrite result = node.shallowCopy();
		Term right = (Term) node.getRight();
		//TODO:rewrite to be cov(i,right)
		
		result.replaceChildren(
                (Term) node.getLeft(),
                right,
                context);
		id++;
		return transform((Term) result);
	}
}

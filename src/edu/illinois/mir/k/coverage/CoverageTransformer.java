package edu.illinois.mir.k.coverage;


import java.util.Map.Entry;

import org.kframework.kil.ASTNode;
import org.kframework.kil.Attribute;
import org.kframework.kil.Bag;
import org.kframework.kil.Cell;
import org.kframework.kil.Configuration;
import org.kframework.kil.IntBuiltin;
import org.kframework.kil.KList;
import org.kframework.kil.Map;
import org.kframework.kil.MapItem;
import org.kframework.kil.Rule;
import org.kframework.kil.Sentence;
import org.kframework.kil.StringBuiltin;
import org.kframework.kil.Term;
import org.kframework.kil.loader.Context;
import org.kframework.kil.visitors.BasicTransformer;
import org.kframework.kil.visitors.exceptions.TransformerException;

public class CoverageTransformer extends BasicTransformer{

	
	int id = 0;
	public CoverageTransformer(String name, Context context) {
		super(name, context);	
	}
	
	int i = 0;
	@Override
	public ASTNode transform(Rule node) throws TransformerException {
		Rule r = node.shallowCopy();
		Term body = r.getBody();
		//coverage only works for non-function rules.
		if(!r.containsAttribute("function")){

		
		}
		return transform((Sentence) r);
	}
	
	@Override
	public ASTNode transform(Configuration node){
		
		Cell coverageCell = new Cell();
		coverageCell.setLabel("coverage");
		coverageCell.setContents(new KList());		
		Cell metaCell = new Cell();
		metaCell.setLabel("meta");
		Map map = createMetaMap();
		metaCell.setContents(map);
		Term body = node.getBody();
		if( body instanceof Cell){
			Bag b = new Bag();
			b.add(body);
			body = b;
		}
		
		((Bag)body).add(coverageCell);
		((Bag)body).add(metaCell);
		

		
		//System.out.println("Node is: "+node.toString());
		return node;
	}


	private Map createMetaMap() {
		Map map = new Map();
		
		for(Entry<Long, Rule> entry : MetaCoverageTransformer.idToRule.entrySet()){
			Long id = entry.getKey();			
			MapItem item = new MapItem(IntBuiltin.of(id), StringBuiltin.of(entry.getValue().getAttributes().toString()));			
			map.add(item);
		}
		return map;
	}

	
}

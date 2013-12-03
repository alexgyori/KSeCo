package edu.illinois.mir.k.coverage;

import java.io.IOException;

import org.kframework.backend.BasicBackend;
import org.kframework.compile.utils.CompilerSteps;
import org.kframework.kil.Definition;
import org.kframework.kil.loader.Context;
import org.kframework.main.FirstStep;
import org.kframework.main.LastStep;
import org.kframework.utils.Stopwatch;

public class CoverageBackend extends  BasicBackend{
	
	public CoverageBackend(Stopwatch sw, Context context){
		super(sw,context);
				
	}

	@Override
	public void run(Definition definition) throws IOException { }

	@Override
	public String getDefaultStep() {
        return "LastStep";
    }

	
	@Override
    public CompilerSteps<Definition> getCompilationSteps() {
        CompilerSteps<Definition> steps = new CompilerSteps<Definition>(context);
        steps.add(new FirstStep(this, context));
        
        steps.add(new CoverageTransformer("CoverageBackend", context));        
        
        steps.add(new LastStep(this, context));

        return steps;
    }

}

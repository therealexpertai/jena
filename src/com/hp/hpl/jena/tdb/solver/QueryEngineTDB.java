/*
 * (c) Copyright 2008 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package com.hp.hpl.jena.tdb.solver;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.sparql.algebra.Op;
import com.hp.hpl.jena.sparql.core.DatasetGraph;
import com.hp.hpl.jena.sparql.engine.Plan;
import com.hp.hpl.jena.sparql.engine.QueryEngineFactory;
import com.hp.hpl.jena.sparql.engine.QueryEngineRegistry;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
import com.hp.hpl.jena.sparql.engine.main.QueryEngineMain;
import com.hp.hpl.jena.sparql.util.Context;

public class QueryEngineTDB extends QueryEngineMain
{
    // ---- Wiring
    static public QueryEngineFactory getFactory() { return factory ; } 
    static public void register()       { QueryEngineRegistry.addFactory(factory) ; }
    static public void unregister()     { QueryEngineRegistry.removeFactory(factory) ; }
    
    // ---- Object
    public QueryEngineTDB(Op op, DatasetGraph dataset, Binding input, Context context)
    { super(op, dataset, input, context) ; }

    
    public QueryEngineTDB(Query query, DatasetGraph dataset, Binding input, Context context)
    { super(query, dataset, input, context) ; }
    
    // Choose the algebra-level optimizations to invoke. 
    @Override
    protected Op modifyOp(Op op)
    { 
        return super.modifyOp(op) ;
        //return Algebra.optimize(op) ;
    }

    // ---- Factory
    private static QueryEngineFactory factory = new QueryEngineFactory()
    {
        public boolean accept(Query query, DatasetGraph dataset, Context context) 
        { return true ; }

        public Plan create(Query query, DatasetGraph dataset, Binding input, Context context)
        {
            QueryEngineTDB engine = new QueryEngineTDB(query, dataset, input, context) ;
            return engine.getPlan() ;
        }
        
        public boolean accept(Op op, DatasetGraph dataset, Context context) 
        { return true ; }

        public Plan create(Op op, DatasetGraph dataset, Binding binding, Context context)
        {
            QueryEngineTDB engine = new QueryEngineTDB(op, dataset, binding, context) ;
            return engine.getPlan() ;
        }
    } ;
}

/*
 * (c) Copyright 2008 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
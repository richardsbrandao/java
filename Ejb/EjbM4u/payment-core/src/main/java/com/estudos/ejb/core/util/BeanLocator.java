package com.estudos.ejb.core.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BeanLocator {

	public static Object lookupLocal(String jndiName) {
        Context context = null;
        try {
            context = new InitialContext();
            return context.lookup(jndiName);
        } catch (NamingException e) {
//            LOG.error("Erro ao efetuar lookup.", e);
            throw new IllegalStateException(e);
        } finally {
            try {
                context.close();
            } catch (NamingException e) {
//                LOG.error("Erro ao encerrar contexto JNDI.", e);
                throw new IllegalStateException(e);
            }
        }
    }
	
}

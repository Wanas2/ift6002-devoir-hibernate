package ca.ulaval.glo4002.garage.interfaces.filters;

import ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate.EntityManagerProvider;
import ca.ulaval.glo4002.garage.infrastructure.persistence.hibernate.EntityManagerProviderFactory;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.*;

public class EntityManagerContextFilter implements Filter {
	private EntityManagerFactory entityManagerFactory ;

	@Override
	public void init(FilterConfig filterConfig) {
		entityManagerFactory = EntityManagerProviderFactory.getFactory();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager entityManager = null;

		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityManagerProvider.setEntityManager(entityManager);
			chain.doFilter(request, response);
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}

			EntityManagerProvider.clearEntityManager();
		}
	}

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}
}

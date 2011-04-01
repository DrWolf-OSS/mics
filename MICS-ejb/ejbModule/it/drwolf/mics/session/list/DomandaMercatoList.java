package it.drwolf.mics.session.list;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("domandaMercatoList")
public class DomandaMercatoList extends EntityQuery<DomandaMercato> {

	private static final String EJBQL = "select domandaMercato from DomandaMercato domandaMercato";

	private static final String[] RESTRICTIONS = {};

	private DomandaMercato domandaMercato = new DomandaMercato();

	public DomandaMercatoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DomandaMercato getDomandaMercato() {
		return domandaMercato;
	}
}

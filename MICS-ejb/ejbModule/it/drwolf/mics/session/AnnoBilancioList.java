package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("annoBilancioList")
public class AnnoBilancioList extends EntityQuery<AnnoBilancio> {

	private static final String EJBQL = "select annoBilancio from AnnoBilancio annoBilancio";

	private static final String[] RESTRICTIONS = {};

	private AnnoBilancio annoBilancio = new AnnoBilancio();

	public AnnoBilancioList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AnnoBilancio getAnnoBilancio() {
		return annoBilancio;
	}
}

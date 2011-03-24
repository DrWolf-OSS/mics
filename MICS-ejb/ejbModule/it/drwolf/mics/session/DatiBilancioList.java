package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("datiBilancioList")
public class DatiBilancioList extends EntityQuery<DatiBilancio> {

	private static final String EJBQL = "select datiBilancio from DatiBilancio datiBilancio";

	private static final String[] RESTRICTIONS = {};

	private DatiBilancio datiBilancio;

	public DatiBilancioList() {
		datiBilancio = new DatiBilancio();
		datiBilancio.setId(new DatiBilancioId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DatiBilancio getDatiBilancio() {
		return datiBilancio;
	}
}

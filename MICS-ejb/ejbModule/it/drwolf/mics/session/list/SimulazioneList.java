package it.drwolf.mics.session.list;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("simulazioneList")
public class SimulazioneList extends EntityQuery<Simulazione> {

	private static final String EJBQL = "select simulazione from Simulazione simulazione";

	private static final String[] RESTRICTIONS = {};

	private Simulazione simulazione = new Simulazione();

	public SimulazioneList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Simulazione getSimulazione() {
		return simulazione;
	}
}

package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("outputSimulazioneList")
public class OutputSimulazioneList extends EntityQuery<OutputSimulazione> {

	private static final String EJBQL = "select outputSimulazione from OutputSimulazione outputSimulazione";

	private static final String[] RESTRICTIONS = {};

	private OutputSimulazione outputSimulazione;

	public OutputSimulazioneList() {
		outputSimulazione = new OutputSimulazione();
		outputSimulazione.setId(new OutputSimulazioneId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public OutputSimulazione getOutputSimulazione() {
		return outputSimulazione;
	}
}

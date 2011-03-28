package it.drwolf.mics.session;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("trimestreSimulazioneList")
public class TrimestreSimulazioneList extends EntityQuery<TrimestreSimulazione> {

	private static final String EJBQL = "select trimestreSimulazione from TrimestreSimulazione trimestreSimulazione";

	private static final String[] RESTRICTIONS = { "lower(trimestreSimulazione.nomeTrimestre) like lower(concat(#{trimestreSimulazioneList.trimestreSimulazione.nomeTrimestre},'%'))", };

	private TrimestreSimulazione trimestreSimulazione = new TrimestreSimulazione();

	public TrimestreSimulazioneList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public TrimestreSimulazione getTrimestreSimulazione() {
		return trimestreSimulazione;
	}
}

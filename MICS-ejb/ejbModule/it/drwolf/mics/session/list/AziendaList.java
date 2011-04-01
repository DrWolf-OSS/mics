package it.drwolf.mics.session.list;

import it.drwolf.mics.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("aziendaList")
public class AziendaList extends EntityQuery<Azienda> {

	private static final String EJBQL = "select azienda from Azienda azienda";

	private static final String[] RESTRICTIONS = {
			"lower(azienda.azienda) like lower(concat(#{aziendaList.azienda.azienda},'%'))",
			"lower(azienda.settore) like lower(concat(#{aziendaList.azienda.settore},'%'))", };

	private Azienda azienda = new Azienda();

	public AziendaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Azienda getAzienda() {
		return azienda;
	}
}

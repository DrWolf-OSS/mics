package it.drwolf.mics.model;

import it.drwolf.mics.entity.DatiBilancio;
import it.drwolf.mics.entity.DomandaMercato;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;

public class MiICSiThinkModel {

	@In
	EntityManager entityManager;

	public String execute(DatiBilancio db, DomandaMercato dm) {
		BigDecimal quotaVolumeAziendale1 = new BigDecimal(0);

		BigDecimal quotaVolumeAziendale2 = new BigDecimal(0);

		BigDecimal inputMercatoAnnuali = new BigDecimal(0);

		BigDecimal outputMercatoAnnuale = new BigDecimal(0);

		BigDecimal quotaVolumeAziendaleIniziale = new BigDecimal(
				db.getQuantitaVendute());

		quotaVolumeAziendale1 = quotaVolumeAziendaleIniziale
				.add(quotaVolumeAziendaleIniziale.multiply(dm
						.getVolumeMercatoAnno1()
						.subtract(dm.getVolumeMercatoAnno0())
						.divide(dm.getVolumeMercatoAnno0(), 5,
								RoundingMode.FLOOR)));

		quotaVolumeAziendale2 = quotaVolumeAziendale1.add(quotaVolumeAziendale1
				.multiply(dm
						.getVolumeMercatoAnno2()
						.subtract(dm.getVolumeMercatoAnno1())
						.divide(dm.getVolumeMercatoAnno1(), 5,
								RoundingMode.FLOOR)));

		// considero una unità come un trimestre, quindi 3 anni = 12 trimestri
		for (int time = 0; time < 12; time++) {
			if ((time >= 0) && (time < 4)) {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno0().subtract(
						quotaVolumeAziendaleIniziale);
			} else if ((time >= 4) && (time < 8)) {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno1().subtract(
						quotaVolumeAziendale1);
				outputMercatoAnnuale = dm.getVolumeMercatoAnno0().subtract(
						quotaVolumeAziendaleIniziale);
			} else {
				inputMercatoAnnuali = dm.getVolumeMercatoAnno2().subtract(
						quotaVolumeAziendale2);
				outputMercatoAnnuale = dm.getVolumeMercatoAnno1().subtract(
						quotaVolumeAziendale1);
			}

		}

		return "OK";
	}
}

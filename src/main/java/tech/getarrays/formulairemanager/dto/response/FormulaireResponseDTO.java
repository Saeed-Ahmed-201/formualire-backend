package tech.getarrays.formulairemanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormulaireResponseDTO implements  DTOEntity {

	private Long id;
	private String nom;
	private String prenom;
	private String datenais;
	private String num;
	private String cite;
	private String province;
	private String telephone1;
	private String utilisateur_Code;
	private String telephone2;
	private Double nombre_d_enfant_d_age_prescolaire;
	private Double nombre_d_enfants_scolarises ;
	private Double nombre_de_chomeurs;
	private Double nombre_de_personnes_atteintes;
	private Double somme;
	private String optionalValue;
	private  String optionalValue2;
	private String frais_de_soin;
	private String frais_d_etudes;
	private String paiement_de_la_consommation_d_eau;
	private String paiement_de_la_consommation_d_electricite;
	private String payer_les_frais_de_loyer;
	private int age;
	private int numero_de_carte_d_instructions;
	private String exploitationHabitation;
	private String etatCivil;
	private String parents_ou_ascendants_en_parrainage;
	private String etatDeSante;
	private  String revenuContinental;
	private String couvertureSociale;
	private String evaluationDuComite;

}

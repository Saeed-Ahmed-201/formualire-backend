package tech.getarrays.formulairemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formulaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
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
    private String etatCivil;
    private String parents_ou_ascendants_en_parrainage;
    private String etatDeSante;
    private  String revenuContinental;
    private String couvertureSociale;
    private String evaluationDuComite;

    private String frais_de_soin = "false";
    private String frais_d_etudes = "false";
    private String paiement_de_la_consommation_d_eau = "false";
    private String paiement_de_la_consommation_d_electricite = "false";
    private String payer_les_frais_de_loyer  = "false";
    private int age;
    private int numero_de_carte_d_instructions;
    private String exploitationHabitation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    
	public void setUtilisateur_Code(String string) {
		// TODO Auto-generated method stub
		
	}



}


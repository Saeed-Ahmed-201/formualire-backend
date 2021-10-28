package tech.getarrays.formulairemanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.getarrays.formulairemanager.model.Formulaire;

@Repository
public interface FormulaireRepo extends JpaRepository<Formulaire , Long> {
    
	    @Query("SELECT f FROM Formulaire f JOIN f.user as u WHERE u.isFilledFormulaire != false")
	    public List<Formulaire> findRegistredFormulaire();
	    

	    
}

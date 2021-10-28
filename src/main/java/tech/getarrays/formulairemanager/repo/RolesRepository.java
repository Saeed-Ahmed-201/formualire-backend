package tech.getarrays.formulairemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.getarrays.formulairemanager.enums.eRoles;
import tech.getarrays.formulairemanager.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long>{
	  public Roles findByName(eRoles name);
}

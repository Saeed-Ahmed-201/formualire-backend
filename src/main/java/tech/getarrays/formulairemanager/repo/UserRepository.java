package tech.getarrays.formulairemanager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.getarrays.formulairemanager.enums.eRoles;
import tech.getarrays.formulairemanager.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

       User findByEmail(String email);
       boolean existsByEmail(String email);
       
       @Query("SELECT u FROM User as u WHERE  u.id != :adminId")
       public List<User> findAllUsers(@Param("adminId") Long adminId);
       
       @Query("SELECT u FROM User as u JOIN u.roles r WHERE r.name != :role")
       public List<User> findAllUsers(@Param("role") eRoles role);
       
//       @Query("DELETE FROM User as u  WHERE u.id = :userId")
//       public void deleteById(@Param("userId") Long userId);
}

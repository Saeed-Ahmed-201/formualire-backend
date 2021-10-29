package tech.getarrays.formulairemanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.getarrays.formulairemanager.enums.eRoles;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Roles {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
       
       @Enumerated(EnumType.STRING)
	   private eRoles name = eRoles.ADMIN;

       @ManyToMany(mappedBy = "roles")
       @JsonBackReference
       private List<User> users;
       
       public Roles(eRoles role) {
    	      this.name = role;
       }

}



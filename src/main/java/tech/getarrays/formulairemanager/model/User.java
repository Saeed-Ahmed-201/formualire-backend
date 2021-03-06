package tech.getarrays.formulairemanager.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name =" users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String email;
      
      @Column(name = "password", length=255)
      private String password;
      
      private String fullName;
      
      private String contact;
      
      private boolean isFilledFormulaire = false;
      
      @ManyToMany(cascade = CascadeType.PERSIST, fetch  = FetchType.EAGER)
      @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =  @JoinColumn(name = "role_id"))
      private Set<Roles> roles;
      
      @OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
      private Formulaire formulaire; 

}

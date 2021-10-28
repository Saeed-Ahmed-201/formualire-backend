package tech.getarrays.formulairemanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.getarrays.formulairemanager.dto.response.FormulaireResponseDTO;
import tech.getarrays.formulairemanager.enums.eRoles;
import tech.getarrays.formulairemanager.model.Formulaire;
import tech.getarrays.formulairemanager.model.User;
import tech.getarrays.formulairemanager.repo.FormulaireRepo;
import tech.getarrays.formulairemanager.repo.UserRepository;
import tech.getarrays.formulairemanager.util.Mapper;

@Service
public class FormulaireAdmin {
	   
	   @Autowired
	   private final UserRepository userRepo;
	   @Autowired
	   private final FormulaireRepo formulaireRepo;
	   
	    private Mapper mapper = new Mapper();
	   
	   @Autowired
	   public FormulaireAdmin(FormulaireRepo formulaireRepo, UserRepository userRepo) {
		      this.formulaireRepo = formulaireRepo;
		      this.userRepo = userRepo;
	   }
	   
	   public List<User> getAllUsers(Long adminId) {
		      return userRepo.findAllUsers(adminId);
	   }
	   
	   public Formulaire findFormualireByUserId(Long userId) {
		        User user = userRepo.findById(userId).orElse(null);
		      return user.getFormulaire();
	   }
	   
	   public Formulaire findFormualireById(Long formulaireId) {
	      return formulaireRepo.findById(formulaireId).orElse(null);
  }
	   
	   
	   public List<FormulaireResponseDTO> findAllFormulaires(){
	    	List<FormulaireResponseDTO> response = new ArrayList<>();
	        List<Formulaire> all = formulaireRepo.findRegistredFormulaire();
	        if(!all.isEmpty()) {
	            	all.forEach(item  -> {
	            		response.add((FormulaireResponseDTO) mapper.convertToDTO(item, new FormulaireResponseDTO()));
	            	});
	        }
	        return response;
	    }
	   
	   @Transactional
	   @Modifying
	   public  boolean deleteUserById(Long userId){
	        userRepo.deleteById(userId);
	        return  true;
	    }
	   
	   public void deleteFormulaire(Long id) {
		      Formulaire formulaire = formulaireRepo.findById(id).orElse(null);
		      User user = formulaire.getUser();
		      user.setFilledFormulaire(false);
		      userRepo.save(user);
		     this.formulaireRepo.delete(formulaire);
	   }
	   
//	   @Transactional
//	   @Modifying
//	   public  List<FormulaireResponseDTO> deleteFormulaire(Long id){
//		   System.out.println(id);
//		   Formulaire formulaire = this.formulaireRepo.findById(id).orElse(null);
//		    formulaireRepo.deleteById(id);
//	        List<FormulaireResponseDTO> response = new ArrayList<>();
////	        List<Formulaire> all = formulaireRepo.findAll();
////	        if(!all.isEmpty()) {
////	            	all.forEach(item  -> {
////	            		response.add((FormulaireResponseDTO) mapper.convertToDTO(item, new FormulaireResponseDTO()));
////	            	});
////	        }
//	        return response;
//	    }

}

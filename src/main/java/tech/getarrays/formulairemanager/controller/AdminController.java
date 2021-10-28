package tech.getarrays.formulairemanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.getarrays.formulairemanager.dto.response.FormulaireResponseDTO;
import tech.getarrays.formulairemanager.dto.response.Response;
import tech.getarrays.formulairemanager.model.Formulaire;
import tech.getarrays.formulairemanager.model.User;
import tech.getarrays.formulairemanager.service.FormulaireAdmin;
import tech.getarrays.formulairemanager.service.FormulaireService;

@RestController
@RequestMapping(value = "formulaire")
public class AdminController {
	
	   
	   private final FormulaireAdmin formulaireAdmin;
	   
	   private final FormulaireService formulaireService;
	   
	   public AdminController(FormulaireAdmin formulaireAdmin, FormulaireService formulaireService) {
		   this.formulaireAdmin = formulaireAdmin;
		   this.formulaireService = formulaireService;
	   }
	   
	   @CrossOrigin
	   @GetMapping(value = "/users/{adminId}")
	   public ResponseEntity<Response<?>> getAllUsers(@PathVariable("adminId") Long adminId){
		      List<User> users = formulaireAdmin.getAllUsers(adminId);
		      if(!users.isEmpty()) {
		    	  return ResponseEntity.status(200).body(new Response<List<User>>(users, 200));
		      }
		      return ResponseEntity.status(200).body(new Response<String>("No users found", 404));
	   }
	   
	   @CrossOrigin
	   @GetMapping(value = "/all")
	   public ResponseEntity<Response<?>> findAllFormulaire(){
		      List<FormulaireResponseDTO> formulaires = formulaireAdmin.findAllFormulaires();
		      if(!formulaires.isEmpty()) {
		    	 return ResponseEntity.status(200).body(new Response<List<FormulaireResponseDTO>>(formulaires, 200));
		      }
		      return ResponseEntity.status(200).body(new Response<String>("No users found", 404));
	   }
	   
	    @CrossOrigin
	    @PostMapping(value = "/admin/add/{userId}")
	    public ResponseEntity<Response<?>> addFormulaire(@RequestBody Formulaire formulaire, @PathVariable("userId") Long userId) {
	        boolean addedStatus = formulaireService.addFormulaire(formulaire, userId);
	        if(addedStatus) {        	
	        	return new ResponseEntity<Response<?>>(new Response<String>("Record Added Successfully", 201), HttpStatus.CREATED);
	        }
	       	return new ResponseEntity<Response<?>>(new Response<String>("SOMETHING WENT WRONG", 500), HttpStatus.BAD_REQUEST);
	    }
	    
	    @CrossOrigin
	    @GetMapping("/record/{userId}")
	    public ResponseEntity<Response<?>> getUserFormulaire(@PathVariable("userId") Long userId){
	    	   Formulaire formulaire = formulaireAdmin.findFormualireByUserId(userId);
	    	   if(formulaire != null) {
	    		   return new ResponseEntity<Response<?>>(new Response<Formulaire>(formulaire, 200), HttpStatus.CREATED); 
	    	   }
	    	   return new ResponseEntity<Response<?>>(new Response<String>("NOT FOUND FORMULAIRE", 404), HttpStatus.NOT_FOUND);
	    }
	    
	    @CrossOrigin
	    @GetMapping("/single/record/{formulaireId}")
	    public ResponseEntity<Response<?>> getSingleFormulaire(@PathVariable("formulaireId") Long formulaireId){
	    	   Formulaire formulaire = formulaireAdmin.findFormualireById(formulaireId);
	    	   if(formulaire != null) {
	    		   return new ResponseEntity<Response<?>>(new Response<Formulaire>(formulaire, 200), HttpStatus.CREATED); 
	    	   }
	    	   return new ResponseEntity<Response<?>>(new Response<String>("NOT FOUND FORMULAIRE", 404), HttpStatus.NOT_FOUND);
	    }
	    
	    @CrossOrigin
	    @DeleteMapping("/user/delete/{id}")
	    public  ResponseEntity<?> deleteUserById(@PathVariable("id") Long userId) {
			if(formulaireAdmin.deleteUserById(userId)) {
				return ResponseEntity.status(200).body(new Response<Boolean>(true, 200));
			}
			return ResponseEntity.status(200).body(new Response<String>("No users found", 404));
	    }
	    
	    @CrossOrigin
	    @DeleteMapping("/delete/{id}")
	    public  ResponseEntity<Response<?>> deleteFormulaire(@PathVariable("id") Long id) {
	    			formulaireAdmin.deleteFormulaire(id);
			return ResponseEntity.status(200).body(new Response("Successfully deleted!", 200));        
	    }

}

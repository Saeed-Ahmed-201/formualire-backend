package tech.getarrays.formulairemanager.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.getarrays.formulairemanager.dto.response.FormulaireResponseDTO;
import tech.getarrays.formulairemanager.dto.response.Response;
import tech.getarrays.formulairemanager.model.Formulaire;
import tech.getarrays.formulairemanager.service.FormulaireService;

@RestController 
@RequestMapping("/formulaire")
public class FormulaireResource {
    private final FormulaireService formulaireService;

    public FormulaireResource(FormulaireService formulaireService)  {
        this.formulaireService = formulaireService;
    }
//    @CrossOrigin
//    @GetMapping("/all")
//    public ResponseEntity<List<FormulaireResponseDTO>> getAllFormulaire () {
//        List<FormulaireResponseDTO> formulaires = formulaireService.findAllFormulaires();
//        return new ResponseEntity<>(formulaires, HttpStatus.OK);
//    }
    
//    @CrossOrigin
//    @GetMapping("view/own/{id}")
//    public ResponseEntity<Formulaire> getFormulaireByIdOwn(@PathVariable("id") Long id){
//        Formulaire formulaire = formulaireService.findFormulaireByIdOwn(id);
//        return new ResponseEntity<>(formulaire, HttpStatus.OK);
//    }
//    
//    @CrossOrigin
//    @GetMapping("/find/{id}")
//    public ResponseEntity<Formulaire> getFormulaireById (@PathVariable("id") Long id){
//        Formulaire formulaire = formulaireService.findFormulaireById(id);
//        return new ResponseEntity<>(formulaire, HttpStatus.OK);
//    }
    @CrossOrigin
    @PostMapping(value = "/add/{userId}")
    public ResponseEntity<Response<?>> addFormulaire(@RequestBody Formulaire formulaire, @PathVariable("userId") Long userId) {
        boolean addedStatus = formulaireService.addFormulaire(formulaire, userId);
        if(addedStatus) {        	
        	return new ResponseEntity<Response<?>>(new Response<String>("Record Added Successfully", 201), HttpStatus.CREATED);
        }
       	return new ResponseEntity<Response<?>>(new Response<String>("SOMETHING WENT WRONG", 500), HttpStatus.BAD_REQUEST);
    }
    
    @CrossOrigin
    @PutMapping ("/update/{id}")
    public ResponseEntity<Formulaire> updateFormulaire(@RequestBody Formulaire formulaire, @PathVariable("id") Long id) {
        Formulaire updateFormulaire = formulaireService.updateFormulaire(formulaire, id);
        return new ResponseEntity<>(updateFormulaire, HttpStatus.OK);
    }
    
//    @CrossOrigin
//    @DeleteMapping("/delete/{id}")
//    public  ResponseEntity<?> deleteFormulaire(@PathVariable("id") Long id) {
//    	List<FormulaireResponseDTO> response = formulaireService.deleteFormulaire(id);
//		return ResponseEntity.ok(response);        
//    }







}

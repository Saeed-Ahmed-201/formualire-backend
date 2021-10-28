package tech.getarrays.formulairemanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.getarrays.formulairemanager.dto.response.FormulaireResponseDTO;
import tech.getarrays.formulairemanager.exception.UserNotFoundException;
import tech.getarrays.formulairemanager.model.Formulaire;
import tech.getarrays.formulairemanager.model.User;
import tech.getarrays.formulairemanager.repo.FormulaireRepo;
import tech.getarrays.formulairemanager.repo.UserRepository;
import tech.getarrays.formulairemanager.util.Mapper;

@Service
public class FormulaireService {
    @Autowired
    private final FormulaireRepo formulaireRepo;
    
    @Autowired
    private final UserRepository userRepo;
    
    
    private Mapper mapper = new Mapper();


    @Autowired
    public FormulaireService(FormulaireRepo formulaireRepo) {
        this.formulaireRepo = formulaireRepo;
		this.userRepo = null;
    }

    public boolean addFormulaire(Formulaire formulaire, Long userId){
    	User user = userRepo.findById(userId).orElse(null);
    	if(user != null) {    		
    		user.setFilledFormulaire(true);
    		userRepo.save(user);
    		formulaire.setUser(user);
    		formulaire.setUtilisateur_Code(UUID.randomUUID().toString());
    		return formulaireRepo.save(formulaire) != null ? true: false;
    	}
    	return false;
    }
    
    public List<FormulaireResponseDTO> findAllFormulaires(){
    	List<FormulaireResponseDTO> response = new ArrayList<>();
        List<Formulaire> all = formulaireRepo.findAll();
        if(!all.isEmpty()) {
            	all.forEach(item  -> {
            		response.add((FormulaireResponseDTO) mapper.convertToDTO(item, new FormulaireResponseDTO()));
            	});
        }
        return response;
    }

    public Formulaire updateFormulaire(Formulaire formulaire, Long id){
       Formulaire existingFormulaire =  formulaireRepo.findById(id).orElse(null);
       User user = existingFormulaire.getUser();
       formulaire.setUser(user);
       return formulaireRepo.save(formulaire);
    }
    

//    public Formulaire findFormulaireByIdOwn(Long id){
//        return formulaireRepo.findFormulaireById(id).orElseThrow(() -> new UserNotFoundException("User by id "+ id + " was not found"));
//    } 
//
//    public Formulaire findFormulaireById(Long id){
//        return formulaireRepo.findFormulaireById(id).orElseThrow(() -> new UserNotFoundException("User by id "+ id + " was not found"));
//    }
    
    
}

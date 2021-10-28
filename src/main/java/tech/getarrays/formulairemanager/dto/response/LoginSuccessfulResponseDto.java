package tech.getarrays.formulairemanager.dto.response;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginSuccessfulResponseDto{

	private Long id;
    private String token;
    private String userFullName;
    private String userEmail;
    
    private boolean isFilledFormulaire;
    
    private Set<String> roles = new HashSet();
}

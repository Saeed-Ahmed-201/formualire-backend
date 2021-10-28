package tech.getarrays.formulairemanager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
	
    private String fullName;
    private String email;
    private String contact;
    private String password;
}

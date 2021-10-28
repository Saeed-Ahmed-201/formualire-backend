package tech.getarrays.formulairemanager.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.getarrays.formulairemanager.config.JwtTokenUtil;
import tech.getarrays.formulairemanager.dto.request.LoginRequestDto;
import tech.getarrays.formulairemanager.dto.request.SignupRequestDto;
import tech.getarrays.formulairemanager.dto.response.LoginSuccessfulResponseDto;
import tech.getarrays.formulairemanager.dto.response.Response;
import tech.getarrays.formulairemanager.enums.eRoles;
import tech.getarrays.formulairemanager.model.Roles;
import tech.getarrays.formulairemanager.model.User;
import tech.getarrays.formulairemanager.repo.RolesRepository;
import tech.getarrays.formulairemanager.repo.UserRepository;
import tech.getarrays.formulairemanager.service.UserDetailsImpl;

@RestController
public class AuthentificationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RolesRepository rolesRepo;


    @PostMapping(value = "/signup/user")
    @CrossOrigin
    public ResponseEntity<Response> signupAccount(@RequestBody SignupRequestDto signup){
       if(userRepository.existsByEmail(signup.getEmail())) {
    	   return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response<String>("User already exist by this email", 404));
       }
       else {
           User user = new User();
           Set<Roles> roles = new HashSet<>();
           Roles role = rolesRepo.findByName(eRoles.USER);
           roles.add(role);
           user.setFullName(signup.getFullName());
           user.setEmail(signup.getEmail());
           user.setRoles(roles);
           user.setPassword(encoder.encode(signup.getPassword()));
           userRepository.save(user);
           return ResponseEntity.status(HttpStatus.CREATED).body(new Response<String>("Successfully account created", 201));   
       }
    }

    @PostMapping(value = "/signin")
    @CrossOrigin
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto login){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = new HashSet<>();
        for(GrantedAuthority authority: userDetails.getAuthorities()) {
        	roles.add(authority.getAuthority());
        } 
        return ResponseEntity.ok(new Response<LoginSuccessfulResponseDto>(new LoginSuccessfulResponseDto(userDetails.getId(),jwtToken,userDetails.getUsername(),userDetails.getEmail(), userDetails.isFilledFormulaire(), roles),200));

    }

}

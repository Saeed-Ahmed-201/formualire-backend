package tech.getarrays.formulairemanager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import tech.getarrays.formulairemanager.model.Roles;
import tech.getarrays.formulairemanager.model.User;

@Data
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String fullName;
    private String email;
    private boolean isFilledFormulaire;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorties;

    public UserDetailsImpl(long id, String fullName, String email, String password,boolean isFilledFormulaire,
                           Collection<? extends GrantedAuthority> authorties) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.authorties = authorties;
        this.isFilledFormulaire = isFilledFormulaire;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorties;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Roles role: user.getRoles()) {
        	authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }
        return new UserDetailsImpl(user.getId(), user.getFullName(), user.getEmail(), user.getPassword(),user.isFilledFormulaire()
        		, authorities);
    }

}
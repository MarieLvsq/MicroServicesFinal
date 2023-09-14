package com.urbanisationsi.microservicessecurite.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.urbanisationsi.microservicessecurite.dao.GestionnairePrevoyanceRepository;
import com.urbanisationsi.microservicessecurite.dao.RoleRepository;
import com.urbanisationsi.microservicessecurite.model.GestionnairePrevoyance;
import com.urbanisationsi.microservicessecurite.model.Role;

@Service
public class GestionnairePrevoyanceService implements UserDetailsService {

	@Autowired
	GestionnairePrevoyanceRepository gestionnairePrevRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleRepository roleRepository;

	public GestionnairePrevoyance findByEmail(String mail) {
		return gestionnairePrevRepo.findByEmail(mail);
	}

	public void sauveGestionnairePrevoyance(GestionnairePrevoyance gestionnairePrevoyance) {
	    System.out.println("User data to save: " + gestionnairePrevoyance.toString());
		gestionnairePrevoyance.setMotdepasse(bCryptPasswordEncoder.encode(gestionnairePrevoyance.getMotdepasse()));
		gestionnairePrevoyance.setActive(true);
		Role gestionnairePrevoyanceRole = roleRepository.findByRole("ADMIN");
		gestionnairePrevoyance.setRoles(new HashSet<>(Arrays.asList(gestionnairePrevoyanceRole)));
		gestionnairePrevRepo.save(gestionnairePrevoyance);
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

		GestionnairePrevoyance gestionnairePrevoyance = gestionnairePrevRepo.findByEmail(mail);
		if (gestionnairePrevoyance != null) {
			List<GrantedAuthority> autorisations = getGestionnairePrevoyanceAutorisation(
					gestionnairePrevoyance.getRoles());
			return autoriserGestionnairePrevoyance(gestionnairePrevoyance, autorisations);
		} else {
			throw new UsernameNotFoundException("Non autorisé.");
		}
	}

	private UserDetails autoriserGestionnairePrevoyance(GestionnairePrevoyance gestionnairePrevoyance,
			List<GrantedAuthority> autorisations) {
		return new org.springframework.security.core.userdetails.User(gestionnairePrevoyance.getEmail(), // Use email as
																											// username
				gestionnairePrevoyance.getMotdepasse(), // Use password from the database
				gestionnairePrevoyance.isActive(), // Determine if the user is active
				true, // Account not expired
				true, // Credentials not expired
				true, // Account not locked
				autorisations // Authorities (roles) assigned to the user
		);
	}

	private List<GrantedAuthority> getGestionnairePrevoyanceAutorisation(Set<Role> gestionnairePrevoyanceRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		gestionnairePrevoyanceRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}

}

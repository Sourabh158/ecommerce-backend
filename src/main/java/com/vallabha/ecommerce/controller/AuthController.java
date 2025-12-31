package com.vallabha.ecommerce.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // Import this
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vallabha.ecommerce.dto.LoginRequest;
import com.vallabha.ecommerce.dto.LoginResponse;
import com.vallabha.ecommerce.dto.MessageResponse;
import com.vallabha.ecommerce.dto.SignupRequest;
import com.vallabha.ecommerce.model.AppRole;
import com.vallabha.ecommerce.model.Cart;
import com.vallabha.ecommerce.model.Role;
import com.vallabha.ecommerce.model.User;
import com.vallabha.ecommerce.repository.CartRepository;
import com.vallabha.ecommerce.repository.RoleRepository;
import com.vallabha.ecommerce.repository.UserRepository;
import com.vallabha.ecommerce.security.jwt.JwtUtils;
import com.vallabha.ecommerce.security.services.UserDetailsImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    JwtUtils jwtUtils;

	@Autowired 
	CartRepository cartRepository;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder encoder; // 1. Inject Encoder

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

        return ResponseEntity.ok(response);
    }
    
 // AuthController.java के अंदर

    @PostMapping("/register") // या "/signup" जो भी आपने रखा है
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        
        // 1. Check if Username/Email exists (Ye code same rahega)
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // 2. User Create karo
        User user = new User(signUpRequest.getUsername(), 
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()));

        // 3. --- YAHAN CHANGE HAI (Role Logic) ---
        Set<Role> roles = new HashSet<>();

        // Hum zabardasti 'ROLE_USER' dhundh kar user ko de rahe hain
        Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        
        roles.add(userRole); 
        // ^ Bas yahi role add hoga. Admin ka koi chance nahi.

        user.setRoles(roles);
        
        // 4. Save User
        userRepository.save(user);

        // 5. Cart Logic (Ye tumhare purane code me tha, isliye yaha hai)
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
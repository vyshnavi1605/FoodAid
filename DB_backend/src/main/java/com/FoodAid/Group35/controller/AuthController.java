package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.entity.Client;
import com.FoodAid.Group35.entity.Companies;
import com.FoodAid.Group35.model.AuthenticationRequest;
import com.FoodAid.Group35.model.AuthenticationResponse;
import com.FoodAid.Group35.model.SignupRequest;
import com.FoodAid.Group35.repository.ClientRepository;
import com.FoodAid.Group35.repository.CompanyRepository;
import com.FoodAid.Group35.services.MyUserDetailsService;
import com.FoodAid.Group35.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CompanyRepository companyRepository;


    @RequestMapping("/getDetails")
    public Client firstPage(Client client, Principal principal) {
        String userName = principal.getName();
        System.out.println(userName);

        Client client1 = clientRepository.getClientByUsername(userName);
        System.out.println(client1);

        return client1;
    }


    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveClient (@Valid @RequestBody SignupRequest signupRequest){
        if(clientRepository.existsByUsername(signupRequest.getUsername())){
            return new ResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
        }
        if(clientRepository.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity("Email already exists",HttpStatus.BAD_REQUEST);
        }

        Client client = new Client();
        BeanUtils.copyProperties(signupRequest,client);
        clientRepository.save(client);
        return ResponseEntity.ok("Created Successfully");
    }

    @GetMapping("/getAllCompanies")
    public List<String> allCompanies(){
        return companyRepository.fetchByCompanyName();
    }
    

	@PutMapping("/UpdatedClient/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails){
        if(clientRepository.existsByUsername(clientDetails.getUsername())){
            return new ResponseEntity("Username already exists", HttpStatus.BAD_REQUEST);
        }
        if(clientRepository.existsByEmail(clientDetails.getEmail())){
            return new ResponseEntity("Email already exists",HttpStatus.BAD_REQUEST);
        }

        Client client = clientRepository.findById(id);
		
		client.setFirstName(clientDetails.getFirstName());
		client.setLastName(clientDetails.getLastName());
		client.setEmail(clientDetails.getEmail());
		client.setUsername(clientDetails.getUsername());
		Client updatedClient = clientRepository.save(client);
		return ResponseEntity.ok(updatedClient);
	}
  
    
}

package com.qatorze.attimino.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.qatorze.attimino.dtos.UserRequestDTO;
import com.qatorze.attimino.dtos.UserResponseDTO;
import com.qatorze.attimino.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Endpoint pour la gestion des users")
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

    @Autowired
    private UserService userService;

    // Endpoint pour update le User
    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUserDTO = userService.updateUser(userRequestDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    // Endpoint pour retrouver un User à partir de son ID
    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    // Endpoint pour delete un User grace à son Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Renvoie un erreure 204 No Content
    }
}

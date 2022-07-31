package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//Crear un usuario
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	//Listar o leer usuario
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Usuario> oUser = usuarioService.findById(id);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUser);
	}
	
	//Actualizar
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuarioDetalles, @PathVariable Long id){
		Optional<Usuario> usuario=usuarioService.findById(id);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.get().setName(usuarioDetalles.getName());
		usuario.get().setEmail(usuarioDetalles.getEmail());
		usuario.get().setClave(usuarioDetalles.getClave());
		usuario.get().setActivado(usuarioDetalles.getActivado());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario.get()));
	}
	
	//Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!usuarioService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuarioService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	//Listar o leer todo
	@GetMapping
	public List<Usuario> readAll(){
		List<Usuario> usuarios = StreamSupport
				.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return usuarios;
	}
	

}

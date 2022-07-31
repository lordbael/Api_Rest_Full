package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(org.springframework.data.domain.Pageable pageable) {
		
		return usuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		usuarioRepository.deleteById(id);
		
	}

	

}

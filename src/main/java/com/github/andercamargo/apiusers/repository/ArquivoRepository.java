package com.github.andercamargo.apiusers.repository;

import com.github.andercamargo.apiusers.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
    List<Arquivo> findByIdUsuarioOwner(Long idUsuario);
}

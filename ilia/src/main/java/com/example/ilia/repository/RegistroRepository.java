package com.example.ilia.repository;

import com.example.ilia.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

    Optional<Registro> findByDia(String dia);

    Optional<List<Registro>> findByDiaStartsWith(String dia);
}

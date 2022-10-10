package Wycena.ZWWG.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObrotNaPrasieRepo extends JpaRepository< ObrotNaPrasie, Integer >
{ }


package ftnwebcinema.Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftnwebcinema.Cinema.entity.Administrator;

public interface AdministratorRepo extends JpaRepository<Administrator, Long> {

}
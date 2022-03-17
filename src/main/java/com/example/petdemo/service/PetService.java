package com.example.petdemo.service;

import com.example.petdemo.demo.Pet;
import com.example.petdemo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository)
    {
        this.petRepository=petRepository;
    }
    public Optional<Pet> findById(Long id){return petRepository.findById(id);}

    public Optional<Pet> findByStatus(String status){return petRepository.findByStatus(status);}

    public void delete(Long id){petRepository.delete(id);}

    public void save(Pet pet){petRepository.save(pet);}

    public void update(Long id,String name,String status){petRepository.updatePet(id,name,status);}
}

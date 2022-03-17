package com.example.petdemo.repository;

import com.example.petdemo.demo.Category;
import com.example.petdemo.demo.Pet;
import com.example.petdemo.demo.Tag;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class PetRepository {
    private Map<Long, Pet> pets=new HashMap<>();

    @PostConstruct
    private void initialize(){createPet();}

    private void createPet(){

        Pet pet=new Pet();
        pet.setId(1L);
        pet.setCategory(putCategory(1,"Dog"));
        pet.setName("Rex");
        pet.setPhoto(putPhoto("C:\\Documents\\Images1"));
        pet.setTag(putTag(1,"tag1"));
        pet.setStatus("available");
        pets.put(pet.getId(),pet);

        pet=new Pet();
        pet.setId(2L);
        pet.setCategory(putCategory(2,"Cat"));
        pet.setName("Lucy");
        pet.setPhoto(putPhoto("C:\\Documents\\Images2"));
        pet.setTag(putTag(2,"tag2"));
        pet.setStatus("available");
        pets.put(pet.getId(),pet);

    }

    private Category putCategory(int id,String name)
    {
        Category category=new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    private List<String> putPhoto(String url)
    {
        List<String> photos=new ArrayList<>();
        photos.add(url);
        return photos;
    }

    private Tag putTag(int id, String name){
        Tag tag=new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }

    public Optional<Pet> findById(Long id){
        return Optional.ofNullable((Pet)this.pets.getOrDefault(id,null));
    }

    public Optional<Pet> findByStatus(String status){
        return Optional.ofNullable((Pet)this.pets.getOrDefault(status,null));
    }

    public void updatePet(Long id,String name,String status){
        Pet pet;
        pet=pets.get(id);
        pet.setName(name);
        pet.setStatus(status);
        pets.replace(id,pet);

    }

    public void delete(Long id){pets.remove(id);}

    public void save(Pet pet){
        pets.compute(pet.getId(),(key,value)-> (Pet) pets);
    }
}

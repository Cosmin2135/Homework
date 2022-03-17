package com.example.petdemo.controller;

import com.example.petdemo.demo.Pet;
import com.example.petdemo.exception.DataNotFoundException;
import com.example.petdemo.exception.InvalidDataException;
import com.example.petdemo.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(value = "Pet Controller", tags = "/pet")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pet")
public class PetController {

        private final PetService petService;

        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success"),
                @ApiResponse(code = 400, message = "Invalid Value"),
                @ApiResponse(code = 404, message = "Pet Not Found!"),
                @ApiResponse(code = 405, message = "Invalid Input")
        })
        @GetMapping(path = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
        public Optional<Pet> getPetById(@PathVariable Long petId) {
            if(petId < 0)
            {
                throw new DataNotFoundException("Pet with this id not found");
            }

            return petService.findById(petId);
        }

        @GetMapping(path = "/findByStatus")
        public Optional<Pet> getPetByStatus(@PathVariable String status) {
        if(status==null)
        {
            throw new DataNotFoundException("Pet with this status not found");
        }

        return petService.findByStatus(status);
    }

        @DeleteMapping(path = "/{petId}")
        public void deletePet(@PathVariable() Long petId)
        {
            petService.delete(petId);
        }

        @Validated
        public void savePet(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult){
          if(bindingResult.hasErrors())
              throw new InvalidDataException("Invalid pet");
          petService.save(pet);
        }

        public void updatePet(Long id,String name,String status){
            if(id<0 && name==null && status==null)
            {
                throw new DataNotFoundException("Pet not found");
            }
            petService.update(id,name,status);
        }

}

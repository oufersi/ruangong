package com.example.forum.Assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.forum.Controllers.UserController;
import com.example.forum.Entities.Passage;

@Component
public class PassageAssembler implements RepresentationModelAssembler<Passage, EntityModel<Passage>> {
    @Override
    public EntityModel<Passage> toModel(Passage passage){

        return EntityModel.of(passage,
            linkTo(methodOn(UserController.class).one(passage.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("passages")
        );
    }
}

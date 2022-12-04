package com.example.forum.assemblers;

import com.example.forum.controllers.UserController;
import com.example.forum.entities.NormalUser;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NormalUserAssembler implements RepresentationModelAssembler<NormalUser, EntityModel<NormalUser>>{
    @Override
    public EntityModel<NormalUser> toModel(NormalUser user){

        return EntityModel.of(user,
            linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

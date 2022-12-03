package com.example.forum.Assemblers;

import com.example.forum.Controllers.UserController;
import com.example.forum.Entities.NormalUser;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<NormalUser, EntityModel<NormalUser>>{
    @Override
    public EntityModel<NormalUser> toModel(NormalUser user){

        return EntityModel.of(user,
            linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

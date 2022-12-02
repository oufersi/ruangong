package com.example.forum.Assemblers;

import com.example.forum.Controllers.UserController;
import com.example.forum.Entities.User;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>>{
    @Override
    public EntityModel<User> toModel(User user){

        return EntityModel.of(user,
            linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

package com.example.forum.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.forum.controllers.UserController;
import com.example.forum.entities.PostContent;

@Component
public class PostContentAssembler implements RepresentationModelAssembler<PostContent, EntityModel<PostContent>> {
    @Override
    public EntityModel<PostContent> toModel(PostContent passage){

        return EntityModel.of(passage,
            linkTo(methodOn(UserController.class).one(passage.getId())).withSelfRel(),
            linkTo(methodOn(UserController.class).all()).withRel("passages")
        );
    }
}

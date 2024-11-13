package com.mohamed.blogball.DTO;

import com.mohamed.blogball.model.role.RoleName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private RoleName roleName;

}

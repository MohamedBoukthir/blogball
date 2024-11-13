package com.mohamed.blogball.payload;

import com.mohamed.blogball.DTO.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {

    private UserDto userDto;
    private String error;
    private String message;

}

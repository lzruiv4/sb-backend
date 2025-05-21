package com.lam.sb_backend.exception;

import java.util.UUID;

public class UserNotFoundException extends SBException {

    public UserNotFoundException(UUID userId) {
        super(ErrorCode.USER_NOT_FOUND, "User ID " + userId + " is not found");
    }

}

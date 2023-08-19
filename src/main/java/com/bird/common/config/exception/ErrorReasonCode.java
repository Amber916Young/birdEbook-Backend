package com.bird.common.config.exception;

public enum ErrorReasonCode {
    Invalid_Username_Password, // username or password is incorrect for login
    Duplicated_Username, // username is already used when creating a new user
    Duplicated_UserEmail, // username is already used when creating a new user
    Not_Allowed_User, // the user is not allowed to call the specific API
    Not_Found_Entity, // the entity the caller tries to update/delete doesn't exist
    Invalid_Reset_Key, // the token for password reset is not valid
    Size_Limit_Exceeded, // max size of files to upload exceeds 10mb
    Server_Error, // unexpected server error
    WikiAction_Already_Delete,
    WikiAction_Cannot_Found,
    Article_Cannot_Update,
    Tags_Cannot_Found,
    Category_Cannot_Found,
    User_Not_Found,
    Company_Not_Found,
    Email_Send_Fail,
    Email_Template_Create_Fail,
    Email_Template_Exist,
    Duplicated_Company_Email,
    Duplicated_Company_Name,
    Company_Not_Verify,
    Invalid_Parameters,
    ACCESS_Denied,
    Upload_Image_invalid,
    Not_File,

    Duplicated_Collect_Name,
    Collect_Not_Found,
}

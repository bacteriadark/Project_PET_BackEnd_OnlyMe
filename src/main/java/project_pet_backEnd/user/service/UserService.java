package project_pet_backEnd.user.service;

import project_pet_backEnd.user.dto.*;
import project_pet_backEnd.utils.commonDto.ResultResponse;

public interface UserService {
    void  localSignUp(UserSignUpRequest userSignUpRequest);
    ResultResponse localSignIn(UserLoginRequest userLoginRequest);
    boolean  validatedCaptcha(String email,String captcha);
    ResultResponse generateCaptcha(String email);
    void  sendEmail(String to, String subject, String body);
    UserProfileResponse getUserProfile(Integer userId);
    ResultResponse adjustUserProfile(Integer userId, AdjustUserProfileRequest adjustUserProfileRequest);

    void adjustPassword(Integer userId,String password);
    ResultResponse forgetPassword(String userEmail);

    ResultResponse forgetRenewPassword(String code, String newPassword);

    String checkUserIsSingUp(String email);
}

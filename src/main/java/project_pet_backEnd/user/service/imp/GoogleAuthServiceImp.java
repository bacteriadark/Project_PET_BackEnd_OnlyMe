package project_pet_backEnd.user.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import project_pet_backEnd.user.dao.UserDao;
import project_pet_backEnd.user.dto.ResultResponse;
import project_pet_backEnd.user.dto.UserSignUpRequest;
import project_pet_backEnd.user.dto.oAuth.OAuthRequest;
import project_pet_backEnd.user.dto.oAuth.OAuthResponse;
import project_pet_backEnd.user.dto.oAuth.UserInfoResponse;
import project_pet_backEnd.user.service.OAuthService;
import project_pet_backEnd.user.service.UserService;
import project_pet_backEnd.user.vo.IdentityProvider;
import project_pet_backEnd.user.vo.User;
import project_pet_backEnd.userManager.service.UserManagerService;
import project_pet_backEnd.utils.AllDogCatUtils;
import project_pet_backEnd.utils.UserJwtUtil;


@Service
public class GoogleAuthServiceImp implements OAuthService {
    @Autowired
    private UserDao userDao;
    @Value("${google.client-id}")
    private String clientId;
    @Value("${google.client-secret}")
    private String clientSecret;

    @Autowired
    private UserJwtUtil userJwtUtil;
    public ResultResponse oAuthLogin(OAuthRequest oauthRequest){

        OAuthResponse OAuthResponse =getGoogleToken(oauthRequest);

        UserInfoResponse userInfoResponse=getUserInfo(OAuthResponse.getAccess_token());

        User user=userDao.getUserByEmail(userInfoResponse.getEmail());
        ResultResponse rs =new ResultResponse();
        if(user!=null){
            //若有此使用者
            String token=generateLoginToken(user.getUserId().toString());
            rs.setMessage(token);
            System.out.println(token);
            return  rs;
        }
        UserSignUpRequest userSignUpRequest =new UserSignUpRequest();
        userSignUpRequest.setUserEmail(userInfoResponse.getEmail());
        userSignUpRequest.setUserName(userInfoResponse.getName());
        byte[] userPic = AllDogCatUtils.downloadImageAsByteArray(userInfoResponse.getPicture());
        userSignUpRequest.setUserPic(userPic);
        userSignUpRequest.setIdentityProvider(IdentityProvider.Google);
        userSignUpRequest.setUserGender(2);
        Integer userId=userDao.localSignUp(userSignUpRequest);
        String token=generateLoginToken(userId.toString());
        System.out.println(token);
        rs.setMessage(token);
        return  rs;
    }
    public OAuthResponse getGoogleToken(OAuthRequest oauthRequest){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", oauthRequest.getCode());
        parameters.add("client_id", clientId);
        parameters.add("client_secret", clientSecret);
        parameters.add("redirect_uri", "http://localhost:5500");
        parameters.add("grant_type", "authorization_code");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);


        ResponseEntity<OAuthResponse> responseEntity = restTemplate.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                requestEntity,
                OAuthResponse.class
        );

        OAuthResponse oauthResponse = responseEntity.getBody();
        return oauthResponse;
    }


    public UserInfoResponse getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);


        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);


        ResponseEntity<UserInfoResponse> responseEntity = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                requestEntity,
                UserInfoResponse.class
        );

        UserInfoResponse userInfoResponse = responseEntity.getBody();
        return userInfoResponse;
    }

    private String generateLoginToken(String sub){
        return userJwtUtil.createJwt(sub);
    }
}

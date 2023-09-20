//package project_pet_backEnd.socialMedia.report.controller;
//
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import project_pet_backEnd.socialMedia.report.dto.req.MessageReportRequest;
//import project_pet_backEnd.socialMedia.report.dto.req.PostReportRequest;
//import project_pet_backEnd.socialMedia.report.dto.req.ReportRequest;
//import project_pet_backEnd.socialMedia.report.service.ReportService;
//import project_pet_backEnd.socialMedia.report.vo.MesReport;
//import project_pet_backEnd.socialMedia.report.vo.PostReport;
//import project_pet_backEnd.utils.commonDto.ResultResponse;
//
//import javax.validation.Valid;
//
//@Api(tags = "使用者檢舉功能")
//@RestController
//@RequestMapping("/user/social/report")
//@Validated
//public class ReportController {
//
//    @Autowired
//    private ReportService reportService;
//
//    /**
//     * user attribute @RequestAttribute("userId") Integer userId
//     */
//
//    @ApiOperation("User檢舉留言")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_U", value = "User Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PostMapping("/message/{messageId}")
//    public ResponseEntity<ResultResponse<String>> createMesReport(@PathVariable("messageId") int messageId, @Valid @RequestBody ReportRequest reportRequest, @RequestAttribute("userId") Integer userId) {
//        ResultResponse<String> response = reportService.createMesRep(userId, messageId, reportRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//
//    @ApiOperation("User檢舉貼文")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_U", value = "User Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PostMapping("/post/{postId}")
//    public ResponseEntity<ResultResponse<String>> createPostReport(@PathVariable("postId") int postId, @Valid @RequestBody ReportRequest reportRequest, @RequestAttribute("userId") Integer userId) {
//        ResultResponse<String> response = reportService.createPostRep(userId, postId, reportRequest);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
//
//
//}

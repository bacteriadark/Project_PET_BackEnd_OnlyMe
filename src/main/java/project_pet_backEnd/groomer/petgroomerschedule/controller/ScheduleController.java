//package project_pet_backEnd.groomer.petgroomerschedule.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import project_pet_backEnd.groomer.petgroomerschedule.dto.request.BatchInsertScheduleReq;
//import project_pet_backEnd.groomer.petgroomerschedule.dto.request.ScheduleInsertReq;
//import project_pet_backEnd.groomer.petgroomerschedule.dto.request.ScheduleModifyReq;
//import project_pet_backEnd.groomer.petgroomerschedule.dto.response.GetScheduleRes;
//import project_pet_backEnd.groomer.petgroomerschedule.dto.response.ListForScheduleRes;
//import project_pet_backEnd.groomer.petgroomerschedule.service.GroomerScheduleService;
//import project_pet_backEnd.utils.commonDto.ResultResponse;
//
//import javax.validation.Valid;
//import java.util.Calendar;
//import java.util.List;
//
//@Api(tags = "美容師班表功能")
//@RestController
//@Validated
//public class ScheduleController {
//
//    @Autowired
//    GroomerScheduleService groomerScheduleService;
//
//    //排班頁面選擇美容師
//    @ApiOperation("Man排班頁面表列美容師")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
//    @GetMapping("/manager/schedulePageGroomer")
//    public ResultResponse<List<ListForScheduleRes>> getAllGroomerForSchedule(){
//        return groomerScheduleService.getAllGroomerForSchedule();
//    }
//
//    //查詢該月排班
//    @ApiOperation("Man排班表查詢")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
//    @GetMapping("/manager/schedule")
//    public ResultResponse<List<GetScheduleRes>> getMonthScheduleByPgId(
//            @RequestParam(value = "pgId") Integer pgId,
//            @RequestParam(value = "year",required = false)Integer yearParam,
//            @RequestParam(value = "month",required = false)Integer monthParam){
//
//
//        return groomerScheduleService.getMonthScheduleForMan(yearParam, pgId, monthParam);
//    }
//
//    //修改該筆班表，，前端需顯示可能會取消已預約的預約單，讓使用者確認。
//    @ApiOperation("Man排班表修改")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
//    @PostMapping("/manager/modifySchedule")
//    public ResultResponse<String> modifySchedule(@RequestBody ScheduleModifyReq scheduleModifyReq){
//        return groomerScheduleService.modifySchedule(scheduleModifyReq);
//    }
//    //新增單筆班表
//    @ApiOperation("Man排班新增")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
//    @PostMapping("/manager/insertNewSchedule")
//    public ResultResponse<String> insertNewSchedule(@RequestBody ScheduleInsertReq scheduleInsertReq){
//        return groomerScheduleService.insertNewSchedule(scheduleInsertReq);
//    }
//
//    //批次新增班表
//    @ApiOperation("Man排班批次新增")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
//    @PostMapping("/manager/batchInsertSchedule")
//    public ResultResponse<String> batchInsertSchedule(@RequestBody @Valid BatchInsertScheduleReq batchInsertScheduleReq){
//        return groomerScheduleService.insertBatchSchedule(batchInsertScheduleReq);
//    }
//
//    //--------------------------------美容師個人管理(排班)-----------------------------------------//
//    @ApiOperation("pg排班查詢")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師個人管理')")
//    @GetMapping("/manager/scheduleForPg")
//    public ResultResponse<List<GetScheduleRes>> getMonthScheduleForPg(
//            @RequestAttribute(name = "managerId") Integer managerId,
//            @RequestParam(value = "year",required = false)Integer yearParam,
//            @RequestParam(value = "month",required = false)Integer monthParam
//    ){
//
//        return groomerScheduleService.getMonthScheduleForPg(managerId, yearParam, monthParam);
//    }
//
//
//    //排班頁面選擇美容師
//    @ApiOperation("pg排班頁面表列美容師")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師個人管理')")
//    @GetMapping("/manager/scheduleGetGroomers")
//    public ResultResponse<List<ListForScheduleRes>> getAllGroomerForScheduleForPg(){
//        return groomerScheduleService.getAllGroomerForSchedule();
//    }
//
//    //查詢該月排班
//    @ApiOperation("pg排班頁面其他美容師查詢")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization_M", value = "Manager Access Token", required = true, dataType = "string", paramType = "header")
//    })
//    @PreAuthorize("hasAnyAuthority('美容師個人管理')")
//    @GetMapping("/manager/scheduleForOtherPg")
//    public ResultResponse<List<GetScheduleRes>> getMonthScheduleByPgIdForPg(
//            @RequestParam(value = "pgId") Integer pgId,
//            @RequestParam(value = "year",required = false)Integer yearParam,
//            @RequestParam(value = "month",required = false)Integer monthParam){
//
//
//        return groomerScheduleService.getMonthScheduleForMan(yearParam, pgId, monthParam);
//    }
//}

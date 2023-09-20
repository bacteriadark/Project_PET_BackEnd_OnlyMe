package project_pet_backEnd.groomer.petgroomercollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project_pet_backEnd.groomer.petgroomer.dto.PGQueryParameter;
import project_pet_backEnd.groomer.petgroomer.dto.response.PortfolioReq;
import project_pet_backEnd.groomer.petgroomer.dto.response.PortfolioRes;
import project_pet_backEnd.groomer.petgroomercollection.service.PortfolioService;
import project_pet_backEnd.groomer.petgroomercollection.vo.Portfolio;
import project_pet_backEnd.utils.commonDto.Page;
import project_pet_backEnd.utils.commonDto.ResultResponse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@Validated
public class PortfolioController {

    @Autowired
    PortfolioService service;

    /**
     * 新增作品
     *
     * @param rest
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
    @PostMapping("/portfolio/insert")
    public ResponseEntity<?> insert(
            @RequestBody Portfolio rest
    ) {
        rest.setPorUpload(new Date());
        ResultResponse resultResponse = service.insert(rest);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    /**
     * 更新作品
     *
     * @param porId
     * @param pgId
     * @param porTitle
     * @param porText
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
    @PostMapping("/portfolio/update")
    public ResponseEntity<?> update(
            @RequestParam @NotNull Integer porId,
            @RequestParam @NotNull Integer pgId,
            @RequestParam @NotNull String porTitle,
            @RequestParam @NotBlank String porText
    ) {
        Portfolio rest = new Portfolio();
        rest.setPorId(porId);
        rest.setPgId(pgId);
        rest.setPorTitle(porTitle);
        rest.setPorText(porText);
        rest.setPorUpload(new Date());
        ResultResponse resultResponse = service.update(rest);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    /**
     * 删除作品
     *
     * @param portfolio
     * @return
     */
    //    @PreAuthorize("hasAnyAuthority('美容師管理')")
    @PostMapping("/portfolio/delete")
    public ResponseEntity<?> delete(
            @RequestBody Portfolio portfolio
    ) {
        Portfolio rest = new Portfolio();
        rest.setPorId(portfolio.getPorId());
        ResultResponse resultResponse = service.delete(rest);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    /**
     * 查询作品详情
     *
     * @param porId
     * @return
     */
//    @PreAuthorize("hasAnyAuthority('美容師管理')")
    @PostMapping("/portfolio/findById")
    public ResponseEntity<?> findById(
            @RequestParam @NotNull Integer porId
    ) {
        Portfolio rest = new Portfolio();
        rest.setPorId(porId);
        PortfolioRes portfolio = service.findById(rest);
        return ResponseEntity.status(200).body(portfolio);
    }

    /**
     * 作品列表
     *
     * @param req
     * @return
     */
    @PostMapping("/portfolio/list")
    public ResponseEntity<Page<List<PortfolioRes>>> list(
            @RequestBody PortfolioReq req) {
        PGQueryParameter pgQueryParameter = new PGQueryParameter();
        pgQueryParameter.setSearch(req.getSearch());
        Integer limit = req.getPageSize();
        Integer offset = (req.getCurrentPage() - 1) * req.getPageSize();
        pgQueryParameter.setLimit(limit);
        pgQueryParameter.setOffset(offset);
        Page<List<PortfolioRes>> list = service.list(pgQueryParameter);
        return ResponseEntity.status(200).body(list);
    }
}

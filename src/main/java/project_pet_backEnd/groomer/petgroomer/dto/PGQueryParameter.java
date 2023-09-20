package project_pet_backEnd.groomer.petgroomer.dto;

import lombok.Data;
import project_pet_backEnd.groomer.petgroomer.dto.orderby.PGOrderBy;
import project_pet_backEnd.userManager.dto.Sort;
@Data
public class PGQueryParameter {
    private String search;
    private Integer userId;
    private Integer pgId;
    private String porId;
    private PGOrderBy order;
    //NUM_APPOINTMENTS,PG_NAME
    private Sort sort;
    private Integer limit = 10;
    private Integer offset = 0;
}

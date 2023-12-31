package project_pet_backEnd.socialMedia.activityManager.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActivityReq {
    // 建立活動、修改活動
    String title;
    String content;
    //base64編碼
    String activityPicture;
    String startTime;
    String endTime;
    String activityTime;
    Integer enrollLimit;
}


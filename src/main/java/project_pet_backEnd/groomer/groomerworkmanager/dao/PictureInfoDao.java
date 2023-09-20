package project_pet_backEnd.groomer.groomerworkmanager.dao;

import project_pet_backEnd.groomer.groomerworkmanager.vo.PictureInfo;
import project_pet_backEnd.groomer.petgroomer.dto.PGQueryParameter;

import java.util.List;

public interface PictureInfoDao {


    /**
     * 新增作品图片
     * @param rest
     */
    void insert(PictureInfo rest);

    /**
     * 修改作品图片
     * @param rest
     */
    void update(PictureInfo rest);

    /**
     * 删除作品图片
     * @param rest
     */
    void delete(PictureInfo rest);

    /**
     * 查询作品图片
     * @param rest
     * @return
     */
    PictureInfo findById(PictureInfo rest);


    /**
     * 作品图片列表
     * @param PGQueryParameter
     * @return
     */
    List<PictureInfo> list(PGQueryParameter PGQueryParameter);

    List<PictureInfo> getAllPicture();

    /**
     * 统计数量
     * @param PGQueryParameter
     * @return
     */
    Integer count(PGQueryParameter PGQueryParameter);

}

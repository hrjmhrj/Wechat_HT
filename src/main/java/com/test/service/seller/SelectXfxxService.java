package com.test.service.seller;

import com.test.entity.system.Json;
import com.test.mapper.seller.PeiZhiMapper;
import com.test.mapper.seller.XfxxMapper;
import com.test.mapper.seller.XfxxZjbMapper;
import com.test.mapper.initial.XfxxDZDHYHZHMapper;
import com.test.mapper.initial.XfxxFjhMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SelectXfxxService {
    @Resource
    private XfxxZjbMapper xfxxZjbMapper;

    @Resource
    private XfxxMapper xfxxMapper;

    @Resource
    private PeiZhiMapper peiZhiMapper;

    @Resource
    private XfxxFjhMapper xfxxFjhMapper;

    @Resource
    private XfxxDZDHYHZHMapper xfxxDZDHYHZHMapper;

    public List<HashMap<String,String>> getXfxxListByUser(String USERID){
        return xfxxZjbMapper.getXfxxListByUser(USERID);
    }

    public Json addXfxxToUser(HashMap<String,String> hm){
        try {
            if(xfxxZjbMapper.selectByUserIdAndXfxxId(hm) == null){
                hm.put("UUID",UUID.randomUUID().toString());
                xfxxZjbMapper.insertSelective(hm);
                return new Json(true,200,"授权成功");
            }else {
                return new Json(false,0,"已存在,授权失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }

    public Json deleteRelation(String UUID){
        try {
            xfxxZjbMapper.deleteByPrimaryKey(UUID);
            return new Json(true,200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }









    public boolean isNull(Object object){
        if(object!= null && !object.toString().trim().equals("")){
            return true;
        }
        return false;
    }




    public Json addfj(HashMap<String, String> hm) {
        try {
            if (null==xfxxFjhMapper.selectByPrimaryKey(hm)){
                xfxxFjhMapper.insertSelective(hm);
                return new Json(true,200,"新增分机成功");
            }else {
                return new Json(false,0,"已存在相同id的分机");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }

    public Json deletefj(String bumenid) {
        try {
            xfxxFjhMapper.deleteByPrimaryKey(bumenid);
            return new Json(true,200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }



    public Json deleteXFJS(String xfjs) {
        try {
            xfxxDZDHYHZHMapper.deleteByxfjs(xfjs);
            return new Json(true,200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }

    public Json addXFJS(HashMap<String, String> hm) {
        try {
            if (null==xfxxDZDHYHZHMapper.selectByPrimaryKey(hm)){
                xfxxDZDHYHZHMapper.insertSelective(hm);
                return new Json(true,200,"新增销方地址电话银行账号成功");
            }else {
                return new Json(false,0,"已存在同名的销方账户类型");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Json(false,0,"数据库异常");
        }
    }
}

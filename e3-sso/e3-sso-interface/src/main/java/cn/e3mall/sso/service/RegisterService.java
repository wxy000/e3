package cn.e3mall.sso.service;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbUser;

/**
 * @author 王兴毅
 * @date 2018.08.11 12:54
 */
public interface RegisterService {

    E3Result checkData(String param, int type);
    E3Result register(TbUser user);
}

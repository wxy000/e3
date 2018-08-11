package cn.e3mall.sso.service;

import cn.e3mall.common.util.E3Result;

/**
 * @author 王兴毅
 * @date 2018.08.11 15:23
 */
public interface LoginService {

    E3Result userLogin(String username, String password);
}

package cn.e3mall.sso.service;

import cn.e3mall.common.util.E3Result;

/**
 * @author 王兴毅
 * @date 2018.08.11 17:38
 */
public interface TokenService {

    E3Result getUserByToken(String token);
}

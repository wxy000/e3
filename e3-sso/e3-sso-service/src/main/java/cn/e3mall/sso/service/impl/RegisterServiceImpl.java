package cn.e3mall.sso.service.impl;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.11 12:56
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public E3Result checkData(String param, int type) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //1用户名，2手机号
        if (type == 1){
            criteria.andUsernameEqualTo(param);
        }else if (type == 2){
            criteria.andPhoneEqualTo(param);
        }else if (type == 3){
            criteria.andEmailEqualTo(param);
        }else {
            return E3Result.build(400, "数据类型错误！");
        }
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list != null && list.size() > 0){
            return E3Result.ok(false);
        }
        return E3Result.ok(true);
    }

    @Override
    public E3Result register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone())){
            return E3Result.build(400, "用户数据不完整，注册失败！");
        }
        E3Result result = checkData(user.getUsername(), 1);
        if (!(boolean)result.getData()){
            return E3Result.build(400, "此用户名被占用！");
        }
        result = checkData(user.getPhone(), 2);
        if (!(boolean)result.getData()){
            return E3Result.build(400, "此手机号被占用！");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        tbUserMapper.insert(user);
        return E3Result.ok();
    }
}

package cn.e3mall.cart.service;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbItem;

import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.12 14:35
 */
public interface CartService {

    E3Result addCart(long userId, long itemId, int num);
    E3Result mergeCart(long userId, List<TbItem> itemList);
    List<TbItem> getCartList(long userId);
    E3Result updateCartNum(long userId, long itemId, int num);
    E3Result deleteCartItem(long userId, long itemId);
}

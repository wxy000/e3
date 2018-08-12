package cn.e3mall.cart.controller;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.util.CookieUtils;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.12 10:24
 */
@Controller
public class CartController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;

    @Value("${COOKIE_CART_EXPIRT}")
    private Integer COOKIE_CART_EXPIRT;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null){
            cartService.addCart(user.getId(), itemId, num);
            return "cartSuccess";
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        boolean flag = false;
        for (TbItem tbItem : cartList){
            if (tbItem.getId() == itemId.longValue()){
                flag = true;
                tbItem.setNum(tbItem.getNum() + num);
                break;
            }
        }
        if (!flag){
            TbItem tbItem = itemService.getItemById(itemId);
            tbItem.setNum(num);
            String image = tbItem.getImage();
            if (StringUtils.isNotBlank(image)){
                tbItem.setImage(image.split(",")[0]);
            }
            cartList.add(tbItem);
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRT, true);
        return "cartSuccess";
    }

    private List<TbItem> getCartListFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request, "cart", true);
        if (StringUtils.isBlank(json)){
            return new ArrayList<>();
        }
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }

    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request, HttpServletResponse response){
        List<TbItem> cartList = getCartListFromCookie(request);
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null){
            cartService.mergeCart(user.getId(), cartList);
            CookieUtils.deleteCookie(request, response, "cart");
            cartList = cartService.getCartList(user.getId());
        }
        request.setAttribute("cartList", cartList);
        return "cart";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCartNum(@PathVariable long itemId, @PathVariable int num, HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null){
            cartService.updateCartNum(user.getId(), itemId, num);
            return E3Result.ok();
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        for (TbItem tbItem : cartList){
            if (tbItem.getId().longValue() == itemId){
                tbItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRT, true);
        return E3Result.ok();
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable long itemId, HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null){
            cartService.deleteCartItem(user.getId(), itemId);
            return "redirect:/cart/cart.html";
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        for (TbItem tbItem : cartList){
            if (tbItem.getId().longValue() == itemId){
                cartList.remove(tbItem);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRT, true);
        return "redirect:/cart/cart.html";
    }
}

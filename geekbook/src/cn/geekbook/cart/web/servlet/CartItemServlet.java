package cn.geekbook.cart.web.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.geekbook.book.domain.Book;
import cn.geekbook.book.service.BookService;
import cn.geekbook.cart.domain.CartItem;
import cn.geekbook.cart.service.CartItemService;
import cn.geekbook.commons.CommonUtils;
import cn.geekbook.servlet.BaseServlet;
import cn.geekbook.user.domain.User;

public class CartItemServlet extends BaseServlet {
	private CartItemService cartItemService = new CartItemService();
	
	/**
	 * 加载多个CartItem
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadCartItems(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user=(User) req.getSession().getAttribute("sessionUser");
		if(user.getActivationCode()==null){
		req.setAttribute("code", "error");//为了显示X图片
		req.setAttribute("msg", "登录是一种态度，请登录！");
		//req.getSession().setAttribute("lastAccessPage", value)
		req.getRequestDispatcher("/jsps/msg.jsp").forward(req, resp);
		}
		else{
		/*
		 *  获取cartItemIds参数
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		double total = Double.parseDouble(req.getParameter("total"));
		
		/*
		 *  通过service得到List<CartItem>
		 */
		List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
		/*
		 *  保存，然后转发到/cart/showitem.jsp
		 */
		req.setAttribute("cartItemList", cartItemList);
		req.setAttribute("total", total);
		req.setAttribute("cartItemIds", cartItemIds);
		return "f:/jsps/cart/showitem.jsp";
		}
		return null;
	}
	
	public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cartItemId = req.getParameter("cartItemId");
		  String str=req.getParameter("quantity");
		int quantity = Integer.parseInt(str);
		User user=(User) req.getSession().getAttribute("sessionUser");
		
		if(user.getActivationCode()==null){
			List<CartItem> list=(List<CartItem>) req.getSession().getAttribute("cartItemList");
			for(CartItem cartitem:list){
			  if(cartitem.getCartItemId().equals(cartItemId)){
				  cartitem.setQuantity(quantity);
				// 给客户端返回一个json对象
					StringBuilder sb = new StringBuilder("{");
					sb.append("\"quantity\"").append(":").append(cartitem.getQuantity());
					sb.append(",");
					sb.append("\"subtotal\"").append(":").append(cartitem.getSubtotal());
					sb.append("}");
					resp.getWriter().print(sb);
			  }
			}
			
		}else
		{
		CartItem cartItem = cartItemService.updateQuantity(cartItemId, quantity);
		
		// 给客户端返回一个json对象
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
		sb.append(",");
		sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
		sb.append("}");

		resp.getWriter().print(sb);
		}
		return null;
	}
	
	/**
	 * 批量删除功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cartItemIds参数
		 * 2. 调用service方法完成工作
		 * 3. 返回到list.jsp
		 */
		String cartItemIds = req.getParameter("cartItemIds");
		String[] cartItemArray=cartItemIds.split(",");
	   User user =(User) req.getSession().getAttribute("sessionUser");
		
		if(user.getActivationCode()==null){
      List<CartItem> list=(List<CartItem>) req.getSession().getAttribute("cartItemList");
     for(String str:cartItemArray){
      for(CartItem cartitem:list){
    	  if(cartitem.getCartItemId().equals(str)){
    		  list.remove(cartitem);
    	  }
           }
         }
 	     req.getSession().setAttribute("cartItemList", list);
		}
		else{
		cartItemService.batchDelete(cartItemIds);
		}
		return myCart(req, resp);
	}
	
	/**
	 * 添加购物车条目
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到CartItem(bid, quantity)
		 */
		Map map = req.getParameterMap();
		CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
		
		Book book = CommonUtils.toBean(map, Book.class);
		book=(new BookService()).load(book.getBid());
		HttpSession session=req.getSession(false);
		User user = (User)(session.getAttribute("sessionUser"));
		
		if(user.getActivationCode()==null){
			cartItem.setCartItemId(CommonUtils.uuid());
			cartItem.setBook(book);
			cartItem.setUser(user);
			
			if(req.getSession().getAttribute("cartItemList")==null){
				List<CartItem> listF=new CopyOnWriteArrayList<CartItem>();
				listF.add(cartItem);
				req.getSession().setAttribute("cartItemList", listF);
			}else{
			List<CartItem> listA=(List<CartItem>) req.getSession().getAttribute("cartItemList");
			if(listA.size()==0){
				listA.add(cartItem);
			}else{
				int count=0;
				CartItem cartitem1;
				for(CartItem cartitem:listA){ 
					if(cartitem.getBook().getBid().equals(book.getBid())){
						
						count=cartItem.getQuantity()+cartitem.getQuantity();
						cartItem=cartitem;
					}
				}	
				if(listA.contains(cartItem)){
					cartItem.setQuantity(count);
				}else{
					listA.add(cartItem);
				}
		
			req.getSession().setAttribute("cartItemList", listA);
			}
			}
		}
		else{
				
		cartItem.setBook(book);
		cartItem.setUser(user);
		
		/*
		 * 2. 调用service完成添加
		 */
		cartItemService.add(cartItem);
		}
	
		/*
		 * 3. 查询出当前用户的所有条目，转发到list.jsp显示
		 */
		return myCart(req, resp);
	}

	
	/**
	 * 我的购物车
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到uid
		 */
		User user = (User)req.getSession().getAttribute("sessionUser");
		String uid = user.getUid();
		if(user.getActivationCode()==null){
			List<CartItem> cartItemList=(List<CartItem>) req.getSession().getAttribute("cartItemList");
			req.setAttribute("cartItemList", cartItemList);
			return "f:/jsps/cart/list.jsp";
		}else{
		/*
		 * 2. 通过service得到当前用户的所有购物车条目
		 */
		List<CartItem> cartItemLIst = cartItemService.myCart(uid);
		/*
		 * 3. 保存起来，转发到/cart/list.jsp
		 */
		req.setAttribute("cartItemList", cartItemLIst);
		return "f:/jsps/cart/list.jsp";
	}
}
}

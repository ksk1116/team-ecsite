package jp.co.internous.wings.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import jp.co.internous.wings.model.domain.TblCart;
import jp.co.internous.wings.model.domain.dto.CartDto;
import jp.co.internous.wings.model.form.CartForm;
import jp.co.internous.wings.model.mapper.TblCartMapper;
import jp.co.internous.wings.model.session.LoginSession;

@Controller
@RequestMapping("/wings/cart")
public class CartController {

	@Autowired
	private LoginSession loginSession;

	@Autowired
	private TblCartMapper tblCartMapper;

	Gson gson = new Gson();

	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getLoginFlag() ? loginSession.getUserId() : loginSession.getTempUserId();

		List<CartDto> cartDto = tblCartMapper.findCart(userId);

		m.addAttribute("loginSession", loginSession);
		m.addAttribute("cartDto", cartDto);

		return "cart";
	}

	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {

		int userId = loginSession.getLoginFlag() ? loginSession.getUserId() : loginSession.getTempUserId();
		f.setUserId(userId);

		TblCart tblCart = new TblCart(f);
		if (tblCartMapper.findByProductIdAndUserId(userId, f.getProductId()) > 0) {
			tblCartMapper.updateProductCountByProductIdAndUserId(f.getProductCount(), f.getProductId(), userId);
		} else {
			tblCartMapper.insert(tblCart);
		}
		List<CartDto> cartDto = tblCartMapper.findCart(userId);
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("cartDto", cartDto);

		return "cart";
	}

	@RequestMapping("/delete")
	public String deleteCart(CartForm f, Model m) {
		int userId = loginSession.getLoginFlag() ? loginSession.getUserId() : loginSession.getTempUserId();
		String[] deleteid = f.getId();
		int[] delId = Stream.of(deleteid).mapToInt(Integer::parseInt).toArray();

		for (int i = 0; i < delId.length; i++) {
			int id = delId[i];
			tblCartMapper.deleteById(id);
		}

		List<CartDto> cartDto = tblCartMapper.findCart(userId);
		m.addAttribute("loginSession", loginSession);
		m.addAttribute("cartDto", cartDto);

		return "cart";
	}
}

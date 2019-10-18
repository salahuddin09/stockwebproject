
package com.soft.stock.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends StockAdminBaseController {
	@Override
	protected String getHeaderTitle() {
		return "Home";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}

}

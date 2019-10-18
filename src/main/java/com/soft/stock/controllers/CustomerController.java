/**
 * 
 */
package com.soft.stock.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.soft.stock.entities.Customer;
import com.soft.stock.security.SecurityUtil;
import com.soft.stock.services.CustomerService;


@Controller
@Secured(SecurityUtil.MANAGE_CUSTOMERS)
public class CustomerController extends StockAdminBaseController {
	private static final String viewPrefix = "customers/";

	@Autowired
	private CustomerService customerService;

	@Override
	protected String getHeaderTitle() {
		return "Manage Customers";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String listCustomers(Model model) {
		List<Customer> list = customerService.getAllCustomers();
		model.addAttribute("customers", list);
		return viewPrefix + "customers";
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public String viewCustomerForm(@PathVariable Integer id, Model model) {
		Customer customer = customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return viewPrefix + "view_customer";
	}

}

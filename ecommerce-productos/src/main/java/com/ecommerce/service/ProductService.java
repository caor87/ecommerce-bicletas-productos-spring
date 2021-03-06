package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.ProductDTO;
import com.ecommerce.repository.ProductDAO;

@Service
public class ProductService {

	@Autowired
	ProductDAO productDAO;

	public ProductDTO save(ProductDTO product) {
		return productDAO.insert(product);
	}

	public List<ProductDTO> findAll() {
		return productDAO.findAll();
	}

	public ProductDTO getProductById(int id) {
		return productDAO.findById(id).orElse(null);
	}

	public ProductDTO updateProduct(int id, ProductDTO product) {
		return productDAO.save(product);
	}

	public void deleteProduct(int id) {
		productDAO.deleteById(id);
	}

	public String findByname(String productName) {
		List<ProductDTO> products = productDAO.findAll();
		for (ProductDTO productDTO : products) {
			if (productDTO.getName_pro().equalsIgnoreCase(productName)) {
				return "Producto existente";
			}
		}
		return "Producto no existente";
	}

	public List<ProductDTO> modifyAmountProductById(List<ProductDTO> product) {
		int amount = 0;
		int cont = 0;
		for (int i = 1; i <= productDAO.findAll().size(); i++) {

			for (ProductDTO productDTO : product) {
				if (productDTO.getId() == i) {
					if (productDTO.getCantidad() > amount) {
						amount = productDTO.getCantidad();
					}
				}
			}
			for (ProductDTO productDTO : product) {
				if (productDTO.getId() == i) {
					productDTO.setAmount_prod(productDTO.getAmount_prod() - amount);
					updateProduct(i, productDTO);
				}
			}
			amount = 0;
		}

		for (ProductDTO productDTO : product) {
			productDTO.setCantidad(0);
			updateProduct(cont, productDTO);
			cont++;
		}
		cont = 0;
		return product;
	}

}


package pl.edu.vistula.firstrestapispring.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.product.domain.Product;
import pl.edu.vistula.firstrestapispring.product.repository.ProductRepository;
import pl.edu.vistula.firstrestapispring.product.support.ProductMapper;
import pl.edu.vistula.firstrestapispring.product.support.exception.ProductExceptionSupplier;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = productRepository.save(productMapper.toProduct(request));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse find(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));

        productRepository.save(productMapper.toProduct(product, request));
        return productMapper.toProductResponse(product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductExceptionSupplier.productNotFound(id));
        productRepository.deleteById(product.getId());
    }
}

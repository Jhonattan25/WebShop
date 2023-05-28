package co.edu.uniquindio.webshop.service;

import co.edu.uniquindio.webshop.dto.ProductPOST;
import co.edu.uniquindio.webshop.dto.ProductResponse;
import co.edu.uniquindio.webshop.dto.ProductStockDTO;
import co.edu.uniquindio.webshop.dto.ProductStocksQtyDTO;
import co.edu.uniquindio.webshop.model.Product;
import co.edu.uniquindio.webshop.repository.ProductRepository;
import co.edu.uniquindio.webshop.service.excepciones.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final String PRODUCT_STOCK_ERROR = "El Producto no tiene stock";
    private final String PRODUCT_NOT_FOUND = "El Producto no existe";

    public ProductResponse save(ProductPOST product){
        return convert(productRepo.save(convert(product)));
    }

    public void delete(long id) {
        Product product = findById(id);
        product.setIsActive(false);
        productRepo.save(product);
    }

    public Product findById(long id){
        return productRepo.findByIdAndIsActive(id,true)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
    }
    public ProductResponse findByIdProduct(long id){
        return convert(findById(id));
    }
    public List<ProductResponse> findAll(){
        return productRepo.findAllByIsActive(true).stream()
                .map(element -> convert(element))
                .collect(Collectors.toList());
    }
    public ProductResponse update(long id, ProductPOST productPOST){
        findById(id);
        Product newProduct = convert(productPOST);
        newProduct.setId(id);
        return convert(productRepo.save(newProduct));
    }
    public void validateStocks(ProductStocksQtyDTO productStocksQtyDTO){
         List<Product> productList = productStocksQtyDTO.productStockList().stream()
                .map(element -> validateStock(element))
                 .collect(Collectors.toList());
         productRepo.saveAll(productList);
    }
    public Product validateStock(ProductStockDTO productStockDTO) {
        long id = productStockDTO.id();
        Product product = findById(id);
        int qty =productStockDTO.quantity();
        if (product.getStock() < qty){
            throw new ProductNotFoundException(PRODUCT_STOCK_ERROR);
        }
        product.setStock(product.getStock() - qty);
        return product;
    }
    private Product convert(ProductPOST productPOST){
        Product nuevo = Product.builder()
                .name(productPOST.name())
                .description(productPOST.description())
                .price(productPOST.price())
                .stock(productPOST.stock())
                .imageUrl(productPOST.imageUrl())
                .isActive(true)
                .build();
        return nuevo;
    }
    private ProductResponse convert(Product product){
        ProductResponse response = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .build();
        return response;
    }

}

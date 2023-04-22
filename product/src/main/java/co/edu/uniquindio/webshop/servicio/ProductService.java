package co.edu.uniquindio.webshop.servicio;

import co.edu.uniquindio.webshop.dto.ProductPOST;
import co.edu.uniquindio.webshop.dto.ProductResponse;
import co.edu.uniquindio.webshop.dto.ProductStockDTO;
import co.edu.uniquindio.webshop.dto.ProductStocksQtyDTO;
import co.edu.uniquindio.webshop.model.Product;
import co.edu.uniquindio.webshop.repo.ProductRepo;
import co.edu.uniquindio.webshop.servicio.excepciones.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public ProductResponse save(ProductPOST product){
        return convert(productRepo.save(convert(product)));
    }

    public void delete(long id) {
        findById(id);
        productRepo.deleteById(id);
    }
/*    public Boolean isbnsExist(LibroISBNDTO libroISBNDTO){

        List<Libro> libros = libroRepo.findAllById( libroISBNDTO.isbns() );

        if(libros.size()!=libroISBNDTO.isbns().size()){

            List<String> isbnsExistentes = libros.stream().map(Libro::getIsbn).toList();

            String noEncontrados = libroISBNDTO.isbns()
                    .stream()
                    .filter(id -> !isbnsExistentes.contains(id))
                    .map(Object::toString)
                    .collect(Collectors.joining(","));

            throw new LibroNoEncontradoException("Los libros con los isbn "+noEncontrados+" no existen");

        }

        return true;
    }*/

    public Product findById(long id){
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("El Producto no existe"));
    }
    public ProductResponse findByIdProduct(long id){
        return convert(findById(id));
    }
    public List<ProductResponse> findAll(){
        return productRepo.findAll().stream()
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
            throw new ProductNotFoundException("El Producto no tiene stock");
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

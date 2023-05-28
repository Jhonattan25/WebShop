package co.edu.uniquindio.webshop.controller;

import co.edu.uniquindio.webshop.dto.ProductPOST;
import co.edu.uniquindio.webshop.dto.ProductResponse;
import co.edu.uniquindio.webshop.dto.ProductStocksQtyDTO;
import co.edu.uniquindio.webshop.dto.Response;
import co.edu.uniquindio.webshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response<ProductResponse>> save(@RequestBody ProductPOST productPOST){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Response<>("Producto creado correctamente", productService.save(productPOST)) );
    }

    @GetMapping("/all")
    public ResponseEntity<Response<List<ProductResponse>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>("Productos consultados correctamente", productService.findAll()) );
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<Response<ProductResponse>> update(@PathVariable long idProduct,
                                                     @RequestBody ProductPOST productPOST) {
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>(
                "Producto actualizado correctamente", productService.update(idProduct, productPOST)) );
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Response<ProductResponse>> findById(@PathVariable long idProduct) {
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>(
                "Producto consultado correctamente", productService.findByIdProduct(idProduct)));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Response<String>> delete(@PathVariable long idProduct) {
        productService.delete(idProduct);
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>(
                "Producto eliminado correctamente") );
    }

    @PutMapping("/stock")
    public ResponseEntity<Response<String>> stock(@RequestBody ProductStocksQtyDTO productStocksQtyDTO) {
        productService.validateStocks(productStocksQtyDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>(
                "Productos existentes y actualizados correctamente"));
    }
}

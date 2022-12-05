package com.store.sportswear.api;

import com.store.sportswear.dto.createDto.ProductCreateDto;
import com.store.sportswear.dto.viewDto.ProductViewDto;
import com.store.sportswear.entity.Product;
import com.store.sportswear.request.CampaignCreateRequest;
import com.store.sportswear.request.ConfirmCartRequest;
import com.store.sportswear.request.PriceIncreaseRequest;
import com.store.sportswear.request.ProductDetailsUpdateRequest;
import com.store.sportswear.service.product.ProductService;
import com.store.sportswear.service.product.UpdateProductPriceService;
import com.store.sportswear.shared.ECommerceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client/products/")
@RequiredArgsConstructor
@CrossOrigin
public class ClientController {
    private final ProductService productService;

    private final UpdateProductPriceService updateProductPriceService;


    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        final List<Product> products = productService.getAll();
        if(products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductName/{productName}")
    public ResponseEntity<?> getByproductName(@PathVariable String productName) {
        String productsName = "";
        List<Product> products = this.productService.getByproductName(productName);
        for(Product product: products) {
            productsName = product.getProductName();
        }
        if(!productName.equals(productsName)) {
            return ResponseEntity.ok(ECommerceMessage.NOT_FOUND_THIS_NAME);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductBrand/{productBrand}")
    public List<Product> getByproductBrand(@PathVariable String productBrand) {
        return this.productService.getByproductBrand(productBrand);
    }

    @GetMapping("slice")
    public ResponseEntity<List<Product>> slice(Pageable pageable) {
        final List<Product> products = this.productService.slice(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("getDto")
    public ResponseEntity<?> getDto() {
        List<ProductViewDto> products = this.productService.getDto();
        if(products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(products);
        }
    }

    @PostMapping("addToCart/{id}")
    public ResponseEntity<?> addToCart(@RequestBody @PathVariable int id) {
        productService.addToCart(id);
        return ResponseEntity.ok(ECommerceMessage.ADD_TO_CART);
    }

    @GetMapping("getCart")
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(productService.getCart());
    }

    @DeleteMapping("removeFromCart/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable int id) {
        productService.removeFromCart(id);
        return ResponseEntity.ok(ECommerceMessage.REMOVE_FROM_CART);
    }

    @GetMapping("searchByProduct/{productName}")
    public ResponseEntity<?> searchByProduct(@PathVariable String productName) {
        Map<Integer, Object> result = productService.searchByProduct(productName);
        return ResponseEntity.ok(result);
    }

    @PostMapping("confirmCart")
    public ResponseEntity<?> confirmCart(@RequestBody ConfirmCartRequest confirmCartRequest) {
        productService.confirmCart(confirmCartRequest);
        return ResponseEntity.ok(ECommerceMessage.ITEMS_IN_THE_CART_HAVE_BEEN_PURCHASED);
    }

    @GetMapping("getAllConfirmedCart")
    public ResponseEntity<?> getAllConfirmedOrder() {
        return ResponseEntity.ok(productService.getAllConfirmedOrder());
    }

    @GetMapping("getConfirmedOrderById/{id}")
    public ResponseEntity<?> getConfirmedOrderById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getConfirmedOrderById(id));
    }

    @GetMapping("getConfirmedOrderByOrderNumber/{orderNumber}")
    public ResponseEntity<?> getConfirmedOrderByOrderNumber(@PathVariable Long orderNumber) {
        return ResponseEntity.ok(productService.getConfirmedOrderByOrderNumber(orderNumber));
    }


    @PutMapping("priceIncrease")
    public ResponseEntity<?> priceIncrease(@RequestBody PriceIncreaseRequest priceIncreaseRequest) {
        updateProductPriceService.priceIncrease(priceIncreaseRequest);
        return ResponseEntity.ok("success");
    }


    @PutMapping("addFavorite/{productId}")
    public ResponseEntity<?> addFavorite(@PathVariable("productId") int productId) {
        productService.addFavorite(productId);
        return ResponseEntity.ok(ECommerceMessage.ADDED_TO_FAVORITES);
    }

    @GetMapping("getNumberOfFavorite/{productId}")
    public ResponseEntity<?> getNumberOfFavorite(@PathVariable("productId") int productId) {
        return ResponseEntity.ok(productService.getNumberOfFavorite(productId));
    }

    @PutMapping("removeFromFavorite/{productId}")
    public ResponseEntity<?> removeFromFavorite(@PathVariable("productId") int productId) {
        productService.removeFromFavorites(productId);
        return ResponseEntity.ok(ECommerceMessage.REMOVE_FROM_FAVORITES);
    }
}

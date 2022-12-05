package com.store.sportswear.service.product;

import com.store.sportswear.entity.Product;
import com.store.sportswear.entity.EUser;
import com.store.sportswear.repository.ProductRepository;
import com.store.sportswear.request.CampaignCreateRequest;
import com.store.sportswear.request.PriceIncreaseRequest;
import com.store.sportswear.service.SendEmailService;
import com.store.sportswear.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProductPriceServiceImpl implements UpdateProductPriceService {

    private final ProductRepository productRepository;

    private final SendEmailService emailService;

    private final UserService userService;

    @Override
    public void createCampaign(CampaignCreateRequest campaignCreateRequest) {
        Optional<Product> product = productRepository.findById(campaignCreateRequest.getProductId());
        List<EUser> EUsers = userService.getAll();

        if (product.isPresent()) {
            product.get().setProductPrice( product.get().getProductPrice() - campaignCreateRequest.getDiscountAmount());
            productRepository.save(product.get());

            for (EUser EUser : EUsers) {
                if (EUser.isNotificationPermission()) {
                    emailService.sendEmails(EUser.getEMail(), "Big Discount", product.get().getProductBrand() +" "+ product.get().getProductName()
                            + " Big discount on product!");
                }
            }
        }
    }

    @Override
    public void priceIncrease(PriceIncreaseRequest priceIncreaseRequest) {
        Optional<Product> product = productRepository.findById(priceIncreaseRequest.getProductId());

        if (product.isPresent()) {
            product.get().setProductPrice(product.get().getProductPrice() + priceIncreaseRequest.getAmount());
            productRepository.save(product.get());
        }
    }
}

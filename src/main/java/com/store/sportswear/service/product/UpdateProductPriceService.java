package com.store.sportswear.service.product;

import com.store.sportswear.request.CampaignCreateRequest;
import com.store.sportswear.request.PriceIncreaseRequest;

public interface UpdateProductPriceService {
    void createCampaign(CampaignCreateRequest campaignCreateRequest);
    void priceIncrease(PriceIncreaseRequest priceIncreaseRequest);
}

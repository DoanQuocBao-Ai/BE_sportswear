package com.store.sportswear.service;

import com.store.sportswear.entity.EUser;
import com.store.sportswear.entity.PromoCode;
import com.store.sportswear.exception.NotFoundException;
import com.store.sportswear.repository.PromoCodeRepository;
import com.store.sportswear.request.PromoCodeCreateRequest;
import com.store.sportswear.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService{

    private final UserService userService;

    private final PromoCodeRepository promoCodeRepository;

    private final CreateCodeService createCodeService;

    private final SendEmailService sendEmailService;

    @Override
    public String createPromoCode(PromoCodeCreateRequest promoCodeRequest) {
        Optional<EUser> user = Optional.ofNullable(userService.getById(promoCodeRequest.getUserId()));

        if (user.isPresent()) {
            PromoCode promoCode = new PromoCode();
            promoCode.setCode(createCodeService.createCode() + promoCodeRequest.getAmount());
            promoCode.setCreateDate(new Date());
            promoCode.setAmount(promoCodeRequest.getAmount());
            promoCode.setExpirationDate(new Date(promoCodeRequest.getYear(), promoCodeRequest.getMonth(), promoCodeRequest.getDate()));
            promoCode.setEUser(user.get());

            promoCodeRepository.save(promoCode);
            userService.add(user.get());

            sendEmailService.sendEmails(user.get().getEMail(), "You have promo code\n Expiration Date: "+ promoCode.getExpirationDate() + "\nCode: "
                    + promoCode.getCode(), "You Have Promo Code");
            return promoCode.getCode();
        }

        return "failed";
    }

    @Override
    public List<PromoCode> getAll() {
        return promoCodeRepository.findAll();
    }

    @Override
    public PromoCode getById(Long id) {
        return promoCodeRepository.findById(id).orElseThrow(() -> new NotFoundException("promo code couldn't be found by following id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        promoCodeRepository.deleteById(id);
    }
}

package com.hs.yourfit.domain.user.service.Impl;

import com.hs.yourfit.core.error.exception.UserDefineException;
import com.hs.yourfit.core.security.jwt.JwtCore;
import com.hs.yourfit.domain.user.entity.User;
import com.hs.yourfit.domain.user.entity.UserAddress;
import com.hs.yourfit.domain.user.entity.UserBusinessInfo;
import com.hs.yourfit.domain.user.entity.enums.UserRole;
import com.hs.yourfit.domain.user.exception.AddressNotFoundException;
import com.hs.yourfit.domain.user.exception.BusinessInfoNotFoundException;
import com.hs.yourfit.domain.user.repository.UserAddressRepository;
import com.hs.yourfit.domain.user.repository.UserBusinessInfoRepository;
import com.hs.yourfit.domain.user.service.UserDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDeleteServiceImpl implements UserDeleteService {

    private final JwtCore jwtCore;
    private final UserAddressRepository userAddressRepository;
    private final UserBusinessInfoRepository userBusinessInfoRepository;

    @Override
    public String deleteUserAddr(Long userAddressId, String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        if(!userAddressRepository.existsByUserAndId(user,userAddressId)){
            throw new UserDefineException("해당 회원과 주소지가 일치하지 않는 요청입니다.");
        }
        UserAddress userAddress = userAddressRepository.findById(userAddressId).orElseThrow(AddressNotFoundException::new);

        if(userAddress.isRepresent()){
            throw new UserDefineException("대표 주소지는 삭제 할 수 없습니다.");
        }else{
           userAddress.delete();
        }

        return "success";
    }

    @Override
    public String deleteCustomerUser(String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);

        if(user.getUserRole() == UserRole.SELLER){
            throw new UserDefineException("해당 회원은 일반 사용자가 아닙니다.");
        }

        List<UserAddress> userAddressList = userAddressRepository.findByUser(user);

        userAddressList.forEach(userAddress -> userAddress.delete());

        user.delete();

        return "success";
    }

    @Override
    public String deleteSellerUser(String accessToken) {
        User user = jwtCore.findUserByToken(accessToken);
        UserBusinessInfo userBusinessInfo = userBusinessInfoRepository.findByUser(user)
                .orElseThrow(BusinessInfoNotFoundException::new);
        List<UserAddress> userAddressList = userAddressRepository.findByUser(user);

        if(user.getUserRole() != UserRole.SELLER){
            throw new UserDefineException("해당 회원은 법인 사용자가 아닙니다.");
        }

        userAddressList.forEach(userAddress -> userAddress.delete());

        userBusinessInfo.delete();
        user.delete();

        return "success";
    }
}

package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.UserDetails;
import org.acme.repository.ErgoUserRepository;

import java.util.List;

@ApplicationScoped
public class ErgoUserServiceImpl implements ErgoUserService{

    @Inject
    private ErgoUserRepository ergoUserRepository;

    @Override
    public List<UserDetails> getAllErgoUsers() {
        return ergoUserRepository.listAll();
    }

    /**
     * @param gpnID
     * @return
     */
    @Override
    public UserDetails getErgoUserById(Long gpnID) {
        return ergoUserRepository.findById(gpnID);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public UserDetails createErgoUser(UserDetails user) {
         ergoUserRepository.persist(user);
         return user;
    }

    /**
     * @param gpnID
     * @param user
     * @return
     */
    @Override
    public UserDetails updateErgoUser(Long gpnID, UserDetails user) {
        UserDetails userDetails = ergoUserRepository.findById(gpnID);
        if (userDetails != null) {
            userDetails.setErgoUserName(user.getErgoUserName());
            userDetails.setErgoUserPassword(user.getErgoUserPassword());
            userDetails.setErgoUserEmail(user.getErgoUserEmail());
            userDetails.setErgoUserDesignation(user.getErgoUserDesignation());
            userDetails.setIsCustomized(user.getIsCustomized());
            ergoUserRepository.persist(userDetails);
            return userDetails;
        }
        return null;
    }

    /**
     * @param gpnID
     */
    @Override
    public Boolean deleteErgoUserById(Long gpnID) {
        return ergoUserRepository.deleteById(gpnID);
    }
}

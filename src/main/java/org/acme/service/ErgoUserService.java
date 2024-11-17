package org.acme.service;

import org.acme.model.UserDetails;
import java.util.List;

public interface ErgoUserService {

    List<UserDetails> getAllErgoUsers();

    UserDetails getErgoUserById(final Long gpnID);

    UserDetails createErgoUser(final UserDetails user);

    UserDetails updateErgoUser(final Long gpnID, final UserDetails user);

    Boolean deleteErgoUserById(final Long gpnID);
}

package com.jbdl.wallet_service.Repository;

import com.jbdl.wallet_service.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletOperationRepository extends JpaRepository<Wallet,Long> {
    Wallet findByUserId(Long userId);
}

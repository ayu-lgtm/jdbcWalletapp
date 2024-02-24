package com.jbdl.Repository;

import com.jbdl.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOperationRepository extends JpaRepository<User,Long> {
}

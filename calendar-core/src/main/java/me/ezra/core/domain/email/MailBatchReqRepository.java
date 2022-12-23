package me.ezra.core.domain.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailBatchReqRepository extends JpaRepository<SendMailBatchReq, Long> { }

package me.ezra.batch.job.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DebuggingItemReadListener<SendMailBatchReq> implements ItemReadListener<SendMailBatchReq> {

    @Override
    public void beforeRead() {
        log.info("ItemReadListener - beforeRead");
    }

    @Override
    public void afterRead(SendMailBatchReq item) {
        log.info("Read Item: {}", item);
    }

    @Override
    public void onReadError(Exception ex) {

    }
}

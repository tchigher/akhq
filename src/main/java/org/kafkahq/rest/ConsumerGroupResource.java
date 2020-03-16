package org.kafkahq.rest;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.extern.slf4j.Slf4j;
import org.kafkahq.service.ConsumerGroupService;
import org.kafkahq.service.dto.ConsumerGroupd.ConsumerGroupListDTO;
import org.kafkahq.service.dto.ConsumerGroupd.ConsumerGroupMemberDTO;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller("${kafkahq.server.base-path:}/api")
public class ConsumerGroupResource {
    private ConsumerGroupService consumerGroupService;

    @Inject
    public ConsumerGroupResource(ConsumerGroupService consumerGroupService) {
        this.consumerGroupService =consumerGroupService;
    }

    @Get("/group")
    public ConsumerGroupListDTO fetchAllConsumerGroup(String clusterId, String view, @Nullable String search, Optional<Integer> pageNumber) throws ExecutionException, InterruptedException {
        log.debug("Fetch all Consumer Groups");
        return consumerGroupService.getConsumerGroup(clusterId, view, Optional.ofNullable(search), pageNumber);
    }


    @Get("/group/members")
    public ArrayList<ConsumerGroupMemberDTO> fetchAllConsumerGroupMembers(String clusterId , String groupId ,@Nullable String search) throws ExecutionException, InterruptedException {
        log.debug("Fetch all Consumer Groups");
        return consumerGroupService.getConsumerGroupMembers(clusterId, groupId, Optional.ofNullable(search));
    }



}

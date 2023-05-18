package com.depromeet.reunion.server.domain.group.service;

public interface GroupApprovalService {

    String approveGroup(long memberId);

    String rejectGroup(long memberId);
}

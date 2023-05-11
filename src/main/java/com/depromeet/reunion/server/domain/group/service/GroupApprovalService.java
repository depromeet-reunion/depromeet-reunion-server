package com.depromeet.reunion.server.domain.group.service;

public interface GroupApprovalService {

    void approveGroup(long memberId);

    void rejectGroup(long memberId);
}

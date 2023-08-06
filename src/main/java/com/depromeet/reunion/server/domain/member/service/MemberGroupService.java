package com.depromeet.reunion.server.domain.member.service;

import com.depromeet.reunion.server.domain.member.repository.MemberGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberGroupService {

    private final MemberGroupRepository memberGroupRepository;

    @Transactional(readOnly = true)
    public List<String> getAllPart(){
        return memberGroupRepository.findAllPart();
    }

    @Transactional(readOnly = true)
    public List<Long> getAllUnit(){
        return memberGroupRepository.findAllUnit();
    }

}

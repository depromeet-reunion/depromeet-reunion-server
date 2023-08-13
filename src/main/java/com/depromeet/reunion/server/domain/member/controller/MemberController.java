package com.depromeet.reunion.server.domain.member.controller;

import com.depromeet.reunion.server.domain.member.dto.MemberResponseDto;
import com.depromeet.reunion.server.domain.member.model.entity.Member;
import com.depromeet.reunion.server.global.annotation.ReqMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member Controller", description = "회원 API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    @Operation(summary = "내 정보 조회", description = "이름, 기수, 파트 등의 내 정보를 반환합니다. 인증이 필요한 요청입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MemberResponseDto.class)))),
    })
    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo(@ReqMember Member member) {
        return ResponseEntity.ok(MemberResponseDto.fromEntity(member));
    }
}

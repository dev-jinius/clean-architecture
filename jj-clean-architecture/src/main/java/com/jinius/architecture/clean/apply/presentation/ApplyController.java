package com.jinius.architecture.clean.apply.presentation;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.application.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 특강 신청 API
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/hhplus/apply")
public class ApplyController {

    private static final Logger log = LoggerFactory.getLogger(ApplyController.class);

    private final ApplyService applyService;    // 특강 신청 서비스

    /**
     * 특강 신청
     */
    @PostMapping("/special")
    public ResponseEntity<ApplyResponse> apply(@RequestBody ApplyRequest request) {
        ApplyResponse applyResponse = null;

        return ResponseEntity.ok().body(applyResponse);
    }

    /**
     * 특강 신청 완료 조회
     */
    @GetMapping("/special/success/{userId}")
    public ResponseEntity<ApplyResponse> success(@RequestParam(value = "userId") ApplyRequest request) {
        ApplyResponse applyResponse = null;

        return ResponseEntity.ok().body(applyResponse);
    }

}

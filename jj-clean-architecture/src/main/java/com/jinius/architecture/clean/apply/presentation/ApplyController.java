package com.jinius.architecture.clean.apply.presentation;

import com.jinius.architecture.clean.apply.application.dto.ApplyRequest;
import com.jinius.architecture.clean.apply.application.dto.ApplyResponse;
import com.jinius.architecture.clean.apply.application.dto.LectureResponse;
import com.jinius.architecture.clean.apply.application.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/")
    public ResponseEntity<ApplyResponse> apply(@RequestBody ApplyRequest request) {
        ApplyResponse applyResponse = applyService.apply(request);
        return ResponseEntity.ok().body(applyResponse);
    }

    /**
     * 특강 신청 완료 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<String> applyResult(@PathVariable(value = "userId") ApplyRequest request) {
        Boolean success = applyService.getSuccess(request);
        String result = success ? "특강 신청에 성공했습니다." : "특강 신청에 실패했습니다.";
        return ResponseEntity.ok().body(result);
    }

    /**
     * 특강 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<LectureResponse>> lectureList() {
        List<LectureResponse> list = applyService.getLectureList();
        return ResponseEntity.ok().body(list);
    }
}

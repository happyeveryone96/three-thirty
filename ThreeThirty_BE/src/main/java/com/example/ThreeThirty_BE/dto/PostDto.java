package com.example.ThreeThirty_BE.dto;

import com.nimbusds.openid.connect.sdk.assurance.evidences.attachment.Attachment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String post_type_title;
    private String company_title;
    private String post_content;
    private List<String> hashtag_content;
    private List<Attachment> attach_file;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attachment {
        private String attach_file_url;
        private String attach_file_type;
    }
}

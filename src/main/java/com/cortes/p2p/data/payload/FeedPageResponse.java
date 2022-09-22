package com.cortes.p2p.data.payload;

import com.cortes.p2p.data.DTO.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedPageResponse {
    private int feedsCount;
    private int totalFeedPages;
    private int feedPageNumber;
    private boolean isLastFeedPage;
    private List<PostDTO> feeds;
}

package com.chillerz.bloggerspark.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FollowUserRequest {

    @NonNull
    String followerId;
    @NonNull
    String followingId;
}

package com.anikinkirill.playground.data.posts

import com.anikinkirill.playground.domain.posts.UserPostDomain

class PostMapper {

    fun map(posts: List<PostDto>): List<UserPostDomain> {
        return posts.map { post -> mapPost(dto = post) }
    }

    private fun mapPost(dto: PostDto): UserPostDomain {
        return UserPostDomain(
            userId = dto.userId,
            postId = dto.id,
            title = dto.title,
            body = dto.body,
        )
    }
}
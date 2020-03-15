package com.example.kotlin.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * @description
 * Because spring data doesn't support Page as response for annotated queries on a MongoRepository interface,
 * we have to do something to know if they are more elements in next pages.
 * So, having this into account, this custom pageable request one more element for the page to know if they are more elements than the requested.
 * this approach doesn't add any performance penalization.
 * We can use this approach either with @Query or @Aggregations
 * @see Page<T> , the latest element will be removed there.
 * @see <a href="https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries.aggregation"/> mongodb repositories aggregations </a>
 */
class CustomPageRequest(page: Int, size: Int, sort: Sort) : PageRequest(page, size + 1, sort) {

    companion object {
        fun of(page: Int, size: Int, order: OrderDirection, orderBy: OrderField): Pageable {

            val sort = Sort.by(
                Sort.Order(Sort.Direction.fromString(order.name), orderBy.value)
            )

            return CustomPageRequest(page, size, sort)
        }
    }

    /**
     * Custom implementation for offset trick
     */
    override fun getOffset(): Long {
        return (super.getPageNumber() * (super.getPageSize() - 1)).toLong()
    }

}

/**
 * This page interprets the list answered as part of a paginated @Query or @Aggregation.
 * It let's us know if they are more elements to search for and it removes the last one to retrieve exactly the expected result
 */
class Page<T>(
    var content: List<T>,
    private val pageable: Pageable
) {

    val hasMore = content.size > pageable.pageSize - 1

    init {
        content = if (hasMore) content.dropLast(1) else content
    }

    override fun toString(): String {
        return "\ncontent: ${content.toString()} \nhastMore: $hasMore"
    }

}

enum class OrderDirection {
    ASC,
    DESC
}

enum class OrderField(val value: String) {
    CREATION_DATE("creation_date"),
    NAME("name.firstName")
}

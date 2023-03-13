package com.example.test_task_compose_2.domain.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.test_task_compose_2.data.retrofit.model.ErrorResponse
import com.example.test_task_compose_2.data.retrofit.repository.GitUserRepository
import com.example.test_task_compose_2.ui.model.ListsUserUi
import com.example.test_task_compose_2.util.Constants

class UsersPagingSource(
    private val gitUserRepository: GitUserRepository
): PagingSource<Int, ListsUserUi>() {
    override fun getRefreshKey(state: PagingState<Int, ListsUserUi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(Constants.USER_PER_PAGE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(Constants.USER_PER_PAGE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListsUserUi> {
        return try {
            val since = params.key ?: 0
            val users = gitUserRepository.getUsers(
                since = since,
                perPage = Constants.USER_PER_PAGE
            )

            LoadResult.Page(
                data = users,
                prevKey = null,
                nextKey = users.lastOrNull()?.id ?: 0
            )
        } catch (errorResponse: ErrorResponse) {
            LoadResult.Error(errorResponse)
        }
    }
}